package org.example.testcases;

import io.qameta.allure.*;
import org.example.base.BaseTest;
import org.example.pages.AddToCartPage;
import org.example.pages.WriteDataInExcelPage;
import org.example.utils.ExcelWriterUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class WriteDataInExcelTest extends BaseTest {

    @Epic("Bag Filters Module")
    @Feature("Bag Filters Verification")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the filters from UI list data using excel file")
    @Test
    public void writeDataInExcel() throws InterruptedException {
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        WriteDataInExcelPage writeDataInExcelPage = new WriteDataInExcelPage(driver);

        addToCartPage.selectBag();
        Thread.sleep(3000);
        // Fetching text from WebElement list
        List<String> filtersFromUI = writeDataInExcelPage.getFilterOptionTexts();
        // Writing to Excel
        // Ensure folder at project root level
        new File("writeExcelData").mkdirs();
        ExcelWriterUtils excelWriter = new ExcelWriterUtils("FilterOptions");
        excelWriter.writeListToExcel(filtersFromUI);
        excelWriter.saveExcel("writeExcelData/FilterOutput.xlsx");
        excelWriter.closeWorkbook();

        // Read back from Excel
        List<String> filtersFromExcel = ExcelWriterUtils.readColumnValues("FilterOptions", 0);

        // Assert
        Assert.assertEquals(filtersFromExcel.get(0), filtersFromUI.get(0), "UI and Excel data do not match!");

    }
}
