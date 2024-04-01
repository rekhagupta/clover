@search
Feature: Google Search

  Scenario Outline: Search on google and validating the results
    Given I navigate to url
    Then I enter the "<keyword>" to search
    And I validate the results "<results>"
    Then I close the browser
    Examples:
    | keyword | results |
    |clover | clover  |