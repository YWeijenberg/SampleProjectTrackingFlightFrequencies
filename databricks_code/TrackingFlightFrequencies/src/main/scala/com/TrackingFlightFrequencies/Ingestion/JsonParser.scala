package com.TrackingFlightFrequencies.Ingestion

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import com.TrackingFlightFrequencies.Ingestion.JsonSchema.jsonSchema
import org.apache.spark.sql.DataFrame

object JsonParser extends SparkSessionProvider {
  import spark.implicits._
  def parse (s: String): DataFrame = {
    spark.read.schema(jsonSchema).json(Seq(s).toDS)
  }
}
