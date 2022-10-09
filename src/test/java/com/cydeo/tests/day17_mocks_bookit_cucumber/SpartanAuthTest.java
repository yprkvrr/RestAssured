package com.cydeo.tests.day17_mocks_bookit_cucumber;

import com.cydeo.utils.SpartanSecureOfficeHours;
import com.cydeo.utils.SpartanSecureTestBase;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SpartanAuthTest extends SpartanSecureOfficeHours {
//OFFICE HOUR

    @Test
    public void testPublicUser() {
        given().accept(ContentType.JSON)
                .get("/spartans").prettyPeek()
                .then().log().ifValidationFails()
                .statusCode(401);
    }

    @Test
    public void testAuthenticatedUser() {
        given().accept(ContentType.JSON)
                .auth().basic("user", "user")
                .get("/spartans").prettyPeek()
                .then().log().ifValidationFails()
                .statusCode(200).and().contentType(ContentType.JSON);

    }

    @Test
    public void testEditorDelete() {
        given().accept(ContentType.JSON)
                .pathParam("id", 98)
                .auth().basic("editor", "editor")
                .delete("/spartans/{id}").prettyPeek()
                .then().log().ifValidationFails()
                .statusCode(403);
    }


    @ParameterizedTest
    @ValueSource(strings = {"admin", "editor", "user"})
    public void testAllUsersGET(String role) {
        given().accept(ContentType.JSON)
                .auth().basic(role,role)
                .get("/spartans").prettyPeek().then().log().ifValidationFails()
                .statusCode(200);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/GETSpartans.csv",numLinesToSkip = 1)
    public void testAllUsersCSV(String username,String password){
        given().accept(ContentType.JSON)
                .auth().basic(username,password)
                .get("/spartans").prettyPeek().then().log().ifValidationFails()
                .statusCode(200);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/DeleteSpartans.csv",numLinesToSkip = 1)
    public void deleteSpartanTestAllUsersCSV(String username,String password,int id,int statusCode){
        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .auth().basic(username,password)
                .delete("/spartans/{id}").prettyPeek().then().log().ifValidationFails()
                .statusCode(statusCode);
    }
}
