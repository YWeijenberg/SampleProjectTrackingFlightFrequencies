resource "azurerm_consumption_budget_subscription" "subscription_budget" {
  name            = "subscription_budget"
  subscription_id = data.azurerm_subscription.current.id

  amount     = 70
  time_grain = "Monthly"

  time_period {
    start_date = "2024-03-01T00:00:00Z"
    end_date   = "2025-03-01T00:00:00Z"
  }

  notification {
    enabled   = true
    threshold = 50
    operator  = "EqualTo"

    contact_roles = [
      "Owner"
    ]
  }

  notification {
    enabled   = true
    threshold = 80.0
    operator  = "EqualTo"

    contact_roles = [
      "Owner"
    ]
  }

  notification {
    enabled   = true
    threshold = 100.0
    operator  = "GreaterThanOrEqualTo"

    contact_roles = [
      "Owner"
    ]
  }
}