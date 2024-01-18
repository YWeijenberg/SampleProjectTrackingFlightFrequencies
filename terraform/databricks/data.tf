data "databricks_node_type" "smallest" {
  local_disk = true
}

# Get the current latest version of databricks and spark
data "databricks_spark_version" "latest_lts" {
  long_term_support = true
}

data "azurerm_key_vault_secret" "urlRepository" {
  name         = "urlRepository"
  key_vault_id = var.azurerm_keyvault_id
}

data "azurerm_key_vault_secret" "gitUserName"{
  name = "gitUserName"
  key_vault_id = var.azurerm_keyvault_id
}

data "azurerm_key_vault_secret" "gitPat" {
  name = "gitPat"
  key_vault_id = var.azurerm_keyvault_id
}

# data "databricks_cluster_policy" "personal" {
#   name = "Personal Compute"
# }
