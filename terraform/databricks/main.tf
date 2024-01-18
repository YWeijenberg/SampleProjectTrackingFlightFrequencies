# Create a databricks workspace
resource "azurerm_databricks_workspace" "databricksworkspace" {
  name                = "TrackingFlightFrequencies_dbw"
  resource_group_name = var.rg_name
  location            = var.region
  sku                 = "trial"

  tags = {
    # Put any tags here
  }
}

resource "azurerm_role_assignment" "databricks_identity_role" {
  scope                = azurerm_databricks_workspace.databricksworkspace.id
  role_definition_name = "Contributor" # Or any other role as per your requirements
  principal_id         = var.identity_prinicpal_id
}

# Create a databricks secret scope backed by an azure key vault
resource "databricks_secret_scope" "kv" {
  name = "keyvault-managed"

  keyvault_metadata {
    resource_id = var.key_vault_id
    dns_name    = var.vault_uri
  }
}

# # Create cluster for personal use during project
resource "databricks_cluster" "personal_cluster" {
  # policy_id               = data.databricks_cluster_policy.personal.id
  depends_on              = [azurerm_databricks_workspace.databricksworkspace]
  cluster_name            = "personal_cluster"
  spark_version           = data.databricks_spark_version.latest_lts.id
  node_type_id            = data.databricks_node_type.smallest.id
  autotermination_minutes = 60
  data_security_mode      = "SINGLE_USER"
  num_workers             = 0
  spark_conf = {
    "spark.databricks.cluster.profile" = "singleNode"
    "spark.master"                     = "local[*]"
  }
  custom_tags = {
    "ResourceClass" = "SingleNode"
  }
}