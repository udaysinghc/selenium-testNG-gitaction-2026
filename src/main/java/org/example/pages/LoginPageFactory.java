package org.example.pages;

import org.example.base.BaseTest;
import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPageFactory extends BaseTest {

    WebDriver driver;

    @FindBy(css = ".panel > .header > .authorization-link > a")
    private WebElement signInCTA;

    @FindBy(css = "[id='email']")
    private WebElement email;

    @FindBy(css = "[name='login[password]']")
    private WebElement password;

    @FindBy(css = "[class*='login primary']")
    private WebElement loginCTA;

    @FindBy(css = ":nth-child(2) > .customer-welcome > .customer-name > .action")
    private WebElement dropdownMenu;

    @FindBy(xpath = "(//li[@class='authorization-link']//following-sibling::a)[1]")
    private WebElement signOutCTA;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
        Assert.assertTrue(signOutCTA.isDisplayed(), "Sign out CTA is not visible");
        js.clickElement(driver, signOutCTA);
    }


}
