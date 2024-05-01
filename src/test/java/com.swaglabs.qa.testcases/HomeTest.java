package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.CartPage;
import com.swaglabs.qa.pages.HomePage;
import com.swaglabs.qa.pages.LoginPage;
import com.swaglabs.qa.pages.ProductDisplayPage;
import com.swaglabs.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.testng.Assert.assertEquals;

public class HomeTest extends Base {

    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    ProductDisplayPage productDisplayPage;

    public HomeTest(){
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
    public void verifyAllTheElementsInHomePage() throws InterruptedException {
        boolean allElementsDisplayed = homePage.getAllTheElementDisplayStatus();
        Assert.assertTrue(allElementsDisplayed, "Not all elements in the home page are displayed.");
    }

    @Test(priority = 2)
    public void verifyEmptyCart() throws InterruptedException {
        homePage.clickOnCartButton();
        cartPage= new CartPage(driver);
        Assert.assertNull(cartPage.getProductTitleInCart(0), "Product title element should not be available in an empty cart");

    }

    @Test(priority = 3)
    public void verifyItemsInTheCart()  {
        homePage.clickOnAddToCart(0);
        homePage.clickOnCartButton();
        cartPage= new CartPage(driver);
    }

    @Test(priority = 4)
    public void verifyProductClickNavigation()  {
        homePage.clickProductTitle(0);
       productDisplayPage = new ProductDisplayPage(driver);
        Assert.assertTrue(productDisplayPage.IsProductDisplayPageTitleDisplayed(), "Product display page  title should be available");

    }

    @Test(priority = 5)
    public void verifyAddToCart() throws InterruptedException {
        // Add items to the cart
        for (int i = 0; i < 3; i++) {
            homePage.clickOnAddToCart(i);
        }

        // Go to the cart page
        homePage.clickOnCartButton();
        cartPage = new CartPage(driver);

        // Verify that product title elements are available in the cart
        for (int i = 0; i < 3; i++) {
            Assert.assertNotNull(cartPage.getProductTitleInCart(i), "Product title element should be available");
        }

    }

    @Test(priority = 6)
    public void verifyRemove() throws InterruptedException {
        homePage.clickOnAddToCart(0);
        homePage.clickOnRemove(0);
        homePage.clickOnCartButton();
        cartPage = new CartPage(driver);
        Assert.assertNull(cartPage.getProductTitleInCart(0), "Product title element should not be available in an empty cart");


    }

    @Test(priority = 7)
    public void verifyCartCountIncrease() throws InterruptedException {
        homePage.clickOnAddToCart(0);
        String initialCartCount = homePage.getCartCount();
        // Add items to the cart
        for (int i = 1; i < 2; i++) {
            homePage.clickOnAddToCart(i);
        }
        String finalCartCount = homePage.getCartCount();

        Assert.assertTrue(Integer.parseInt(finalCartCount) > Integer.parseInt(initialCartCount),
                "Final cart count should be greater than initial cart count");


    }

    @Test(priority = 8)
    public void verifyCartCountDecrease() throws InterruptedException {
        // Add items to the cart
        for (int i = 0; i < 2; i++) {
            homePage.clickOnAddToCart(i);
        }
        String initialCartCount = homePage.getCartCount();
        homePage.clickOnRemove(0);
        String finalCartCount = homePage.getCartCount();

        Assert.assertTrue(Integer.parseInt(finalCartCount) < Integer.parseInt(initialCartCount),
                "Final cart count should be greater than initial cart count");


    }

    @Test(priority = 9)
    public void verifyHomePageDetails() throws InterruptedException {
        String actualPageTitle = homePage.retrieveHomePageTitle();
        System.out.println(actualPageTitle);
        String expectedPageTitle = dataProp.getProperty("PageTitle");
        String actualPageURL = homePage.retrieveHomePageURL();
        String expectedPageURL = dataProp.getProperty("HomePageURL");
        System.out.println(actualPageURL);
        Assert.assertEquals(actualPageTitle,expectedPageTitle,"Expected page title is missing");
        Assert.assertEquals(actualPageURL,expectedPageURL, "Expected page URL is missing");
    }


    @Test(priority = 10)
    public void verifyNavigationOfTwitter() {
        String parentWindowHandle = driver.getWindowHandle();
        homePage.clickOnTwitterIcon();
        Set<String> windowHandles = driver.getWindowHandles();

// Switch to the new window handle
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String newTabUrl = driver.getCurrentUrl();

// Compare the new URL with the expected URL
        String expectedUrl = dataProp.getProperty("TwitterURL");
        Assert.assertEquals(newTabUrl, expectedUrl, "URL of the new tab is not as expected");
        driver.switchTo().window(parentWindowHandle);

    }

    @Test(priority = 11)
    public void verifyNavigationOfFacebook() {
        String parentWindowHandle = driver.getWindowHandle();
        homePage.clickOnFacebookIcon();
        Set<String> windowHandles = driver.getWindowHandles();

// Switch to the new window handle
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String newTabUrl = driver.getCurrentUrl();

// Compare the new URL with the expected URL
        String expectedUrl = dataProp.getProperty("FacebookURL");
        Assert.assertEquals(newTabUrl, expectedUrl, "URL of the new tab is not as expected");
        driver.switchTo().window(parentWindowHandle);
    }

    @Test(priority = 12)
    public void verifyNavigationOfLinkedin() {
        String parentWindowHandle = driver.getWindowHandle();
        homePage.clickOnLinkedInIcon();
        Set<String> windowHandles = driver.getWindowHandles();

// Switch to the new window handle
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String newTabUrl = driver.getCurrentUrl();

// Compare the new URL with the expected URL
        String expectedUrl = dataProp.getProperty("LinkedInURL");
        Assert.assertEquals(newTabUrl, expectedUrl, "URL of the new tab is not as expected");
        driver.switchTo().window(parentWindowHandle);
    }

    @Test(priority = 13)
    public void verifyCompareAllProductInHomePageTest() throws IOException {
        for (int i = 0; i < 6; i++) {
            String ActualProductTitle = homePage.getProductTitle(i);
            String ActualProductDescription = homePage.getProductDescription(i);
            String ActualPrice = homePage.getProductPriceList(i);

            // URLs of the images to compare
            String referenceImageUrl = dataProp.getProperty("Product(" + i + ").ImageURL");
            String actualImageUrl = homePage.getImageUrl(i);

            // Download images from URLs
            BufferedImage referenceImage = homePage.downloadImage(referenceImageUrl);
            BufferedImage actualImage = homePage.downloadImage(actualImageUrl);

            String ExpectedProductTitle = dataProp.getProperty("Product(" + i + ").ItemName");
            String ExpectedProductDescription = dataProp.getProperty("Product(" + i + ").Description");
            String ExpectedPrice =dataProp.getProperty("Product(" + i + ").ItemPrice");

            // Perform assertions
            try {
                Assert.assertTrue(homePage.compareImages(referenceImage, actualImage), "Image is not matching for product " + i);
                Assert.assertEquals(ActualProductTitle, ExpectedProductTitle, "Actual Product Title does not match Expected Product Title for product " + i);
                Assert.assertEquals(ActualProductDescription, ExpectedProductDescription, "Actual Product Description does not match Expected Product Description for product " + i);
                Assert.assertEquals(ActualPrice, ExpectedPrice, "Actual Price does not match Expected Price for product " + i);
            } catch (AssertionError e) {
                // Handle the assertion failure (print error message, log, etc.)
                System.out.println("Assertion failed for product " + i + ": " + e.getMessage());
                // You can choose to continue the loop or break based on your requirements
                Assert.fail("Assertion failed for product " + i + ": " + e.getMessage());
            }
        }

    }






}
