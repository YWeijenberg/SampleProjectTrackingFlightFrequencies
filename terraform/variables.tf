variable "region" {
  type        = string
  default     = "West Europe"
  description = "The Azure region where the project will be deployed."
}

variable "rg_name" {
  type        = string
  default     = "TrackingFlightFrequencies"
  description = "Name of resource group where project will be deployed."
}

variable "prefix" {
  type        = string
  default     = "FlightFreq"
  description = "Default prefix for all resources excluding rg_name."
}