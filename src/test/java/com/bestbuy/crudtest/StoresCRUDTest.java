package com.bestbuy.crudtest;

import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.equalTo;



@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StoresCRUDTest extends TestBase {
    static String name = "Testing Store" + TestUtils.getRandomValue();
    static String type = "Testing Tools"+ TestUtils.getRandomValue();
    static String address = "268 Alan Lodge"+ TestUtils.getRandomValue();
    static String address2 = "Nether Street"+ TestUtils.getRandomValue();
    static String city = "London"+ TestUtils.getRandomValue();
    static String state = "North"+ TestUtils.getRandomValue();
    static String zip = "52525ZQ"+ TestUtils.getRandomValue();
    static double lat = 45.958785;
    static double lng = -90.445596;
    static String hours = "Mon: 9-6; Tue: 9-6; Wed: 9-6; Thurs: 9-6; Fri: 9-6; Sat: 9-6; Sun: 9-6";
    static long storeId;


    @Steps
    StoresSteps storesSteps;

    @Title("This test will create a new stores and verify its generated")
    @Test
    public void test001() {
        storeId = storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours).log().all().extract().response()
                .body().jsonPath().getLong("id");
        System.out.println("store id is : " + storeId);
    }

    @Title("This test will get the stores information by ID")
    @Test
    public void test002() {
        storesSteps.getStoreById(storeId).statusCode(200);

    }

    @Title("This test will update the store information and verify the updated information")
    @Test

    public void test003() {


        name = name+"_changed";
        type = type+"_changed";
        address =address+"_updated";
        address2 = address2 +"_updated";
        hours = "Mon: 8-6; Tue: 8-6; Wed: 8-6; Thurs: 8-6; Fri: 8-6; Sat: 8-6; Sun: 8-6";

        storesSteps.updateStore(storeId,name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);
        storesSteps.getStoreById(storeId).body("name",equalTo(name));

    }

    @Title("This test will delete the store and verify the store is deleted ")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeId).statusCode(200);
        storesSteps.getStoreById(storeId).statusCode(404);
    }



}
