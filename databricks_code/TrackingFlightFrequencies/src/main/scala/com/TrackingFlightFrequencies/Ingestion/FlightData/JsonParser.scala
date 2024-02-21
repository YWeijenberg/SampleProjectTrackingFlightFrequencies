package com.TrackingFlightFrequencies.Ingestion.FlightData

import com.TrackingFlightFrequencies.Ingestion.FlightData.JsonSchema.jsonSchema
import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame

object JsonParser extends SparkSessionProvider {
  import spark.implicits._
  // Parse the api request string to dataframe format using the schema defined in JsonSchema
  def parse (s: String): DataFrame = {
    spark.read.schema(jsonSchema).json(Seq(s).toDS)
  }
}
