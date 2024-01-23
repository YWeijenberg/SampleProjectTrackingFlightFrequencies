resource "azurerm_storage_account" "stgacc" {
  name                     = "${lower(var.prefix)}stgacc9724"
  location                 = var.region
  resource_group_name      = var.rg_name
  account_tier             = "Standard"
  account_replication_type = "LRS"
}

