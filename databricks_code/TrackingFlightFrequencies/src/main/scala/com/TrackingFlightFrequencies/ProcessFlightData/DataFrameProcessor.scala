package com.TrackingFlightFrequencies.ProcessFlightData

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, count, to_date}
import com.TrackingFlightFrequencies.Ingestion.AirportDefinitions.ReadAirportDefinitions.airportDefinitions

object DataFrameProcessor extends SparkSessionProvider {
  def dataFrameProcessor(df: DataFrame, defPath: String): DataFrame = {
    val airportNameIataPairs = airportDefinitions(defPath).select(col("iata"), col("airport"))

    val dfFiltered = df.select(
      col("arrival_iata"),
      col("departure_scheduled"),
      col("departure_actual"),
      col("flight_number"),
      to_date(col("flight_date"), "yyyy-MM-dd").alias("flight_date"),
    )

    // Aggregate and get count
    val dfAggregated = dfFiltered.groupBy("arrival_iata", "flight_date").agg(count("*").alias("count"))

    // Correctly apply casting to integer type
    val dfAggregatedCasted = dfAggregated.withColumn("count", dfAggregated("count").cast("int"))

    val dfWithNames = dfAggregatedCasted
      .join(airportNameIataPairs, dfAggregated("arrival_iata") === airportNameIataPairs("iata"), "left")
      .select(col("arrival_iata"), col("count"), col("flight_date"), col("airport"))

    dfWithNames
  }
}
