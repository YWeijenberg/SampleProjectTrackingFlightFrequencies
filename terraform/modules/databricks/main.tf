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
  object_id          = var.databricks_identity_principal_id
  secret_permissions = ["Delete", "Get", "List", "Set", "Purge"]
}

# Step 3: Grant Managed Identity access to the Storage Account
resource "azurerm_role_assignment" "role_assignment_blob_databricks" {
  scope                = var.stgacc_id
  role_definition_name = "Storage Blob Data Contributor" 
  principal_id         = azurerm_user_assigned_identity.databricks_identity.principal_id
}

# Create a databricks secret scope backed by an azure key vault
resource "databricks_secret_scope" "kv" {
  name = "keyvault-managed"

  keyvault_metadata {
    resource_id = var.keyvault_id
    dns_name    = var.vault_uri
  }
}

# Create cluster for personal use during project
resource "databricks_cluster" "personal_cluster" {
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

resource "databricks_git_credential" "git_credentials" {
  git_provider          = "gitHub"
  git_username          = data.azurerm_key_vault_secret.gitUserName.value
  personal_access_token = data.azurerm_key_vault_secret.gitPat.value
}

# Add integration with github repo in databricks workspace
resource "databricks_repo" "repository" {
  url    = data.azurerm_key_vault_secret.urlRepository.value
  branch = "dev"
}

resource "databricks_secret" "storage_key" {
  key          = "blob_storage_key"
  string_value = var.stgacc_key
  scope        = databricks_secret_scope.kv.name
}