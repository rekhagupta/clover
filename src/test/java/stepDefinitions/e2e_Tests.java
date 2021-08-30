package stepDefinitions;

import java.io.UnsupportedEncodingException;
import java.util.*;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.propFileUtil;
import utilities.httpCalls;
import utilities.utils;

public class e2e_Tests {
    private static Response response;

    public Properties envData = new propFileUtil().getProperty("apiTest");

    @Given("I'm using the OpenWeather API")
    public void iMUsingTheOpenWeatherMapApi() {
        RestAssured.baseURI = envData.getProperty("url");
    }

    @When("I make a valid API {string} request by {string} for {string}")
    public void iMakeAValidAPIRequestByFor(String type, String param, String path) throws UnsupportedEncodingException {
       response = httpCalls.HTTPRequest(envData.getProperty("url"),
                new HashMap<>(),
                type,
                utils.paramsToMap(param, ";", envData.getProperty("appId")),
                path);

        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I get a valid response with Code={string}")
    public void iGetAValidResponseWithCode(String statusCode) {
        Assert.assertTrue("Returned Status Code :" +statusCode+ " is not a match", Integer.parseInt(statusCode) == response.getStatusCode());
    }

    @And("I get the {string} in the response")
    public void iGetTheInTheResponse(String validations) throws UnsupportedEncodingException {
        HashMap<String, String> validationsMap = utils.paramsToMap(validations, ",", "");

        for (Map.Entry mapElement : validationsMap.entrySet())
           Assert.assertTrue("Could not validate for parameter = "+ (String)mapElement.getKey() , response.jsonPath().get((String)mapElement.getKey()).toString().equalsIgnoreCase((String)mapElement.getValue()));
    }
}
