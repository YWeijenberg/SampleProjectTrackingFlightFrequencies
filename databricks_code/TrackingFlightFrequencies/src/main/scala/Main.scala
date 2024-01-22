import com.databricks.dbutils_v1.DBUtilsHolder.dbutils
import Ingestion.ApiRequest
import Ingestion.JsonParser
import SampleData.SampleData
import Ingestion.dataFrameDenester

object Main {
  def main(args: Array[String]): Unit = {
//    val jsonString = ApiRequest.request(url)
    val raw_df = JsonParser.parse(SampleData.sampleData)
    val flattenedDf = dataFrameDenester.flattenDataFrame(raw_df)
    flattenedDf.show()
  }
}