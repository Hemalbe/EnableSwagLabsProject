package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.CartPage;
import com.swaglabs.qa.pages.HomePage;
import com.swaglabs.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

public class HamburgerTest extends Base {

    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;

    public HamburgerTest(){
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        loginPage = new LoginPage(driver);
        homePage= loginPage.Login(prop.getProperty("SValidUserName"),prop.getProperty("SValidPassword"));
        homePage.clickOnHamburgerMenu();
    }
    @AfterMethod
    public void tearDown(){

        driver.quit();
    }

    // Adding the priority we set the test case order else it will run with an alphabetical order.
    @Test(priority = 1)
    public void verifyHamburgerMenuOptions() throws InterruptedException {
        // First, retrieve the result map from the utility method
        Map<String, Boolean> result = homePage.verifyHamburgerMenuOptions();

        // Now perform assertions on the retrieved result
        Assert.assertTrue(result.get("logoutButton"), "Logout button should be visible.");
        Assert.assertTrue(result.get("allItemsOption"), "All Items option should be visible.");
        Assert.assertTrue(result.get("aboutOption"), "About option should be visible.");
        Assert.assertTrue(result.get("resetAppState"), "Reset App State should be visible.");
    }

    @Test(priority = 2)
    public void verifyAllItemOptions() throws InterruptedException {
        homePage.clickOnCartButton();
        homePage.clickOnHamburgerMenu();
        homePage.clickOnAllItemsOption();
        Assert.assertTrue(homePage.getDisplayStatusOfHomePageAppLogo(), "HomePage App logo is not visible");
  }

    @Test(priority = 3)
    public void verifyAboutOptions() {
        homePage.clickOnAboutOption();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(dataProp.getProperty("AboutUrl")));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, dataProp.getProperty("AboutUrl"), "The browser did not navigate to the expected URL.");
    }

    @Test(priority = 4)
    public void verifyResetAppOptions() {
        homePage.clickOnAddToCart(0);
        homePage.clickOnResetAppStateOption();
        Assert.assertTrue(homePage.isElementNotPresent(homePage.getCartCountElement()),"App has been reset");

    }
    @Test(priority = 5)
    public void verifyCloseOptions() throws InterruptedException {
        homePage.clickOnHamburgerMenuCloseButton();
        Assert.assertTrue(homePage.isElementNotPresent(homePage.getAllItemsOptionElement()),"Close button is sucessful");

    }


}
