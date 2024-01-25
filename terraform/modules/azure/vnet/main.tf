resource "azurerm_virtual_network" "vnet1" {
  name                = "vnet1"
  address_space       = ["10.2.0.0/16", "10.179.0.0/16"]
  location            = var.region
  resource_group_name = var.rg_name
}

resource "azurerm_subnet" "private-subnet" {
  name                 = "private-subnet"
  resource_group_name  = var.rg_name
  virtual_network_name = azurerm_virtual_network.vnet1.name
  address_prefixes     = ["10.179.0.0/18"]
  service_endpoints = ["Microsoft.KeyVault","Microsoft.Storage"]
  delegation {
    name = "private_delegation_databricks"
    service_delegation {
      name = "Microsoft.Databricks/workspaces"
    }
  }
}

resource "azurerm_subnet" "public-subnet" {
  name                 = "public-subnet"
  resource_group_name  = var.rg_name
  virtual_network_name = azurerm_virtual_network.vnet1.name
  address_prefixes     = ["10.179.64.0/18"]
  service_endpoints = ["Microsoft.KeyVault","Microsoft.Storage"]
  delegation { 
    name = "public_delegation_databricks"
    service_delegation {
      name = "Microsoft.Databricks/workspaces"
    }
  }
}

resource "azurerm_network_security_group" "security_group" {
  name                = "${var.rg_name}-nsg"
  location            = var.region
  resource_group_name = var.rg_name
}

resource "azurerm_subnet_network_security_group_association" "public_sunbnet_association" {
  subnet_id = azurerm_subnet.public-subnet.id
  network_security_group_id = azurerm_network_security_group.security_group.id
}
resource "azurerm_subnet_network_security_group_association" "private_subnet_association" {
  subnet_id                 = azurerm_subnet.private-subnet.id
  network_security_group_id = azurerm_network_security_group.security_group.id
} 