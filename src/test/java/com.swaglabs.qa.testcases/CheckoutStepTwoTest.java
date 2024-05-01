package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutStepTwoTest extends Base {
    LoginPage loginPage;
    HomePage homePage;
    ProductDisplayPage productDisplayPage;
    CartPage cartPage;
    CheckoutStepOnePage checkoutStepOnePage;
    CheckoutStepTwoPage checkoutStepTwoPage;
    CheckoutCompletePage checkoutCompletePage;

    public CheckoutStepTwoTest(){
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

    }
    @AfterMethod
    public void tearDown(){
        homePage.resetApp();
        driver.quit();
    }

    // Adding the priority we set the test case order else it will run with an alphabetical order.
    @Test(priority = 1)
    public void verifyAllItemsCheckOutTwoPage() {
        boolean allElementsDisplayed = checkoutStepTwoPage.getAllTheElementDisplayStatus();
        Assert.assertTrue(allElementsDisplayed, "Not all elements in the Check out one page are displayed.");

    }

    @Test(priority = 2)
    public void verifyCancelButton() {
        checkoutStepOnePage.clickOnCancelButton();
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(), "Home Page is loaded");

    }

    @Test(priority = 3)
    public void verifyOrder() {
        checkoutStepTwoPage.clickOnFinish();
        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.isOrderCompleteDescriptionDisplayed(), "Complete Order Page is displyed");

    }

    @Test(priority = 4)
    public void verifyReorder() {
        checkoutStepTwoPage.clickOnFinish();
        checkoutCompletePage = new CheckoutCompletePage(driver);
        checkoutCompletePage.clickOnBackToHome();
        homePage.clickOnAddToCart(0);
        homePage.clickOnCartButton();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.enterAddress(dataProp.getProperty("Firstname"),dataProp.getProperty("Lastname"),dataProp.getProperty("Postalcode"));
        checkoutStepTwoPage.clickOnFinish();
        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.isOrderCompleteDescriptionDisplayed(), "ReOrder is sucessful");

    }

    @Test(priority = 5)
    public void verifyLogoutAndOrder() {
        homePage.clickOnHamburgerMenu();
        homePage.clickOnLogoutButton();
        homePage= loginPage.Login(prop.getProperty("SValidUserName"),prop.getProperty("SValidPassword"));
        homePage.clickOnAddToCart(0);
        homePage.clickOnCartButton();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.enterAddress(dataProp.getProperty("Firstname"),dataProp.getProperty("Lastname"),dataProp.getProperty("Postalcode"));
        checkoutStepTwoPage.clickOnFinish();
        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.isOrderCompleteDescriptionDisplayed(), "Complete Order Page is displyed");

    }

    @Test(priority = 6)
    public void verifyNaviagteBackAndOrder() {
        driver.navigate().back();
        driver.navigate().forward();
        checkoutStepTwoPage.clickOnFinish();
        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.isOrderCompleteDescriptionDisplayed(), "Complete Order Page is displyed");

    }


}
