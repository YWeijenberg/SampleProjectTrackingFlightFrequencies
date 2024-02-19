package com.TrackingFlightFrequencies.SampleData

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import com.TrackingFlightFrequencies.Ingestion.FlightData.JsonSchema.jsonSchema
import org.apache.spark.sql.DataFrame

object SampleData extends SparkSessionProvider {
  val sampleData: DataFrame = {
    spark.read.schema(jsonSchema).json("/sampleData.json")
  }
}
