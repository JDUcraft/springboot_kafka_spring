Feature: Belly

  Scenario: a few cukes
    Given I have 42 cukes in my belly
    When I wait 1 hour
    Then my belly should growl

  @US27330
  Scenario: a few cukes
    Given I have 21 cukes in my belly
    When I wait 7 hour
    Then my belly should growl

  Scenario: a few more
    Given my belly should growl