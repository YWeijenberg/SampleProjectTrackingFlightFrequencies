output "storage_account_id" {
  value = azurerm_storage_account.stgacc.id
}

output "storage_account_name" {
  value = azurerm_storage_account.stgacc.name
}

output "storage_container_name" {
  value = azurerm_storage_container.adls.name
}

output "storage_sas_token" {
  value = data.azurerm_storage_account_sas.sas_token.sas
}