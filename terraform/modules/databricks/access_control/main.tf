# Create a user assigned identity for databricks
resource "azurerm_user_assigned_identity" "databricks_identity" {
  resource_group_name = var.rg_name
  location            = var.region

  name = "databricks_identity"
}

# Create an access policy for the key vault giving permissions to databricks
resource "azurerm_key_vault_access_policy" "kv_access_policy" {
  key_vault_id       = var.keyvault_id
  tenant_id          = data.azurerm_client_config.current.tenant_id
  object_id          = azurerm_user_assigned_identity.databricks_identity.principal_id
  secret_permissions = ["Delete", "Get", "List", "Set", "Purge"]
}

# Grant Managed Identity access to the Storage Account
resource "azurerm_role_assignment" "role_assignment_blob_databricks" {
  scope                = var.stgacc_id
  role_definition_name = "Storage Blob Data Contributor"
  principal_id         = azurerm_user_assigned_identity.databricks_identity.principal_id
}