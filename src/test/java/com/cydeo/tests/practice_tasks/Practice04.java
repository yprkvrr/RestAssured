package com.cydeo.tests.practice_tasks;

import com.cydeo.utils.TypiCodeTestBase;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Practice04 extends TypiCodeTestBase {


    @Test
    public void verifyContentAndStatus() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/posts");
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
    }

    /**
     * - Given accept type is Json
     * - Path param "id" value is 1
     * - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
     * - Then status code is 200
     * <p>
     * -And json body contains "repellat provident"
     * - And header Content - Type is Json
     * - And header "X-Powered-By" value is "Express"
     * - And header "X-Ratelimit-Limit" value is 1000
     * - And header "Age" value is more than 100
     * <p>
     * - And header "NEL" value contains "success_fraction"
     */
    @Test
    public void verifyHeaders() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 1)
                .when().get("/{id}");
        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertTrue(response.asString().contains("repellat provident"));
        assertEquals("application/json; charset=utf-8", response.contentType());
        assertEquals("Express", response.getHeader("X-Powered-By"));
        assertEquals("1000", response.getHeader("X-Ratelimit-Limit"));
        assertTrue(Integer.parseInt(response.getHeader("Age")) > 100);
        assertTrue(response.getHeader("NEL").contains("success_fraction"));
    }
    /**
     * - Given accept type is Json
     * - Path param "id" value is 12345
     * - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
     * - Then status code is 404
     *
     * - And json body contains "{}"
     */

    @Test
    public void clientError(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",12345)
                .when().get("/{id}");
        assertEquals(404,response.statusCode());
        assertTrue(response.asString().contains("{}"));
    }
}
