package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ExcelReader {
    FileInputStream fis;
    File excel;
    private ConfigReader configReader = new ConfigReader();
    Properties prop = configReader.initprop();

    public XSSFWorkbook excelFileInitiator() throws IOException {
        excel = new File(prop.getProperty("Data"));
        fis = new FileInputStream(excel);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        return wb;
    }

    public String[][] readExcelDoubleColumn() throws IOException {
        XSSFWorkbook wb = excelFileInitiator();
        XSSFSheet ws = wb.getSheet(prop.getProperty("SheetLNG"));
        int rowNum = ws.getLastRowNum();
        int colNum = ws.getRow(0).getLastCellNum();
        String[][] data = new String[rowNum][colNum - 1];


        for (int i = 1; i <= rowNum; i++) {
            XSSFRow row = ws.getRow(i);
            for (int j = 1; j <= colNum - 1; j++) {
                XSSFCell cell = row.getCell(j);
                String value = cell.getStringCellValue();
                data[i - 1][j - 1] = value;
            }
        }
        return data;
    }

    public String[] readExcelSingleColumn() throws IOException {
        XSSFWorkbook wb = excelFileInitiator();
        XSSFSheet ws = wb.getSheet(prop.getProperty("SheetSB"));

        int rowNum = ws.getLastRowNum();
        String[] data = new String[rowNum];


        for (int i = 1; i <= rowNum; i++) {
            XSSFRow row = ws.getRow(i);
            XSSFCell cell = row.getCell(0);
            String value = cell.getStringCellValue();
            data[i - 1] = value;
        }
        return data;
    }

    public void writeExcelDoubleColumn(String[][] extractedData) throws IOException {

        XSSFWorkbook wb = excelFileInitiator();
        XSSFSheet ws = wb.getSheet(prop.getProperty("SheetSB"));
        int rowNum = extractedData.length;
        int colNum = extractedData[0].length;

        for (int i = 0; i < rowNum; i++) {
            XSSFRow row = ws.getRow(i+1);
            for (int j = 0; j < colNum; j++) {
                XSSFCell cell = row.createCell(j+1);
                String value = extractedData[i][j];
                cell.setCellValue(value);
            }
        }
        fis.close();
        FileOutputStream outputStream = new FileOutputStream("excel");
        wb.write(outputStream);
        wb.close();
        outputStream.close();
    }

}
