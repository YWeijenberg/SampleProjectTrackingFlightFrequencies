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

variable "keyvault_id" {
  description = "The ID of the Azure Key Vault"
  type        = string
}

variable "vault_uri" {
  description = "The DNS name of the Azure Key Vault"
  type        = string
}

variable "databricks_identity_principal_id" {
  type        = string
  description = "Principal ID of the user assigned identity assigned to databricks "
}

variable "stgacc_id" {
  type = string 
  description = "Id of the storage account"
}

variable "stgacc_key" {
  type = string
  description = "Access key of storage account"
}