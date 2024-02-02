package com.TrackingFlightFrequencies.ProcessFlightData

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col
import com.TrackingFlightFrequencies.Ingestion.AirportDefinitions.ReadAirportDefinitions.airportDefinitions

object DataFrameProcessor extends SparkSessionProvider {
  def dataFrameProcessor(df: DataFrame, definitionsPath: String): DataFrame = {
    val airportNameIataPairs = airportDefinitions(definitionsPath).select(col("iata_code"), col("name"))

    val dfFiltered = df.select(
      col("departure_icao"),
      col("arrival_iata"),
      col("departure_scheduled"),
      col("departure_actual"),
      col("flight_date"),
      col("flight_number")
    )
    val dfAggregated = dfFiltered.groupBy("arrival_iata").count()

    val dfWithNames = dfAggregated
      .join(airportNameIataPairs, dfAggregated("arrival_iata") === airportNameIataPairs("iata_code"))
      .select(col("iata_code"), col("name"), col("count"))

    dfWithNames
  }
}
