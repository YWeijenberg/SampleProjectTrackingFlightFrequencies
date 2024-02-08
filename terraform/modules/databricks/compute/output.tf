output "sql_warehouse_id" {
  value = databricks_sql_endpoint.sql_endpoint.id
}

output "data_source_id" {
  value = databricks_sql_endpoint.sql_endpoint.data_source_id
}

output "instance_pool_id" {
  value = databricks_instance_pool.dbpool.id
}