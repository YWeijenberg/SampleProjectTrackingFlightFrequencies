ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "TrackingFlightFrequencies",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.4.1",
      "org.apache.spark" %% "spark-sql" % "3.4.1",
      "org.apache.hadoop" % "hadoop-azure" % "3.3.6",
      "com.lihaoyi" %% "requests" % "0.8.0",
      "com.databricks" %% "dbutils-api" % "0.0.6"
      // Add other Spark modules as needed
    )

)
