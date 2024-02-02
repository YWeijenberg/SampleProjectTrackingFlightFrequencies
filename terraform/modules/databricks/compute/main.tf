# Create cluster for personal use during project
resource "databricks_cluster" "personal_cluster" {
  cluster_name            = "personal_cluster"
  spark_version           = data.databricks_spark_version.latest_lts.id
  node_type_id            = data.databricks_node_type.smallest.id
  autotermination_minutes = 60
  data_security_mode      = "LEGACY_SINGLE_USER_STANDARD"
  num_workers             = 0
  spark_conf = {
    "spark.databricks.cluster.profile" = "singleNode"
    "spark.master"                     = "local[*]"
  }
  custom_tags = {
    "ResourceClass" = "SingleNode"
  }
}