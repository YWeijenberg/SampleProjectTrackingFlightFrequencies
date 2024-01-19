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
  subscription_id            = data.azurerm_key_vault_secret.subscriptionId
}
