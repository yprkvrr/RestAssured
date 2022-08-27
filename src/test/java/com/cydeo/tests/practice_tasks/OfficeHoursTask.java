package com.cydeo.tests.practice_tasks;

import com.cydeo.utils.HrTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;

public class OfficeHoursTask extends HrTestBase {
    @Test
    public void task01() {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("region_id", 500);
        requestBody.put("region_name", "testing");

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody).log().body()
                .when().post("/regions/").prettyPeek()
                .then().statusCode(201)
                .contentType(ContentType.JSON)
                .body("region_id", is(500))
                .body("region_name", is("testing"));

    }
}
