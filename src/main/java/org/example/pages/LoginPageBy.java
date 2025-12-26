package org.example.pages;

import org.example.base.BaseTest;
import org.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPageBy extends BaseTest {

    WebDriver driver;

    private By signInCTA = By.cssSelector(".panel > .header > .authorization-link > a");
    private By email = By.cssSelector("[id='email']");
    private By password = By.cssSelector("[name='login[password]']");
    private By loginCTA = By.cssSelector("[class*='login primary']");
    private By loggedUserName = By.cssSelector(":nth-child(2) > .greet > .logged-in");

    private By dropdownMenu = By.cssSelector(":nth-child(2) > .customer-welcome > .customer-name > .action");
    private By signOutCTA = By.xpath("(//li[@class='authorization-link']//following-sibling::a)[1]");

    public LoginPageBy(WebDriver driver) {
        this.driver = driver;
    }


    public void login(String user, String pass) {
        WaitUtils.waitForElement(driver, signInCTA);
        js.clickElement(driver, signInCTA);
        js.sendKeys(driver, email, user);
        js.sendKeys(driver, password, pass);
        js.clickElement(driver, loginCTA);

    }

    public void verifyLogoutCTA() throws InterruptedException {
        Thread.sleep(5000);
        js.clickElement(driver, dropdownMenu);
        Thread.sleep(2000);
        WebElement signOutCTAElement = driver.findElement(signOutCTA);
        Assert.assertTrue(signOutCTAElement.isDisplayed(), "Sign out CTA is not visible");
        js.clickElement(driver, signOutCTA);
    }

}
