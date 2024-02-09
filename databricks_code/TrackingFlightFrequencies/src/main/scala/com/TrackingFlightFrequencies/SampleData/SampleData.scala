package com.TrackingFlightFrequencies.SampleData

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider

object SampleData extends SparkSessionProvider {
  val sampleData = {
    spark.read.schema().json("/sampleData.json")
  }
}
