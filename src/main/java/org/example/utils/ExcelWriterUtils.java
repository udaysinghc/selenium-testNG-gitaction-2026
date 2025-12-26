package org.example.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelWriterUtils {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelWriterUtils(String sheetName) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    public void writeListToExcel(List<String> dataList) {
        int rowNum = 0;
        for (String data : dataList) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(data);
        }
    }

    public void saveExcel(String filePath) {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readColumnValues(String sheetName, int columnIndex) {
        List<String> values = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("writeExcelData/FilterOutput.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null) {
                    values.add(cell.toString());
                }
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

}
