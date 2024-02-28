package com.TrackingFlightFrequencies.SparkSession

import com.TrackingFlightFrequencies.Variables.GlobalVars
import com.TrackingFlightFrequencies.Variables.GlobalVars.{sasToken, storageAccountname}
import org.apache.spark.sql.SparkSession

trait SparkSessionProvider {

  var spark: SparkSession = SparkSession.builder.getOrCreate()

  spark.conf.set(s"fs.azure.account.auth.type.${storageAccountname}.dfs.core.windows.net", "SAS")
  spark.conf.set(s"fs.azure.sas.token.provider.type.${storageAccountname}.dfs.core.windows.net", "org.apache.hadoop.fs.azurebfs.sas.FixedSASTokenProvider")
  spark.conf.set(s"fs.azure.sas.fixed.token.${storageAccountname}.dfs.core.windows.net", sasToken)
}
