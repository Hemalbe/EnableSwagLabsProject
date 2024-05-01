package com.swaglabs.qa.testcases;

import com.swaglabs.qa.base.Base;
import com.swaglabs.qa.pages.SeachHomePage;

import com.swaglabs.qa.pages.SearchLoginPage;
import com.swaglabs.qa.pages.SearchPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class SearchPageTest extends Base {

    SearchPage searchPage;
    SeachHomePage seachHomePage;
    SearchLoginPage searchLoginPage;

    public SearchPageTest(){
        super();
    }


    public WebDriver driver;

    @BeforeMethod
    public void Setup() {

        driver = initializeBrowserSeachAndOpenApplicationURL(prop.getProperty("browserName"));
        seachHomePage = new SeachHomePage(driver);
    }


    @AfterMethod
    public void tearDown() {

        driver.quit();

    }



    @Test(priority =1)
    public void VerifySearchWithValidProduct() {
        searchPage= seachHomePage.SearchForAProduct(dataProp.getProperty("validProduct"));
        Assert.assertTrue(searchPage.isPresentSerachValue(), "Valid product HP is not Displayed");

    }

    @Test(priority =2)
    public void VerifySearchWithInvalidProduct() {
        searchPage= seachHomePage.SearchForAProduct(dataProp.getProperty("invalidProduct"));
        System.out.println(searchPage.getProductCriteriaNotMetWarning());
        Assert.assertEquals(searchPage.getProductCriteriaNotMetWarning(),dataProp.getProperty("InvalidProductWarning"), "Product not found");

    }


    @Test(priority =3, dependsOnMethods= {"VerifySearchWithInvalidProduct"})
    public void VerifySearchWithEmptyProduct() {

        searchPage = seachHomePage.clickOnSerachOption();
        Assert.assertEquals(searchPage.getProductCriteriaNotMetWarning(),dataProp.getProperty("noProductTextInSearch"), "Product not found");

    }

    @Test(priority =4)
    public void VerifySearchWithValidProductAfterLogingIn() {
        searchLoginPage= new SearchLoginPage(driver);
        seachHomePage.naviagteToLoginPage();
        searchLoginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
        searchPage= seachHomePage.SearchForAProduct(dataProp.getProperty("validProduct"));
        Assert.assertTrue(searchPage.isPresentSerachValue(), "Valid product HP is not Displayed");

    }

    @Test(priority =5)
    public void VerifySearchValueProvidesMoreThanOneValue() {
        searchPage = seachHomePage.SearchForAProduct(dataProp.getProperty("validProduct1"));
        int numberOfProductsDisplayed = searchPage.getProductTitles().size();
        Assert.assertTrue(numberOfProductsDisplayed > 2, "Less than two products displayed");
    }

    @Test(priority =6)
    public void testSearchPlaceholders() {
        searchPage = seachHomePage.clickOnSerachOption();
        String expectedPlaceholder = dataProp.getProperty("ExpectedSearchPlaceHolder");
        String actualPlaceholder = searchPage.getSearchInputPlaceholder();
        Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "Placeholder text is incorrect.");
    }

    @Test(priority =7)
    public void testSearchUsingCriteria() {
        searchPage = seachHomePage.clickOnSerachOption();
        searchPage.enterSearchText(dataProp.getProperty("validProduct"));  // Assuming "HP" is the product name from test data
        searchPage.clickSearchButton();
        boolean isDisplayed = searchPage.isPresentSerachValue();
        Assert.assertTrue(isDisplayed, "Searched product is not displayed in the search results.");
    }

    @Test(priority =8)
    public void testSearchUsingProductDescription() {
        searchPage = seachHomePage.clickOnSerachOption();
        searchPage.enterSearchText(dataProp.getProperty("TextFromProductDescription"));
        searchPage.checkDescriptionSearch();
        searchPage.clickSearchButton();
        boolean isDisplayed = searchPage.isProductDisplayed(dataProp.getProperty("validProduct2"));
        Assert.assertTrue(isDisplayed, "Product with the specified text in its description is not displayed.");
    }

    @Test(priority =9)
    public void testSearchByCategoryWithValid() {
        searchPage = seachHomePage.clickOnSerachOption();
        searchPage.enterSearchText(dataProp.getProperty("validProduct2"));
        searchPage.selectCategory(dataProp.getProperty("ValidMacCategory"));
        searchPage.clickSearchButton();
        Assert.assertTrue(searchPage.isProductDisplayed("iMac"), "iMac should be displayed when searching in the 'Mac' category.");

 }

    @Test(priority =10)
    public void testSearchByCategoryWithInvalid() {
        searchPage = seachHomePage.clickOnSerachOption();
        searchPage.enterSearchText(dataProp.getProperty("validProduct2"));
        searchPage.selectCategory(dataProp.getProperty("InValidPCCategory"));
        searchPage.clickSearchButton();
        Assert.assertTrue(searchPage.isNoResultsDisplayed(), "'There is no product that matches the search criteria' should be displayed when searching in the wrong category.");
    }


    @Test(priority =11)
    public void testSearchWithSubcategories() {
        searchPage = seachHomePage.clickOnSerachOption();
        searchPage.enterSearchText(dataProp.getProperty("validProduct2"));
        searchPage.selectCategory(dataProp.getProperty("ParentCategory"));
        searchPage.clickSearchButton();
        searchPage.selectSearchInSubcategories();
        searchPage.clickSearchButton();
        Assert.assertTrue(searchPage.isProductDisplayed(dataProp.getProperty("validProduct2")), "iMac should be displayed when searching in subcategories.");
    }


}
