package Ingestion

import SparkSession.SparkSessionProvider
import Ingestion.JsonSchema.jsonSchema
import org.apache.spark.sql.DataFrame

object JsonParser extends SparkSessionProvider {
  import spark.implicits._
  def parse (s: String): DataFrame = {
    spark.read.schema(jsonSchema).json(Seq(s).toDS)
  }
}
