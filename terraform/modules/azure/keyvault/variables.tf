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

variable "secrets" {
  type        = map(string)
  description = "Map of secrets that should be declared in the key vault"
}

variable "EntraIDUsername" {
  type        = string
  description = "Entra ID username of the user logged into CLI"
}