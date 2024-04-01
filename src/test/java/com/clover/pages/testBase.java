package com.clover.pages;

import com.clover.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class testBase {
    protected WebDriver driver = Driver.getDriver();

    public testBase() throws Exception{
        PageFactory.initElements(driver, this);
    }
}