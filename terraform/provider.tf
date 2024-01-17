# Configure Terraform
terraform {
  required_providers {
    azurerm = {
      source = "hashicorp/azurerm"
    }
  }
}

# Configure the Microsoft Azure Provider
provider "azurerm" {
  features {}
  skip_provider_registration = true
  subscription_id            = "26f72568-1444-4d55-858f-91155ab83d3d"
}
