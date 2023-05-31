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
                .then().statusCode(200);
    }

    // 1) Extract the value of limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }
    // 2) Extract the value of total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }
    // 3) Extract the name of the 5Th store
    @Test
    public void test003() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of 5Th store : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }
    // 4) Extract the name of the all store
    @Test
    public void test004() {
        List< String> storeNameList = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the name of all store : " + storeNameList);
        System.out.println("------------------End of Test---------------------------");
    }
    // 5) Extract the storeId of the all store
    @Test
    public void test005() {
        List<Integer> storeIdList = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of storeId is : " + storeIdList);
        System.out.println("------------------End of Test---------------------------");
    }
    // 6) Print the size of the data list
    @Test
    public void test006() {
        List<?> listSize = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of data list size is : " + listSize.size());
        System.out.println("------------------End of Test---------------------------");
    }
    // 7) Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String,?>> list = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the value of the store where store name is : " + list);
        System.out.println("------------------End of Test---------------------------");
    }
    // 8) Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String >address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get the address of the store where store name = Rochester : " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get all the services of 8th store
    @Test
    public void test009() {
        List<HashMap<String,?>>services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the services of 8th store : " + services);
        System.out.println("------------------End of Test---------------------------");
    }
    // 10) Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        // List<HashMap<String,?>>storeServices = response.extract().path("data.findAll{it.name=='Windows Store'}.storeservices");
        List<HashMap<String,?>> storeServices = response.extract().path("data.findAll{it.services.find{it.name=='Windows Store'}!=null}.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get storeservices of the store where service name = Windows Store : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }
    // 11)  Get all the storeId of all the store
    @Test
    public void test011() {
        List<Integer>storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the storeId of all the store: " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }
    // 12)  Get id of all the store
    @Test
    public void test012() {
        List<Integer>storeIdAll = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of all the store: " + storeIdAll);
        System.out.println("------------------End of Test---------------------------");
    }
    // 13)  Find the store names Where state = ND
    @Test
    public void test013() {
        List<String>storeState = response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the store names Where state = ND : " + storeState);
        System.out.println("------------------End of Test---------------------------");
    }
    // 14)  Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<HashMap<String,?>>serviceList = response.extract().path("data.findAll{it.name=='Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the Total number of services for the store where store name = Rochester : " + serviceList);
        System.out.println("------------------End of Test---------------------------");
    }
    // 15)  Find the createdAt for all services whose name = “Windows Store"
    @Test
    public void test015() {
        //List<HashMap<String,?>>createdList = response.extract().path("data.services.storeServices.findAll{it.name=='Windows Store'}.createdAt");
        List<HashMap<String,?>> createdAtList = response.extract().path("data.findAll{it.services.find{it.name=='Windows Store'}!=null}.services.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all services whose name = “Windows Store: " + createdAtList);
        System.out.println("------------------End of Test---------------------------");
    }
    // 16)  Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<HashMap<String,?>>fargoList = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the name of all services Where store name = “Fargo”: " + fargoList);
        System.out.println("------------------End of Test---------------------------");
    }
    // 17) Find the zip of all the store
    @Test
    public void test017() {
        List<String>zipList = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store : " + zipList);
        System.out.println("------------------End of Test---------------------------");
    }
    // 18) Find the zip of store name = Roseville
    @Test
    public void test018() {
        //  String zipListRoseville = response.extract().path("data[3].zip");
        List<String> zipListRoseville = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store : " + zipListRoseville);
        System.out.println("------------------End of Test---------------------------");
    }
    // 19) Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        // List<HashMap<String,?>> serviceNameTheater = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.services.storeservices");
        List<HashMap<String,?>> serviceNameTheater = response.extract().path("data.findAll{it.services.find{it.name=='Magnolia Home Theater'}!=null}.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the storeservices details of the service name = Magnolia Home Theater : " + serviceNameTheater);
        System.out.println("------------------End of Test---------------------------");
    }
    // 20) Find the lat of all the stores
    @Test
    public void test020() {
        List<Double> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the lat of all the stores : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }
}
