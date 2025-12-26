package org.example.testcases;

import io.qameta.allure.Description;
import org.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.utils.Constants.SANDBOX_HOME_PAGE_TITLE;


public class SandboxTitleTest extends BaseTest {

    @Description("Verify title of the page.")
    @Test
    public void homePageTitle() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, SANDBOX_HOME_PAGE_TITLE);
    }


}
