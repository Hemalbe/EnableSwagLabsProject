package com.swaglabs.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class HomePage {

    WebDriver driver;

    //objects
    @FindBy(xpath="//div[@class=\"app_logo\"]")
    private WebElement homePageAppLogo;

    @FindBy(xpath="//button[@id='react-burger-menu-btn']")
    private WebElement hamburgerMenu;

    @FindBy(id="logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(id="inventory_sidebar_link")
    private WebElement allItemsOption;

    @FindBy(id="about_sidebar_link")
    private WebElement aboutOption;

    @FindBy(id="reset_sidebar_link")
    private WebElement resetAppState;

    @FindBy(id="react-burger-cross-btn")
    private WebElement hamburgerMenuCloseButton;

    @FindBy(xpath="//span[@class='title']")
    private WebElement pageHeading;

    @FindBy(xpath="//select[@class='product_sort_container']")
    private WebElement filterOption;

    @FindBy(xpath="//a[@class='shopping_cart_link']")
    private WebElement cartButton;

    @FindBy(xpath="//a[@id='item_4_img_link']/img")
    private WebElement backPackImgElement;

    @FindBy(xpath="//*[@id=\"item_4_title_link\"]/div")
    private WebElement backPackTitleElement;

    @FindBy(xpath="//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/div")
    private WebElement backPackDescriptionElement;

    @FindBy(xpath="//div[@class='inventory_list']//div[1]//div[2]//div[2]//div[1]")
    private WebElement backPackPriceElement;


    @FindBy(id="add-to-cart-sauce-labs-backpack")
    private WebElement backPackAddToCartElement;

    @FindBy(id="remove-sauce-labs-backpack")
    private WebElement backPackRemoveElement;

    @FindBy(xpath="//div[@class='inventory_item']")
    private List<WebElement> itemList;

    @FindBy(xpath=".//img[@class='inventory_item_img']")
    private List<WebElement> imageList;

    @FindBy(xpath=".//div[@class='inventory_item_label']/a")
    private List<WebElement> titleList;

    @FindBy(xpath=".//div[@class='inventory_item_desc']")
    private List<WebElement> descriptionList;

    @FindBy(xpath=".//div[@class='inventory_item_price']")
    private List<WebElement> priceList;

    @FindBy(xpath=".//button[contains(@data-test, 'add-to-cart')]")
    private List<WebElement> addToCartList;

    @FindBy(xpath=".//button[contains(@data-test, 'remove')]")
    private List<WebElement> removeList;

    @FindBy(xpath="//a[normalize-space()='Twitter']")
    private WebElement twitterLogo;

    @FindBy(xpath="//a[normalize-space()='Facebook']")
    private WebElement facebookLogo;

    @FindBy(xpath="//a[normalize-space()='LinkedIn']")
    private WebElement linkedinLogo;

    @FindBy(xpath="//div[@class='footer_copy']")
    private WebElement footerText;

    @FindBy(xpath="//span[@class='shopping_cart_badge']")
    private WebElement cartCount;






    public HomePage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    //Actions

    public boolean getDisplayStatusOfHomePageAppLogo() {

        return homePageAppLogo.isDisplayed();

    }

    public boolean getAllTheElementDisplayStatus() {
        boolean allElementsDisplayed = homePageAppLogo.isDisplayed() &&
                pageHeading.isDisplayed() &&
                filterOption.isDisplayed()&&
                cartButton.isDisplayed()&&
                twitterLogo.isDisplayed()&&
                facebookLogo.isDisplayed()&&
                linkedinLogo.isDisplayed()&&
                footerText.isDisplayed();

        // Check the display status of multiple elements


        List<String> itemDetailsList = itemDetails();

        // Check if all elements in the item details are visible
        for (WebElement item : itemList) {
            if (!item.isDisplayed()) {
                allElementsDisplayed = false;
                break; // Exit loop early if any item is not visible
            }
        }

        return allElementsDisplayed;
    }

    public List<String> itemDetails() {
        List<String> itemDetailsList = new ArrayList<>();

        if (!itemList.isEmpty()) {
            for (int i = 0; i < itemList.size(); i++) {
                WebElement item = itemList.get(i);
                WebElement imageElement = imageList.get(i);
                WebElement titleElement = titleList.get(i);
                WebElement priceElement = priceList.get(i);
                WebElement descriptionElement = descriptionList.get(i);

                String imageURL = imageElement.getAttribute("src");
                String itemName = titleElement.getText();
                String itemPrice = priceElement.getText();
                String description = descriptionElement.getText();

                String itemDetails = "Item " + (i + 1) + ":\n" +
                        "Image URL: " + imageURL + "\n" +
                        "Item Name: " + itemName + "\n" +
                        "Item Price: " + itemPrice + "\n" +
                        "Description: " + description + "\n";

                itemDetailsList.add(itemDetails);
            }
        } else {
            System.out.println("Item list is empty.");
        }

        return itemDetailsList;
    }

    public void clickOnHamburgerMenu(){
        hamburgerMenu.click();
    }

    public void clickOnLogoutButton(){
        logoutButton.click();
    }

    public String referenceUrl(){

        String srcValue = backPackImgElement.getAttribute("src");
        System.out.println("Data URL: " + srcValue);
//        String actualImageUrl = "https://www.saucedemo.com/"+srcValue;
        return srcValue;
    }

    public BufferedImage downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        return ImageIO.read(url);
    }

    public boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        // Compare image width and height
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }

        // Compare individual pixels
        for (int y = 0; y < imgA.getHeight(); y++) {
            for (int x = 0; x < imgA.getWidth(); x++) {
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }




//    public List<String> printImageList() {
//        List<String> itemImageListList = new ArrayList<>();
//
//        if (!itemList.isEmpty()) {
//            for (int i = 0; i < itemList.size(); i++) {
//                WebElement item = itemList.get(i);
//                WebElement imageElement = imageList.get(i);
//
//                String imageURL = imageElement.getAttribute("src");
//
//                System.out.println("Item " + (i + 1) + ":");
//                System.out.println("Image URL: " + imageURL);
//
//                String itemDetails = "Item " + (i + 1) + ":\n";
//
//                itemImageListList.add(itemDetails);
//            }
//        }else{
//            System.out.println("Image list is empty.");
//        }
//
//        return itemImageListList;
//    }

    public String getImageUrl(int index) {
        if (index >= 0 && index < imageList.size()) {
            WebElement imageElement = imageList.get(index);
            System.out.println("Get Singe Image URl "+ imageElement.getAttribute("src"));
            return imageElement.getAttribute("src");
        } else {
            return "Index out of bounds";
        }
    }

    public String getProductTitle(int index) {
        if (index >= 0 && index < titleList.size()) {
            WebElement titleElement = titleList.get(index);
            System.out.println("Get Image Title"+ titleElement.getText());
            return titleElement.getText();
        } else {
            return "Index out of bounds";
        }
    }

    public void clickProductTitle(int index) {
        if (index >= 0 && index < titleList.size()) {
            WebElement titleElement = titleList.get(index);
            System.out.println("Get Image Title"+ titleElement.getText());
            titleElement.click();
        }
    }

    public String getProductDescription(int index) {
        if (index >= 0 && index < descriptionList.size()) {
            WebElement descriptionElement = descriptionList.get(index);
            System.out.println("Get description Title"+ descriptionElement.getText());
            return descriptionElement.getText();
        } else {
            return "Index out of bounds";
        }
    }

    public String getProductPriceList(int index) {
        if (index >= 0 && index < priceList.size()) {
            WebElement priceElement = priceList.get(index);
            System.out.println("Get price"+ priceElement.getText());
            return priceElement.getText();
        } else {
            return "Index out of bounds";
        }
    }

    public boolean clickOnAddToCart(int index) {
        if (index >= 0 && index < addToCartList.size()) {
            WebElement addToCartElement = addToCartList.get(index);
            addToCartElement.click();
            return true; // Click operation successful
        } else {
            return false; // Index out of bounds
        }
    }


    public boolean clickOnRemove(int index) {
        if (index >= 0 && index < removeList.size()) {
            WebElement removeElement = removeList.get(index);
            removeElement.click();
            return true; // Click operation successful
        } else {
            return false; // Index out of bounds
        }
    }

//    public void selectFilter(){
//        Actions actions = new Actions(driver);
//        new Select(filterOption);
//        select.selectByValue("s");//"az","za","lohi","hilo"
//        actions.sendKeys(Keys.ENTER);


//        // Click on the select element to open the dropdown
//        filterOption.click();
//        String filterOptionTextValue= filterOption.getText();
//
//        // Locate the option element by its visible text
//        WebElement optionElement = driver.findElement(By.xpath("//option[text()='" + filterOptionTextValue + "']"));
//        optionElement.click();

        // Click on the option element
//        optionElement.click();

//    }

    public void selectFilter(String filterOptionValue) {
        // Create a Select object using the filterOption WebElement
        Select select = new Select(filterOption);

        // Select the option by its value
        select.selectByValue(filterOptionValue);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER);


    }

    public boolean isYourCartTitleDisplayed(){

        return pageHeading.isDisplayed();

    }


    public Map<String, Boolean> verifyHamburgerMenuOptions() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set appropriate timeout
        Map<String, Boolean> visibilityStatus = new HashMap<>();

        // Wait for each element to be visible and then check their visibility
        visibilityStatus.put("logoutButton", wait.until(ExpectedConditions.visibilityOf(logoutButton)).isDisplayed());
        visibilityStatus.put("allItemsOption", wait.until(ExpectedConditions.visibilityOf(allItemsOption)).isDisplayed());
        visibilityStatus.put("aboutOption", wait.until(ExpectedConditions.visibilityOf(aboutOption)).isDisplayed());
        visibilityStatus.put("resetAppState", wait.until(ExpectedConditions.visibilityOf(resetAppState)).isDisplayed());

        return visibilityStatus;
    }

    public void clickOnCartButton(){

        // Create a WebDriverWait instance with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        // Wait for the checkout button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        cartButton.click();
    }

    public HomePage clickOnAllItemsOption(){
        allItemsOption.click();
        return new HomePage(driver);
    }

    public void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void clickOnAboutOption(){
        aboutOption.click();
    }

    public void clickOnResetAppStateOption(){
        resetAppState.click();
        driver.navigate().refresh();
    }

    public void resetApp(){
        hamburgerMenu.click();
        resetAppState.click();
        driver.navigate().refresh();
    }

    public void clickOnHamburgerMenuCloseButton(){
        hamburgerMenuCloseButton.click();
    }

    public WebElement getAllItemsOptionElement(){
        return allItemsOption;
    }

    public String getCartCount(){
        return cartCount.getText();
    }

    public WebElement getCartCountElement(){
        return cartCount;
    }

    public boolean isElementNotPresent(WebElement cartCount) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10) ); // Wait for up to 10 seconds

        try {
            wait.until(ExpectedConditions.invisibilityOf(cartCount));
            System.out.println("Element is not present.");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element is not present.");
            return true;
        }
    }

    public String retrieveHomePageTitle() {

        String HomePageTitle = driver.getTitle();


        return HomePageTitle;
    }
    public String retrieveHomePageURL() {

        String HomePageURL = driver.getCurrentUrl();

        return HomePageURL;

    }

    public void clickOnTwitterIcon(){
        twitterLogo.click();
    }

    public void clickOnFacebookIcon(){
        facebookLogo.click();
    }

    public void clickOnLinkedInIcon(){
        linkedinLogo.click();
    }


}
