Feature: Throw exception in case that divide by zero
  zero exception
  Scenario: divisor is zero
    Given divisor is zero
    When I want to use divider
    Then I should get IllegalArgumentException
