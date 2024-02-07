package com.TrackingFlightFrequencies.ProcessFlightData

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col
import com.TrackingFlightFrequencies.Ingestion.AirportDefinitions.ReadAirportDefinitions.airportDefinitions

object DataFrameProcessor extends SparkSessionProvider {
  def dataFrameProcessor(df: DataFrame, defPath: String): DataFrame = {
    val airportNameIataPairs = airportDefinitions(defPath).select(col("iata"), col("airport"))

    val dfFiltered = df.select(
      col("arrival_iata"),
      col("departure_scheduled"),
      col("departure_actual"),
      col("flight_date"),
      col("flight_number")
    )
    val dfAggregated = dfFiltered.groupBy("arrival_iata","flight_date").count()

    val dfWithNames = dfAggregated
      .join(airportNameIataPairs, dfAggregated("arrival_iata") === airportNameIataPairs("iata"), "left")
      .select(col("arrival_iata"), col("count"), col("flight_date"), col("airport"))

    dfWithNames
  }
}
