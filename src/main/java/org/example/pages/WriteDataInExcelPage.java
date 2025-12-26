package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class WriteDataInExcelPage {

    WebDriver driver;

    @FindBy(css = "div[class='filter-options-title']")
    private List<WebElement> filterOptionsTitles;


    public WriteDataInExcelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public List<String> getFilterOptionTexts() {
        return filterOptionsTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

/*    public void writeExcelData() {
        ExcelWriterUtils excelWriter = new ExcelWriterUtils("FilterOptions");
        List<String> filters = filterOptionsTitles
        excelWriter.writeListToExcel(filterOptionsTitles);
        excelWriter.saveExcel("src/main/resources/FilterOutput.xlsx");
        excelWriter.closeWorkbook();
    }*/

}
