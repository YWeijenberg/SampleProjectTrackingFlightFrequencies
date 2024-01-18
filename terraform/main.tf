# Create a resource group
resource "azurerm_resource_group" "TrackingFlightFrequencies" {
  name     = var.rg_name
  location = var.region
}

resource "azurerm_user_assigned_identity" "databricks_identity" {
  resource_group_name = azurerm_resource_group.TrackingFlightFrequencies.name
  location            = azurerm_resource_group.TrackingFlightFrequencies.location

  name = "databricks_identity"
}

# Create a key vault inside of the resource group
resource "azurerm_key_vault" "keyvault" {
  name                     = "${var.prefix}-kv"
  location                 = azurerm_resource_group.TrackingFlightFrequencies.location
  resource_group_name      = azurerm_resource_group.TrackingFlightFrequencies.name
  tenant_id                = data.azurerm_client_config.current.tenant_id
  purge_protection_enabled = false
  sku_name                  = "standard"
}

# Create an access policy for the key vault
resource "azurerm_key_vault_access_policy" "kv_access_policy" {
  key_vault_id       = azurerm_key_vault.keyvault.id
  tenant_id          = data.azurerm_client_config.current.tenant_id
  object_id          = azurerm_user_assigned_identity.databricks_identity.principal_id
  secret_permissions = ["Delete", "Get", "List", "Set"]
}

# Create a databricks module from the databricks folder
module "databricks_module" {
  source = "./databricks"

  # Pass variables to module
  rg_name = var.rg_name
  region  = var.region
}