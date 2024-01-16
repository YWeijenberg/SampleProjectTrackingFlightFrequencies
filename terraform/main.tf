resource "azurerm_resource_group" "TrackingFlightFrequencies" {
  name     = var.rg_name
  location = var.region
}

module "databricks_module" {
  source = "./databricks"
  
  # Pass variables to module
  rg_name = var.rg_name
  region = var.region
}