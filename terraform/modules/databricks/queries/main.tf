resource "databricks_sql_query" "create_table" {
  data_source_id = var.data_source_id
  name           = "Create table departures_count_{icao}"
  query          = <<-EOT
    CREATE TABLE IF NOT EXISTS departures_count_EHAM (
      id BIGINT GENERATED ALWAYS AS IDENTITY,
      arrival_iata STRING,
      count INT,
      flight_date DATE,
      airport STRING)
    EOT
}

resource "databricks_sql_query" "visualization_dep_by_arr" {
  data_source_id = var.data_source_id
  name           = "Query of Departures Count by Arrival Airport"
  query          = <<-EOT
    SELECT count, airport FROM departures_count_eham
    WHERE flight_date = current_date()
    ORDER BY count DESC
    EOT
}

resource "databricks_sql_query" "visualization_dep_over_time" {
  data_source_id = var.data_source_id
  name           = "Query of Departure Count over Time"
  query          = <<-EOT
    SELECT flight_date, SUM(count) AS total_flights
    FROM departures_count_eham
    GROUP BY flight_date
    ORDER BY flight_date DESC
    EOT
}