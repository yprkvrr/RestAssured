package com.cydeo.tests.day16_methodsource_mocks;

import com.cydeo.utils.BookItApiTestBase;
import com.cydeo.utils.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

public class BookItApiMethodSourceExcelTest extends BookItApiTestBase {
    @DisplayName("Get /sign -> data from BookItQa3.xlsx ")
    @ParameterizedTest
    @MethodSource("getUserCredentials")
    public void bookItAuthTest(Map<String, String> userInfo) {
        System.out.println("userInfo = " + userInfo);
        given().accept(ContentType.JSON)
                .and().queryParams(userInfo)
                .when().get("/sign")
                .then().spec(responseSpec)
                .and().body("accessToken", not(blankOrNullString()));
    }

    public static List<Map<String, String>> getUserCredentials() {
        String filePath = "src/test/resources/BookItQa3.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(filePath, "QA3");
        List<Map<String, String>> data = excelUtil.getDataList();

        return data;


    }

    @Test
    public void loginTestUsingLoop() {
        List<Map<String, String>> allData = getUserCredentials();

        for (Map<String, String> userInfo : allData) {
            System.out.println("userInfo = " + userInfo);
            try {
                given().accept(ContentType.JSON)
                        .and().queryParams(userInfo)
                        .when().get("/sign")
                        .then().spec(responseSpec)
                        .and().body("accessToken", not(blankOrNullString()));
            }catch (Throwable e){
                System.out.println("e = " + e.getMessage());
            }

        }
    }
}
