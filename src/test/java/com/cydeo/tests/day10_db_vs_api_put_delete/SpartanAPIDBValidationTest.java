package com.cydeo.tests.day10_db_vs_api_put_delete;

import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.DBUtils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanAPIDBValidationTest extends SpartanTestBase {
    /**
     * given accept is json and content type is json
     * and body is:
     * {
     * "gender": "Male",
     * "name": "PostVSDatabase"
     * "phone": 1234567425
     * }
     * When I send POST request to /spartans
     * Then status code is 201
     * And content type is json
     * And "success" is "A Spartan is Born!"
     * When I send database query
     * SELECT name, gender, phone
     * FROM spartans
     * WHERE spartan_id = newIdFrom Post request;
     * Then name, gender , phone values must match with POST request details
     */
    @DisplayName("POST /api/spartan -> then validate in DB")
    @Test
    public void postNewSpartanThenValidateInDBTest() {
        Map<String, Object> postRequestMap = new HashMap<>();
        postRequestMap.put("gender", "Male");
        postRequestMap.put("name", "PostVSDatabase");
        postRequestMap.put("phone", "1234567425");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postRequestMap)
                .when().post("/spartans");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
//        assertThat(response.statusCode(), equalTo(201));
//        assertThat(response.contentType(), equalTo("application/json"));
        assertThat(jsonPath.getString("success"), equalTo("A Spartan is Born!"));

        int spartanId = jsonPath.getInt("data.id");
        String query = "SELECT name,gender,phone FROM spartans WHERE spartan_id =" + spartanId;

        String dbUrl = ConfigurationReader.getProperty("spartan.db.url");
        String dbUserName = ConfigurationReader.getProperty("spartan.db.userName");
        String dbPassword = ConfigurationReader.getProperty("spartan.db.password");

        DBUtils.createConnection(dbUrl, dbUserName, dbPassword);
        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);
        assertThat(dbMap.get("NAME"),equalTo(postRequestMap.get("name")));
        assertThat(dbMap.get("GENDER"),equalTo(postRequestMap.get("gender")));
        assertThat(dbMap.get("PHONE"),equalTo(postRequestMap.get("phone")));
        DBUtils.destroy();
    }
}
