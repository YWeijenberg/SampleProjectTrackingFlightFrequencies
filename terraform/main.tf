locals {
  region = "West Europe"
  rg_name = "TrackingFlightFrequencies"
}

resource "azurerm_resource_group" "TrackingFlightFrequencies" {
  name     = local.rg_name
  location = local.region
}

resource "azurerm_databricks_workspace" "example" {
  name                = "TrackingFlightFrequencies_dbw"
  resource_group_name = local.rg_name
  location            = local.region
  sku                 = "trial"

  tags = {
    #Put any tags here
  }
}