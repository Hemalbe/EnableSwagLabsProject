package com.swaglabs.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage {

    WebDriver driver;

    @FindBy(xpath="//div[normalize-space()='Payment Information:']")
    private WebElement paymentInformationTitle;

    @FindBy(xpath="//div[normalize-space()='SauceCard #31337']")
    private WebElement cardInformation;


    @FindBy(xpath="//div[normalize-space()='Shipping Information:']")
    private WebElement shippingInformationTitle;

    @FindBy(xpath="//div[normalize-space()='Free Pony Express Delivery!']")
    private WebElement shippingAddress;

    @FindBy(xpath="//div[normalize-space()='Price Total']")
    private WebElement totalPriceTitle;

    @FindBy(xpath="//span[@class='title']")
    private WebElement pageHeading;

    @FindBy(xpath="//div[@class='summary_subtotal_label']")
    private WebElement itemTotal;

    @FindBy(xpath="//div[@class='summary_tax_label']")
    private WebElement tax;

    @FindBy(xpath="//div[@class='summary_total_label']")
    private WebElement totalSum;

    @FindBy(xpath="//button[@id='finish']")
    private WebElement finishButton;

    @FindBy(id="cancel")
    private WebElement cancelButton;

    public CheckoutStepTwoPage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isPaymentInformationTitleDisplayed(){

        return paymentInformationTitle.isDisplayed();

    }

    public boolean isCardInformationDisplayed(){

        return cardInformation.isDisplayed();

    }

    public boolean isShippingInformationTitleDisplayed(){

        return shippingInformationTitle.isDisplayed();

    }

    public boolean isShippingAddressDisplayed(){

        return shippingAddress.isDisplayed();

    }

    public boolean isTotalPriceTitleDisplayed(){

        return totalPriceTitle.isDisplayed();

    }

    public CheckoutCompletePage clickOnFinish(){

        finishButton.click();
        return new CheckoutCompletePage(driver);
    }

    public boolean getAllTheElementDisplayStatus() {
        boolean allElementsDisplayed = pageHeading.isDisplayed() &&
                paymentInformationTitle.isDisplayed() &&
                cardInformation.isDisplayed()&&
                shippingInformationTitle.isDisplayed()&&
                shippingAddress.isDisplayed()&&
                totalPriceTitle.isDisplayed()&&
                itemTotal.isDisplayed()&&
                tax.isDisplayed()&&
                totalSum.isDisplayed()&&
                finishButton.isDisplayed()&&
                cancelButton.isDisplayed();

        return allElementsDisplayed;
    }
}
