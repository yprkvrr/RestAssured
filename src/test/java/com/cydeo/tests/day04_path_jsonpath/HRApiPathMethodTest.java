package com.cydeo.tests.day04_path_jsonpath;

import com.cydeo.utils.HrTestBase;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class HRApiPathMethodTest extends HrTestBase {

    @Test
    public void readCountriesUsingPathTest(){
        Response response=given().accept(ContentType.JSON)
                .when().get("countries");

        assertEquals(200,response.statusCode());
        System.out.println("response.path(\"items.country_id\") = " + response.path("items[0].country_id"));

        System.out.println("response.path(\"item.country_name[0]\") = " + response.path("items[0].country_name"));

        assertEquals("AR",response.path("items[0].country_id"));
        assertEquals("Argentina",response.path("items[0].country_name"));

        List<String >countries=response.path("items.country_name");
        System.out.println(countries);
    }
}
