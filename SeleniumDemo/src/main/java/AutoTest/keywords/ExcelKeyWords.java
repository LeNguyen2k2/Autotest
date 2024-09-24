package Autotest.keywords;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelKeywords {

    private final Logger logger = LoggerFactory.getLogger(ExcelKeywords.class);
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;

    public void setExcelFile(String excelFilePath, String sheetName) {
        try {
            logger.info("Setting excel file with path '{}', and sheet name '{}'", excelFilePath,
                    sheetName);
            //Create an object of File class to open xls file
            File file = new File(excelFilePath);

            //Create an object of FileInputStream class to read excel file
            FileInputStream inputStream = new FileInputStream(file);

            //creating workbook instance that refers to .xls file
            workbook = new XSSFWorkbook(inputStream);

            //creating a Sheet object
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet named '" + sheetName + "' does not exist in the Excel file.");
            }
            logger.info("Set excel file with path '{}', and sheet name '{}' successfully", excelFilePath,
                    sheetName);
        } catch (Exception e) {
            logger.error("Fail to set excel file with path '{}', and sheet name '{}'. Root cause: {}",
                    excelFilePath, sheetName, e.getMessage());
        }

    }

    public String getCellData(int rowNumber, int cellNumber) {
        //getting the cell value from rowNumber and cell Number
        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        if(cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Check if the cell contains a date
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
        }
        return null;
    }

    public int getRowCountInSheet() {
        return sheet.getLastRowNum() - sheet.getFirstRowNum();
    }

    public void setCellValue(int rowNum, int cellNum, String cellValue, String excelFilePath) {
        try {
            //creating a new cell in row and setting value to it
            sheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}