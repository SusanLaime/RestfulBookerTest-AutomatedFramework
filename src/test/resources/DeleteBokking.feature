@run
Feature: Dele bookings endpoint

  Background:
    Given I perform a POST call to the auth endpoint and save the token

    Scenario: Delete booking should delete a booking
      Given I perform a DELETE call to the booking endpoint with id "8"
      Then I verify that the status code is 200


    Scenario: Delete booking should return 404 when provided id does not exist
      Given I perform a DELETE call to the booking endpoint with id "invalid"
      Then I verify that the status code is 404