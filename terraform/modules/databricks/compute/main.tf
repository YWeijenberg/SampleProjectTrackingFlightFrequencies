resource "databricks_sql_endpoint" "sql_endpoint" {
  name             = "Endpoint for running queries"
  cluster_size     = "2X-Small"
  max_num_clusters = 1

  enable_photon             = false
  enable_serverless_compute = true
  warehouse_type            = "PRO"
  auto_stop_mins            = 1
}

# Cluster pool 
resource "databricks_instance_pool" "dbpool" {
  instance_pool_name                    = "flight data pool"
  min_idle_instances                    = 0
  max_capacity                          = 1
  node_type_id                          = data.databricks_node_type.smallest.id
  idle_instance_autotermination_minutes = 10
  preloaded_spark_versions              = [data.databricks_spark_version.latest_lts.id]
}