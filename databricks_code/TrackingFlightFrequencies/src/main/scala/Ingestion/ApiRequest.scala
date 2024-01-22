package Ingestion

import com.databricks.dbutils_v1.DBUtilsHolder.dbutils

object ApiRequest {

  def request(url: String, apiKey: String): String = {
    val r = requests.get(
      url = url,
      params = Map("access_key" -> apiKey)
    )
    if (r.statusCode == 200) r.text else s"Error: ${r.statusCode}"
  }
}