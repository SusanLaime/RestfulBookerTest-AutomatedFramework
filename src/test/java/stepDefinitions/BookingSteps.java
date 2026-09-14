package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.RestfulBookerEndpoints;
import entities.Booking;
import entities.BookingDates;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import util.Request;

import java.util.List;

import static org.hamcrest.Matchers.hasKey;

public class BookingSteps {
    Response response;
    String token;

    @When("I perform a GET call to the bookings endpoint with id {string}")
    public void getBooking(String id){
         response = Request.getById(RestfulBookerEndpoints.GET_BOOKING_ENDPOINT, id);
    }



    @And("I verify that the following fields are present in the root")
    public void verifyFieldsInRoot(DataTable fields){
        List<String> data = fields.transpose().asList(String.class);
        //|firstname|lastname|totalprice|depositpaid|bookingdates|additionalneeds|
        //["firstname", "lastname", "totalprice", "depositpaid", "bookingdates", "additionalneeds"]
        for(String field: data){
            response.then().body("$", hasKey(field));
        }
    }
    @And("I verify that the following fields are present inside {string} key")
    public void verifyFieldsInsideKey(String key, DataTable fields){
        List<String> data = fields.transpose().asList(String.class);
        // ['checkin', 'checkout']
        for(String field: data){
            response.then().body(key, hasKey(field));
        }
    }
    @Then("I verify that the status code is {int}")
    public void verifyStatusCode(int statusCode){
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("I verify that the following key values are in the response")
    public void verifyKeyValues(DataTable data){
        List<List<String>> keyValues = data.transpose().asLists();
        for(List<String> keyValue: keyValues){
            response.then().assertThat().body(keyValue.get(0), Matchers.equalTo(keyValue.get(1)));
        }
    }
    @And("I perform a POST call to the bookings endpoint with the following data")
    public void postBooking(DataTable body) throws JsonProcessingException {
        List<String> data = body.transpose().asList(String.class);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(data.get(4));
        bookingDates.setCheckout(data.get(5));

        Booking booking= new Booking();
        booking.setFirstname(data.get(0));
        booking.setLastname(data.get(1));
        booking.setTotalprice(Integer.parseInt(data.get(2)));
        booking.setDepositpaid(Boolean.parseBoolean(data.get(3)));
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds(data.get(6));

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);
        System.out.println(payload);
        response = Request.post(RestfulBookerEndpoints.POST_BOOKING_ENDPOINT, payload);
    }

    @And("I perform a PUT call to the booking endpoint with id {string} and the following data")
    public void putBooking(String id, DataTable body) throws JsonProcessingException {
        List<String> data = body.transpose().asList(String.class);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(data.get(4));
        bookingDates.setCheckout(data.get(5));

        Booking booking = new Booking();
        booking.setFirstname(data.get(0));
        booking.setLastname(data.get(1));
        booking.setTotalprice(Integer.parseInt(data.get(2)));
        booking.setDepositpaid(Boolean.parseBoolean(data.get(3)));
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds(data.get(6));

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);
        System.out.println(payload);
        response = Request.put(RestfulBookerEndpoints.PUT_BOOKING_ENDPOINT, id, payload, token);
        System.out.println("STATUS: " + response.getStatusCode());
        System.out.println("BODY: " + response.asString());
        System.out.println("HEADERS: " + response.getHeaders());
    }

    @And("I perform a POST call to the auth endpoint and save the token")
    public void postAuth(){
        response = Request.post(RestfulBookerEndpoints.POST_AUTH, "{\"username\" : \"admin\", \"password\" : \"password123\"}");
        token = response.jsonPath().getString("token");
    }

    @And("I perform a DELETE call to the booking endpoint with id {string}")
    public void deleteBooking(String id){
        response = Request.delete(RestfulBookerEndpoints.DELETE_BOOKING_ENDPOINT, id, token);
    }


}
