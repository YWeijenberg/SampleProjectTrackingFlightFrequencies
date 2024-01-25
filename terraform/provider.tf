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
  azure_workspace_resource_id = azurerm_databricks_workspace.databricksworkspace.id
}

# Configure the Microsoft Azure Provider
provider "azurerm" {
  features {
    key_vault {
      recover_soft_deleted_key_vaults = false 
      recover_soft_deleted_secrets = false
    }
  }
  skip_provider_registration = true
  subscription_id            = var.subscriptionId
}
