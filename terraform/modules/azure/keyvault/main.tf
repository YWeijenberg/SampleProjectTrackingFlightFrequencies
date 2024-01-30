# Create a key vault inside of the resource group
resource "azurerm_key_vault" "keyvault" {
  name                     = "${var.prefix}-kv"
  location                 = var.region
  resource_group_name      = var.rg_name
  tenant_id                = data.azurerm_client_config.current.tenant_id
  purge_protection_enabled = false
  sku_name                 = "standard"
}

# Create an access policy for the key vault giving permissions to user
resource "azurerm_key_vault_access_policy" "user_access_policy" {
  key_vault_id       = azurerm_key_vault.keyvault.id
  tenant_id          = data.azurerm_client_config.current.tenant_id
  object_id          = data.azuread_user.my_user.id
  secret_permissions = ["Delete", "Get", "List", "Set", "Purge"]
}

resource "azurerm_key_vault_secret" "keyvault_secrets" {
  for_each = var.secrets

  name  = each.key
  value = each.value

  key_vault_id = azurerm_key_vault.keyvault.id
}

resource "azurerm_key_vault_secret" "storage_sas_secret" {
  name = "storageSas"
  value = var.storage_sas_token
  key_vault_id = azurerm_key_vault.keyvault.id
}

resource "azurerm_key_vault_secret" "storage_account_name" {
  name = "storageAccountName"
  value = var.stgacc_name
  key_vault_id = azurerm_key_vault.keyvault.id
}

resource "azurerm_key_vault_secret" "container_name" {
  name = "containerName"
  value = var.container_name
  key_vault_id = azurerm_key_vault.keyvault.id
}