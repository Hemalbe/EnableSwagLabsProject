package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.HomePage;
import com.swaglabs.qa.pages.LoginPage;
import com.swaglabs.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class LoginTest extends Base {
    // to load the properties we need to add constructor

    LoginPage loginPage;
    HomePage homePage;

    public LoginTest() {
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }

    // Adding the priority we set the test case order else it will run with an alphabetical order.
    @Test(priority = 1, dataProvider = "ValidCredentialsSupplier", invocationCount = 2, dataProviderClass = Utilities.class)
    public void verifyLoginWithValidCredentials(String username, String password) {
        homePage = loginPage.Login(username, password);
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(), "HomePage App logo is not visible");
    }

//    public void verifyLoginWithValidCredentials(String username, String password) {
//        loginPage.enterUsername(username);
//        loginPage.enterPassword(password);
//        loginPage.clickLoginButton();
//        driver.findElement(By.id("user-name")).sendKeys(username);
//        driver.findElement(By.id("password")).sendKeys(password);
//        driver.findElement(By.id("login-button")).click();
//        Assert.assertTrue(loginPage.isAppLogoDisplyed(),"App logo is not visible");
//
//    }

    //    @DataProvider(name="ValidCredentialsSupplier")
//    public Object[][] supplyTestData(){
//        Object[][] data ={{"problem_user","secret_sauce"},
//                    {"problem_user","secret_sauce"},
//                    {"problem_user","secret_sauce"},
//                    {"performance_glitch_user", "secret_sauce"}};
//        return data;
//    }

    @Test(priority = 2)
    public void verifyLoginWithInValidCredentials() throws InterruptedException {
        loginPage.Login(dataProp.getProperty("InvalidUserName"), dataProp.getProperty("InvalidPassword"));
        String actualWarningMessage = loginPage.retrieveUsernamePasswordNotMatchingWarningText();
        String expectedWarningMessage = dataProp.getProperty("EmailPasswordNoMatchWarningMessage");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displyed");
    }

    @Test(priority = 3)
    public void verifyLoginWithInValidUsernameAndValidPassword() throws InterruptedException {
        loginPage.Login(dataProp.getProperty("InvalidUserName"), prop.getProperty("SValidPassword"));
        String actualWarningMessage = loginPage.retrieveUsernamePasswordNotMatchingWarningText();
        String expectedWarningMessage = dataProp.getProperty("EmailPasswordNoMatchWarningMessage");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displyed");
    }

    @Test(priority = 4)
    public void verifyLoginWithValidUsernameAndInValidPassword() throws InterruptedException {
        loginPage.Login(prop.getProperty("SValidUserName"), dataProp.getProperty("InvalidPassword"));
        String actualWarningMessage = loginPage.retrieveUsernamePasswordNotMatchingWarningText();
        String expectedWarningMessage = dataProp.getProperty("EmailPasswordNoMatchWarningMessage");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displyed");
    }

    @Test(priority = 5)
    public void verifyLoginWithNoCredential() throws InterruptedException {
        loginPage.clickLoginButton();
        String actualWarningMessage = loginPage.retrieveUsernamePasswordNotMatchingWarningText();
        String expectedWarningMessage = dataProp.getProperty("UsernameRequiredWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displyed");
    }

    @Test(priority = 6)
    public void verifyLoginUsingKeyBoardTabs() {
        homePage = loginPage.loginUsingKeyboardTabs(prop.getProperty("SValidUserName"), prop.getProperty("SValidPassword"));
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(), "HomePage App logo is not visible");
    }

    @Test(priority = 7)
    public void verifyLoginPlaceHolderAreDisplayed() {
        loginPage.placeHolderValuesAreDisplayed();
    }

    @Test(priority = 8)
    public void verifyLoginAndLogoutUsingBrowserBackButtons() throws InterruptedException {
        homePage = loginPage.loginUsingKeyboardTabs(prop.getProperty("SValidUserName"), prop.getProperty("SValidPassword"));
        driver.navigate().back();
        driver.navigate().forward();
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(), "HomePage App logo is not visible");
    }

    @Test(priority = 9)
    public void verifyLoginUsingLockedOutCredentials() throws InterruptedException {
        homePage = loginPage.loginUsingKeyboardTabs(dataProp.getProperty("LockedOutUserName"), prop.getProperty("SValidPassword"));
        String actualWarningMessage = loginPage.retrieveUsernamePasswordNotMatchingWarningText();
        String expectedWarningMessage = dataProp.getProperty("LockedOutUserErrorWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed");
    }

    @Test(priority = 10)
    public void verifyLoginErrorCloseButton() throws InterruptedException {
        loginPage.Login(prop.getProperty("SValidUserName"), dataProp.getProperty("InvalidPassword"));
        Assert.assertFalse(loginPage.errorCloseButton(), "Error message still present after button click");
    }


    @Test(priority = 11)
    public void verifyLoginPageDetails() throws InterruptedException {
        String actualPageTitle = loginPage.retrieveLoginPageTitle();
        String expectedPageTitle = dataProp.getProperty("PageTitle");
        String actualPageURL = loginPage.retrieveLoginPageURL();
        String expectedPageURL = dataProp.getProperty("LoginPageURL");
        Assert.assertEquals(actualPageTitle,expectedPageTitle,"Expected page title is missing");
        Assert.assertEquals(actualPageURL,expectedPageURL, "Expected page URL is missing");
    }

//    @Test(priority = 11)
//    public void compareProductImages() throws IOException {
//        homePage = loginPage.loginUsingKeyboardTabs(prop.getProperty("SValidUserName"), prop.getProperty("SValidPassword"));
//
//        // URLs of the images to compare
//        String referenceImageUrl = "https://www.saucedemo.com/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg";
//        String actualImageUrl = homePage.referenceUrl();
//
//        // Download images from URLs
//        BufferedImage referenceImage = homePage.downloadImage(referenceImageUrl);
//        BufferedImage actualImage = homePage.downloadImage(actualImageUrl);
//
//        // Compare images
//        Assert.assertTrue(homePage.compareImages(referenceImage, actualImage), "Image is not matching");
//    }

//    @Test(priority = 12)
//    public void PrintDetails() {
//        homePage = loginPage.loginUsingKeyboardTabs(prop.getProperty("SValidUserName"), prop.getProperty("SValidPassword"));
//        homePage.selectFilter("az");
//       assertEquals(homePage.getProductTitle(0),dataProp.getProperty("Product(1).ItemName"),"the filter option is not working");
//
//   }

}

