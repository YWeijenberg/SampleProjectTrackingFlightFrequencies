output "create_table_query_id" {
  value = databricks_sql_query.create_table.id
}

output "visualization_dep_by_arr_id" {
  value = databricks_sql_query.visualization_dep_by_arr.id
}

output "visualization_dep_over_time_id" {
  value = databricks_sql_query.visualization_dep_over_time.id
}