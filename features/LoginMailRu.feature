Feature: Test e-mail page Mail.ru

  Scenario Outline: Test positive login page
    Given I am on a main application page
    When I login as correct user
    Then I see user e-mail link
    Given I am on a inbox application page
    When I select "<number>" letters
    Then I see selected letters
    Examples:
      | number |
      | 2
