resource "azurerm_storage_account" "stgacc" {
  name                     = "${var.prefix}-stgacc"
  location                 = var.region
  resource_group_name      = var.rg_name
  account_tier             = "Standard"
  account_replication_type = "LRS"
}