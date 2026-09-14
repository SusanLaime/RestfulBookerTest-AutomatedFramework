Feature: POST Booking endpoints

  Background: Post bookings should allow to create new bookings

  Scenario: Post /booking should create a new booking
    Given I perform a POST call to the bookings endpoint with the following data
    |Jorge|Lopez|4500|true|2026-10-09|2026-10-11|Wifi|
    Then I verify that the status code is 200
    And I verify that the following key values are in the response
      |booking.firstname|booking.lastname |
      |Jorge            |Lopez            |
