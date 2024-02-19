package com.TrackingFlightFrequencies.DataArchiving

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame
import com.TrackingFlightFrequencies.Variables.GlobalVars

object DataFrameArchiver extends SparkSessionProvider with GlobalVars {
  def writeDataFrameToBlob(dataFrame: DataFrame,
                           isDeparture: Boolean = true) : Unit = {

    val outputDirectory = s"archive/${airportIcao}/${if (isDeparture) "departures" else "arrivals"}"
    val outputPath =
      s"abfss://${containerName}@${storageAccountname}.dfs.core.windows.net/" +
        s"${outputDirectory}"

    dataFrame.write.mode("append").parquet(outputPath)
  }
}
