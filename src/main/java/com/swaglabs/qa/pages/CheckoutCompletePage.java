package com.swaglabs.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {

    WebDriver driver;


    @FindBy(xpath="//img[@alt='Pony Express']")
    private WebElement orderCompleteImageIcon;

    @FindBy(xpath="//span[@class='title']")
    private WebElement pageHeading;

    @FindBy(xpath="//h2[@class='complete-header']")
    private WebElement orderCompleteTitle;

    @FindBy(xpath="//div[@class='complete-text']")
    private WebElement orderCompleteDescription;

    @FindBy(xpath="//button[@id='back-to-products']")
    private WebElement backToHomeButton;


    public CheckoutCompletePage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isOrderCompleteImageIconDisplayed(){

        return orderCompleteImageIcon.isDisplayed();

    }

    public boolean isOrderCompleteTitleDisplayed(){

        return orderCompleteTitle.isDisplayed();

    }

    public boolean isOrderCompleteDescriptionDisplayed(){

        return orderCompleteDescription.isDisplayed();

    }

    public HomePage clickOnBackToHome(){

       backToHomeButton.click();
       return new HomePage(driver);
    }

    public boolean getAllTheElementDisplayStatus() {
        boolean allElementsDisplayed = pageHeading.isDisplayed() &&
                orderCompleteImageIcon.isDisplayed() &&
                orderCompleteTitle.isDisplayed()&&
                orderCompleteDescription.isDisplayed()&&
                backToHomeButton.isDisplayed();

        return allElementsDisplayed;
    }


}
