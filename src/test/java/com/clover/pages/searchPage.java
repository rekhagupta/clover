package com.clover.pages;

import com.clover.utilities.Driver;
import com.clover.utilities.propFileUtil;
import com.clover.utilities.utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class searchPage extends testBase{

    public searchPage() throws Exception {
    }
    @FindBy (name = "q")
    WebElement search;

    @FindBy (xpath = "//h3[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'')]")
    List<WebElement> results;

    public void gotoUrl(){
        Driver.getDriver().get(propFileUtil.getProperty("url"));
    }

    public void search(String keyword){
        utils.waitForVisibility(search,2);
        search.sendKeys(keyword + Keys.ENTER);
    }

    public void validate (String str) throws InterruptedException {
        utils.waitForPageLoad(3);

        List<WebElement> links = driver.findElements(By.xpath("//span[contains(.,'" + str + "')]"));
        Assert.assertTrue("The result is not relevant.", links.get(0).getAttribute("innerText").toLowerCase().contains(str) );
        links.get(0).click();
    }

    public void closeBrowser(){
        Driver.quit();
    }
}