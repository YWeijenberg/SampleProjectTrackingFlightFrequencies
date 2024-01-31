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

variable "stgacc_name" {
  type        = string
  description = "Name of storage account"
}

variable "storage_sas_token" {
  type        = string
  description = "Access sas token of storage account"
}

variable "container_name" {
  type        = string
  description = "Name of the container used for blob storage"
}