package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.CartPage;
import com.swaglabs.qa.pages.HomePage;
import com.swaglabs.qa.pages.LoginPage;
import com.swaglabs.qa.pages.ProductDisplayPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProductDisplayTest extends Base {

    LoginPage loginPage;
    HomePage homePage;
    ProductDisplayPage productDisplayPage;
    CartPage cartPage;

    public ProductDisplayTest(){
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
    public void verifyAllItemsInProductDisplayTest() {
        homePage.clickProductTitle(0);
        productDisplayPage = new ProductDisplayPage(driver);
        boolean allElementsFromProductDisplayedPage = productDisplayPage.getAllTheElementDisplayStatus();
        Assert.assertTrue(allElementsFromProductDisplayedPage, "Not all elements in the home page are displayed.");


    }

    @Test(priority = 2)
    public void verifyCompareProductDisplayTest() throws IOException {
        String ActualProductTitle= homePage.getProductTitle(0);
        String ActualProductDescription=homePage.getProductDescription(0);
        String ActualPrice = homePage.getProductPriceList(0);
        homePage.clickProductTitle(0);
        // URLs of the images to compare
        String referenceImageUrl = dataProp.getProperty("Product(0).ImageURL");
        productDisplayPage= new ProductDisplayPage(driver);
        String actualImageUrl = productDisplayPage.getProductDisplayImageTitle();

        // Download images from URLs
        BufferedImage referenceImage = homePage.downloadImage(referenceImageUrl);
        BufferedImage actualImage = homePage.downloadImage(actualImageUrl);

        String ExpectedProductTitle= productDisplayPage.getProductDisplayPageTitle();
        String ExpectedProductDescription=productDisplayPage.getProductDisplayPageDescription();
        String ExpectedPrice = productDisplayPage.getProductDisplayPagePrice();


        // Compare images
        Assert.assertTrue(homePage.compareImages(referenceImage, actualImage), "Image is not matching");
        Assert.assertEquals(ActualProductTitle, ExpectedProductTitle, "Actual Product Title does not match Expected Product Title");
        Assert.assertEquals(ActualProductDescription, ExpectedProductDescription, "Actual Product Description does not match Expected Product Description");
        Assert.assertEquals(ActualPrice, ExpectedPrice, "Actual Price does not match Expected Price");




    }

    @Test(priority = 3)
    public void verifyAddToCartAndRemoveCartBehaviour() throws IOException {
     homePage.clickOnAddToCart(0);
     homePage.clickProductTitle(0);
     productDisplayPage= new ProductDisplayPage(driver);
     Assert.assertTrue(productDisplayPage.isRemoveCartDisplayed(),"The comparison for add to cart between the page is broken");

    }

    @Test(priority = 4)
    public void verifyBackToProductButton() throws IOException {
        homePage.clickProductTitle(0);
        productDisplayPage= new ProductDisplayPage(driver);
        productDisplayPage.clickOnBackToProductButton();
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(),"Back to product button sucessfully loads home page ");

    }

    @Test(priority = 5)
    public void verifyAddToCartButtonInProductDisplyPage() throws IOException {
        homePage.clickProductTitle(0);
        productDisplayPage= new ProductDisplayPage(driver);
        productDisplayPage.clickOnAddToCart();
        homePage.clickOnCartButton();
        cartPage= new CartPage(driver);
        Assert.assertNotNull(cartPage.getProductTitleInCart(0), "Product title element should be available");

    }

    @Test(priority = 6)
    public void verifyRemoveCartButtonInProductDisplyPage() throws IOException {
        homePage.clickProductTitle(0);
        productDisplayPage= new ProductDisplayPage(driver);
        productDisplayPage.clickOnAddToCart();
        productDisplayPage.clickRemoveCart();
        homePage.clickOnCartButton();
        cartPage= new CartPage(driver);
        Assert.assertNull(cartPage.getProductTitleInCart(0), "Product title element should not be available in an empty cart");

    }

    @Test(priority = 7)
    public void verifyHomePageDetails() throws InterruptedException {
        homePage.clickProductTitle(0);
        productDisplayPage= new ProductDisplayPage(driver);
        String actualPageTitle = productDisplayPage.retrieveProductDisplayPageTitle();
        System.out.println(actualPageTitle);
        String expectedPageTitle = dataProp.getProperty("PageTitle");
        String actualPageURL = productDisplayPage.retrieveProductDisplayURL();
        String expectedPageURL = dataProp.getProperty("Product(0).URL");
        System.out.println(actualPageURL);
        Assert.assertEquals(actualPageTitle,expectedPageTitle,"Expected page title is missing");
        Assert.assertEquals(actualPageURL,expectedPageURL, "Expected page URL is missing");
    }


}
