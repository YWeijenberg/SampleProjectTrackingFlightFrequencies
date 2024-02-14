resource "databricks_sql_dashboard" "d1" {
  name = "Flight Freqiencies at Schiphol Airport"
}

resource "databricks_sql_visualization" "flights_by_destination" {
  name     = "Number of Flights Departing by Arrival Destination"
  query_id = var.visualization_dep_by_arr_id
  type     = "chart"
  options  = file("${path.module}/visualization_options_flights_per_day.json")
}

resource "databricks_sql_visualization" "flights_by_arrivals" {
  name     = "Number of Flights Arriving by Departure Destination"
  query_id = var.visualization_arr_by_dep_id
  type     = "chart"
  options  = file("${path.module}/visualization_options_flights_per_day.json")
}

resource "databricks_sql_visualization" "departures_over_time" {
  name     = "Number of Departing Flight Per Day"
  query_id = var.visualization_dep_over_time_id
  type     = "chart"
  options  = file("${path.module}/visualization_options_flights_over_time.json")
}

resource "databricks_sql_visualization" "arrivals_over_time" {
  name     = "Number of Arriving Flight Per Day"
  query_id = var.visualization_arr_over_time_id
  type     = "chart"
  options  = file("${path.module}/visualization_options_flights_over_time.json")
}

resource "databricks_sql_widget" "d1w1" {
  dashboard_id     = databricks_sql_dashboard.d1.id
  visualization_id = databricks_sql_visualization.flights_by_destination.id

  position {
    size_x = 4
    size_y = 15
    pos_x  = 0
    pos_y  = 0
  }
}

resource "databricks_sql_widget" "d1w2" {
  dashboard_id     = databricks_sql_dashboard.d1.id
  visualization_id = databricks_sql_visualization.flights_by_arrivals.id

  position {
    size_x = 4
    size_y = 15
    pos_x  = 0
    pos_y  = 0
  }
}

resource "databricks_sql_widget" "d1w3" {
  dashboard_id     = databricks_sql_dashboard.d1.id
  visualization_id = databricks_sql_visualization.departures_over_time.id

  position {
    size_x = 3
    size_y = 6
  }
}

resource "databricks_sql_widget" "d1w4" {
  dashboard_id     = databricks_sql_dashboard.d1.id
  visualization_id = databricks_sql_visualization.arrivals_over_time.id

  position {
    size_x = 3
    size_y = 6
    pos_x = 3
  }
}