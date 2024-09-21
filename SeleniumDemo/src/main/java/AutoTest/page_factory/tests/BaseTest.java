package Autotest.page_factory.tests;

import Autotest.keywords.ExcelKeywords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import Autotest.keywords.WebUI;
import Autotest.page_factory.pages.Login;

import java.lang.reflect.Method;

public class BaseTest {

  protected WebUI webUI;
  private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

//  private static final String BROWSER = "chrome";
  private static final String URL = "https://demo.guru99.com/V4";
  private static final String EXCEL_FILE_PATH = "D:/Autotest/SeleniumDemo/src/main/java/Autotest/datafiles/Book1.xlsx";
  private static final String SHEET_NAME = "Sheet1";
  protected static final String USER_ID = "mngr588662";
  protected static final String USER_PASSWORD = "ApYvAjA";

  protected Login objLogin;
  protected ExcelKeywords objdata;

  @Parameters(value = "browser")
  @BeforeTest
  public void setUp(String browser) {
    webUI = new WebUI();
    webUI.openBrowser(browser, URL);
    webUI.maximizeWindow();
    objLogin = new Login(webUI);
    setupExcel();
  }

  public void setupExcel() {
    objdata = new ExcelKeywords();
    objdata.setExcelFile(EXCEL_FILE_PATH, SHEET_NAME);
  }

  @BeforeMethod
  public void beforeMethod(Method method) {
    logger.info("=============== Start {}",method.getName());
  }

  @AfterMethod
  public void afterMethod(Method method) {
    logger.info("=============== End {}",method.getName());
  }

  @AfterTest
  public void tearDown() {
    webUI.closeBrowser();
  }

}
