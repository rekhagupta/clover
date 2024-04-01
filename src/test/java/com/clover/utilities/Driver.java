package com.clover.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private static WebDriver driver = null;

    public static WebDriver getDriver() {
        if (driver == null) {
            if ((!propFileUtil.getProperty("remote").equalsIgnoreCase("true")) || (propFileUtil.getProperty("remote")==null))
                driver = createDriver(propFileUtil.getProperty("browser"));
            else {
                driver = RemoteWebDriver();
            }
        }
        return driver;
    }

    /**
     * createDriver calls the specific driver initialization for the browser, which uses Selenium Manager (from Selenium 4.6),
     * webdriver files are no longer required.
     * based on browser set in the properties
     * @return WebDriver */
    private static WebDriver createDriver(String browserName) {
        WebDriver webDriver = null;
        switch (browserName.toLowerCase()) {
            case "chrome": default:     //Default is chrome driver OR else we can throw an exception
                webDriver = startChromeDriver();
                break;
            case "edge":
                webDriver = startEdgeDriver();
                break;
            case "firefox":
                webDriver = startFirefoxDriver();
                break;
            case "safari":
                webDriver = startSafariDriver();
                break;
//            default:
//                throw new IllegalArgumentException(
//                        String.format("Specified browser: %s not supported. Pick the acceptable browsers." ,browserName));
        }
        webDriver.manage().window().maximize();
        return webDriver;
    }

    public static WebDriver startChromeDriver() {
        final ChromeOptions chromeOptions = new ChromeOptions();

        // Preferences can be added based on:
        // https://chromium.googlesource.com/chromium/src/+/master/chrome/common/pref_names.cc
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        //This will open the local chrome from
        // /Applications/Google Chrome.app/Contents/MacOS/Google Chrome
        // If needed, we can directly run the chromeDriver for local run
        // This is useful when running locally
        // System.setProperty("webdriver.chrome.driver", "<local chromeDriver>");
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        return chromeDriver;
    }

    private static WebDriver startEdgeDriver() {
        final EdgeOptions edgeOptions = new EdgeOptions();

        //Arguments
        edgeOptions.addArguments("window-size=500,500");
        edgeOptions.addArguments("start-maximized");

        //Preferences:
        HashMap<String, Object> edgePrefs = new HashMap<>();
        edgePrefs.put("profile.default_content_settings.popups", 0);
        edgeOptions.setExperimentalOption("prefs", edgePrefs);

        WebDriver edgeDriver = new EdgeDriver(edgeOptions);
        return edgeDriver;
    }

    private static WebDriver startFirefoxDriver(){
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        final FirefoxProfile firefoxProfile = new FirefoxProfile();

        firefoxOptions.setProfile(firefoxProfile);

        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
        return firefoxDriver;
    }

    private static SafariDriver startSafariDriver(){
        SafariDriver safariDriver = new SafariDriver();

        //Capabilities or Options to be added.
        return safariDriver;
    }

    private static RemoteWebDriver RemoteWebDriver(){
        RemoteWebDriver rm;
        //Assume if the tests are running on sauceLabs via CI/CD integration
        //Set sauceLabs related options for user name etc
        Map<String, String> sauceOptions = new HashMap<>();
        sauceOptions.put("username", propFileUtil.getProperty("sauce_username"));
        sauceOptions.put("accessKey", propFileUtil.getProperty("sauce_accesskey"));
        sauceOptions.put("name", "Automation Run");
        sauceOptions.put("screenResolution", "1600x1200");

        //also set the browser based options based on browser property
        String browser = propFileUtil.getProperty("browser");
        AbstractDriverOptions browserOptions = null;
        switch (browser.toLowerCase()){
            case "chrome": default:
                browserOptions = new ChromeOptions();
                browserOptions.setBrowserVersion("123");
                break;
            case "edge":
                browserOptions = new EdgeOptions();
                browserOptions.setBrowserVersion("100");
                break;
            //Add other browsers here
        }
        browserOptions.setCapability("sauce.options", sauceOptions);
        try{
            driver = new RemoteWebDriver(new URL(propFileUtil.getProperty("sauceURL")), browserOptions);

        } catch (Exception e) {
            //Log the error
            e.printStackTrace();
            // and since the remoteDriver has failed, lets default to Chrome
            startChromeDriver();
        }
        return (RemoteWebDriver) driver;
    }

    public static void quit() {
        if (driver != null) {
            try {
                driver.close();
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
