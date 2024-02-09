package com.TrackingFlightFrequencies.ProcessFlightData

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, count, to_date}
import com.TrackingFlightFrequencies.Ingestion.AirportDefinitions.ReadAirportDefinitions.airportDefinitions

object DataFrameProcessor extends SparkSessionProvider {
  def dataFrameProcessor(df: DataFrame,
                         isDeparture: Boolean = true,
                         defPath: String): DataFrame = {
    val airportNameIataPairs = airportDefinitions(defPath).select(col("iata"), col("airport"))

    val dfFiltered = df.select(
      col("movement_airport_iata").as("airport_iata"),
      col(s"${if (isDeparture) "departure" else "arrival"}_isCargo").as("isCargo"),
      col("movement_scheduledTime_local").cast("date").as("flight_date")
    )

    // Aggregate and get count
    val dfAggregated = dfFiltered.groupBy("airport_iata", "isCargo", "flight_date").agg(count("*").alias("count"))

    // Correctly apply casting to integer type
    val dfAggregatedCasted = dfAggregated.withColumn("count", dfAggregated("count").cast("int"))

    val dfWithNames = dfAggregatedCasted
      .join(airportNameIataPairs, dfAggregated("airport_iata") === airportNameIataPairs("iata"), "left")
      .select(col("airport_iata"),
        col("count"),
        col("isCargo"),
        col("flight_date"),
        col("airport").as("airport_name")
      )

    dfWithNames
  }
}
