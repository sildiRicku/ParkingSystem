Feature: Parking System Service Functionality

  Background:
    Given a parking system exists in the database

  Scenario: Retrieve Rules for Parking System
    When I retrieve rules for the parking system with ID 2
    Then a list of rules should be returned
