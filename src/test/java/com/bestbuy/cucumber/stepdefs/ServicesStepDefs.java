package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.steps.ServiceSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;


public class ServicesStepDefs {

    static String name = "Camera Recycling" + TestUtils.getRandomValue();
    static long servicesId;

    @Steps
    ServiceSteps serviceSteps;


    @When("^User sends a GET request to the services endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheServicesEndpointTheyMustGetBackAValidStatusCode() {
       serviceSteps.getAllServices().statusCode(200);

    }

    @When("^I create a new services by providing the information name$")
    public void iCreateANewServicesByProvidingTheInformationName() {
        servicesId = serviceSteps.createNewServices(name).log().all().statusCode(201).extract().response()
                .body().jsonPath().getLong("id");
        System.out.println("services ID is" + servicesId);
    }


    @Then("^I verify the services is created$")
    public void iVerifyTheServicesIsCreated() {
        serviceSteps.getServicesById(servicesId).log().all().statusCode(200);
        System.out.println("services id is : " +  servicesId);
    }

    @When("^I send GET request to services endpoint with Id \"([^\"]*)\" , I should received the services information$")
    public void iSendGETRequestToServicesEndpointWithIdIShouldReceivedTheServicesInformation(String _servicesId)  {
        serviceSteps.getServicesById(servicesId).log().all().statusCode(200);
    }

    @When("^I update a created services providing the new name$")
    public void iUpdateACreatedServicesProvidingTheNewName() {
        name = name+"_Changed";

        serviceSteps.updateServices(servicesId,name).statusCode(200);

    }

    @Then("^I verify the services is updated$")
    public void iVerifyTheServicesIsUpdated() {

        serviceSteps.getServicesById(servicesId).body("name",equalTo(name));
    }

    @When("^I delete a created services ,they must get back a valid status code  is 200$")
    public void iDeleteACreatedServicesTheyMustGetBackAValidStatusCodeIs() {
        serviceSteps.deleteServicesById(servicesId).statusCode(200);

    }

    @Then("^I verify the services is created is deleted$")
    public void iVerifyTheServicesIsCreatedIsDeleted() {
        serviceSteps.getServicesById(servicesId).statusCode(404);
    }
}


