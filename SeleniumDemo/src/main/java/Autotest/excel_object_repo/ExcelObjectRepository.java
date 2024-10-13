package Autotest.excel_object_repo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelObjectRepository {

    private Map<String, By> objectRepo;

    public ExcelObjectRepository(String filePath) {
        objectRepo = loadObjectRepository(filePath);
    }

    // Method to load the object repository from Excel
    private Map<String, By> loadObjectRepository(String filePath) {
        Map<String, By> locatorsMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming the object repository is in the first sheet
            for (Row row : sheet) {
                // Read the element name, locator type, and locator value
                Cell elementNameCell = row.getCell(0);
                Cell locatorTypeCell = row.getCell(1);
                Cell locatorValueCell = row.getCell(2);

                if (elementNameCell != null && locatorTypeCell != null && locatorValueCell != null) {
                    String elementName = elementNameCell.getStringCellValue();
                    String locatorType = locatorTypeCell.getStringCellValue();
                    String locatorValue = locatorValueCell.getStringCellValue();

                    // Convert locator type and value to a By object
                    By locator = getBy(locatorType, locatorValue);
                    locatorsMap.put(elementName, locator);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load object repository from Excel file: " + filePath, e);
        }
        return locatorsMap;
    }

    // Method to convert locator type and value to a By object
    private By getBy(String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
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

    // Method to retrieve the locator By object for a given element name
    public By getLocator(String elementName) {
        By locator = objectRepo.get(elementName);
        if (locator != null) {
            return locator;
        } else {
            throw new IllegalArgumentException("No locator found for element: " + elementName);
        }
    }
}
