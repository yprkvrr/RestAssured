package com.cydeo.tests.day14_specifications;

import com.cydeo.utils.BookItApiTestBase;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class BookItSpecTest extends BookItApiTestBase {
    /**
     * Given accept type is Json
     * And header Authorization is access token of teacher
     * When I send GET request to "/api/teachers/me
     * Then status code is 200
     * And content type is json
     * And teacher info is presented in payload
     */
    @Test
    public void teacherInfoTest() {
       Map<String,?>teacherMap= given().spec(teacherReqSpec)
                .when().get("/api/teachers/me")
                .then().spec(responseSpec).and().extract().body().as(Map.class);

        Assertions.assertEquals(1816,teacherMap.get("id"));
        Assertions.assertEquals("Barbabas",teacherMap.get("firstName"));
        Assertions.assertEquals("Lyst",teacherMap.get("lastName"));
        Assertions.assertEquals("teacher",teacherMap.get("role"));
    }

}
