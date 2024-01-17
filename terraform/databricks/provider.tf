terraform {
  required_providers {
    databricks = {
      source = "databricks/databricks"
    }
    azurerm = {
      source = "hashicorp/azurerm"
    }
  }
}

provider "azurerm" {
  features {}
  skip_provider_registration = true
  subscription_id            = "26f72568-1444-4d55-858f-91155ab83d3d"
}

provider "databricks" {
  host = "https://adb-3439348632111253.13.azuredatabricks.net/"
}