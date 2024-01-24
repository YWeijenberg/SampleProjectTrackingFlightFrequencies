output "storage_account_id" {
  value = azurerm_storage_account.stgacc.id
}

output "sorage_account_key" {
  value = azurerm_storage_account.stgacc.primary_access_key
}

output "storage_account_name" {
  value = azurerm_storage_account.stgacc.name
}

output "storage_container_name" {
  value = azurerm_storage_container.blob.name
}