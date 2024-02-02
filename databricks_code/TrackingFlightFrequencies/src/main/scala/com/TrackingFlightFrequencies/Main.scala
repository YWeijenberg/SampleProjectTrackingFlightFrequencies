package com.TrackingFlightFrequencies

import com.TrackingFlightFrequencies.DataArchiving.DataFrameArchiver
import com.TrackingFlightFrequencies.Ingestion.FlightData.{ApiRequest, DataFrameDenester, JsonParser}
import com.TrackingFlightFrequencies.ProcessFlightData.DataFrameProcessor
import com.TrackingFlightFrequencies.Variables.GlobalVars
import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider

// Import for Local Environment Testing
import com.TrackingFlightFrequencies.SampleData.SampleData.sampleData

object Main extends GlobalVars with SparkSessionProvider {
  def main(args: Array[String]): Unit = {
    // Code for Databricks Environment .jar
    val jsonString: String = ApiRequest.request(url = url, apiKey = apiKey)
//    val raw_df = JsonParser.parse(jsonString)
//    val flattenedDf = DataFrameDenester.flattenDataFrame(raw_df)
//    DataFrameArchiver.writeDataFrameToBlob(
//      storageAccountName = storageAccountname,
//      containerName = containerName,
//      airportIcao = "EHAM",
//      dataFrame = flattenedDf,
//      rg_name = rg_name
//    )

    // Code for Local Environment Testing
//    val jsonString = sampleData
    val raw_df = JsonParser.parse(jsonString)
    val flattenedDf = DataFrameDenester.flattenDataFrame(raw_df)
    val processedDf = DataFrameProcessor.dataFrameProcessor(flattenedDf)
    processedDf.show()
  }
}