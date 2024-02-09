resource "databricks_sql_dashboard" "d1" {
  name   = "Flight Freqiencies By Airport"
}

resource "databricks_sql_visualization" "flights_by_destination" {
  name = "Number of Flights Departing by Arrival Destination"
  query_id = var.visualization_dep_by_arr_id
  type = "chart"
  options = file("${path.module}/flights_by_destination.json")
}

resource "databricks_sql_widget" "d1w1" {
  dashboard_id = databricks_sql_dashboard.d1.id
  visualization_id = databricks_sql_visualization.flights_by_destination.id

    position {
    size_x = 5
    size_y = 12
    pos_x = 0
    pos_y = 0
  }
}

