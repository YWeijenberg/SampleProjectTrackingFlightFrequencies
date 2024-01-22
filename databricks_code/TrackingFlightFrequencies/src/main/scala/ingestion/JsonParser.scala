package ingestion

import SparkSession.SparkSessionProvider
import org.apache.spark.sql.DataFrame

object JsonParser extends SparkSessionProvider {
//  def parse (s: String): DataFrame = {
//    val jsonRDD = spark.sparkContext.parallelize(Seq(s))
//    spark.read.schema(schema).json(jsonRDD)
//  }
}
