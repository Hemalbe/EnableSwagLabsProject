package com.swaglabs.qa.base;

import com.swaglabs.qa.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    public Properties prop;
    public Properties dataProp;

    //constructor
    public Base() {

        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(("/Users/hemalathaa/Desktop/EnableSwagLabsProject/src/main/java/com/swaglabs/qa/config/config.properties"));
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        dataProp = new Properties();
        try {
            FileInputStream datafis = new FileInputStream(("/Users/hemalathaa/Desktop/EnableSwagLabsProject/src/main/java/com/swaglabs/qa/testdata/testdata.properties"));
            dataProp.load(datafis);
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }

    public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
        WebDriver driver = initializeBrowser(browserName);
        driver.get(prop.getProperty("url"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"login_logo\"]")).isDisplayed(), "Login page is not displayed");
        return driver;
    }

    public WebDriver initializeBrowserSeachAndOpenApplicationURL(String browserName) {
        WebDriver driver = initializeBrowser(browserName);
        driver.get(prop.getProperty("SearchURL"));
        return driver;
    }

    private WebDriver initializeBrowser(String browserName) {
        WebDriver driver;
        if (browserName.equals("chrome")) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equals("safari")) {
            driver = new SafariDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        return driver;
    }
}
