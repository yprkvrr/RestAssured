package com.cydeo.tests.day08_hamcrest;

import com.cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cydeo.utils.SpartanTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class SpartanHamcrestTest extends SpartanTestBase {
    /**
     * given accept type is json
     * and path id is 24
     * When i send get request to /spartans/{id}
     * then status code is 200
     * and content type is application/json
     * and id" is 24,
     *     "name" is "Julio",
     *     "gender" is "Male",
     *     "phone" is 9393139934
     */
    @Test
    public void spartanHamcrest(){
        given().accept(ContentType.JSON)
                .pathParam("id",24)
                .when().get("/spartans/{id}")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("id",is(24))
                .and().assertThat().body("name",is("Julio"))
                .and().assertThat().body("gender",is("Male"))
                .and().assertThat().body("phone",is(9393139934l));
    }

    /**
     Given accept type is json
     And query param nameContains value is "e"
     And query param gender value is "Female"
     When I send get request to /spartans/search
     Then status code is 200
     And content type is Json
     And json response data is as expected
     totalElement is 34
     has ids: 94, 98,91, 81
     has names: Jocelin, Georgianne, Catie, Marylee,Elita
     every gender is Female
     every name contains e
     */
    @DisplayName("GET /spartans/search -> hamcrest assertion")
    @Test
    public void searchTest(){
        given().accept(ContentType.JSON)
                .and().queryParam("nameContains","e")
                .and().queryParam("gender","Female")
                .when().get("/spartans/search")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().header("Date",containsString("2022"))
                .and().assertThat().body("totalElement",is(34))
                .and().assertThat().body("content.id",hasItems(94, 98,91, 81))
                .and().assertThat().body("content.name",hasItems("Jocelin", "Georgianne", "Catie", "Marylee","Elita"),"content.gender",everyItem(is("Female")),"content.name",everyItem(containsStringIgnoringCase("e")));
    }
}
