package com.cydeo.tests.day15_data_driven_testing;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;

public class CsvSourceDDT {
    @ParameterizedTest
    @CsvSource({"7,5,12",
            "3,99,102",
            "32,44,76",
            "38,41,79"})
    public void basicAddTest(int num1, int num2, int expSum) {
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("expSum = " + expSum);

        int sum = num1 + num2;
        System.out.println("sum = " + sum);
        assertThat(sum, equalTo(expSum));
    }

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @ParameterizedTest
    @CsvSource({"New York City,NY",
            "Denver,CO",
            "Boston,MA",
            "East Pittsburgh,PA",
            "Raleigh,NC",
            "San Diego,CA",
            "Baltimore,MD"})
    public void cityAndStateZipCodeAPITest(String city, String state) {
        Map<String, String> cities = new HashMap<>();
        cities.put("state",state);
        cities.put("city",city);

        given().accept(ContentType.JSON)
                .and().pathParams(cities)
                .when().get("/us/{state}/{city}")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("'place name'",equalTo(city)).log().all();
    }
}
