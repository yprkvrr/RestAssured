package com.cydeo.tests.day05_path_jsonpath;

import com.cydeo.utils.SaprtanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartanJsonPathTest extends SaprtanTestBase {
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

    @Test
    public void getSpartanJsonPathTest(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",13)
                .when().get("/spartans/{id}");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        JsonPath jsonPath = response.jsonPath();

        assertEquals(13,jsonPath.getInt("id"));
        assertEquals("Jaimie",jsonPath.getString("name"));
        assertEquals("Female",jsonPath.getString("gender"));
        assertEquals("7842554879",jsonPath.getString("phone"));
    }

}