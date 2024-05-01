package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.HomePage;
import com.swaglabs.qa.pages.LoginPage;
import com.swaglabs.qa.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends Base {

    LoginPage loginPage;
    HomePage homePage;

    public LogoutTest(){
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
    public void verifyLogoutHamburgerMenu() {
        homePage.clickOnHamburgerMenu();
        homePage.clickOnLogoutButton();
        Assert.assertTrue(loginPage.getDisplayStatusOfLoginPageLogo(),"Login page is not displayed");

    }

    @Test(priority = 2)
    public void verifyLogoutByClosingSession() {
        driver.close();
        setUp();
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(),"Home page is not displyed");

    }

    @Test(priority = 3)
    public void verifyLogoutByUsingBrowserBackButton() {
        homePage.clickOnHamburgerMenu();
        homePage.clickOnLogoutButton();
        driver.navigate().back();
        String actualWarningMessage= loginPage.retrieveUsernamePasswordNotMatchingWarningText();
        String expectedWarningMessage= dataProp.getProperty("InventoryAccessErrorWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displyed");

    }

    @Test(priority = 4)
    public void verifyLogoutAndLogin() {
        homePage.clickOnHamburgerMenu();
        homePage.clickOnLogoutButton();
        loginPage.Login(prop.getProperty("SValidUserName"),prop.getProperty("SValidPassword"));
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(),"App logo is not visible");

    }

    @Test(priority = 5, dataProvider = "ValidCredentialsSupplier", dataProviderClass = Utilities.class)
    public void verifyLogoutUsingMultipleUsers(String username, String password) {
        homePage.clickOnHamburgerMenu();
        homePage.clickOnLogoutButton();
        homePage= loginPage.Login(username, password);
        homePage.clickOnHamburgerMenu();
        homePage.clickOnLogoutButton();
        Assert.assertTrue(loginPage.getDisplayStatusOfLoginPageLogo(),"Login page is not displayed");

    }

}
