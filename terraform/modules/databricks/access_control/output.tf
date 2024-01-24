output "db_identity_principal_id" {
    value = azurerm_user_assigned_identity.databricks_identity.principal_id
}