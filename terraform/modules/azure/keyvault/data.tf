data "azuread_user" "my_user" {
  user_principal_name = var.EntraIDUsername
}

data "azurerm_client_config" "current" {
}
