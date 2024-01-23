variable "prefix" {
  type        = string
  description = "Default prefix for all resources excluding rg_name."
}

variable "rg_name" {
  type        = string
  description = "The Azure region where the project will be deployed."
}

variable "region" {
  type        = string
  description = "Name of resource group where project will be deployed."
}
