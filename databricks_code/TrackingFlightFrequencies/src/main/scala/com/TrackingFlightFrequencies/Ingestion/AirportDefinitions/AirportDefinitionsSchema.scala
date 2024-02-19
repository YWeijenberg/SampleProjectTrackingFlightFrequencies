package com.TrackingFlightFrequencies.Ingestion.AirportDefinitions

import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType, DoubleType}


object AirportDefinitionsSchema {
  val airportDefinitionSchema = StructType(Array(
    StructField("country_code", StringType, nullable = true),
    StructField("region_name", StringType, nullable = true),
    StructField("iata", StringType, nullable = true),
    StructField("icao", StringType, nullable = true),
    StructField("airport", StringType, nullable = true),
    StructField("latitude", DoubleType, nullable = true),
    StructField("longitude", DoubleType, nullable = true)
  ))
}
