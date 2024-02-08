resource "databricks_job" "flightfreq_pipeline" {
  name        = "Flight Frequencies Pipeline"
  description = "Job for running the .jar that executes the pipeline for getting flight frequencies into databricks"

  job_cluster {
    job_cluster_key = "j"
    new_cluster {
      num_workers    = 0
      spark_version  = data.databricks_spark_version.latest_lts.id
      node_type_id   = data.databricks_node_type.smallest.id
      runtime_engine = "STANDARD"

      spark_conf = {
        "spark.databricks.cluster.profile" = "singleNode"
        "spark.master"                     = "local[*]"
      }
      custom_tags = {
        "ResourceClass" = "SingleNode"
      }
    }
  }
  task {
    task_key = "a"

    sql_task {
      warehouse_id = var.sql_warehouse_id
      query {
        query_id = var.create_table_query_id
      }
    }
  }
}