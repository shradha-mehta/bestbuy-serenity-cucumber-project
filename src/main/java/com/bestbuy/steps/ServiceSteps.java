package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.pojo.ServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class ServiceSteps {

    @Step("Creating services with name : {0} ")

    public ValidatableResponse createNewServices(String name){

        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);


        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(servicePojo)
                .post(EndPoints.POST_A_SERVICES)
                .then();
    }
    @Step("Getting the services information with servicesId : {0}")

    public ValidatableResponse getServicesById(long servicesId){
        return SerenityRest.rest()
                .given()
                .pathParam("id",servicesId)
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID )
                .then();
    }
    @Step("Updating Services information with servicesId : {0} name : {1} ")

    public ValidatableResponse updateServices(long servicesId,String name){

        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);


        return  SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .pathParam("id",servicesId)
                .log().all()
                .when()
                .body(servicePojo)
                .patch(EndPoints.UPDATE_SERVICES_BY_ID )
                .then();
    }

    @Step("Deleting the Services information with ServicesId : {0} ")

    public ValidatableResponse deleteServicesById(long servicesId){
        return SerenityRest.rest()
                .given()
                .pathParam("id",servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then();



    }
    @Step("Getting all services Information ")

    public ValidatableResponse getAllServices() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then();

    }



}
