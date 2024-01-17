variable "rg_name" {
  type        = string
  description = "The Azure region where the project will be deployed."
}

variable "region" {
  type        = string
  description = "Name of resource group where project will be deployed."
}

variable "user_name" {
  default     = ""
  type        = string
  description = "Email of the user that will use the compute instance"
}