# Create a resource group
resource "azurerm_resource_group" "TrackingFlightFrequencies" {
  name     = var.rg_name
  location = var.region
}

module "keyvault_module" {
  source = "./modules/azure/keyvault"

  # Pass variables to module
  rg_name = azurerm_resource_group.TrackingFlightFrequencies.name
  region  = azurerm_resource_group.TrackingFlightFrequencies.location
  prefix  = var.prefix
  secrets = var.keyvault_secrets

  EntraIDUsername = var.EntraIDUsername

  depends_on = [azurerm_resource_group.TrackingFlightFrequencies]
}

module "storage_module" {
  source  = "./modules/azure/storage"
  rg_name = azurerm_resource_group.TrackingFlightFrequencies.name
  region  = azurerm_resource_group.TrackingFlightFrequencies.location
  prefix  = var.prefix
}

# Create a databricks module from the databricks folder
module "databricks_module" {
  source = "./modules/databricks"

  # Pass variables to module
  rg_name                          = azurerm_resource_group.TrackingFlightFrequencies.name
  region                           = azurerm_resource_group.TrackingFlightFrequencies.location
  keyvault_id                      = module.keyvault_module.keyvault_id
  vault_uri                        = module.keyvault_module.keyvault_uri
  databricks_identity_principal_id = module.databricks_module.databricks_identity_principal_id

  depends_on = [module.keyvault_module]
}
