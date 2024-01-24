resource "azurerm_virtual_network" "vnet1" {
  name                = "vnet1"
  address_space       = ["10.2.0.0/16", "10.179.0.0/16"]
  location            = var.region
  resource_group_name = var.rg_name
}

resource "azurerm_subnet" "subnet1" {
  name                 = "subnet1"
  resource_group_name  = var.rg_name
  virtual_network_name = azurerm_virtual_network.vnet1.name
  address_prefixes     = ["10.2.0.0/24"]
  #   service_endpoints    = ["Microsoft.KeyVault", "Microsoft.Storage"]
}

