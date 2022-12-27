package com.bestbuy.testsuite;


import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProductAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200); //method type of this is validatable response

    }

    @Test
    public void test11() {
    // 11. Verify if the total is equal to 5195
        response.body("total", equalTo(51957));

    }

    @Test
    public void test12() {
    // 12. Verify if the stores of limit is equal to 10
        response.body("limit", equalTo(10));

    }

    @Test
    public void test13() {
    //13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));

    }

    @Test
    public void test14() {
    //14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
        response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"));
    }

    @Test
    public void test15() {
    //15. Verify the productId=150115 inside categories of the third name is “Household Batteries”
        response.body("data[3].categories[2].name",equalTo("Household Batteries"));
    }

    @Test
    public void test16() {
    //16. Verify the categories second name = “Housewares” of productID = 333179
        response.body("data[8].categories[1].name", equalTo("Housewares"));

    }

    @Test
    public void test17() {
    //17. Verify the price = 4.99 of forth product
        response.body("data[3].price.toString()", equalTo("4.99"));

    }

    @Test
    public void test18() {
    // 18. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
        response.body("data[5].name", equalTo("Duracell - D Batteries (4-Pack)"));
    }

    @Test
    public void test19() {
    //        19. Verify the ProductId = 333179 for the 9th product
        response.body("data[8].id", equalTo(333179));


    }

    @Test
    public void test20() {
    //20. Verify the productId = 346575 have 5 categories
        response.body("data[9].categories.size()", equalTo(5));

    }
}
