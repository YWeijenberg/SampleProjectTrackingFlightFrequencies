resource "azurerm_databricks_workspace" "databricksworkspace" {
  name                = "TrackingFlightFrequencies_dbw"
  resource_group_name = var.rg_name
  location            = var.region
  sku                 = "trial"

  tags = {
    # Put any tags here
  }
}

# Get the current latest version of databricks and spark
data "databricks_spark_version" "latest_lts" {
  depends_on        = [azurerm_databricks_workspace.databricksworkspace]
  long_term_support = true
}

data "databricks_cluster_policy" "personal" {
  name = "Personal Compute"
}

# Create cluster for personal use during project
resource "databricks_cluster" "personal_cluster" {
  depends_on              = [azurerm_databricks_workspace.databricksworkspace]
  cluster_name            = "personal_cluster"
  policy_id               = data.databricks_cluster_policy.personal.id
  spark_version           = data.databricks_spark_version.latest_lts.id
  node_type_id            = "Standard_DS3_v2"
  autotermination_minutes = 60
  single_user_name        = "Yannick Weijenberg"
}