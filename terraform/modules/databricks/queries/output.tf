output "create_dep_table_query_id" {
  value = databricks_sql_query.create_departures_table.id
}

output "create_arr_table_query_id" {
  value = databricks_sql_query.create_arrivals_table.id
}

output "visualization_dep_by_arr_id" {
  value = databricks_sql_query.visualization_dep_by_arr.id
}

output "visualization_arr_by_dep_id" {
  value = databricks_sql_query.visualization_arr_by_dep.id
}

output "visualization_dep_over_time_id" {
  value = databricks_sql_query.visualization_dep_over_time.id
}

output "visualization_arr_over_time_id" {
  value = databricks_sql_query.visualization_arr_over_time.id
}