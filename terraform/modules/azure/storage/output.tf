output "storage_account_id" {
  value = azurerm_storage_account.stgacc.id
}

output "airport_definitions_stgacc_url" {
  value = azurerm_storage_blob.airport_definitions_blob.url
}

output "stgacc_name" {
  value = azurerm_storage_account.stgacc.name
}

output "sas_token" {
  value = data.azurerm_storage_account_sas.sas_token.sas
}