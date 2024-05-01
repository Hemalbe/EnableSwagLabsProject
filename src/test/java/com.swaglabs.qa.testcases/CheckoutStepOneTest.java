package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutStepOneTest extends Base {
    LoginPage loginPage;
    HomePage homePage;
    ProductDisplayPage productDisplayPage;
    CartPage cartPage;
    CheckoutStepOnePage checkoutStepOnePage;
    CheckoutStepTwoPage checkoutStepTwoPage;

    public CheckoutStepOneTest(){
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        loginPage = new LoginPage(driver);
        homePage= loginPage.Login(prop.getProperty("SValidUserName"),prop.getProperty("SValidPassword"));
        homePage.clickOnAddToCart(0);
        homePage.clickOnCartButton();
        cartPage=new CartPage(driver);
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage = new CheckoutStepOnePage(driver);

    }
    @AfterMethod
    public void tearDown(){
        homePage.resetApp();
        driver.quit();
    }

    // Adding the priority we set the test case order else it will run with an alphabetical order.
    @Test(priority = 1)
    public void verifyAllItemsCheckOutOnePage() {
        boolean allElementsDisplayed =checkoutStepOnePage.getAllTheElementDisplayStatus();
        Assert.assertTrue(allElementsDisplayed, "Not all elements in the Check out one page are displayed.");

    }

    @Test(priority = 2)
    public void verifyCancelButton() {
        checkoutStepOnePage.clickOnCancelButton();
        Assert.assertNotNull(cartPage.getProductTitleInCart(0), "CartPage is loaded");


    }

    @Test(priority = 3)
    public void VerifyValidAddressField() {
       checkoutStepOnePage.enterAddress(dataProp.getProperty("Firstname"),dataProp.getProperty("Lastname"),dataProp.getProperty("Postalcode"));
       checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
       Assert.assertTrue(checkoutStepTwoPage.isPaymentInformationTitleDisplayed(), "checkout step two page is loaded is loaded");
    }

    @Test(priority = 4)
    public void VerifyEmptyAddressField() throws InterruptedException {
        checkoutStepOnePage.clickOnContinueButton();
        Thread.sleep(3000);
        String actualWarningMessage = checkoutStepOnePage.retrieveEmptyFieldWarningText();
        String expectedWarningMessage = dataProp.getProperty("WarningForEmptyField");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed");

    }

    @Test(priority = 4)
    public void VerifyMissingFirstNameField()  {
        checkoutStepOnePage.enterAddress("",dataProp.getProperty("Lastname"),dataProp.getProperty("Postalcode"));
        checkoutStepOnePage.clickOnContinueButton();
        String actualWarningMessage = checkoutStepOnePage.retrieveEmptyFieldWarningText();
        String expectedWarningMessage = dataProp.getProperty("WarningForEmptyField");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed");

    }
    @Test(priority = 5)
    public void VerifyMissingLastNameField() {
        checkoutStepOnePage.enterAddress(dataProp.getProperty("Firstname"),"",dataProp.getProperty("Postalcode"));
        checkoutStepOnePage.clickOnContinueButton();
        String actualWarningMessage = checkoutStepOnePage.retrieveEmptyFieldWarningText();
        String expectedWarningMessage = dataProp.getProperty("WarningForLastnameEmptyField");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed");
    }

    @Test(priority = 6)
    public void VerifyMissingPostalCodeField() {
        checkoutStepOnePage.enterAddress(dataProp.getProperty("Firstname"),dataProp.getProperty("Lastname"),"");
        checkoutStepOnePage.clickOnContinueButton();
        String actualWarningMessage = checkoutStepOnePage.retrieveEmptyFieldWarningText();
        String expectedWarningMessage = dataProp.getProperty("WarningForPostalCodeEmptyField");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed");
    }

    @Test(priority = 7)
    public void VerifyContinueButton() {
        checkoutStepOnePage.enterFirstName(dataProp.getProperty("Firstname"));
        checkoutStepOnePage.enterLastName(dataProp.getProperty("Lastname"));
        checkoutStepOnePage.enterPostalCode(dataProp.getProperty("Postalcode"));
        checkoutStepOnePage.clickOnContinueButton();
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        Assert.assertTrue(checkoutStepTwoPage.isPaymentInformationTitleDisplayed(), "checkout step two page is loaded is loaded");
    }









}
