terraform {
  required_providers {
    databricks = {
      source = "databricks/databricks"
    }
    azurerm = {
      source = "hashicorp/azurerm"
    }
    random = {
      source = "hashicorp/random"
    }
  }
  backend "azurerm" {
    resource_group_name  = "tfstate"
    storage_account_name = "tfstate12880"
    container_name       = "tfstate"
    key                  = "terraform.tfstate"
  }
}


provider "databricks" {
  azure_workspace_resource_id = azurerm_databricks_workspace.databricksworkspace.id
}

# Configure the Microsoft Azure Provider
provider "azurerm" {
  features {
    key_vault {
    }
  }
  skip_provider_registration = true
  subscription_id            = var.subscriptionId
}

