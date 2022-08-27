package com.cydeo.tests.day07_deserialization;

import com.cydeo.utils.SpartanTestBase;
import org.junit.jupiter.api.Test;
import com.cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllSpartansPOJOTest extends SpartanTestBase {
    /**
     * Given accept type is json
     * when I send GET request to /spartans
     * Then status code is 200
     * And content type is json
     * And I can convert json to list of spartan pojos
     */
    @Test
    public void allSpartansToPojoTest() {
        Response response=given().accept(ContentType.JSON)
                .when().get("/spartans");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        //covert response to JsonPath
        JsonPath jsonPath=response.jsonPath();

        //using Jsonpath extract List of spartans / do deserialization
        List<Spartan>spartans=jsonPath.getList("",Spartan.class);

        System.out.println(spartans.size());
        System.out.println(spartans.get(0));

        List<Spartan> female = spartans.stream().filter(spartan -> spartan.getGender().equals("Female")).collect(Collectors.toList());
        System.out.println(female.size());
    }
}
