package com.TrackingFlightFrequencies.SparkSession

import com.TrackingFlightFrequencies.Variables.GlobalVars
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait SparkSessionProvider extends GlobalVars {
//  private val defaultConf = new SparkConf()
//    .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
//    .set("spark.sql.parquet.compression.codec", "snappy")
//    .set("spark.sql.parquet.mergeSchema", "false")
//    .set("spark.sql.sources.partitionOverwriteMode", "dynamic")
//    .set("spark.log.level", "Warn")
//    .set(s"fs.azure.account.auth.type.${storageAccountname}.dfs.core.windows.net", "SAS")
//    .set(s"fs.azure.sas.token.provider.type.${storageAccountname}.dfs.core.windows.net", "org.apache.hadoop.fs.azurebfs.sas.FixedSASTokenProvider")
//    .set(s"fs.azure.sas.fixed.token.${storageAccountname}.dfs.core.windows.net", sasToken)
//
//  private val conf = {
//    if (defaultConf.contains("spark.master")) defaultConf
//    else defaultConf.setMaster("local[*]")
//  }
//
//  val spark: SparkSession = SparkSession.builder.config(conf).getOrCreate()

  val spark: SparkSession = SparkSession.builder.getOrCreate()

  spark.conf.set(s"fs.azure.account.auth.type.${storageAccountname}.dfs.core.windows.net", "SAS")
  spark.conf.set(s"fs.azure.sas.token.provider.type.${storageAccountname}.dfs.core.windows.net", "org.apache.hadoop.fs.azurebfs.sas.FixedSASTokenProvider")
  spark.conf.set(s"fs.azure.sas.fixed.token.${storageAccountname}.dfs.core.windows.net", sasToken)
}
