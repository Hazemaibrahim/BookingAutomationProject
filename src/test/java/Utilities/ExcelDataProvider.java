package Utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelDataProvider {

    public static Object[][] readExcelData(String filePath, String sheetName) {
        Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rowCount - 1][colCount]; // skip header row

            // Match HomePage.selectDates parsing (dd-MMM-yyyy)
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    if (row.getCell(j) != null) {
                        switch (row.getCell(j).getCellType()) {
                            case STRING:
                                data[i - 1][j] = row.getCell(j).toString();
                                break;
                            case NUMERIC:
                                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(row.getCell(j))) {
                                    Date excelDate = row.getCell(j).getDateCellValue();
                                    data[i - 1][j] = dateFormat.format(excelDate);
                                } else {
                                    data[i - 1][j] = String.valueOf((int) row.getCell(j).getNumericCellValue());
                                }
                                break;
                            default:
                                data[i - 1][j] = row.getCell(j).toString();
                        }
                    } else {
                        data[i - 1][j] = "";
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @DataProvider(name = "bookingData")
    public static Object[][] bookingDataProvider() {
        return readExcelData("src/main/resources/testdata.xlsx", "Sheet1");
    }
}
