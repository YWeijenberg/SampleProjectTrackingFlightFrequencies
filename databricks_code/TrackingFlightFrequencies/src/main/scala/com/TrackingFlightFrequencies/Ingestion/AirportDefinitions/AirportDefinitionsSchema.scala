package com.TrackingFlightFrequencies.Ingestion.AirportDefinitions

import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType, DoubleType}


object AirportDefinitionsSchema {
  val airportDefinitionSchema = StructType(Array(
    StructField("ident", StringType, nullable = true),
    StructField("type", StringType, nullable = true),
    StructField("name", StringType, nullable = true),
    StructField("elevation_ft", IntegerType, nullable = true),
    StructField("continent", StringType, nullable = true),
    StructField("iso_country", StringType, nullable = true),
    StructField("iso_region", StringType, nullable = true),
    StructField("municipality", StringType, nullable = true),
    StructField("gps_code", StringType, nullable = true),
    StructField("iata_code", StringType, nullable = true),
    StructField("local_code", StringType, nullable = true),
    StructField("coordinates", StringType, nullable = true)
  ))

}
