package org.example.testcases;

import org.example.base.BaseTest;
import org.example.pages.AddToCartPage;
import org.example.utils.ExcelUtils;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void loginWithByLocators() throws InterruptedException {
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        String bagText = ExcelUtils.getCellValue("Sheet1", 1, 0);
        String bagAmount = ExcelUtils.getCellValue("Sheet1", 1, 1);
        addToCartPage.selectBag();
        addToCartPage.clickOnBagByTextEle(bagText);
        double priceAsDouble = Double.parseDouble(bagAmount);
        addToCartPage.verifyTheBagPrice(priceAsDouble);
        addToCartPage.verifyAddToCartItem();
        addToCartPage.verifyOrderSummary();
    }


}
