Feature: Retrieving all parking systems

  Scenario: Retrieve a list of all parking systems
    Given the parking system repository is populated
    When the getAllParkingSystems method is called
    Then a list of parking system DTOs is returned
    And the list is sorted in ascending order by identifier
