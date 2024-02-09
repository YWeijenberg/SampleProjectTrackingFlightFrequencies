package com.TrackingFlightFrequencies.Variables

import com.databricks.dbutils_v1.DBUtilsHolder.dbutils

trait GlobalVars {
  // Variables for .jar in Databricks
  val url = "http://api.aviationstack.com/v1/flights?dep_icao=EHAM"
  private val secretScope = "keyvault-managed"
  val apiHost: String = dbutils.secrets.get(secretScope,"apiHost")
  val rg_name: String = dbutils.secrets.get(secretScope, "rgName")
  val apiKey: String = dbutils.secrets.get(secretScope, "apiKey")
  val sasToken: String = dbutils.secrets.get(secretScope, "storageSas")
  val storageAccountname: String = dbutils.secrets.get(secretScope, "storageAccountName")
  val containerName: String = dbutils.secrets.get(secretScope, "containerName")
  val airportIcao: String = "EHAM"
  val definitionsPath: String = s"abfss://${containerName}@${storageAccountname}.dfs.core.windows.net/data/airport_definitions.csv"

//  // variables for local dev env
//  val rg_name: String = "TrackingFlightFrequencies"
//  val apiKey: String = "a6a3214f08d9f47202428d7ef5fb46be"
//  // Make sure to renew the sasToken in Azure before implementing .jar
//  val sasToken: String = "?sv=2022-11-02&ss=bfqt&srt=co&sp=rwdlacupyx&se=2024-02-01T22:35:14Z&st=2024-02-01T14:35:14Z&spr=https&sig=myWR8RnnfIjymB0YVB4PP2OVOtwgGgOCdyiVdGqdqC8%3D"
//  val storageAccountname: String = "flightfreqstgacc9724"
//  val containerName: String = "flightfreq"
//  val airportIcao: String = "EHAM"
//  val definitionsPath: String = "src/main/scala/com/TrackingFlightFrequencies/data/airport-codes.csv"
}
