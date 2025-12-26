package org.example.testcases;

import org.example.base.BaseTest;
import org.example.pages.LoginPageBy;
import org.testng.annotations.Test;

public class LoginWithByLocatorsTest extends BaseTest {

    @Test
    public void loginWithByLocators() throws InterruptedException {
        LoginPageBy loginPageBy = new LoginPageBy(driver);
        loginPageBy.login("magento21@getnada.com", "Test@1234");
        loginPageBy.verifyLogoutCTA();
    }
}
