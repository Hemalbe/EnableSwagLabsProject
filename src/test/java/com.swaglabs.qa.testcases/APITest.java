package com.swaglabs.qa.testcases;

import com.swaglabs.qa.API.APIConfig;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class APITest extends APIConfig {

        @Test(priority = 1)
        void getStatus(){
            given()
                    .when()
                    .get(APIConfig.BASE_URL)
                    .then()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK");
        }

        @Test(priority = 2)
        void testStatusCodeAndStatusLineForInventory() {
            given()
                    .when()
                    .get(APIConfig.BASE_URL + APIConfig.INVENTORY_ENDPOINT)
                    .then()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK");
        }

        @Test(priority = 3)
        void testStatusCodeAndStatusLineForCart() {
            given()
                    .when()
                    .get(APIConfig.BASE_URL + APIConfig.CART_ENDPOINT)
                    .then()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK");
        }

        @Test(priority = 4)
        void testStatusCodeAndStatusLineForCheckoutStepOneBadRequest() {
            given()
                    .header("text/html; charset=utf-8", "application/json")
                    .when()
                    .get(APIConfig.BASE_URL + APIConfig.CHECKOUT_STEP_ONE_ENDPOINT)
                    .then()
                    .statusCode(400)
                    .statusLine("HTTP/1.1 400 Bad Request");
        }

        @Test(priority = 5)
        void testStatusCodeAndStatusLineForCheckoutStepTwo() {
            given()
                    .when()
                    .get(APIConfig.BASE_URL + APIConfig.CHECKOUT_STEP_TWO_ENDPOINT)
                    .then()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK");
        }

        @Test(priority = 6)
        void testStatusCodeAndStatusLineForCheckoutComplete() {
            given()
                    .when()
                    .get(APIConfig.BASE_URL + APIConfig.CHECKOUT_COMPLETE_ENDPOINT)
                    .then()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK");
        }

        @Test(priority = 7)
        void testStatusCodeAndStatusLineForNonExistentPage() {
            given()
                    .when()
                    .get(APIConfig.BASE_URL + APIConfig.NON_EXISTENT_PAGE_ENDPOINT)
                    .then()
                    .statusCode(404)
                    .statusLine("HTTP/1.1 404 Not Found");
        }

        @Test(priority = 8)
        void testStatusCodeAndStatusLineForCartNotFound() {
            given()
                    .when()
                    .get(APIConfig.BASE_URL + APIConfig.CART_NOT_FOUND_ENDPOINT)
                    .then()
                    .statusCode(404)
                    .statusLine("HTTP/1.1 404 Not Found");
        }

        @Test(priority = 11)
        void testStatusCodeAndStatusLineForSubmit() {
            given()
                    .post(APIConfig.LOCKED_OUT_USER_ENDPOINT)
                    .then()
                    .statusCode(503)
                    .statusLine("HTTP/1.1 503 Service Unavailable");
        }

    @Test(priority = 12)
    void testStatusCodeAndStatusLineForProductDisplay() {
        given()
                .when()
                .get(APIConfig.BASE_URL +APIConfig.PRODUCT_DISPLAY_ENDPOINT)
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
    }
}
