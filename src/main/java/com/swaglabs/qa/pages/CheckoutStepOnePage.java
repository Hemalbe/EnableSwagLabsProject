package com.swaglabs.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepOnePage {

    WebDriver driver;

    @FindBy(id="first-name")
    private WebElement firstNameField;

    @FindBy(xpath="//span[@class='title']")
    private WebElement pageHeading;

    @FindBy(id="last-name")
    private WebElement lastNameField;

    @FindBy(id="postal-code")
    private WebElement postalCodeField;

    @FindBy(id="cancel")
    private WebElement cancelButton;

    @FindBy(id="continue")
    private WebElement continueButton;

    @FindBy(xpath="//h3[@data-test='error']")
    private WebElement WarningForEmptyField;



    public CheckoutStepOnePage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    public void enterFirstName(String firstname){
        firstNameField.sendKeys(firstname);
    }

    public boolean isFirstNameDisplay(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the checkout button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField));

        return firstNameField.isDisplayed();
    }

    public void enterLastName(String lastname){
        lastNameField.sendKeys(lastname);
    }

    public void enterPostalCode(String postalcode){
        postalCodeField.sendKeys(postalcode);
    }

    public void enterAddress(String Firstname, String Lastname, String PostalCode){
        firstNameField.sendKeys(Firstname);
        lastNameField.sendKeys(Lastname);
        postalCodeField.sendKeys(PostalCode);
        clickOnContinueButton();
    }

    public CartPage clickOnCancelButton(){
        cancelButton.click();
        return new CartPage(driver);
    }

    public void clickOnContinueButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the checkout button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField));

        continueButton.click();
    }

    public String retrieveEmptyFieldWarningText() {

        return WarningForEmptyField.getText();

    }



    public boolean getAllTheElementDisplayStatus() {
        boolean allElementsDisplayed = pageHeading.isDisplayed() &&
                firstNameField.isDisplayed() &&
                lastNameField.isDisplayed()&&
                postalCodeField.isDisplayed()&&
                cancelButton.isDisplayed()&&
                continueButton.isDisplayed();

        return allElementsDisplayed;
    }
}
