output "storage_account_id" {
    value = azurerm_storage_account.stgacc.id
}

output "sorage_account_key" {
    value = azurerm_storage_account.stgacc.primary_access_key
}
