Feature: HealthInsurance
  Scenario: Collect HealthInsurance Products
    Given user opens policy bazaar website
    When user hover on insurance products
    Then user should be able to see all health insurance product
    Then close the browser