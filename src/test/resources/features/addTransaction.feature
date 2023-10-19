Feature: Saving a Transaction for Parking System

  Scenario: Save a Transaction for a Parking System and Get Exit Time
    Given a parking system with ID 1
    And a transaction with the following details:
      | Transaction Payment Type | Entry Time          | Transaction Value | Plate Number |
      | CASH                     | 2023-10-19T08:00:00 | 10.00            | ABC-123      |
    When I save the transaction for the parking system
    And I request the exit time for the transaction
    Then the total money for the parking system should be 10.00
    And a transaction with the same details should be saved
    And the exit time should be calculated as "2023-10-19T08:10:00" # Adjust with the expected exit time
