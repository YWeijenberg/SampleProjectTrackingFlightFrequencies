data "azurerm_client_config" "current" {
}

data "azuread_user" "my_user" {
  user_principal_name = "yweijenberg@tudelft.nl"
}