package Autotest.keywords;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
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
                    return String.valueOf(cell.getNumericCellValue());
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

//    public void setCellValue(int rowNum, int cellNum, String cellValue, String excelFilePath) {
//        try {
//            //creating a new cell in row and setting value to it
//            sheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);
//
//            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//            workbook.write(outputStream);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void setCellValue(int rowIndex, int colIndex, String value, String excelFilePath) {
        if (sheet == null) {
            throw new IllegalStateException("Excel sheet is not initialized. Make sure to initialize the ExcelUtils with a valid file and sheet name.");
        }

        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex); // Create the row if it does not exist
        }

        Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        cell.setCellValue(value); // Set the value in the cell

        // Save the changes to the Excel file (optional, if needed)
        try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write to Excel file: " + e.getMessage());
        }
    }


    public static Workbook getWorkbook(String filePath) {
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            return new XSSFWorkbook(fis);
        } catch (IOException e) {
            logger.error("Failed to read workbook {}. Root cause: {}", filePath, e.getMessage());
        }
        return null;
    }

    public static Sheet getExcelSheetByName(String filePath, String sheetName) {
        Workbook workbook = getWorkbook(filePath);
        if(workbook != null) {
            return workbook.getSheet(sheetName);
        }
        return null;
    }

    public static Cell getFirstCellByValue(Sheet sheet, String cellValue) {
        Cell cell = null;
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                cell = cellIterator.next();
                if (cell.toString().equals(cellValue)) {
                    return cell;
                }
            }
        }
        return null;
    }

    public static Cell getLastCellByValue(Sheet sheet, String cellValue) {
        Cell cell = null;
        Iterator<Row> rowIterator = sheet.rowIterator();
        int lastRow = 0;
        int lastCell = 0;
        int count = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                cell = cellIterator.next();
                if (cell.toString().equals(cellValue)) {
                    count++;
                    lastRow = cell.getRowIndex();
                    lastCell = cell.getColumnIndex();
                }
            }
        }
        if(count > 0) {
            return sheet.getRow(lastRow).getCell(lastCell);
        }
        return null;
    }

    public static Cell getCellByIndex(Sheet sheet, int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        return row.getCell(colIndex);
    }

    public static String getCellValueByIndex(Sheet sheet, int rowIndex, int cellIndex) {
        Cell cell = getCellByIndex(sheet, rowIndex, cellIndex);
        if(cell != null) {
            return cell.getStringCellValue();
        }
        return "";
    }

    public static void saveWorkbook(String excelFilePath, Workbook workbook) {
        try {
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            logger.error("Failed to save workbook in {}. Root cause: {}", excelFilePath, e.getMessage());
        }
    }
}
