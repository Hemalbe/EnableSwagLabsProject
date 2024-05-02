package com.swaglabs.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    //objects
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='error-message-container error']/h3")
    private WebElement usernamePasswordNotMatchingWarning;

    @FindBy(xpath = "//div[@class='login_logo']")
    private WebElement loginPageLogo;

    @FindBy(css = "input[placeholder='Username']")
    private WebElement usernamePlaceholder;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement passwordPlaceholder;

    @FindBy(xpath = "//button[@class='error-button']//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")
    private WebElement errorCloseButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Actions

    public HomePage clickLoginButton() {
        loginButton.click();
        return new HomePage(driver);
    }

    public String retrieveUsernamePasswordNotMatchingWarningText() {

        String warningText = usernamePasswordNotMatchingWarning.getText();
        return warningText;

    }

    public HomePage Login(String username, String password) {
        usernameField.sendKeys(username);
//        Thread.sleep(3000);
        passwordField.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);

    }

    public boolean getDisplayStatusOfLoginPageLogo() {

        return loginPageLogo.isDisplayed();

    }

    public HomePage loginUsingKeyboardTabs(String username, String password) {
        Actions actions = new Actions(driver);
        usernameField.sendKeys(username);
        actions.sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.ENTER)
                .build().perform();
        return new HomePage(driver);

    }

    public boolean placeHolderValuesAreDisplayed() {

    return usernamePlaceholder.isDisplayed() & passwordPlaceholder.isDisplayed();

    }

    public boolean errorCloseButton() {

        errorCloseButton.click();

        return false;
    }

    public String retrieveLoginPageTitle() {


        return driver.getTitle();
    }

    public String retrieveLoginPageURL() {

        return driver.getCurrentUrl();

    }


}
