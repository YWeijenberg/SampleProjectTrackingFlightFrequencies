package com.TrackingFlightFrequencies.DataArchiving

import com.TrackingFlightFrequencies.SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame

object DataFrameArchiver extends SparkSessionProvider {
  def writeDataFrameToBlob(dataFrame: DataFrame,
                            storageAccountName: String,
                            containerName: String,
                            airportIcao: String,
                            dateColumn: String = "flight_date",
                            sasToken: String) : Unit = {
    val outputDirectory = s"archive/s${airportIcao}"
    val outputPath =
      s"wasb://${containerName}@${storageAccountName}.dfs.core.windows.net/" +
      s"${outputDirectory}" // Specify your output directory path

    spark.conf.set(s"fs.azure.account.auth.type.${storageAccountName}.dfs.core.windows.net", "SAS")
    spark.conf.set(s"fs.azure.sas.token.provider.type.${storageAccountName}.dfs.core.windows.net", "org.apache.hadoop.fs.azurebfs.sas.FixedSASTokenProvider")
    spark.conf.set(s"fs.azure.sas.fixed.token.${storageAccountName}.dfs.core.windows.net", sasToken)

    dataFrame.write.mode("overwrite").partitionBy(dateColumn).parquet(outputPath)

  }

}
