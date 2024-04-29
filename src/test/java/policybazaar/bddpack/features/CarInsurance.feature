Feature: HealthInsurance
  Scenario: Validating car number input field
    Given open policy bazaar website
    And user navigates to car insurance option
    When user search for car instance without car number
    Then error message should be displayed
    When user search with invalid car number
    Then search should not proceed and error message should be displayed
    When user search with valid car number
    Then search should proceed and no error message should be displayed
    Then car insurance teardown

  Scenario: Validating Car Insurance form details
    Given open policy bazaar website
    And user navigates to car insurance option
    When user search with valid car number
    Then search should proceed and no error message should be displayed
    When user search without filling any details
    Then user should get get error message for all fields
    When user search with invalid name
    Then user should get error message for invalid name input
    When user searches with invalid email
    Then user should get error message for invalid email input
    When user searches with invalid number
    Then user should get error message for invalid number input
    When user search with valid details
    Then no error message should be displayed
    Then car insurance teardown
