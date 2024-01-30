data "azurerm_storage_account_sas" "sas_token" {
  connection_string = azurerm_storage_account.stgacc.primary_connection_string
  https_only        = true

  start  = var.storage_sas_start
  expiry = var.storage_sas_end

  resource_types {
    service   = false
    container = true
    object    = true
  }

  services {
    blob  = true
    queue = false
    table = false
    file  = false
  }

  permissions {
    read    = true
    write   = true
    delete  = false
    list    = true
    add     = true
    create  = true
    update  = true
    process = true
    tag     = true
    filter  = true
  }
}