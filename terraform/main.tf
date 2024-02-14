# Create a resource group
resource "azurerm_resource_group" "TrackingFlightFrequencies" {
  name     = var.rg_name
  location = var.region
}

resource "random_id" "random_name_id" {
  keepers = {
    random_id = var.random_id
  }

  byte_length = 2
}

module "budget" {
  source  = "./modules/azure/budget"
  rg_name = var.rg_name
}

module "keyvault_module" {
  source = "./modules/azure/keyvault"

  # Pass variables to module
  rg_name     = azurerm_resource_group.TrackingFlightFrequencies.name
  region      = azurerm_resource_group.TrackingFlightFrequencies.location
  prefix      = var.prefix
  secrets     = var.keyvault_secrets
  random_id   = random_id.random_name_id.hex
  stgacc_name = module.storage_module.stgacc_name
  sas_token   = module.storage_module.sas_token

  EntraIDUsername = var.EntraIDUsername

  depends_on = [module.storage_module]
}

module "vnet_module" {
  source = "./modules/azure/vnet"

  # Pass variables to module
  rg_name = azurerm_resource_group.TrackingFlightFrequencies.name
  region  = azurerm_resource_group.TrackingFlightFrequencies.location

}

module "storage_module" {
  source                     = "./modules/azure/storage"
  rg_name                    = azurerm_resource_group.TrackingFlightFrequencies.name
  region                     = azurerm_resource_group.TrackingFlightFrequencies.location
  prefix                     = var.prefix
  subnet_ids                 = [module.vnet_module.private_subnet_id, module.vnet_module.public_subnet_id]
  ip_rules                   = var.ip_rules
  source_airport_definitions = var.source_airport_definitions
  random_id                  = random_id.random_name_id.hex
  storage_sas_start          = "2024-02-07T14:00:01Z"
  storage_sas_end            = "2024-02-20T15:14:51Z"

}


# Create a databricks workspace
resource "azurerm_databricks_workspace" "databricksworkspace" {
  name                = "${var.prefix}_dbw${random_id.random_name_id.hex}"
  resource_group_name = var.rg_name
  location            = var.region
  sku                 = "premium"

  custom_parameters {
    virtual_network_id                                   = module.vnet_module.vnet_id
    private_subnet_name                                  = module.vnet_module.private_subnet_name
    private_subnet_network_security_group_association_id = module.vnet_module.private_subnet_association_id
    public_subnet_name                                   = module.vnet_module.public_subnet_name
    public_subnet_network_security_group_association_id  = module.vnet_module.public_subnet_association_id
    no_public_ip                                         = true
  }

  # depends_on = [module.storage_module]
}

resource "databricks_dbfs_file" "jar" {
  source = "./jar/TrackingFlightFrequencies.jar"
  path   = "/flight_frequencies_pipeline.jar"
}

resource "databricks_dbfs_file" "sample_data" {
  source = "./data/sampleData.json"
  path   = "/sampleData.json"
}

module "db_access_control" {
  source = "./modules/databricks/access_control"

  rg_name     = azurerm_resource_group.TrackingFlightFrequencies.name
  region      = azurerm_resource_group.TrackingFlightFrequencies.location
  stgacc_id   = module.storage_module.storage_account_id
  keyvault_id = module.keyvault_module.keyvault_id
}

module "db_repo" {
  source = "./modules/databricks/repo"

  keyvault_id = module.keyvault_module.keyvault_id
}

module "db_queries" {
  source = "./modules/databricks/queries"

  data_source_id = module.db_compute.data_source_id
}

module "db_jobs" {
  source                    = "./modules/databricks/jobs"
  sql_warehouse_id          = module.db_compute.sql_warehouse_id
  create_arr_table_query_id = module.db_queries.create_arr_table_query_id
  create_dep_table_query_id = module.db_queries.create_dep_table_query_id
  instance_pool_id          = module.db_compute.instance_pool_id

  depends_on = [azurerm_databricks_workspace.databricksworkspace, module.db_access_control, module.keyvault_module]

}

module "db_compute" {
  source = "./modules/databricks/compute"

  depends_on = [azurerm_databricks_workspace.databricksworkspace, module.db_access_control, module.keyvault_module]
}

module "db_secret_scope" {
  source = "./modules/databricks/secret_scope"

  keyvault_id = module.keyvault_module.keyvault_id
  vault_uri   = module.keyvault_module.keyvault_uri

}

module "db_dashboard" {
  source                         = "./modules/databricks/dashboard"
  visualization_arr_by_dep_id    = module.db_queries.visualization_arr_by_dep_id
  visualization_dep_by_arr_id    = module.db_queries.visualization_dep_by_arr_id
  visualization_dep_over_time_id = module.db_queries.visualization_dep_over_time_id
  visualization_arr_over_time_id = module.db_queries.visualization_arr_over_time_id
}

