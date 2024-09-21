package Autotest.test;

import Autotest.keywords.ExcelKeywords;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import Autotest.keywords.WebUI;
import Autotest.pages.Login;


public class BaseTest {

    protected WebUI webUI;

    //  private static final String BROWSER = "chrome";


    private static final String USER_ID = "mngr588638";
    private static final String USER_PASSWORD = "sYgEpeg";

    private static final String EXCEL_FILE_PATH = "D:/Autotest/SeleniumDemo/src/main/java/Autotest/datafiles/Book1.xlsx";
    private static final String SHEET_NAME = "Sheet1";
    private static final String URL = "https://demo.guru99.com/V4";

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


  @AfterTest
    public void tearDown() {
        webUI.closeBrowser();
    }

}
