package com.cydeo.tests.practice_tasks;

import com.cydeo.utils.HrTestBase;
import com.cydeo.utils.SpartanRestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonParamPractice extends HrTestBase {
    /**
     * Q1:
     * - Given accept type is Json
     * - Path param value- US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is 2
     */

    @Test
    public void test01() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country", "US")
                .when().get("/countries/{country}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        JsonPath jsonPath = response.jsonPath();
        assertEquals("US", jsonPath.getString("country_id"));
        assertEquals("United States of America", jsonPath.getString("country_name"));
        assertEquals("2", jsonPath.getString("region_id"));
    }

    /**
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */

    @Test
    public void test02() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();
        List<String> allJobs = jsonPath.getList("items.job_id");
        System.out.println(allJobs);
        for (String each : allJobs) {
            assertTrue(each.startsWith("SA"));
        }
        List<Integer> departmentId = jsonPath.getList("items.department_id");
        for (int each : departmentId) {
            assertTrue(each == 80);
        }
        assertEquals(25, allJobs.size());
    }

    /**
     * - Given accept type is Json
     * -Query param value q= region_id 3
     * - When users sends request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore
     */
    @Test
    public void test03() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();
        List<Integer> regionId = jsonPath.getList("items.region_id");
        for (int each : regionId) {
            assertEquals(3, each);
        }
        assertEquals("6", jsonPath.getString("count"));

        assertTrue(jsonPath.getString("hasMore").equals("false"));
        List<String> country = jsonPath.getList("items.country_name");
        System.out.println(country);
        List<String> requiredCountries =new ArrayList<>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));
        assertTrue(requiredCountries.equals(country));


    }


}
