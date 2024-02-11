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

variable "subnet_ids" {
  type        = list(string)
  description = "Ids of subnets in virtual network"
}

variable "ip_rules" {
  type        = list(string)
  description = "List of public IP ranges that can access"
}

variable "source_airport_definitions" {
  type = string
}

variable "random_id" {
  type = string
}

variable "storage_sas_start" {
  type = string
}

variable "storage_sas_end" {
  type = string
}