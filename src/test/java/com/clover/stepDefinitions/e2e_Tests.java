package com.clover.stepDefinitions;

import com.clover.pages.searchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class e2e_Tests {
    searchPage searchPage;

    public e2e_Tests() throws Exception {
        searchPage = new searchPage();
    }

    @Given("I navigate to url")
    public void gotoBaseUrl() {
        searchPage.gotoUrl();
    }

    @Then("I enter the {string} to search")
    public void enterSearchKey(String searchKeyword) {
        searchPage.search(searchKeyword);
    }

    @And("I validate the results {string}")
    public void validateTheResults(String resultStr) throws InterruptedException {
        //"//h3[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'clover')]"

        searchPage.validate(resultStr);

    }

    @Then("I close the browser")
    public void iCloseTheBrowser() {
        searchPage.closeBrowser();
    }
}
