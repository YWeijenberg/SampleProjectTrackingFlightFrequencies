import com.databricks.dbutils_v1.DBUtilsHolder.dbutils
import Ingestion.{ApiRequest,JsonParser}
import SampleData.SampleData
import Ingestion.dataFrameDenester

object Main {
  def main(args: Array[String]): Unit = {
//    val secretScope = "keyvault-managed"
//    val secretKey = "apiKey"
//    val apiKey = dbutils.secrets.get(secretScope, secretKey)
//    val jsonString = ApiRequest.request(url = url, apiKey = apiKey)
    val raw_df = JsonParser.parse(SampleData.sampleData)
    val flattenedDf = dataFrameDenester.flattenDataFrame(raw_df)
    flattenedDf.show()
  }
}