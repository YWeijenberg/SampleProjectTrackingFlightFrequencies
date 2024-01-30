# Create a resource group
resource "azurerm_resource_group" "TrackingFlightFrequencies" {
  name     = var.rg_name
  location = var.region
}

module "keyvault_module" {
  source = "./modules/azure/keyvault"

  # Pass variables to module
  rg_name                          = azurerm_resource_group.TrackingFlightFrequencies.name
  region                           = azurerm_resource_group.TrackingFlightFrequencies.location
  prefix                           = var.prefix
  secrets                          = var.keyvault_secrets
  storage_sas_token = module.storage_module.storage_sas_token
  stgacc_name = module.storage_module.storage_account_name
  container_name = module.storage_module.storage_container_name

  EntraIDUsername = var.EntraIDUsername

}

module "vnet_module" {
  source = "./modules/azure/vnet"

  # Pass variables to module
  rg_name = azurerm_resource_group.TrackingFlightFrequencies.name
  region  = azurerm_resource_group.TrackingFlightFrequencies.location

}

module "storage_module" {
  source  = "./modules/azure/storage"
  rg_name = azurerm_resource_group.TrackingFlightFrequencies.name
  region  = azurerm_resource_group.TrackingFlightFrequencies.location
  prefix  = var.prefix
  subnet_ids = [module.vnet_module.private_subnet_id,module.vnet_module.public_subnet_id]
  ip_rules = var.ip_rules
  storage_sas_start = "2024-01-25T12:00:00Z"
  storage_sas_end = "2024-03-01T14:00:00Z"
}


# Create a databricks workspace
resource "azurerm_databricks_workspace" "databricksworkspace" {
  name                = "TrackingFlightFrequencies_dbw"
  resource_group_name = var.rg_name
  location            = var.region
  sku                 = "trial"

  custom_parameters {
    virtual_network_id = module.vnet_module.vnet_id
    private_subnet_name = module.vnet_module.private_subnet_name
    private_subnet_network_security_group_association_id = module.vnet_module.private_subnet_association_id
    public_subnet_name = module.vnet_module.public_subnet_name
    public_subnet_network_security_group_association_id = module.vnet_module.public_subnet_association_id
    no_public_ip = true
  }
}

module "db_access_control" {
  source = "./modules/databricks/access_control"

  rg_name = azurerm_resource_group.TrackingFlightFrequencies.name
  region = azurerm_resource_group.TrackingFlightFrequencies.location
  stgacc_id = module.storage_module.storage_account_id
  keyvault_id = module.keyvault_module.keyvault_id

}

# module "db_repo" {
#   source = "./modules/databricks/repo"

#   keyvault_id = module.keyvault_module.keyvault_id
# }

module "db_compute" {
  source = "./modules/databricks/compute"

  depends_on = [azurerm_databricks_workspace.databricksworkspace, module.db_access_control]
}

module "db_secret_scope" {
  source = "./modules/databricks/secret_scope"

  keyvault_id = module.keyvault_module.keyvault_id
  vault_uri   = module.keyvault_module.keyvault_uri
}
