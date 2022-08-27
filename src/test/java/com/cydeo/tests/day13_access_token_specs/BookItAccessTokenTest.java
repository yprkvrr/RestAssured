package com.cydeo.tests.day13_access_token_specs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class BookItAccessTokenTest {
    /**
     * Given accept type is Json
     * And Query params: email = blyst6@si.edu & password = barbabaslyst
     * When I send GET request to
     * Url: https://cybertek-reservation-api-qa3.herokuapp.com/sign
     * Then status code is 200
     * And accessToken should be present and not empty
     */
    @Test
    public void tokenTest() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("email", "blyst6@si.edu")
                .and().queryParam("password", "barbabaslyst")
                .when().get("https://cybertek-reservation-api-qa3.herokuapp.com/sign");
        response.prettyPrint();
        assertThat(response.statusCode(), equalTo(200));
        String access = response.path("accessToken");
        Assertions.assertTrue(access!=null&&!access.isEmpty());

    }
}
