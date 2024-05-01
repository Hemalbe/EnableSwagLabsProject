package com.swaglabs.qa.testcases;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class APIGroceryAppTest {
    private String cartId;

    @Test(priority = 1)
    void getStatus(){
        given()

                .when()
                .get("https://simple-grocery-store-api-testing.glitch.me/status")

                .then()
                .statusCode(200)
                .body("status", equalTo("UP"))
                .log().all();
    }

    @Test(priority = 2)
    void cart(){
        Response response=given()

                .when()
                .post("https://simple-grocery-store-api-testing.glitch.me/carts")
                .thenReturn();

        cartId= response.jsonPath().getString("cartId");

        System.out.println("Cart ID: " + cartId);
    }

    @Test(priority = 3, dependsOnMethods = {"cart"})
    void AddItemTOcart(){
        given()

                .when()
                .post("https://simple-grocery-store-api-testing.glitch.me/carts/{cartId}/items")
                .jsonPath().getString("4rutQ-NPd2S_h6jmD17RU");

//                .then()
//                .statusCode(201)
//                .body("status", equalTo("UP"))
//                .log().all();
    }
}
