import com.databricks.dbutils_v1.DBUtilsHolder.dbutils
import Ingestion.{ApiRequest,JsonParser}
//import SampleData.SampleData
import Ingestion.dataFrameDenester
import DataArchiving.DataFrameArchiver

object Main {
  def main(args: Array[String]): Unit = {
    val url = "http://api.aviationstack.com/v1/flights?dep_icao=EHAM"
    val secretScope = "keyvault-managed"
    val secretKey = "apiKey"
    val secretSas = "storageSas"
    val apiKey = dbutils.secrets.get(secretScope, secretKey)
    val sasToken = dbutils.secrets.get(secretScope, secretSas)
    val jsonString = ApiRequest.request(url = url, apiKey = apiKey)
    val storageAccountname = dbutils.secrets.get(secretScope, storageAccountname)
    val containerName = dbutils.secrets.get(secretScope,containerName)

    val raw_df = JsonParser.parse(jsonString)
    val flattenedDf = dataFrameDenester.flattenDataFrame(raw_df)
    DataFrameArchiver.writeDataFrameToBlob(
      storageAccountName = "flightfreqstgacc9724",
      containerName = "flightfreq",
      airportIcao = "EHAM",
      sasToken = "sv=2022-11-02&ss=bfqt&srt=co&sp=rwdlacupiytfx&se=2024-01-30T18:11:36Z&st=2024-01-30T10:11:36Z&sip=86.93.214.113&spr=https&sig=vKyQhTJ7kDFhekzx%2BIxP1J8TSaqnmmAIG3W6eMZPZb0%3D",
      dataFrame = flattenedDf
      )
  }
}