output "databricks_identity_principal_id" {
  value = azurerm_user_assigned_identity.databricks_identity.principal_id
}

output "databrickworkspace_id" {
  value = azurerm_databricks_workspace.databricksworkspace.id
}