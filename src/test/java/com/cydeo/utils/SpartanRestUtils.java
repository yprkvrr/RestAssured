package com.cydeo.utils;

import com.cydeo.pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanRestUtils {
    static String baseUrl = ConfigurationReader.getProperty("spartan.api.url");

    public static void deleteSpartanById(int spartanId) {
        given().pathParam("id", spartanId)
                .when().delete(baseUrl + "/spartans/{id}")
                .then().log().all();
    }

    public static Spartan getNewSpartan() {
        Faker faker = new Faker();
        Spartan spartan = new Spartan();
        spartan.setName(faker.name().firstName());

        spartan.setGender("Female");
        spartan.setPhone(faker.number().numberBetween(1000000000L, 9999999999L));

        return spartan;

    }

    /**
     Method accepts spartanId and sends a GET request
     * @param spartanId
     * @return is Map object containing response json data
     */
    public static Map<String, Object> getSpartan(int spartanId) {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", spartanId)
                .when().get(baseUrl + "/spartans/{id}");

        Map<String, Object> spartanMap = response.as(Map.class);
        return spartanMap;
    }

}
