data "databricks_node_type" "smallest" {
  local_disk = true
}

# Get the current latest version of databricks and spark
data "databricks_spark_version" "latest_lts" {
  long_term_support = true 
}

# data "databricks_cluster_policy" "personal" {
#   name = "Personal Compute"
# }
