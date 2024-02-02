package com.TrackingFlightFrequencies

import com.TrackingFlightFrequencies.DataArchiving.DataFrameArchiver
import com.TrackingFlightFrequencies.Ingestion.{ApiRequest, JsonParser, DataFrameDenester}
import com.TrackingFlightFrequencies.Variables.GlobalVars

object Main extends GlobalVars{
  def main(args: Array[String]): Unit = {
    val jsonString: String = ApiRequest.request(url = url, apiKey = apiKey)
    val raw_df = JsonParser.parse(jsonString)
    val flattenedDf = DataFrameDenester.flattenDataFrame(raw_df)
    DataFrameArchiver.writeDataFrameToBlob(
      storageAccountName = storageAccountname,
      containerName = containerName,
      airportIcao = "EHAM",
      dataFrame = flattenedDf,
      rg_name = rg_name
    )
  }
}
