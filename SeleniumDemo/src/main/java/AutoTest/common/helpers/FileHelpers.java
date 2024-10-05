package Autotest.common.helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Autotest.common.utils.ExcelUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileHelpers {

  private static final String PROJECT_PATH = System.getProperty("user.dir");
  private static final String SOURCE_FOLDER = "src";
  private static final String MAIN_FOLDER = "main";
  private static final String RESOURCES_FOLDER = "resources";
  private static final String TEST_DATA_FOLDER = "datafiles";

  private static final String EXCEL_EXTENSION = ".xlsx";

  private static final Logger logger = LoggerFactory.getLogger(FileHelpers.class);

  public static String getExcelDataFilePath(String excelFileName) {
    return PROJECT_PATH + File.separator + SOURCE_FOLDER + File.separator + MAIN_FOLDER + File.separator + RESOURCES_FOLDER
        + File.separator + TEST_DATA_FOLDER + File.separator
        + excelFileName + EXCEL_EXTENSION;
  }

  public static List<HashMap<String, String>> getTestData(String excelFilePath, String sheetName, String tableName) {
    List<HashMap<String, String>> listData = new ArrayList<>();
    try {
      Workbook workbook = ExcelUtils.getWorkbook(excelFilePath);
      Sheet sheet = ExcelUtils.getExcelSheetByName(excelFilePath, sheetName);
      Cell tableStart = null;
      if(sheet != null) {
        tableStart = ExcelUtils.getFirstCellByValue(sheet, tableName);
      }
      int startRow = 0;
      if(tableStart != null) {
        startRow = tableStart.getRowIndex();
      }
      int startCol = 0;
      if(tableStart != null) {
        startCol = tableStart.getColumnIndex();
      }
      Cell tableEnd = null;
      if(sheet != null) {
        tableEnd = ExcelUtils.getLastCellByValue(sheet, tableName);
      }
      int endRow = 0;
      if(tableEnd != null) {
        endRow = tableEnd.getRowIndex();
      }
      int endCol = 0;
      if(tableEnd != null) {
        endCol = tableEnd.getColumnIndex();
      }
      logger.info("Loading data from excel file {} at Row [{} - {}] - Column [{} - {}]", excelFilePath, startRow, endRow, startCol, endCol);
      for(int i = startRow + 1; i <= endRow; i++) {
        HashMap<String, String> map = new HashMap<>();
        Row row = sheet.getRow(i);
        for(int j = startCol + 1; j < endCol; j++) {
          String key = ExcelUtils.getCellValueByIndex(sheet, startRow, j);
//          logger.info("Key: {}", key);
          Cell cell = row.getCell(j);
          String value;
          if(cell != null) {
            value = cell.getStringCellValue();
          } else {
            value = "";
          }
//          logger.info("Value: {}", value);
          map.put(key, value);
        }
        listData.add(map);
      }
      if(workbook != null) {
        ExcelUtils.saveWorkbook(excelFilePath, workbook);
      }
    } catch (Exception e) {
      logger.error("Failed to load test data from {}, sheet {}, table {}. Root cause: {}", excelFilePath, sheetName, tableName, e.getMessage());
    }
    return listData;
  }

}
