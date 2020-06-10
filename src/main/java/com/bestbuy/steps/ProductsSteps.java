package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.pojo.ProductsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;


public class ProductsSteps {
    @Step("Creating Products with name : {0} , type : {1} , upc : {2} , price : {3} , description : {4} , model : {5}")

    public ValidatableResponse createNewProduct(String name , String type , String upc , double price , String description , String model){

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setUpc(upc);
        productsPojo.setPrice(price);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(productsPojo)
                .post(EndPoints.POST_A_PRODUCT)
                .then();


    }

    @Step("Getting the Product information with productId : {0}")

    public ValidatableResponse getProductById(long productId){
        return SerenityRest.rest()
                .given()
                .pathParam("id",productId)
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID )
                .then();
    }


    @Step("Updating Product information with productId : {0} name : {1} , type : {2} , upc : {3} , price : {4} , description : {5} , model : {6} ")

    public ValidatableResponse updateProduct(long productId,String name , String type , String upc , double price , String description , String model){

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setUpc(upc);
        productsPojo.setPrice(price);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);

        return  SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .pathParam("id",productId)
                .log().all()
                .when()
                .body(productsPojo)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID )
                .then();
    }

    @Step("Deleting the Product information with productId : {0} ")

    public ValidatableResponse deleteProductById(long productId){
        return SerenityRest.rest()
                .given()
                .pathParam("id",productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();



    }
    @Step("Getting all product Information ")

    public ValidatableResponse getAllProduct() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();

    }


}
