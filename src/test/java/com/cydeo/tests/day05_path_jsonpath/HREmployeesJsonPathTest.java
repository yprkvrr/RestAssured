package com.cydeo.tests.day05_path_jsonpath;

import com.cydeo.utils.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HREmployeesJsonPathTest extends HrTestBase {
    @DisplayName("GET/ employees?limit=200 => Json path filter")
    @Test
    public void hrEmployeeJsonPath() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit", "200")
                .when().get("/employees");
        assertEquals(200, response.statusCode());
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("items[0].employee_id"));
        System.out.println(jsonPath.getInt("items[0].salary"));

        System.out.println(jsonPath.getList("items.findAll{it.salary<4000}.salary"));
        System.out.println(jsonPath.getList("items.findAll{it.departmant_id=90}.first_name"));
    }

}
