Feature: Saving a Transaction for a Parking System
Scenario: Save Transaction
Given a parking system with ID 3 exists in the database
And a transaction with specific details is prepared
When I save the transaction for parking system 3
Then the transaction should be successfully saved
And the total money for the parking system should be updated