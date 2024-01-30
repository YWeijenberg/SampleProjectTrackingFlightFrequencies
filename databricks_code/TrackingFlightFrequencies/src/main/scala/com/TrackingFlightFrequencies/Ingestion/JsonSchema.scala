package com.TrackingFlightFrequencies.Ingestion

import org.apache.spark.sql.types._

object JsonSchema {
  val jsonSchema: StructType = StructType(Array(
    StructField("pagination", StructType(Array(
      StructField("limit", IntegerType, nullable = true),
      StructField("offset", IntegerType, nullable = true),
      StructField("count", IntegerType, nullable = true),
      StructField("total", IntegerType, nullable = true)
    )), nullable = true),
    StructField("data", ArrayType(StructType(Array(
      StructField("flight_date", StringType, nullable = true),
      StructField("flight_status", StringType, nullable = true),
      StructField("departure", StructType(Array(
        StructField("airport", StringType, nullable = true),
        StructField("timezone", StringType, nullable = true),
        StructField("iata", StringType, nullable = true),
        StructField("icao", StringType, nullable = true),
        StructField("terminal", StringType, nullable = true),
        StructField("gate", StringType, nullable = true),
        StructField("delay", IntegerType, nullable = true),
        StructField("scheduled", StringType, nullable = true),
        StructField("estimated", StringType, nullable = true),
        StructField("actual", StringType, nullable = true),
        StructField("estimated_runway", StringType, nullable = true),
        StructField("actual_runway", StringType, nullable = true)
      )), nullable = true),
      StructField("arrival", StructType(Array(
        StructField("airport", StringType, nullable = true),
        StructField("timezone", StringType, nullable = true),
        StructField("iata", StringType, nullable = true),
        StructField("icao", StringType, nullable = true),
        StructField("terminal", StringType, nullable = true),
        StructField("gate", StringType, nullable = true),
        StructField("delay", IntegerType, nullable = true),
        StructField("scheduled", StringType, nullable = true),
        StructField("estimated", StringType, nullable = true),
        StructField("actual", StringType, nullable = true),
        StructField("estimated_runway", StringType, nullable = true),
        StructField("actual_runway", StringType, nullable = true)
      )), nullable = true),
      StructField("airline", StructType(Array(
        StructField("name", StringType, nullable = true),
        StructField("iata", StringType, nullable = true),
        StructField("icao", StringType, nullable = true)
      )), nullable = true),
      StructField("flight", StructType(Array(
        StructField("number", StringType, nullable = true),
        StructField("iata", StringType, nullable = true),
        StructField("icao", StringType, nullable = true),
        StructField("codeshared", StructType(Array(
          StructField("airline_name", StringType, nullable = true),
          StructField("airline_iata", StringType, nullable = true),
          StructField("airline_icao", StringType, nullable = true),
          StructField("flight_number", StringType, nullable = true),
          StructField("flight_iata", StringType, nullable = true),
          StructField("flight_icao", StringType, nullable = true)
        )), nullable = true)
      )), nullable = true),
      StructField("aircraft", StringType, nullable = true), // Update if this has a more complex structure
      StructField("live", StringType, nullable = true) // Update if this has a more complex structure
    )), containsNull = true)
    )))
}
