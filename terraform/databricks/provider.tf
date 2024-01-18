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
  azure_workspace_resource_id = azurerm_databricks_workspace.databricksworkspace.id

  # ARM_USE_MSI environment variable is recommended
  # azure_use_msi = true
}