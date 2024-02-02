package com.TrackingFlightFrequencies.ProcessFlightData

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col

object DataFrameProcessor extends SparkSessionProvider {
  def dataFrameProcessor(df: DataFrame): DataFrame = {
    val dfFiltered = df.select(
      col("departure_icao"),
      col("arrival_iata"),
      col("departure_scheduled"),
      col("departure_actual"),
      col("flight_date"),
      col("flight_number")
    )
    val dfAggregated = dfFiltered.groupBy("arrival_icao").count()

    dfAggregated
  }
}
