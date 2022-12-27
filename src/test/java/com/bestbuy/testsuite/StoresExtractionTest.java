package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {


        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200); //method type of this is validatable response
    }

    @Test
    public void test001() {
//       1. Extract the limit
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //    2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + name);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> allStoreName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of All Store Name are : " + allStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> listOfIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<?> sizeOfIds = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + sizeOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> storeNames = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for store name 'St Cloud' are: " + storeNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<HashMap<String, ?>> rochesterAddress = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address is for Rochester store is : " + rochesterAddress);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<Integer> services = response.extract().path("data.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Services of 8th store are : " + services);
        System.out.println("------------------End of Test---------------------------");

    }


    //10. Get storeservices of the store where service name = Windows Store
  @Test
    public void test0010() {
             List<List<String>> services = response.extract().path("data.find{it.services}.services.findAll{it.name ='Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("storeservices of the store where service name is Windows Store : " + services);
        System.out.println("------------------End of Test---------------------------");

    }

    //11. Get all the storeId of all the store
    @Test
    public void test0011() {
        List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + storeIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //12. Get id of all the store
    @Test
    public void test0012() {
        List<Integer> listOfStoreIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //13. Find the store names Where state = ND
    @Test
    public void test0013() {
        String ND = response.extract().path("data[7].state");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("store names state is : " + ND);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test0014() {
        List<List<Integer>> totalNumberOfSevices = response.extract().path("data.findAll{it.name =='Rochester'}.services.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Total number of services in Rochester are : " + totalNumberOfSevices.get(0).size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test0015() {
        List<List<String>> services = response.extract().path("data.find{it.services}.services.findAll{it.name =='Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Windows Store services are : " + services);
        System.out.println("------------------End of Test---------------------------");

    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test0016() {
        List<List<String>> serviceStoreName = response.extract().path("data.findAll{it.name =='Fargo'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("name of all services Where store name is Fargo : " + serviceStoreName);
        System.out.println("------------------End of Test---------------------------");

    }

    //17. Find the zip of all the store
    @Test
    public void test0017() {
        List<String> allZip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of all store is  : " + allZip);
        System.out.println("------------------End of Test---------------------------");

    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test0018() {
        List<List<String>> zipstoreRoseville = response.extract().path("data.findAll{it.name =='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("name of all services Where store name is Roseville : " + zipstoreRoseville);
        System.out.println("------------------End of Test---------------------------");

    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test0019() {
        List<?> storeServicesOfMagnoliaHomeTheater = response.extract().path("data.find{it.services}.services.findAll{it.name='Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details of the services name is Magnolia Home Theater : " + storeServicesOfMagnoliaHomeTheater);
        System.out.println("------------------End of Test---------------------------");

    }

    //20. Find the lat of all the stores
    @Test
    public void test0020() {
        List<Integer> latOfAllStore = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("name of all services Where store name is Roseville : " + latOfAllStore);
        System.out.println("------------------End of Test---------------------------");
        }

}
