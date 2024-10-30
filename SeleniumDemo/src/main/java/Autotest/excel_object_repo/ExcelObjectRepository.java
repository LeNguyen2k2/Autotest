package Autotest.excel_object_repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelObjectRepository {
    private Map<String, String> elementMap;
    private WebDriver driver;

    public ExcelObjectRepository(String excelFilePath) {
        elementMap = new HashMap<>();
        loadExcelData(excelFilePath);
    }

    // Load data from Excel file
    private void loadExcelData(String excelFilePath) {
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(3);

            // Iterate through rows and read locator info
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Cell keyCell = row.getCell(0); // Element Name
                Cell typeCell = row.getCell(1); // Locator Type
                Cell valueCell = row.getCell(2); // Locator Value

                if (keyCell != null && typeCell != null && valueCell != null) {
                    String key = keyCell.getStringCellValue();
                    String locatorType = typeCell.getStringCellValue();
                    String locatorValue = valueCell.getStringCellValue();

                    elementMap.put(key, locatorType + ":" + locatorValue);
                    System.out.println("Loaded element: " + key + " with " + locatorType + " = " + locatorValue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findElementObject(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty.");
        }

        String locatorInfo = elementMap.get(key);
        if (locatorInfo == null) {
            System.err.println("Locator info not found for key: " + key);
            return null;
        }
        return locatorInfo;
    }

    public WebElement getWebElement(String key) {
        String locatorInfo = findElementObject(key);
        if (locatorInfo != null) {
            String[] parts = locatorInfo.split(";");
            if (parts.length == 2) {
                String locatorType = parts[0].trim();
                String locatorValue = parts[1].trim();
                By by = getBy(locatorType, locatorValue); // Updated to pass both type and value
                return driver.findElement(by);
            } else {
                System.err.println("Invalid locator format for key: " + key);
                return null;
            }
        }
        return null;
    }

    private By getBy(String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "xpath":
                return By.xpath(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "classname":
                return By.className(locatorValue);
            case "tagname":
                return By.tagName(locatorValue);
            case "linktext":
                return By.linkText(locatorValue);
            case "partiallinktext":
                return By.partialLinkText(locatorValue);
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
