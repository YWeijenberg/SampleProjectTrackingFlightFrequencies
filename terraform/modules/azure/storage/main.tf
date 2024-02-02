resource "azurerm_storage_account" "stgacc" {
  name                     = "${lower(var.prefix)}stgacc9724"
  location                 = var.region
  resource_group_name      = var.rg_name
  account_tier             = "Standard"
  account_replication_type = "LRS"
  account_kind             = "StorageV2"
  is_hns_enabled           = true

  network_rules {
    default_action             = "Deny"
    ip_rules                   = var.ip_rules
    virtual_network_subnet_ids = var.subnet_ids
  }
}

resource "azurerm_storage_container" "adls" {
  name                  = lower(var.prefix)
   
  storage_account_name  = azurerm_storage_account.stgacc.name
  container_access_type = "private"
}