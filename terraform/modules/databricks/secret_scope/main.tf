# Create a databricks secret scope backed by an azure key vault
resource "databricks_secret_scope" "kv" {
  name = "keyvault-managed"

  keyvault_metadata {
    resource_id = var.keyvault_id
    dns_name    = var.vault_uri
  }
}