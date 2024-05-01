package com.swaglabs.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDisplayPage {

    WebDriver driver;

    @FindBy(xpath="//img[@class='inventory_details_img']")
    private WebElement productDisplayPageImageTitle;

    @FindBy(xpath="//div[@class='inventory_details_name large_size']")
    private WebElement productDisplayPageTitle;

    @FindBy(xpath=".//div[@class='inventory_details_desc large_size']")
    private WebElement productDisplayPageDescription;

    @FindBy(xpath=".//div[@class='inventory_details_price']")
    private WebElement productDisplayPagePrice;

    @FindBy(xpath="//button[@id='back-to-products']")
    private WebElement backToProductButton;

    @FindBy(xpath="//button[contains(@data-test, 'add-to-cart')]")
    private WebElement addToCart;

    @FindBy(xpath="//button[contains(@data-test, 'remove')]")
    private WebElement remove;



    public ProductDisplayPage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }


    public boolean IsProductDisplayPageImageTitleDisplayed() {

        return productDisplayPageImageTitle.isDisplayed();

    }

    public boolean IsProductDisplayPageTitleDisplayed() {

        return productDisplayPageTitle.isDisplayed();

    }

    public boolean IsProductDisplayPagePriceDisplayed() {

        return productDisplayPagePrice.isDisplayed();

    }

    public boolean IsProductDisplayPageDescriptionDisplayed() {

        return productDisplayPageDescription.isDisplayed();

    }

    public boolean getAllTheElementDisplayStatus() {
        boolean allElementsDisplayed = productDisplayPageImageTitle.isDisplayed() &&
                productDisplayPageTitle.isDisplayed() &&
                productDisplayPageDescription.isDisplayed()&&
                productDisplayPageTitle.isDisplayed()&&
                productDisplayPagePrice.isDisplayed()&&
                backToProductButton.isDisplayed()&&
                addToCart.isDisplayed();

        return allElementsDisplayed;
    }

    public String getProductDisplayPageTitle(){
        return productDisplayPageTitle.getText();
    }

    public String getProductDisplayPageDescription(){
        return productDisplayPageDescription.getText();

    }

    public String getProductDisplayPagePrice(){
        return productDisplayPagePrice.getText();

    }

    public String getProductDisplayImageTitle(){
       return productDisplayPageImageTitle.getAttribute("src");

    }

    public void clickRemoveCart(){

        remove.click();

    }

    public void clickOnBackToProductButton(){

        backToProductButton.click();

    }

    public void clickOnAddToCart(){

        addToCart.click();

    }


    public boolean isRemoveCartDisplayed(){
        return remove.isDisplayed();
    }

    public boolean isAddToCartDisplayed(){
        return addToCart.isDisplayed();
    }

    public String retrieveProductDisplayPageTitle() {

        String ProductDisplayTitle = driver.getTitle();


        return ProductDisplayTitle;
    }
    public String retrieveProductDisplayURL() {

        String ProductDisplayURL = driver.getCurrentUrl();

        return ProductDisplayURL;

    }








}
