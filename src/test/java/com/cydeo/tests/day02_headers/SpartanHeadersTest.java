package com.cydeo.tests.day02_headers;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanHeadersTest {
    /**
     * • When I send a GET request to
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And I Should see all Spartans data in JSON format
     */
    String url = "http://3.93.242.50:8000/api/spartans";

    @DisplayName("GET /api/spartans and expect Json response")
    @Test
    public void getAllSpartanHeadersTest() {
        when().get(url)          //request part
                .then().statusCode(200)
                .and().contentType("application/json");

        // .and().contentType(ContentType.JSON); --> SAME ABOVE

    }

    /**
     * Given Accept type is application/xml
     * • When I send a GET request to
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And I Should see all Spartans data in xml format
     */
    @Test
    @DisplayName("GET /api/spartans with req header")
    public void acceptTypeHeaderTest() {
        given().accept(ContentType.XML)

                .when().get(url)
                .then().statusCode(200)
                .and().contentType(ContentType.XML);
    }

    /**
     * Given Accept type is application/json
     * • When I send a GET request to
     * -----------------------------
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And read headers
     */
    @DisplayName("GET /api/spartans - read headers")
    @Test
    public void readResponseHeaders() {
        Response response = given().accept(ContentType.JSON)
                .when().get(url);

        System.out.println("Date header= " + response.getHeader("Date"));

        System.out.println("Content Type= " + response.getHeader("Content-Type"));

        System.out.println("Connection= " + response.getHeader("Connection"));

//        for (Header header : response.getHeaders()) {
//            System.out.println(header);
//        }

        Headers headers=response.getHeaders();
        System.out.println(headers);


        //ensure header Keep-Alive is present
        assertTrue(response.getHeader("Keep-Alive") != null);

    }
}
