package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.steps.ProductsSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import static org.hamcrest.Matchers.*;



public class ProductStepDefs {

    static String name = "APPLE New Mobile" + TestUtils.getRandomValue();
    static String type = "iOS 5009" + TestUtils.getRandomValue();
    static String upc = "ABC" + TestUtils.getRandomValue();
    static String model = "NP12345" + TestUtils.getRandomValue();
    static double price;
    static long productId;
    static String description;


    @Steps
    ProductsSteps productsSteps;

    @When("^User sends a GET request to the products endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheProductsEndpointTheyMustGetBackAValidStatusCode() {
        productsSteps.getAllProduct().statusCode(200);
    }

    @When("^I create a new product by providing the information name type  upc  price \"([^\"]*)\" description \"([^\"]*)\" model$")
    public void iCreateANewProductByProvidingTheInformationNameTypeUpcPriceDescriptionModel(double price, String description) {
        productId = productsSteps.createNewProduct(name, type, upc, price, description, model).statusCode(201).log().all().statusCode(201).extract().response()
                .body().jsonPath().getLong("id");
    }

    @Then("^I verify the product is created$")
    public void iVerifyTheProductIsCreated() {
        productsSteps.getProductById(productId).log().all().statusCode(200);
    }

    @When("^I send GET request to product endpoint with Id \"([^\"]*)\" , I should received the product information$")
    public void iSendGETRequestToProductEndpointWithIdIShouldReceivedTheProductInformation(String _productId) {
        productsSteps.getProductById(productId).log().all().statusCode(200);

    }


    @When("^I update a created product providing the new name upc price and description$")
    public void iUpdateACreatedProductProvidingTheNewNameUpcPriceAndDescription() {
        name = name + "_Changed";
        upc = upc + "_added";
        price = 89.99;
        description = "This is a placeholder request for changing a product ";
        productsSteps.updateProduct(productId, name, type, upc, price, description, model).log().all().statusCode(200);
    }

    @Then("^I verify the product is updated$")
    public void iVerifyTheProductIsUpdated() {

        productsSteps.getProductById(productId).body("name", equalTo(name));
    }

    @When("^I delete a created product ,they must get back a valid status code  is 200$")
    public void iDeleteACreatedProductTheyMustGetBackAValidStatusCodeIs() {
        productsSteps.deleteProductById(productId).statusCode(200);
    }


    @Then("^I verify the product is deleted$")
    public void iVerifyTheProductIsDeleted() {
        productsSteps.getProductById(productId).statusCode(404);
    }


}


