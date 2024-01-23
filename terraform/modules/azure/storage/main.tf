resource "azurerm_storage_account" "stgacc" {
  name                     = "${lower(var.prefix)}stgacc9724"
  location                 = var.region
  resource_group_name      = var.rg_name
  account_tier             = "Standard"
  account_replication_type = "LRS"
}

resource "azurerm_storage_container" "stgcont" {
  name = lower(var.prefix)
  storage_account_name = azurerm_storage_account.stgacc.name

  depends_on = [azurerm_storage_account.stgacc]
}

