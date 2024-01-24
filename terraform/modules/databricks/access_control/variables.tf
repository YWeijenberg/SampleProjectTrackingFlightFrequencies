variable "stgacc_id" {
  type        = string
  description = "Id of the storage account"
}

variable "keyvault_id" {
  description = "The ID of the Azure Key Vault"
  type        = string
}
 
variable "rg_name" {
  type        = string
  description = "The Azure region where the project will be deployed."
}

variable "region" {
  type        = string
  description = "Name of resource group where project will be deployed."
}