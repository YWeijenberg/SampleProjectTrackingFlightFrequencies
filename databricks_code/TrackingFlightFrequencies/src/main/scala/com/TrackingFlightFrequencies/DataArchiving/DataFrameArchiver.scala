package com.TrackingFlightFrequencies.DataArchiving

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame
import com.TrackingFlightFrequencies.Variables.GlobalVars
import com.TrackingFlightFrequencies.Variables.GlobalVars.{airportIcao, containerName, storageAccountname}

object DataFrameArchiver extends SparkSessionProvider {
  def writeDataFrameToBlob(dataFrame: DataFrame,
                           isDeparture: Boolean = true) : Unit = {
    // Definition of the outpuDirectory
    // Note that this is adjusted for isDeparture
    val outputDirectory = s"archive/${airportIcao}/${if (isDeparture) "departures" else "arrivals"}"

    // Define the full output path including outputDirectory
    val outputPath =
      s"abfss://${containerName}@${storageAccountname}.dfs.core.windows.net/" +
        s"${outputDirectory}"

    // Write dataframe to adls
    dataFrame.write.mode("append").parquet(outputPath)
  }
}
