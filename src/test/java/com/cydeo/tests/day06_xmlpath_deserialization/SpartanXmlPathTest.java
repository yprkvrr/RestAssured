package com.cydeo.tests.day06_xmlpath_deserialization;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanXmlPathTest extends SpartanTestBase {

    /**
     * Given accept type is application/xml
     * When i send GET request to /api/spartans
     * Then status code is 200
     * And content type is XML
     * And spartan at index 0 is matching:
     * id > 107
     * name>Ezio Auditore
     * gender >Male
     * phone >7224120202
     */

    @DisplayName("GET /spartans -> xml path")
    @Test
    public void spartanXMLPathTest() {
        Response response = given().accept(ContentType.XML)
                .when().get("/spartans");

        assertEquals(200, response.statusCode());
        assertEquals("application/xml", response.contentType());

        //convert xml response body to xml path object
        XmlPath xmlPath = response.xmlPath();
        System.out.println("xmlPath.getInt(\"id\") = " + xmlPath.getInt("List.item[0].id"));

        List<String> allNames =xmlPath.getList("List.item.name");
        System.out.println(allNames.size());
    }
}
