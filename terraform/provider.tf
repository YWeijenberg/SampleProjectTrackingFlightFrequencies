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


provider "databricks" {
  azure_workspace_resource_id = module.databricks_module.databrickworkspace_id

  # ARM_USE_MSI environment variable is recommended
  # azure_use_msi = true
}

# Configure the Microsoft Azure Provider
provider "azurerm" {
  features {}
  skip_provider_registration = true
  subscription_id            = var.subscriptionId
}
