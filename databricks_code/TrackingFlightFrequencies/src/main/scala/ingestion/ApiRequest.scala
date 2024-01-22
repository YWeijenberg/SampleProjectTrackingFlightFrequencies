package ingestion

import com.databricks.dbutils_v1.DBUtilsHolder.dbutils

object ApiRequest {
  private val secretScope = "keyvault-managed"
  private val secretKey = "apiKey"
  private val apiKey = dbutils.secrets.get(secretScope,secretKey)

  def request(url: String): String = {
    val r = requests.get(
      url = url,
      params = Map("access_key" -> apiKey)
    )
    if (r.statusCode == 200) r.text else s"Error: ${r.statusCode}"
  }
}