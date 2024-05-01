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
    @FindBy(id="user-name")
    private WebElement usernameFeild;

    @FindBy(id="password")
    private WebElement passwordFeild;

    @FindBy(id="login-button")
    private WebElement loginButton;

    @FindBy(xpath="//div[@class=\"error-message-container error\"]/h3")
    private WebElement usernamePasswordNotMatchingWarning;

    @FindBy(xpath="//div[@class=\"login_logo\"]")
    private WebElement loginPageLogo;

    @FindBy(css="input[placeholder=\"Username\"]")
    private WebElement usernamePlaceholder;

    @FindBy(css="input[placeholder=\"Password\"]")
    private WebElement passwordPlaceholder;

    @FindBy(xpath="//button[@class='error-button']//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")
    private WebElement errorCloseButton;


    public LoginPage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }
    //Actions

    public void enterUsername(String username) {

        usernameFeild.sendKeys(username);

    }

    public void enterPassword(String password) {

        passwordFeild.sendKeys(password);

    }

    public HomePage clickLoginButton() {
        loginButton.click();
        return new HomePage(driver);
    }

    public String retrieveUsernamePasswordNotMatchingWarningText() {

       String warningText=usernamePasswordNotMatchingWarning.getText();
       return warningText;

    }

    public HomePage Login(String username, String password) {
        usernameFeild.sendKeys(username);
        passwordFeild.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);

    }

    public boolean getDisplayStatusOfLoginPageLogo() {

        return loginPageLogo.isDisplayed();

    }

    public HomePage loginUsingKeyboardTabs(String username, String password)  {
        Actions actions = new Actions(driver);
        usernameFeild.sendKeys(username);
        actions.sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.ENTER)
                .build().perform();
        return new HomePage(driver);

    }

    public boolean placeHolderValuesAreDisplayed() {

        boolean userNameDisplayStatus= usernamePlaceholder.isDisplayed();
        boolean passwordDisplayStatus= passwordPlaceholder.isDisplayed();
        return userNameDisplayStatus & passwordDisplayStatus;

    }

    public boolean errorCloseButton() {

        errorCloseButton.click();

        return false;
    }

    public String retrieveLoginPageTitle() {

        String LoginPageTitle = driver.getTitle();


        return LoginPageTitle;
    }
    public String retrieveLoginPageURL() {

        String LoginPageURL = driver.getCurrentUrl();

        return LoginPageURL;

    }




}
