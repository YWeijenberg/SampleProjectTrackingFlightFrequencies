package com.TrackingFlightFrequencies.Variables

import com.databricks.dbutils_v1.DBUtilsHolder.dbutils

trait GlobalVars {
  // Variables for .jar in Databricks
  val url = "https://aerodatabox.p.rapidapi.com/flights/airports/icao/EHAM/"
  private val secretScope = "keyvault-managed"
  val apiHost: String = dbutils.secrets.get(secretScope,"apiHost")
  val apiKey: String = dbutils.secrets.get(secretScope, "apiKey")
  val sasToken: String = dbutils.secrets.get(secretScope, "storageSas")
  val storageAccountname: String = dbutils.secrets.get(secretScope, "storageAccountName")
  val containerName: String = dbutils.secrets.get(secretScope, "containerName")
  val airportIcao: String = "EHAM"
  val definitionsPath: String = s"abfss://${containerName}@${storageAccountname}.dfs.core.windows.net/data/airport_definitions.csv"
}
