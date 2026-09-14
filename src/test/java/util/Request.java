package util;

import constants.RestfulBookerEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Request {

    public static Response get(String endpoint){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.when().get(endpoint);
        return response;
    }

    public static Response getById(String endpoint, String id){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.given().pathParam("id", id)
                .when().get(endpoint);
        return response;
    }

    public static Response post(String endpoint, String payload){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.given().contentType("application/json").accept("application/json").body(payload)
                .when().post(endpoint);
        return response;
    }

    /*public static Response put(String endpoint, String id, String payload, String token){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .and().cookie("token", token)
                .and().pathParam("id", id)
                .when().put(endpoint);
        return response;
    }*/
    public static Response put(String endpoint, String id, String payload, String token){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;

        return RestAssured.given()
                .contentType("application/json")
                .cookie("token", token)
                .pathParam("id", id)
                .body(payload)
                .when()
                .put(endpoint);
    }

    public static Response delete(String endpoint, String id, String token){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.given().cookie("token", token).pathParam("id", id)
                .when().delete(endpoint);
        return response;
    }
}
