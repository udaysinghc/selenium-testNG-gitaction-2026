package org.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class JavaScriptUtils {

    public static void highlightGreen(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid green'", element);
    }

    public static void highlightRed(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }


    public static void sendKeys(WebDriver driver, WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Highlight the element
        js.executeScript("arguments[0].style.border='3px solid green'", element);

        // Send the keys
        element.sendKeys(text);

        // Optional: Remove highlight after a short delay (visual feedback)
        try {
            Thread.sleep(300); // just for a quick visual effect
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        js.executeScript("arguments[0].style.border=''", element);
    }

    public static void sendKeys(WebDriver driver, By locator, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement element = driver.findElement(locator);

        // Send the keys
        element.sendKeys(text);

        // Highlight the element
        js.executeScript("arguments[0].style.border='3px solid green'", element);

        // Optional: Remove highlight after a short delay (visual feedback)
        try {
            Thread.sleep(300); // just for a quick visual effect
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        js.executeScript("arguments[0].style.border=''", element);
    }

    public static void clickElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid green'", element);
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid green'", element);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollDownByRatio(WebDriver driver, double ratio) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Calculate scroll height using JS and scroll to the desired ratio
        String script = "var scrollHeight = document.body.scrollHeight;" +
                "window.scrollTo(0, scrollHeight * " + ratio + ");";
        js.executeScript(script);
    }

    public void clickElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid green'", element);
        js.executeScript("arguments[0].click();", element);
    }

    public void assertDisplay(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid green'", element);
        Assert.assertTrue(element.isDisplayed(), "<<<<<<Element is not visible>>>>>>");
    }

    public void assertDisplay(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid green'", element);
        Assert.assertTrue(element.isDisplayed(), "<<<<<<Element is not visible>>>>>>");
    }


}
