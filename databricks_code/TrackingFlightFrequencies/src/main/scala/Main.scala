import com.databricks.dbutils_v1.DBUtilsHolder.dbutils
import Ingestion.ApiRequest
import Ingestion.JsonParser
import SampleData.SampleData

object Main {
  def main(args: Array[String]): Unit = {
//    val jsonString = ApiRequest.request(url)
    val df = JsonParser.parse(SampleData.sampleData)
    df.show()
  }
}