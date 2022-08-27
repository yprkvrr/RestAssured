package com.cydeo.tests.day09_post_put_delete;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class SpartanPostTest extends SpartanTestBase {

    /**
     * given accept is json and content type is json
     * and body is:
     * {
     * "gender": "Male",
     * "name": "TestPost1"
     * "phone": 1234567425
     * }
     * When I send POST request to /spartans
     * Then status code is 201
     * And content type is json
     * And "success" is "A Spartan is Born!"
     * Data name, gender , phone matches my request details
     */

    @DisplayName("POST new spartan using string json")
    @Test
    public void addNewSpartanAsJsonTest() {
        String jsonBody = "{\n" +
                "     \"gender\": \"Male\",\n" +
                "     \"name\": \"TestPost1\",\n" +
                "     \"phone\": 1234567425\n" +
                "     }";


        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/spartans");


        response.prettyPrint();
        System.out.println("status code = " + response.statusCode());
        assertThat(response.statusCode(), is(201));

        //verify header
        assertThat(response.contentType(), is("application/json"));

        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("success"), equalTo("A Spartan is Born!"));
        assertThat(jsonPath.getString("data.name"), equalTo("TestPost1"));

        assertThat(jsonPath.getString("data.gender"), equalTo("Male"));
        assertThat(jsonPath.getLong("data.phone"), equalTo(1234567425L));

        //delete the spartan after post
        int id = jsonPath.getInt("data.id");
        SpartanRestUtils.deleteSpartanById(id);


    }

    @Test
    public void addNewSpartanAsMapTest() {
        Map<String, Object> spartanMap =new HashMap<>();
        spartanMap.put("gender","Male");
        spartanMap.put("name","TestPost1");
        spartanMap.put("phone",1234567893l);

      Response response=  given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanMap)
                .when().post("/spartans");
        response.prettyPrint();
        assertThat(response.statusCode(), is(201));

        //verify header
        assertThat(response.contentType(), is("application/json"));

        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("success"), equalTo("A Spartan is Born!"));
        assertThat(jsonPath.getString("data.name"), equalTo(spartanMap.get("name")));

        assertThat(jsonPath.getString("data.gender"), equalTo(spartanMap.get("gender")));
        assertThat(jsonPath.getLong("data.phone"), equalTo(spartanMap.get("phone")));

        //delete the spartan after post
        int id = jsonPath.getInt("data.id");
        SpartanRestUtils.deleteSpartanById(id);
    }

    @Test
    public void addNewSpartanAsPojoTest(){
        Spartan spartan=new Spartan();
        spartan.setGender("Male");
        spartan.setName("TestPost1");
        spartan.setPhone(9288839045l);

        Response response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/spartans");

        response.prettyPrint();
        JsonPath jsonPath=response.jsonPath();
        assertThat(jsonPath.getString("data.name"),equalTo(spartan.getName()));
        assertThat(jsonPath.getString("data.gender"),equalTo(spartan.getGender()));
        assertThat(jsonPath.getLong("data.phone"),equalTo(spartan.getPhone()));
        int id = jsonPath.getInt("data.id");
        SpartanRestUtils.deleteSpartanById(id);

    }

}
