package com.cydeo.tests.day07_deserialization;

import com.cydeo.pojo.Spartan;
import com.cydeo.pojo.SpartanSearch;
import org.junit.jupiter.api.Test;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.*;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanSearchPojoTest extends SpartanTestBase {
    /**
     * Given accept type is json
     * And query param nameContains value is "e"
     * And query param gender value is "Female"
     * When I send get request to /spartans/search
     * Then status code is 200
     * And content type is Json
     * And json response data is as expected
     */

    @Test
    public void spartanSearchPojoTest() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/spartans/search");



        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        //deserialization json to SpartanSearch pojo class

        SpartanSearch spartanSearch = response.as(SpartanSearch.class);

        System.out.println(spartanSearch.getTotalElement());
      //  System.out.println(spartanSearch.getContent());  //-->all spartans info
        System.out.println(spartanSearch.getContent().get(0));

        Spartan secondSpartan=spartanSearch.getContent().get(1);
        System.out.println(secondSpartan);
        System.out.println(secondSpartan.getId());
    }
}
