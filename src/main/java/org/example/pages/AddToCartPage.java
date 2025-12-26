package org.example.pages;


import org.example.utils.JavaScriptUtils;
import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class AddToCartPage {

    WebDriver driver;
    public JavaScriptUtils js = new JavaScriptUtils();

    @FindBy(xpath = "//span[text()='Gear']/ancestor::li")
    private WebElement gearMenu;
    @FindBy(xpath = "//span[text()='Bags']/parent::a")
    private WebElement bags;
    @FindBy(css = "span[data-ui-id='page-title-wrapper']")
    private WebElement bagsHeading;
    @FindBy(css = "strong[class*='product-item-name'] a")
    private List<WebElement> bagsList;
    @FindBy(xpath = "(//span[@class='price'])[1]")
    private WebElement bagPrice;
    @FindBy(css = "span[data-ui-id='page-title-wrapper']")
    private WebElement addedProductName;
    @FindBy(css = "button[id='product-addtocart-button']")
    private WebElement addToCartCTA;
    @FindBy(css = "div[data-ui-id='message-success']")
    private WebElement successMsg;
    @FindBy(css = "div[class='minicart-wrapper'] > a")
    private WebElement myCart;
    @FindBy(css = "button[title='Proceed to Checkout']")
    private WebElement proceedToCheckout;
    @FindBy(css = "span[class='title']")
    private WebElement orderSummary;


    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectBag() throws InterruptedException {
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        actions.moveToElement(gearMenu).perform();
        js.clickElement(driver, bags);
        Assert.assertTrue(bagsHeading.isDisplayed(), "bags Heading is not visible");
        js.scrollDownByRatio(driver, 0.25);
        Thread.sleep(2000);
    }

    public void clickOnBagByTextEle(String bagText) {

        for (WebElement bag : bagsList) {
            if (bag.getText().trim().equalsIgnoreCase(bagText)) {
                bag.click();
                break;
            }
        }
    }

    public void verifyTheBagPrice(double expectedAmount) {
        String priceText = bagPrice.getText();
        String actualAmount = priceText.replaceAll("[^0-9.]", ""); // removes $ or other symbols
        System.out.println("Price from UI: " + actualAmount);
        String formattedPrice = String.format("%.2f", expectedAmount);
        System.out.println("Excel Amount: " + formattedPrice);
        Assert.assertEquals(actualAmount, formattedPrice, "Bag price does not match expected amount.");
    }

    public void verifyAddToCartItem() throws InterruptedException {
        WaitUtils.waitForElement(driver, addToCartCTA);
        js.clickElement(driver, addToCartCTA);
        WaitUtils.waitForElement(driver, successMsg);
        Assert.assertTrue(successMsg.isDisplayed());
        js.clickElement(driver, myCart);
        WaitUtils.waitForElement(driver, proceedToCheckout);
        js.clickElement(driver, proceedToCheckout);
    }

    public void verifyOrderSummary() throws InterruptedException {
        WaitUtils.waitForElement(driver, orderSummary);
        Assert.assertTrue(orderSummary.isDisplayed(), "Order summary is not visible");
        Thread.sleep(500);
    }


}
