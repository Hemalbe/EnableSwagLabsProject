package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.CartPage;
import com.swaglabs.qa.pages.HomePage;
import com.swaglabs.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FilterTest extends Base {

    LoginPage loginPage;
    HomePage homePage;


    public FilterTest(){
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        loginPage = new LoginPage(driver);
        homePage= loginPage.Login(prop.getProperty("SValidUserName"),prop.getProperty("SValidPassword"));
    }
    @AfterMethod
    public void tearDown(){

        driver.quit();
    }

    // Adding the priority we set the test case order else it will run with an alphabetical order.
    @Test(priority = 1)
    public void verifySelectFilterAtoZ() throws InterruptedException {
        homePage.selectFilter("az");
        assertEquals(homePage.getProductTitle(0),dataProp.getProperty("Product(0).ItemName"),"the filter option is not working");
    }

    @Test(priority = 2)
    public void verifySelectFilterZtoA() throws InterruptedException {
        homePage.selectFilter("za");
        assertEquals(homePage.getProductTitle(0),dataProp.getProperty("Product(5).ItemName"),"the filter option is not working");
    }

    @Test(priority = 3)
    public void verifySelectFilterPriceLowtoHigh() {
        homePage.selectFilter("lohi");
        assertEquals(homePage.getProductPriceList(0),dataProp.getProperty("Product(4).ItemPrice"),"the filter option is not working");
    }

    @Test(priority = 4)
    public void verifySelectFilterPriceHighToLow() {
        homePage.selectFilter("hilo");
        assertEquals(homePage.getProductPriceList(0),dataProp.getProperty("Product(3).ItemPrice"),"the filter option is not working");
    }

}
