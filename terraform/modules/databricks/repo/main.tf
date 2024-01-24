# Create a git credentials resource for connecting to a databricks repository
resource "databricks_git_credential" "git_credentials" {
  git_provider          = "gitHub"
  git_username          = data.azurerm_key_vault_secret.gitUserName.value
  personal_access_token = data.azurerm_key_vault_secret.gitPat.value
}

# Add integration with github repo in databricks workspace
resource "databricks_repo" "repository" {
  url    = data.azurerm_key_vault_secret.urlRepository.value
  branch = "dev"
}