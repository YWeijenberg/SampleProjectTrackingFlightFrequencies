data "azurerm_storage_account_sas" "sas_token" {
  connection_string = azurerm_storage_account.stgacc.primary_connection_string
  https_only        = true

  start  = var.storage_sas_start
  expiry = var.storage_sas_end

  signed_version = "2022-11-02"

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
    add     = false
    create  = true
    update  = false
    process = false
    tag     = false
    filter  = false
  }
}