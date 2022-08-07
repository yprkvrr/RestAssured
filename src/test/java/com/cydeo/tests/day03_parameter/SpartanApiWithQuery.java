package com.cydeo.tests.day03_parameter;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartanApiWithQuery {
    /**
        Given Accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    String url="http://3.93.242.50:8000/api/spartans";

    @Test
    public void query(){
       Response response= given().log().all().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","e")
                .when().get(url);
       assertEquals(response.statusCode(),200);
       assertTrue(response.contentType().equals("application/json"));
      assertTrue(response.asString().contains("Female"));
      assertTrue(response.asString().contains("Janette"));
    }
}
