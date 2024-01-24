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
  stgacc_key                       = module.storage_module.sorage_account_key

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

# module "db_access_control" {
#   source = "./modules/databricks/access_control"

#   rg_name = azurerm_resource_group.TrackingFlightFrequencies.name
#   region = azurerm_resource_group.TrackingFlightFrequencies.location
#   stgacc_id = module.storage_module.storage_account_id
#   keyvault_id = module.keyvault_module.keyvault_id

# }

# module "db_compute" {
#   source = "./modules/databricks/compute"

#   depends_on = [azurerm_databricks_workspace.databricksworkspace, module.db_access_control]
# }
# module "db_secret_scope" {
#   source = "./modules/databricks/secret_scope"

#   keyvault_id = module.keyvault_module.keyvault_id
#   vault_uri   = module.keyvault_module.keyvault_uri
# }
