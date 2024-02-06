package com.TrackingFlightFrequencies.Ingestion.AirportDefinitions

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import com.TrackingFlightFrequencies.Ingestion.AirportDefinitions.AirportDefinitionsSchema.airportDefinitionSchema
import org.apache.spark.sql.DataFrame
object ReadAirportDefinitions extends SparkSessionProvider {
  def airportDefinitions(definitionsPath: String): DataFrame = {
    val df = spark.read
      .option("header", "true")
      .schema(airportDefinitionSchema)
      .csv(definitionsPath)

    df
  }
}
