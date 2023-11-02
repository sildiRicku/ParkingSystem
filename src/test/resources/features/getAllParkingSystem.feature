#@getAllParkingSystems
#Feature: Testing the getAllParkingSystems method
#
#  Background:
#    Given the parking systems exist in the database
#
#  Scenario: Retrieve all parking systems
#    Given the following parking systems exist in the database:
#      | System Id        | Address         | Total Money |
#      |  1               | Main Street     | 1500        |
#      |  2               | Downtown        | 3262        |
#    When I retrieve all parking systems
#    Then the list of parking systems should be returned
