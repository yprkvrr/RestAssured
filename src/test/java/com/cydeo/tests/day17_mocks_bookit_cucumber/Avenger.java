package com.cydeo.tests.day17_mocks_bookit_cucumber;

import com.cydeo.utils.BookTestBase;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class Avenger extends BookTestBase {


    @Test
    public void dukeTest() {
        String token = BookTestBase.getAccessToken(ConfigurationReader.getProperty("teacher_email"), ConfigurationReader.getProperty("teacher_password"));
        Map<String, Object> authorization = given().accept(ContentType.JSON)
                .header("Authorization", token)
                .when().get("/api/rooms/duke")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .log().all().extract().as(Map.class);

assertThat(authorization.get("id"),is(116));
assertThat(authorization.get("name"),is("duke"));
assertThat(authorization.get("capacity"),is(4));
assertThat(authorization.get("withTV"),is(true));
assertThat(authorization.get("withWhiteBoard"),is(false));
    }
}
