Feature: PUT Booking endpoint

  Background:
    Given I perform a POST call to the auth endpoint and save the token

    Scenario: Put /booking/{id} should update an existing booking
      Given I perform a PUT call to the booking endpoint with id "13" and the following data
        |Jane|Smith|6000|false|2026-11-01|2026-11-10|Breakfast|
      Then I verify that the status code is 200
      And I verify that the following key values are in the response
        |firstname|lastname|
        |Jane     |Smith   |
