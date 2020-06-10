package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.steps.StoresSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;




public class StoresStepDefs {

    static String name = "Testing Store" + TestUtils.getRandomValue();
    static String type = "Testing Tools"+ TestUtils.getRandomValue();
    static String address = "268 Alan Lodge"+ TestUtils.getRandomValue();
    static String address2 = "Nether Street"+ TestUtils.getRandomValue();
    static String city = "London"+ TestUtils.getRandomValue();
    static String state = "North"+ TestUtils.getRandomValue();
    static String zip = "52525ZQ"+ TestUtils.getRandomValue();
    static double lat ;
    static double lng ;
    static String hours ;
    static long storeId;

    @Steps
    StoresSteps storesSteps;



    @When("^User sends a GET request to the stores endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheStoresEndpointTheyMustGetBackAValidStatusCode() {
        storesSteps.getAllStores().statusCode(200);
    }

    @When("^I create a new stores by providing the information name type  address addresstwo  city state zip lat \"([^\"]*)\" lng \"([^\"]*)\" hours \"([^\"]*)\"$")
    public void iCreateANewStoresByProvidingTheInformationNameTypeAddressAddresstwoCityStateZipLatLngHours(double lat, double lng, String hours) {
        storeId = storesSteps.createNewStore(name,type,address,address2,city,state,zip,lat,lng,hours).statusCode(201).log().all().statusCode(201).extract().response()
                .body().jsonPath().getLong("id");
    }

    @Then("^I verify the stores is created$")
    public void iVerifyTheStoresIsCreated() {
        storesSteps.getStoreById(storeId).log().all().statusCode(200);

    }


    @When("^I send GET request to stores endpoint with Id \"([^\"]*)\" , I should received the store information$")
    public void iSendGETRequestToStoresEndpointWithIdIShouldReceivedTheStoreInformation(String _storeId)  {
        storesSteps.getStoreById(storeId).log().all().statusCode(200);

    }

    @When("^I update a created store providing the new name type and hours$")
    public void iUpdateACreatedStoreProvidingTheNewNameTypeAndHours() {
        name = name + "_Changed";
        type = type + "_added";
        hours = "Mon: 8-5; Tue: 8-5; Wed: 8-5; Thurs: 8-5; Fri: 8-5; Sat: 8-5; Sun: 8-4";
        storesSteps.updateStore(storeId,name,type,address,address2,city,state,zip,lat,lng,hours).log().all().statusCode(200);
    }

    @Then("^I verify the store is updated$")
    public void iVerifyTheStoreIsUpdated() {
       storesSteps.getStoreById(storeId).body("name", equalTo(name));
    }

    @When("^I delete a created store ,they must get back a valid status code is 200$")
    public void iDeleteACreatedStoreTheyMustGetBackAValidStatusCodeIs() {
        storesSteps.deleteStore(storeId).statusCode(200);
    }


    @Then("^I verify the store is deleted$")
    public void iVerifyTheStoreIsDeleted() {
        storesSteps.getStoreById(storeId).statusCode(404);

    }



}
