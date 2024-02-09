package com.TrackingFlightFrequencies

import com.TrackingFlightFrequencies.DataArchiving.DataFrameArchiver
import com.TrackingFlightFrequencies.Ingestion.FlightData.{ApiRequest, DataFrameDenester, JsonParser}
import com.TrackingFlightFrequencies.ProcessFlightData.DataFrameProcessor
import com.TrackingFlightFrequencies.SampleData.SampleData.sampleData
import com.TrackingFlightFrequencies.Variables.GlobalVars
import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider

object Main extends GlobalVars with SparkSessionProvider {
  def main(args: Array[String]): Unit = {
    // Code for Databricks Environment .jar
//    val jsonString: String = ApiRequest.request(url = url, apiKey = apiKey, apiHost = apiHost)
    val jsonString: String = sampleData
    val raw_df = JsonParser.parse(jsonString)
    val flatDepartures = DataFrameDenester.flattenDepartures(raw_df)
    val flatArrivals = DataFrameDenester.flattenArrivals(raw_df)
    DataFrameArchiver.writeDataFrameToBlob(
      storageAccountName = storageAccountname,
      containerName = containerName,
      airportIcao = airportIcao,
      dataFrame = flattenedDf,
      rg_name = rg_name
    )
    val processedDf = DataFrameProcessor.dataFrameProcessor(df=flattenedDf, defPath = definitionsPath)

    // Code for Local Environment Testing
//    val jsonString = sampleData
//    val raw_df = JsonParser.parse(jsonString)
//    val flattenedDf = DataFrameDenester.flattenDataFrame(raw_df)
//    val processedDf = DataFrameProcessor.dataFrameProcessor(df = flattenedDf, definitionsPath = definitionsPath)

    processedDf.write
      .mode("append")
      .option("mergeSchema","true")
      .saveAsTable(s"departures_count_${airportIcao}")
  }
}