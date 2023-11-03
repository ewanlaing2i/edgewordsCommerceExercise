Feature: Purchase items successfully

  Scenario: Applying a discount
    Given I have added an item to the basket
    When I enter a valid discount code
    Then My total is reduced by the appropriate amount

  Scenario: Confirming an order number
    Given I have added an item to the basket
    When I checkout the item
    Then The item's order number is present in my current orders