package com.cydeo.tests.day03_parameter;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiWithParam {
    /**
     * Given accept type is Json
     * And Id parameter value is 5
     * When user sends GET request to /api/spartans/{id}
     * Then response status code should be 200
     * And response content-type: application/json
     * And "Blythe" should be in response payload(body)
     */
    String url = "http://3.93.242.50:8000/api/spartans";

    @DisplayName("GET /api/spartans/{id}")
    @Test
    public void getSingleSpartan() {
        int id = 5;
        given().accept(ContentType.JSON)
                .when().get(url + "/" + id);

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get(url + "/{id}");


        response.prettyPrint();

        System.out.println("status code: " + response.statusCode());

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        System.out.println("content type"+response.contentType());
        assertEquals("application/json",response.getContentType());
        assertTrue(response.asString().contains("Blythe"));
    }
    /**
     *  Given accept type is Json
     *         And Id parameter value is 500
     *         When user sends GET request to /api/spartans/{id}
     *         Then response status code should be 404
     *         And response content-type: application/json
     *         And "Not Found" message should be in response payload
     *
     */
    @DisplayName("GET/api")
    @Test
    public void getSingleSpartanNotFound(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(url + "/{id}"); //api/spartans/500

        System.out.println("status code = " + response.statusCode());
        assertEquals(404, response.statusCode());
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());

        assertEquals("application/json", response.contentType());
        assertEquals(ContentType.JSON.toString() , response.contentType());

        response.prettyPrint();

        //asString() method returns body of response in String format
        assertTrue(response.asString().contains("Not Found"));
        System.out.println("asString = " + response.asString().toUpperCase());
    }




}
