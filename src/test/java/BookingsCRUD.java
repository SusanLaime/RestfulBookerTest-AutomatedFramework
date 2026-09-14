import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.RestfulBookerEndpoints;
import entities.Booking;
import entities.BookingDates;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import util.Request;

import static org.hamcrest.Matchers.hasKey;

public class BookingsCRUD {

    @Test
    public void getBookingTest(){

        Response response = Request.getById(RestfulBookerEndpoints.GET_BOOKING_ENDPOINT, "5");
        response.then().assertThat().statusCode(200);
        response.then().log().body();

        response.then().body("$", hasKey("firstname"));
        response.then().body("$", hasKey("lastname"));
        response.then().body("$", hasKey("totalprice"));
        response.then().body("$", hasKey("depositpaid"));
        response.then().body("$", hasKey("bookingdates"));
        response.then().body("bookingdates", hasKey("checkin"));
//        response.then().body("bookingdates", hasKey("checkout"));
        response.then().body("$", hasKey("additionalneeds"));


        response.then().assertThat().body("firstname", Matchers.equalTo("Mary"));

    }

    @Test
    public void postBookingTest() throws JsonProcessingException {
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2026-10-01");
        bookingDates.setCheckout("2026-10-05");

        Booking booking= new Booking();
        booking.setFirstname("Mauricio");
        booking.setLastname("Viscarra");
        booking.setTotalprice(5000);
        booking.setDepositpaid(true);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Wifi");

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);
        System.out.println(payload);

        Response response = Request.post(RestfulBookerEndpoints.POST_BOOKING_ENDPOINT, payload);

        response.then().assertThat().statusCode(200);
        response.then().log().body();

        response.then().assertThat().body("booking.firstname", Matchers.equalTo(booking.getFirstname()));
    }

    @Test
    public void deleteBookingTest(){
        Response response = Request.post(RestfulBookerEndpoints.POST_AUTH, "{\"username\" : \"admin\", \"password\" : \"password123\"}");
        String token = response.jsonPath().getString("token");
        System.out.println(token);

        Response responseDelete = Request.delete(RestfulBookerEndpoints.DELETE_BOOKING_ENDPOINT, "3", token);
        responseDelete.then().log().body();
        responseDelete.then().assertThat().statusCode(200);
    }

}
