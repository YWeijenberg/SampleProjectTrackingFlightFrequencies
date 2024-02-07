package com.TrackingFlightFrequencies.DataArchiving

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame

object DataFrameArchiver extends SparkSessionProvider {
  def writeDataFrameToBlob(dataFrame: DataFrame,
                           storageAccountName: String,
                           containerName: String,
                           airportIcao: String,
                           dateColumn: String = "flight_date",
                           rg_name: String) : Unit = {
    val outputDirectory = s"${rg_name}/archive/${airportIcao}"
    val outputPath =
      s"abfss://${containerName}@${storageAccountName}.dfs.core.windows.net/" +
        s"${outputDirectory}" // Specify your output directory path

    dataFrame.write.mode("overwrite").partitionBy(dateColumn).parquet(outputPath)

  }

}
