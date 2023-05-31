package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {
    static String name = "Prime" + TestUtils.getRandomValue();
    static String type = "Test" + TestUtils.getRandomValue();
    static String address =  TestUtils.getRandomValue() + " ,Piccadilly Street" ;
    static String address2 = "Oxford Street";
    static String City = "Manchester" ;
    static String state = "England" ;
    static String zip = "445665";
    static double lat = 54.23569;
    static double lng = -79.95;
    static String hours = "Mon: 10-10; Tue: 10-10; Wed: 10-10; Thurs: 10-10; Fri: 10-10; Sat: 10-10; Sun: 10-10";

    static int storeId;


    @Test
    public void createStore() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(City);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("http://localhost:3030/stores");
        response.then().log().all().statusCode(201);
    }



    @Test
    public void getStoreData() {
        Response response = given()
                .when()
                .get("http://localhost:3030/stores");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getStoreId() {
        Response response = given()
                .when()
                .get("http://localhost:3030/stores/8921");
        response.then().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void updateStoreData() {
        StorePojo storePojo = new StorePojo();
        storePojo.setHours("Mon: 10-10; Tue: 10-8; Wed: 10-7; Thurs: 10-6; Fri: 10-5; Sat: 10-9; Sun: 10-8");
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(City);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch("http://localhost:3030/stores/8921");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void deleteStore() {
        Response response = given()
                .when()
                .delete("http://localhost:3030/stores/8921");
        response.prettyPrint();
        response.then().log().all().statusCode(200);


    }

}
