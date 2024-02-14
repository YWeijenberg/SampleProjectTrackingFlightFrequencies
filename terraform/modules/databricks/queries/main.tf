resource "databricks_sql_query" "create_departures_table" {
  data_source_id = var.data_source_id
  name           = "Create table {icao}_departures_count"
  query          = <<-EOT
    CREATE TABLE IF NOT EXISTS EHAM_departures_count (
      id BIGINT GENERATED ALWAYS AS IDENTITY,
      arrival_iata STRING,
      count INT,
      isCargo BOOLEAN,
      flight_date DATE,
      airport_name STRING)
    EOT
}

resource "databricks_sql_query" "create_arrivals_table" {
  data_source_id = var.data_source_id
  name           = "Create table {icao}_arrivals_count"
  query          = <<-EOT
    CREATE TABLE IF NOT EXISTS EHAM_arrivals_count (
      id BIGINT GENERATED ALWAYS AS IDENTITY,
      arrival_iata STRING,
      count INT,
      isCargo BOOLEAN,
      flight_date DATE,
      airport_name STRING)
    EOT
}

resource "databricks_sql_query" "visualization_dep_by_arr" {
  data_source_id = var.data_source_id
  name           = "Query of Departures Count by Arrival Airport"
  query          = <<-EOT
    SELECT count, airport_name, isCargo FROM eham_departures_count
    WHERE flight_date = current_date()
    AND airport_name IS NOT NULL
    ORDER BY count DESC
    EOT
}

resource "databricks_sql_query" "visualization_arr_by_dep" {
  data_source_id = var.data_source_id
  name           = "Query of Arrivals Count by Departure Airport"
  query          = <<-EOT
    SELECT count, airport_name, isCargo FROM eham_arrivals_count
    WHERE flight_date = current_date()
    AND airport_name IS NOT NULL
    ORDER BY count DESC
    EOT
}

resource "databricks_sql_query" "visualization_dep_over_time" {
  data_source_id = var.data_source_id
  name           = "Query of Departure Count over Time"
  query          = <<-EOT
    SELECT flight_date AS `Date`, SUM(count) AS `Total Departures`
    FROM eham_departures_count
    GROUP BY flight_date
    ORDER BY flight_date DESC
    EOT
}

resource "databricks_sql_query" "visualization_arr_over_time" {
  data_source_id = var.data_source_id
  name           = "Query of Arrival Count over Time"
  query          = <<-EOT
    SELECT flight_date AS `Date`, SUM(count) AS `Total Arrivals`
    FROM eham_arrivals_count
    GROUP BY flight_date
    ORDER BY flight_date DESC
    EOT
}