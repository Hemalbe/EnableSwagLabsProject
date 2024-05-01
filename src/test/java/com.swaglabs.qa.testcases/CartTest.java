package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends Base {

    LoginPage loginPage;
    HomePage homePage;
    ProductDisplayPage productDisplayPage;
    CartPage cartPage;
    CheckoutStepOnePage checkoutStepOnePage;

    public CartTest(){
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
        homePage.resetApp();
        driver.quit();
    }

    // Adding the priority we set the test case order else it will run with an alphabetical order.
    @Test(priority = 1)
    public void verifyAllTheElementsInEmptyCartPage() {
        homePage.clickOnCartButton();
        cartPage=new CartPage(driver);
        boolean allElementsInEmptyCartDisplayed = cartPage.getAllTheElementInEmptyCartDisplayStatus();
        Assert.assertTrue(allElementsInEmptyCartDisplayed, "Not all elements in the cart page are displayed.");

    }

    @Test(priority = 2)
    public void verifyAllTheElementsItemsCartPage() {
        // Add items to the cart
        for (int i = 0; i < 3; i++) {
            homePage.clickOnAddToCart(i);
        }
        homePage.clickOnCartButton();
        cartPage=new CartPage(driver);
        boolean allElementsInItemsCartDisplayed = cartPage.getAllTheElementInItemsCartDisplayStatus();
        Assert.assertTrue(allElementsInItemsCartDisplayed, "Not all elements in the cart page are displayed.");

    }

    @Test(priority = 3)
    public void verifyAllTheElementsItemsCartLogoutandLogin() {
        // Add items to the cart
        for (int i = 0; i < 3; i++) {
            homePage.clickOnAddToCart(i);
        }
        homePage.clickOnCartButton();
        cartPage=new CartPage(driver);
        boolean initialAllElementsInItemsCartDisplayed = cartPage.getAllTheElementInItemsCartDisplayStatus();
        homePage.clickOnHamburgerMenu();
        homePage.clickOnLogoutButton();
        homePage= loginPage.Login(prop.getProperty("SValidUserName"),prop.getProperty("SValidPassword"));
        homePage.clickOnCartButton();
        boolean finalAllElementsInItemsCartDisplayed = cartPage.getAllTheElementInItemsCartDisplayStatus();
        Assert.assertEquals(initialAllElementsInItemsCartDisplayed, finalAllElementsInItemsCartDisplayed,
                "Initial and final states of elements in the cart page do not match.");
    }

    @Test(priority = 4)
    public void verifyRemoveButtonInTheCart() {
        homePage.clickOnAddToCart(0);
        homePage.clickOnCartButton();
        cartPage=new CartPage(driver);
        cartPage.clickOnRemove(0);
        Assert.assertNull(cartPage.getProductTitleInCart(0), "Product title element should not be available in an empty cart");

    }

    @Test(priority = 4)
    public void verifyContinueShoppingButtonBehaviour() {
        homePage.clickOnCartButton();
        cartPage=new CartPage(driver);
        cartPage.clickOnContinueShoppingButton();
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(), "HomePage is Displayed");

    }

    @Test(priority = 5)
    public void verifyCheckoutButtonBehaviour() throws InterruptedException {
        homePage.clickOnAddToCart(0);
        homePage.clickOnCartButton();
        cartPage=new CartPage(driver);
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        Assert.assertTrue(checkoutStepOnePage.isFirstNameDisplay(), "checkoutStepOnePage is loaded");

    }



}
