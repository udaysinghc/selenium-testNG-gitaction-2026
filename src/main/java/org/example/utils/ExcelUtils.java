package org.example.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static String getCellValue(String sheetName, int row, int col) {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/TestData.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            String value = sheet.getRow(row).getCell(col).toString();
            workbook.close();
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
