package com.cydeo.tests.practice_tasks;

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
import com.cydeo.utils.HrTestBase;

public class PostRegion extends HrTestBase {
    /**
     * given accept is json
     * and content type is json
     * When I send post request to "/regions/"
     * With json:
     * {
     * "region_id":100,
     * "region_name":"Test Region"
     * }
     * Then status code is 201
     * content type is json
     * region_id is 100
     * region_name is Test Region
     */

    /**
     * given accept is json
     * When I send post request to "/regions/100"
     * Then status code is 200
     * content type is json
     * region_id is 100
     * region_name is Test Region
     */
    @Test
    public void task01() {

        Map<String, Object> regions = new HashMap<>();
        regions.put("region_id", 100);
        regions.put("region_name", "Test Region");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(regions)
                .when().post("/regions/");

        response.prettyPrint();


    }
}
