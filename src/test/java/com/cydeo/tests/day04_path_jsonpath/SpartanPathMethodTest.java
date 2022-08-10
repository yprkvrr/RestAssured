package com.cydeo.tests.day04_path_jsonpath;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPathMethodTest extends SpartanTestBase {
    /**
     * Given accept is json
     * And path param id is 13
     * When I send get request to /api/spartans/{id}
     * ----------
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */
    @DisplayName("GET/ spartan/{id} and path()")
    @Test
    public void readSpartanJson() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .when().get("/spartans/{id}");

        assertEquals(200, HttpStatus.SC_OK);
        assertEquals("application/json", response.contentType());

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"gender\") = " + response.path("gender"));
        System.out.println("response.path(\"phone\") = " + response.path("phone"));
        int spartanId = response.path("id");
        assertEquals(13, spartanId);
        assertEquals("Jaimie", response.path("name"));
        assertEquals("Female", response.path("gender"));
        assertEquals(7842554879L, (long) response.path("phone"));
    }

    /**
     * Given accept is json
     * When I send get request to /api/spartans
     * Then status code is 200
     * And content type is json
     * And I can navigate json array object
     */
    @DisplayName("GET/spartans and path()")
    @Test
    public void readSpartanJsonArray() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        System.out.println("response.path(\"id[0]\") = " + response.path("id[0]"));
        System.out.println("response.path(\"name\") = " + response.path("name[0]"));

//print last
        System.out.println("response.path(\"id[-1]\") = " + response.path("id[-1]"));
        System.out.println("response.path(\"name\") = " + response.path("name[-1]"));

        //get all ids
        List<Integer> allIds = response.path("id");
        System.out.println("allIds size= " + allIds.size());
        System.out.println("alIds= " + allIds);

        List<String> names = response.path("name");
//        for (String each:names){
//            System.out.println("Hi "+each);
//        }
        names.forEach(name-> System.out.println("Hi "+name));
    }
}
