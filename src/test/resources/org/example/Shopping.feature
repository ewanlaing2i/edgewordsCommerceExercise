Feature: Purchase items successfully


  Scenario Outline: Applying a discount
    Given I have added a Beanie to the basket
    When I enter the discount code "<couponCode>"
    Then My total is reduced by "<discountAmount>"%

  Examples:
    |couponCode|discountAmount|
    |edgewords |15            |

  Scenario: Confirming an order number
    Given I have added a Beanie to the basket
    When I checkout the item
    Then I am given an order number
    Then The item's order number is present in my current orders