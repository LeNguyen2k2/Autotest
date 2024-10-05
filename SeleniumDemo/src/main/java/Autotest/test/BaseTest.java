package Autotest.test;

import Autotest.common.utils.ExcelUtils;
import Autotest.common.utils.JsonUtils;  // Import JsonUtils
import Autotest.common.helpers.FileHelpers;
import Autotest.common.keywords.WebUI;
import org.testng.annotations.*;
import Autotest.pages.Login;
import Autotest.model.Customer;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected WebUI webUI;
    private static final String URL = "https://demo.guru99.com/V4";

    protected static final String DATA_FILE_PATH =
            System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                    + File.separator + "resources" + File.separator + "datafiles" + File.separator
                    + "TestData.xlsx";

    private static final String JSON_FILE_PATH =
            System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                    + File.separator + "java" + File.separator + "Autotest" + File.separator + File.separator + "object_repo"
                    + File.separator + "CustomerDataTests.json";  // Path for the JSON file

    private static final String DATA_FILE_NAME = "TestData";

    protected Login objLogin;
    private String sheetName;
    protected ExcelUtils excelUtils = new ExcelUtils();
    protected JsonUtils jsonUtils;  // Declare JsonUtils

    public static Customer customer;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String ALLURE_RESULTS_DIR = "target/allure-results";


    @BeforeSuite
    public void cleanUp() {
        deleteAllureResults(); // Clean up Allure results before running tests
    }

    @Parameters(value = "browser")
    @BeforeTest
    public void setUp(String browser) {
        webUI = new WebUI();
        webUI.openBrowser(browser, URL);
        webUI.maximizeWindow();
        objLogin = new Login(webUI);
        excelUtils.setExcelFile(DATA_FILE_PATH, this.getClass().getSimpleName());
        jsonUtils = new JsonUtils(JSON_FILE_PATH);  // Initialize JsonUtils
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.info("=============== Start {}", method.getName());
    }

    // Method to delete Allure results
    private void deleteAllureResults() {
        File allureResultsDir = new File(ALLURE_RESULTS_DIR);
        if (allureResultsDir.exists()) {
            File[] files = allureResultsDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!file.isDirectory()) {
                        if (file.delete()) {
                            logger.info("Deleted file: {}", file.getName());
                        } else {
                            logger.error("Failed to delete file: {}", file.getName());
                        }
                    }
                }
            }
            logger.info("Allure results deleted.");
        } else {
            logger.info("Allure results directory does not exist.");
        }
    }

    @AfterMethod
    public void afterMethod(Method method) {
        logger.info("=============== End {}", method.getName());
    }

    @AfterTest
    public void tearDown() {
        webUI.closeBrowser();
    }

    public String generateRandomValidEmail(String prefix, String suffix) {
        String pattern = "YYYYMMDDHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return prefix + date + suffix;
    }

    private String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    // Method to find test data in Excel
    public List<HashMap<String, String>> findTestData(String tableName) {
        return FileHelpers.getTestData(FileHelpers.getExcelDataFilePath(DATA_FILE_NAME), getSheetName(), tableName);
    }

    // Method to get data from JSON based on test case ID and field name
    public HashMap<String, String> getJsonTestData(String testCaseId) {
        return jsonUtils.getTestData(testCaseId);
    }
}
