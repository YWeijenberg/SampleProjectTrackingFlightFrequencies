
data "azurerm_key_vault_secret" "urlRepository" {
  name         = "urlRepository"
  key_vault_id = var.keyvault_id
}

data "azurerm_key_vault_secret" "gitUserName" {
  name         = "gitUserName"
  key_vault_id = var.keyvault_id
}

data "azurerm_key_vault_secret" "gitPat" {
  name         = "gitPat"
  key_vault_id = var.keyvault_id
}

