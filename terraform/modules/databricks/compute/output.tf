output "sql_warehouse_id" {
  value = databricks_sql_endpoint.sql_endpoint.id
}

output "data_source_id" {
  value = databricks_sql_endpoint.sql_endpoint.data_source_id
}