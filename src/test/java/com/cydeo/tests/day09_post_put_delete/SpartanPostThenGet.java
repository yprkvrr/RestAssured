package com.cydeo.tests.day09_post_put_delete;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;

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

public class SpartanPostThenGet extends SpartanTestBase {
    Spartan newSpartan = SpartanRestUtils.getNewSpartan();

    @Test
    public void getSpartanN() {
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newSpartan)
                .when().post("/spartans");

        assertThat(response.statusCode(), is(201));
        int newSpartanId = response.jsonPath().getInt("data.id");

        Response getResponse = given().accept(ContentType.JSON)
                .and().pathParam("id", newSpartanId)
                .when().get("/spartans/{id}");
        getResponse.prettyPrint();

        Spartan spartanFromGet=getResponse.as(Spartan.class);
        assertThat(spartanFromGet.getName(),equalTo(newSpartan.getName()));
        assertThat(spartanFromGet.getGender(),equalTo(newSpartan.getGender()));
        assertThat(spartanFromGet.getPhone(),equalTo(newSpartan.getPhone()));

    }
}
