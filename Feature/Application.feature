Feature: Get the data from supermarket

  Scenario: Get all the super market search details
    Given Send a GET request to the search endpoint
    When I get a response from the server
    Then The Response should contain the status code
    And Then Validate the status body
