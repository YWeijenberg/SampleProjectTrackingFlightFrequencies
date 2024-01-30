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
    val storageAccountnameKey = "storageAccountName"
    val containerNameKey = "containerName"
    val apiKey = dbutils.secrets.get(secretScope, secretKey)
    val sasToken = dbutils.secrets.get(secretScope, secretSas)
    val jsonString = ApiRequest.request(url = url, apiKey = apiKey)
    val storageAccountname = dbutils.secrets.get(secretScope, storageAccountnameKey)
    val containerName = dbutils.secrets.get(secretScope,containerNameKey)

    val raw_df = JsonParser.parse(jsonString)
    val flattenedDf = dataFrameDenester.flattenDataFrame(raw_df)
    DataFrameArchiver.writeDataFrameToBlob(
      storageAccountName = storageAccountname,
      containerName = containerName,
      airportIcao = "EHAM",
      sasToken = sasToken,
      dataFrame = flattenedDf
      )
  }
}