resource "databricks_sql_endpoint" "sql_endpoint" {
  name             = "Endpoint for running queries"
  cluster_size     = "2X-Small"
  max_num_clusters = 1

  enable_photon             = false
  enable_serverless_compute = true
  warehouse_type            = "PRO"
  auto_stop_mins            = 1
}