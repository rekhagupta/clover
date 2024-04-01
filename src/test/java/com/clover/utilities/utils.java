package com.clover.utilities;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.HashMap;

public class utils {
    public static void waitForPageLoad(int timeOutSeconds){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        int interval = 10;
        try{
            for (int i=0; i<interval; i++){
                if (!js.executeScript("return document.readyState").toString().equalsIgnoreCase("complete")){
                    Thread.sleep(timeOutSeconds*1000/interval);
                }
            }
        }
        catch (TimeoutException | InterruptedException e){
            Assert.fail("Page counld not be loaded in " + timeOutSeconds + " seconds.");
        }
    }

    public static void waitForVisibility(WebElement ele, int timeOutSeconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutSeconds));
        try{
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(ele)));
        }
        catch (TimeoutException e){
            Assert.fail("Element " + ele.getText() + " is not visible.");
        }
    }
}
