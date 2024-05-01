package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CompleteCheckOutTest extends Base {
    LoginPage loginPage;
    HomePage homePage;
    ProductDisplayPage productDisplayPage;
    CartPage cartPage;
    CheckoutStepOnePage checkoutStepOnePage;
    CheckoutStepTwoPage checkoutStepTwoPage;
    CheckoutCompletePage checkoutCompletePage;

    public CompleteCheckOutTest(){
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        loginPage = new LoginPage(driver);
        homePage= loginPage.Login(prop.getProperty("SValidUserName"),prop.getProperty("SValidPassword"));
        homePage.clickOnAddToCart(0);
        homePage.clickOnCartButton();
        cartPage=new CartPage(driver);
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepOnePage.enterAddress(dataProp.getProperty("Firstname"),dataProp.getProperty("Lastname"),dataProp.getProperty("Postalcode"));
        checkoutStepTwoPage=new CheckoutStepTwoPage(driver);
        checkoutStepTwoPage.clickOnFinish();
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }
    @AfterMethod
    public void tearDown(){
        homePage.resetApp();
        driver.quit();
    }

    // Adding the priority we set the test case order else it will run with an alphabetical order.
    @Test(priority = 1)
    public void verifyAllItemsInProductDisplayTest() {
        boolean allElementsDisplayed = checkoutCompletePage.getAllTheElementDisplayStatus();
        Assert.assertTrue(allElementsDisplayed, "Not all elements in the Complete check out page are displayed.");

    }

    @Test(priority = 2)
    public void verifyBackToHomeButton() {
        checkoutCompletePage.clickOnBackToHome();
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(), "HomePage is loaded");


    }




}
