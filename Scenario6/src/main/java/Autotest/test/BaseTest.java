package Autotest.test;

//import Autotest.common.helpers.FileHelpers;
import Autotest.common.keywords.WebUI;
import Autotest.pages.FirstTV;
import Autotest.pages.Home;
import Autotest.pages.Tivi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.util.Map;
import java.lang.reflect.Method;

public class BaseTest {

    protected WebUI webUI;
    private static final String URL = "https://www.dienmayxanh.com/";
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String ALLURE_RESULTS_DIR = "target/allure-results";

    protected Home objHome;
    protected Tivi objTivi;
    protected FirstTV objfirstTV;



    @BeforeSuite
    public void cleanUp() {
        deleteAllureResults();
    }

    @Parameters(value = "browser")
    @BeforeTest
    public void setUp(String browser) {
        webUI = new WebUI();
        webUI.openBrowser(browser, URL);
        webUI.maximizeWindow();
        objHome = new Home(webUI);
        objfirstTV = new FirstTV(webUI);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.info("=============== Start {}", method.getName());
    }

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


}
