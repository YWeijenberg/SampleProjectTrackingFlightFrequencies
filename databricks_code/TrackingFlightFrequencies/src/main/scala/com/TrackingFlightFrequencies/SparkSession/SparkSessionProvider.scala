package com.TrackingFlightFrequencies.SparkSession

import org.apache.spark.sql.SparkSession

trait SparkSessionProvider {
  lazy val spark: SparkSession = {
    SparkSession.builder
      .appName("Flight Frequencies")
      .master("local[*]")
      .getOrCreate()
  }
}
