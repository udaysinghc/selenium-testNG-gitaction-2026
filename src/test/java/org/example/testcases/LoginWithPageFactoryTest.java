package org.example.testcases;

import org.example.base.BaseTest;
import org.example.pages.LoginPageFactory;
import org.testng.annotations.Test;

public class LoginWithPageFactoryTest extends BaseTest {

    @Test
    public void loginWithPageFactory() throws InterruptedException {
        LoginPageFactory loginPageFactory = new LoginPageFactory(driver);
        loginPageFactory.login("magento21@getnada.com", "Test@1234");
        loginPageFactory.verifyLogoutCTA();
    }
}
