resource "databricks_sql_query" "create_table" {
  data_source_id = var.data_source_id
  name           = "Create table departures_count_{icao}"
  query          = <<-EOT
            CREATE TABLE IF NOT EXISTS departures_count_EHAM (
              id BIGINT GENERATED ALWAYS AS IDENTITY,
              arrival_iata STRING,
              count INT,
              flight_date DATE,
              airport STRING
            )
          EOT
}

