package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.steps.CategoriesSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;


public class CategoriesStepDefs {

    static String name = "APPLE Mobile" + TestUtils.getRandomValue();
    static String id = "ABCMAT"+TestUtils.getRandomValue();
    static String categoriesId;

    @Steps
    CategoriesSteps categoriesSteps;


    @When("^User sends a GET request to the categories endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheCategoriesEndpointTheyMustGetBackAValidStatusCode() {
        categoriesSteps.getAllCategories().statusCode(200);

    }

    @When("^I create a new categories by providing the information name & id$")
    public void iCreateANewCategoriesByProvidingTheInformationNameId() {
        categoriesId = categoriesSteps.createNewCategories(name, id).log().all().statusCode(201).extract().response()
                .body().jsonPath().getString("id");

    }

    @Then("^I verify the categories is created$")
    public void iVerifyTheCategoriesIsCreated() {
        categoriesSteps.getCategorieById(categoriesId).log().all().statusCode(200);
        System.out.println("categories id is : " +  categoriesId);
    }

    @When("^I send GET request to categories endpoint with Id \"([^\"]*)\" , I should received the categories information$")
    public void iSendGETRequestToCategoriesEndpointWithIdIShouldReceivedTheCategoriesInformation(String _categoriesId)  {
        categoriesSteps.getCategorieById(categoriesId).log().all().statusCode(200);

    }

    @When("^I update a created categories providing the new name$")
    public void iUpdateACreatedCategoriesProvidingTheNewName() {
        name = name+"_Changed";

        categoriesSteps.updateCategories(categoriesId,name).statusCode(200);

    }

    @Then("^I verify the categories is updated$")
    public void iVerifyTheCategoriesIsUpdated() {
        categoriesSteps.getCategorieById(categoriesId).body("name",equalTo(name));
    }


    @When("^I delete a created categories ,they must get back a valid status code  is 200$")
    public void iDeleteACreatedCategoriesTheyMustGetBackAValidStatusCodeIs() {
        categoriesSteps.deleteCategoriesById(categoriesId).statusCode(200);
    }

    @Then("^I verify the categories is deleted$")
    public void iVerifyTheCategoriesIsDeleted() {
        categoriesSteps.getCategorieById(categoriesId).statusCode(404);
    }


}
