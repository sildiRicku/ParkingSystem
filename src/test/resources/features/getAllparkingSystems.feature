Feature: Testing the getAllParkingSystems method

  Scenario: Retrieve all parking systems
    Given the parking systems exist in the database
    When I retrieve all parking systems
    Then the list of parking systems should be returned

