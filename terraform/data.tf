data "azurerm_client_config" "current" {
}

data "azurerm_key_vault_secret" "username" {
  name = "username"
  key_vault_id = azurerm_key_vault.keyvault.id
}

data "azuread_user" "my_user" {
  user_principal_name = data.azurerm_key_vault_secret.username.value
}

