package com.TrackingFlightFrequencies.Ingestion.AirportDefinitions

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import com.TrackingFlightFrequencies.Ingestion.AirportDefinitions.AirportDefinitionsSchema.airportDefinitionSchema
object ReadAirportDefinitions extends SparkSessionProvider {
  def readAirportDefinitions(path: String): Unit = {
    val df = spark.read
      .option("header", "true")
      .schema(airportDefinitionSchema)
      .csv(path)

    df
  }
}
