package com.TrackingFlightFrequencies.Variables

import com.databricks.dbutils_v1.DBUtilsHolder.dbutils

object GlobalVars {
  // Variables for .jar in Databricks
  private val secretScope = "keyvault-managed"
  val apiHost: String = dbutils.secrets.get(secretScope,"apiHost")
  val apiKey: String = dbutils.secrets.get(secretScope, "apiKey")
  val sasToken: String = dbutils.secrets.get(secretScope, "storageSas")
  val storageAccountname: String = dbutils.secrets.get(secretScope, "storageAccountName")
  val containerName: String = dbutils.secrets.get(secretScope, "containerName")
  var airportIcao: Option[String] = None
  val airport = airportIcao.getOrElse("EHAM")
  var date: Option[String] = None
  val definitionsPath: String = s"abfss://${containerName}@${storageAccountname}.dfs.core.windows.net/data/airport_definitions.csv"
  val url = s"https://aerodatabox.p.rapidapi.com/flights/airports/icao/${airport}"
}
