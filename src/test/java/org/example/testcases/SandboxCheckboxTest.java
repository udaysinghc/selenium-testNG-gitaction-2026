package org.example.testcases;

import io.qameta.allure.Description;
import org.example.base.BaseTest;
import org.example.pages.SandboxCheckboxPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SandboxCheckboxTest extends BaseTest {


    @Description("Checks a checkbox")
    @Test
    public void favoriteDrinkCheckboxes() throws InterruptedException {
        SandboxCheckboxPage sandboxCheckboxPage = new SandboxCheckboxPage(driver);
        sandboxCheckboxPage.selectCheckbox(1);
//        sandboxCheckboxPage.selectCheckbox(3);
        assertTrue(sandboxCheckboxPage.checkboxIsSelected(1), "Checkbox is not selected.");
    }


}
