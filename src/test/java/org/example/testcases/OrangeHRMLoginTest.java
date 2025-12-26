package org.example.testcases;

import io.qameta.allure.Description;
import org.example.base.BaseTest;
import org.example.pages.OrangeHRMLoginPage;
import org.example.pages.SandboxCheckboxPage;
import org.testng.annotations.Test;

import static org.example.utils.Constants.PASSWORD;
import static org.example.utils.Constants.USERNAME;
import static org.testng.Assert.assertTrue;

public class OrangeHRMLoginTest extends BaseTest {


    @Description("Orange HRM Login Test")
    @Test
    public void hrmLogin() throws InterruptedException {
        OrangeHRMLoginPage orangeHRMLoginPage = new OrangeHRMLoginPage(driver);
        orangeHRMLoginPage.loginHRM(USERNAME, PASSWORD);
        orangeHRMLoginPage.verifyLogout();
    }
}
