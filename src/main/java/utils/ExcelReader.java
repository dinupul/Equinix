package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExcelReader {
    FileInputStream fis;
    File excel;
    private ConfigReader configReader = new ConfigReader();
    Properties prop = configReader.initProp();

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
}
