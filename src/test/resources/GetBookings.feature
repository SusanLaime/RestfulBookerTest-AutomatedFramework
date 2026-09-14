Feature: GET Booking endpoints

  Background: Get bookings should allow to get bookings information

    Scenario: Get /booking/{id} should return an specific booking
      Given I perform a GET call to the bookings endpoint with id "119"
      Then I verify that the status code is 200
      And I verify that the following fields are present in the root
        |firstname|lastname|totalprice|depositpaid|bookingdates|additionalneeds|
      And I verify that the following fields are present inside "bookingdates" key
        |checkin|checkout|
      And I verify that the following key values are in the response
        |firstname|lastname |
        |John     |Smith    |
