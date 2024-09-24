package Autotest.test;

import Autotest.keywords.ExcelKeywords;
import org.testng.annotations.*;
import Autotest.keywords.WebUI;
import Autotest.pages.Login;
import Autotest.model.Customer;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected WebUI webUI;
    private static final String URL = "https://demo.guru99.com/V4";
    protected static final String DATA_FILE_PATH =
            System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java"
                    + File.separator + "Autotest" + File.separator + "datafiles" + File.separator
                    + "TestData.xlsx";

    protected Login objLogin;
    protected ExcelKeywords excelUtils = new ExcelKeywords();;
    public static Customer customer = new Customer();
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
        excelUtils.setExcelFile(DATA_FILE_PATH, "TestCaseSuiteTest");
    }


    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.info("=============== Start {}", method.getName());
    }

    // Method to clean Allure results
    public void cleanAllureResults() {
        deleteAllureResults();
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
}
