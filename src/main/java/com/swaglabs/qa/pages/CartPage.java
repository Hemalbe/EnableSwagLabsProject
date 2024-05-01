package com.swaglabs.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    WebDriver driver;
    //objects

    @FindBy(xpath="//div[@class='cart_quantity_label']")
    private WebElement quantity;

    @FindBy(xpath="//div[@class='cart_desc_label']")
    private WebElement description;

    @FindBy(id="continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id="checkout")
    private WebElement checkoutButton;

    @FindBy(xpath=".//div[@class='cart_quantity']")
    private List<WebElement> cartQuantityList;

    @FindBy(xpath=".//div[@class='cart_item_label']")
    private List<WebElement> cartTitleList;

    @FindBy(xpath="//div[@class='cart_item']")
    private List<WebElement> itemList;

    @FindBy(xpath=".//div[@class='inventory_item_desc']")
    private List<WebElement> descriptionList;

    @FindBy(xpath=".//div[@class='inventory_item_price']")
    private List<WebElement> priceList;

    @FindBy(xpath=".//button[contains(@data-test, 'add-to-cart')]")
    private List<WebElement> addToCartList;

    @FindBy(xpath=".//button[contains(@data-test, 'remove')]")
    private List<WebElement> removeList;



    public CartPage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }


    public boolean isQuantityDisplayed(){

        return quantity.isDisplayed();

    }

    public boolean isDescriptionDisplayed(){

        return description.isDisplayed();

    }

    public boolean isCheckoutButtonDisplayed(){

        return checkoutButton.isDisplayed();

    }

    public boolean clickOnRemove(int index) {
        if (index >= 0 && index < removeList.size()) {
            WebElement addToCartElement = removeList.get(index);
            addToCartElement.click();
            return true; // Click operation successful
        } else {
            return false; // Index out of bounds
        }
    }



    public HomePage clickOnContinueShoppingButton(){

        continueShoppingButton.click();
        return new HomePage(driver);

    }

    public void clickOnCheckoutButton(){

        // Create a WebDriverWait instance with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        // Wait for the checkout button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

        // Click on the checkout button
        checkoutButton.click();
    }

    public String getCartQuantity(int index) {
        if (index >= 0 && index < cartQuantityList.size()) {
            WebElement cartQuantityElement = cartQuantityList.get(index);
            return cartQuantityElement.getText();
        }else {
            return "Index out of bounds";
        }
    }

    public WebElement getProductTitleInCart(int index) {
        if (index >= 0 && index < cartTitleList.size()) {
            WebElement cartTitleElement = cartTitleList.get(index);
            System.out.println("Get Image Title"+ cartTitleElement.getText());
            return cartTitleElement;
        } else {
            return null;
        }
    }

    public boolean getAllTheElementInEmptyCartDisplayStatus() {
        boolean allEmptyCartElementsDisplayed = quantity.isDisplayed() &&
                description.isDisplayed() &&
                continueShoppingButton.isDisplayed()&&
                checkoutButton.isDisplayed();

        return allEmptyCartElementsDisplayed;
    }

    public boolean getAllTheElementInItemsCartDisplayStatus() {
        boolean allItemsCartElementsDisplayed = quantity.isDisplayed() &&
                description.isDisplayed() &&
                continueShoppingButton.isDisplayed()&&
                checkoutButton.isDisplayed();

        List<String> itemDetailsList = itemDetails();
        for (String itemDetails : itemDetailsList) {
            if (!itemDetails.contains("Item Name:") ||
                    !itemDetails.contains("Item Price:") ||
                    !itemDetails.contains("Description:")) {
                allItemsCartElementsDisplayed = false;
                break; // Exit loop early if any item is not visible
            }
        }

        return allItemsCartElementsDisplayed;
    }

    public List<String> itemDetails() {
        List<String> itemDetailsList = new ArrayList<>();

        if (!itemList.isEmpty()) {
            for (int i = 0; i < itemList.size(); i++) {
                WebElement item = itemList.get(i);
                WebElement titleElement = cartTitleList.get(i);
                WebElement priceElement = priceList.get(i);
                WebElement descriptionElement = descriptionList.get(i);
                WebElement  cartQuantityElement = cartQuantityList.get(i);
                WebElement removeCartElement = removeList.get(i);


                String itemName = titleElement.getText();
                String itemPrice = priceElement.getText();
                String description = descriptionElement.getText();
                boolean quantity = cartQuantityElement.isDisplayed();
                boolean removeCart = removeCartElement.isDisplayed();

                // Assert quantity and removeCart visibility
                Assert.assertTrue(quantity, "Quantity is not displayed for item: " + itemName);
                Assert.assertTrue(removeCart, "Remove button is not displayed for item: " + itemName);

                String itemDetails = "Item " + (i + 1) + ":\n" +
                        "Item Name: " + itemName + "\n" +
                        "Item Price: " + itemPrice + "\n" +
                        "Description: " + description + "\n"+
                        "Quantity Displayed: " + quantity + "\n" +
                        "Remove Button Displayed: " + removeCart + "\n";

                itemDetailsList.add(itemDetails);
            }
        } else {
            System.out.println("Item list is empty.");
        }

        return itemDetailsList;
    }


}
