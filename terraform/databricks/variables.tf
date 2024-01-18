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

variable "key_vault_id" {
  description = "The ID of the Azure Key Vault"
  type        = string
}

variable "vault_uri" {
  description = "The DNS name of the Azure Key Vault"
  type        = string
}

variable "identity_prinicpal_id" {
  description = "The prinicpal id of the user assigned identity"
  type = string
}