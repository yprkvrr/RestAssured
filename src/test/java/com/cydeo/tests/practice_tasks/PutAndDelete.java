package com.cydeo.tests.practice_tasks;

import com.cydeo.utils.HrTestBase;
import com.cydeo.utils.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
public class PutAndDelete extends HrTestBase {
    /**
     * given accept is json
     * and content type is json
     * When I send post request to "/regions/"
     * With json:
     * {
     * "region_id":200,
     * "region_name":"Test Region"
     * }
     * Then status code is 201
     * content type is json
     * When I connect to HR database and execute query "SELECT region_id, region_name FROM regions WHERE region_id = 200"
     * Theen region_name from database should match region_name from POST request

     */

    @Test
    public void task02(){
        Map<String, Object> regions = new HashMap<>();
        regions.put("region_id", 800);
        regions.put("region_name", "Test Region");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(regions)
                .when().post("/regions/");

        response.prettyPrint();

        assertThat(response.statusCode(),equalTo(201));
        assertThat(response.contentType(),equalTo("application/json"));
    }
}
