package com.TrackingFlightFrequencies

import com.TrackingFlightFrequencies.DataArchiving.DataFrameArchiver
import com.TrackingFlightFrequencies.Ingestion.FlightData.{ApiRequest, DataFrameDenester, JsonParser}
import com.TrackingFlightFrequencies.ProcessFlightData.DataFrameProcessor
import com.TrackingFlightFrequencies.Variables.GlobalVars
import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider

object Main extends GlobalVars with SparkSessionProvider {
  def main(args: Array[String]): Unit = {

    val jsonString: String = ApiRequest.request(url = url, apiKey = apiKey, apiHost = apiHost)
    val raw_df = JsonParser.parse(jsonString)
    val flatDepartures = DataFrameDenester.flattenDepartures(raw_df)
    val flatArrivals = DataFrameDenester.flattenArrivals(raw_df)

    DataFrameArchiver.writeDataFrameToBlob(
      dataFrame = flatDepartures,
    )
    DataFrameArchiver.writeDataFrameToBlob(
      dataFrame = flatArrivals,
      isDeparture = false
    )

    val processedDepartures = DataFrameProcessor.dataFrameProcessor(df=flatDepartures, defPath = definitionsPath)
    val processedArrivals = DataFrameProcessor.dataFrameProcessor(df=flatArrivals, isDeparture = false, defPath = definitionsPath)

    processedDepartures.write
      .mode("append")
      .option("mergeSchema", "true")
      .saveAsTable(s"${airportIcao}_departures_count")
    processedArrivals.write
      .mode("append")
      .option("mergeSchema", "true")
      .saveAsTable(s"${airportIcao}_arrivals_count")
  }
}