resource "azurerm_databricks_workspace" "example" {
  name                = "TrackingFlightFrequencies_dbw"
  resource_group_name = var.rg_name
  location            = var.region
  sku                 = "trial"

  tags = {
    #Put any tags here
  }
}