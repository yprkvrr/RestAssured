package com.cydeo.tests.day15_data_driven_testing;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.baseURI;

public class CsvFileSources {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ZipCode.csv",numLinesToSkip = 1)
    public void zipCodeTest(String state, String city, int zipCount){
        Map<String, String> cities = new HashMap<>();
        cities.put("state",state);
        cities.put("city",city);
given().accept(ContentType.JSON)
        .and().pathParams(cities)
        .when().get("/us/{state}/{city}")
        .then().assertThat().statusCode(200)
        .and().body("places",hasSize(zipCount));

    }
}
