package org.example.pages;

import org.example.base.BaseTest;
import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SandboxCheckboxPage extends BaseTest {

    WebDriver driver;

    @FindBy(css = "a[href$='form-fields/']") //$ used for end-with.
    private WebElement formFieldsButton;
    @FindBy(css = "h1[itemprop='headline']")
    private WebElement formFieldsHeadline;
    @FindBy(css = "[type='checkbox'][name='fav_drink']")
    private List<WebElement> favoriteDrinkCheckboxes;

    public SandboxCheckboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SandboxCheckboxPage selectCheckbox(int option) throws InterruptedException {
        WaitUtils.waitForElement(driver, formFieldsButton);
        js.clickElement(driver, formFieldsButton);
        WaitUtils.waitForElement(driver, formFieldsHeadline);
        js.clickElement(driver, favoriteDrinkCheckboxes.get(option));
        Thread.sleep(3000);
        return this;
    }


    public boolean checkboxIsSelected(int option) {
        return favoriteDrinkCheckboxes.get(option).isSelected();
    }
}
