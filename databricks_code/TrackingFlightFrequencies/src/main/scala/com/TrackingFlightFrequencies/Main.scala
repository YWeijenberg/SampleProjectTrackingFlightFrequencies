package com.TrackingFlightFrequencies

import com.TrackingFlightFrequencies.DataArchiving.DataFrameArchiver
import com.TrackingFlightFrequencies.Ingestion.FlightData.{ApiRequest, DataFrameDenester, JsonParser}
import com.TrackingFlightFrequencies.ProcessFlightData.DataFrameProcessor
import com.TrackingFlightFrequencies.Variables.GlobalVars
import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import com.TrackingFlightFrequencies.Variables.GlobalVars.{airport, apiHost, apiKey, date, definitionsPath, url}

object Main extends SparkSessionProvider {
  def main(args: Array[String]): Unit = {
    // Set airportIcao from databricks arguments
    GlobalVars.airportIcao = args.headOption
    GlobalVars.date = args.lift(1)
    // Get string from spi request
    val jsonString: String = ApiRequest.request(url = url, apiKey = apiKey, apiHost = apiHost, dateOption = date)
    // Parse json string into a DataFrame
    val raw_df = JsonParser.parse(jsonString)
    // Flatten and separate the arrival and departure data
    val flatDepartures = DataFrameDenester.flattenDepartures(raw_df)
    val flatArrivals = DataFrameDenester.flattenArrivals(raw_df)
    // Logs scheduled time from data ingested from ApiRequest
    println("The sheduled times from departures data ingested from ApiRequest")
    flatDepartures.select("movement_scheduledTime_local").dropDuplicates().show()
    // Archive the arrival and departure data to adls as parquet
    DataFrameArchiver.writeDataFrameToBlob(
      dataFrame = flatDepartures,
    )
    DataFrameArchiver.writeDataFrameToBlob(
      dataFrame = flatArrivals,
      isDeparture = false
    )
    // Process the flattened departures and arrivals to aggregated gold tables
    val processedDepartures = DataFrameProcessor.dataFrameProcessor(df=flatDepartures, defPath = definitionsPath)
    val processedArrivals = DataFrameProcessor.dataFrameProcessor(df=flatArrivals, isDeparture = false, defPath = definitionsPath)
    // Log processed dates
    println("The dates of the processed departures data")
    processedDepartures.select("flight_date").dropDuplicates().show
    // Write gold tables to dbfs where it is able to be queried in databricks
    processedDepartures.write
      .mode("append")
      .option("mergeSchema", "true")
      .saveAsTable(s"${airport}_departures_count")
    processedArrivals.write
      .mode("append")
      .option("mergeSchema", "true")
      .saveAsTable(s"${airport}_arrivals_count")
  }
}