package com.swaglabs.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchPage {

    WebDriver driver;

    //Objects
    @FindBy(linkText="HP LP3065")
    private WebElement serachValue;

    @FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
    private WebElement productCriteriaNotMetWarning;

    @FindBy(xpath = "//option[@value='list']")
    private WebElement listViewOption;

    @FindBy(xpath = "//option[@value='grid']")
    private WebElement gridViewOption;

    @FindBy(css = ".product-layout")
    private List<WebElement> products;

    @FindBy(xpath=".//div[@class='caption']/h4/a")
    private List<WebElement> productTitle;

    @FindBy(xpath=".//div[@class='caption']/p[@class='price']")
    private List<WebElement> productPrice;

    @FindBy(id="input-search")
    private WebElement searchInput;

    @FindBy(id="button-search")
    private WebElement searchButton;

    @FindBy(id="description")
    private WebElement descriptionCheckbox;

    @FindBy(name="category_id")
    private WebElement categoryDropdown;

    @FindBy(name = "sub_category")
    private WebElement subcategoriesCheckbox;







    public	SearchPage(WebDriver driver) {

        this.driver=driver;
        PageFactory.initElements(driver, this);



    }

    //Actions

    public boolean isPresentSerachValue() {

        return serachValue.isDisplayed();

    }

    public  String getProductCriteriaNotMetWarning() {

        return productCriteriaNotMetWarning.getText();

    }


    public List<WebElement> getProductTitles() {
        return productTitle;
    }

    public String getSearchInputPlaceholder() {
        return searchInput.getAttribute("placeholder");
    }

    public void enterSearchText(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }
    public void checkDescriptionSearch() {
        if (!descriptionCheckbox.isSelected()) {
            descriptionCheckbox.click();
        }
    }
    public void selectCategory(String category) {
        Select categorySelect = new Select(categoryDropdown);
        categorySelect.selectByVisibleText(category);
    }

    public void selectSearchInSubcategories() {
        if (!subcategoriesCheckbox.isSelected()) {
            subcategoriesCheckbox.click();
        }
    }

    public boolean isProductDisplayed(String productName) {
        try {
            WebElement product = driver.findElement(By.linkText(productName));
            return product.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoResultsDisplayed() {
        try {
            WebElement noResultsMessage = driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria')]"));
            return noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


}
