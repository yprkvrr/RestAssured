package com.cydeo.tests.day01_intro;

import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import javax.annotation.meta.When;

import static org.junit.jupiter.api.Assertions.*;

public class ReqRestApi {
    /**
     * When User sends GET Request to
     * https://reqres.in/api/users
     * <p>
     * Then Response status code should be 200
     * And Response body should contain "George"
     * And Header Content type should be json
     */

    String url = "https://reqres.in/api/users";

    @DisplayName("GET all users")
    @Test

    public void userGetTest() {
        Response response = when().get(url);

        int statusCode = response.statusCode();

        Assertions.assertEquals(200, statusCode);

        response.then().statusCode(200);
        response.then().assertThat().statusCode(200);

        Assertions.assertTrue(response.asString().contains("George"));
        System.out.println(response.contentType());
        Assertions.assertTrue(response.contentType().contains("json"));

    }

    /**
     * When User Sends get request to API Endpoint:
     * "https://reqres.in/api/users/5"
     * Then Response status code should be 200
     * And Response body should contain user info "Charles"
     */

    @Test
    public void getSingleUserApiTest() {

        Response response=when().get(url+"/5");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertTrue(response.asString().contains("Charles"));
    }

    /**When Send get request to API Endpoint:
            "https://reqres.in/api/users/50"
    Then Response status code should be 404
    And Response body should contain "{}"
*/
    @Test
    public void getSingleUserNegativeApiTest() {
        Response response=when().get(url+"/50");
        Assertions.assertEquals(404,response.statusCode());
        Assertions.assertTrue(response.asString().contains("{}"));
    }
}
