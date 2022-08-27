package com.cydeo.tests.day14_specifications;

import com.cydeo.utils.SpartanSecureTestBase;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanSpecTest extends SpartanSecureTestBase {

//    RequestSpecification requestSpec = given().accept(ContentType.JSON)
//            .and().auth().basic("admin", "admin");
ResponseSpecification responseSpec=expect().statusCode(200).
        and().contentType(ContentType.JSON);
    @Test
    public void allSpartansTest() {
//        given().accept(ContentType.JSON)
//                .and().auth().basic("admin", "admin")
        given().spec(requestSpec)
                .when().get("/spartans")
                .then().spec(responseSpec).log().all();
    }

    @Test
    public void singleSpartansTest() {
        given().spec(requestSpec)
                .and().pathParam("id", 15)
                .when().get("/spartans/{id}")
                .then().spec(responseSpec).log().all();
    }
}
