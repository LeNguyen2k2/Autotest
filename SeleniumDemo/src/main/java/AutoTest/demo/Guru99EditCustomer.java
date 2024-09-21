package Autotest.demo;

import io.qameta.allure.Step;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Autotest.keywords.WebUI;

public class Guru99EditCustomer {

  private static final String BROWSER = "CHROME";
  private static final String URL = "https://demo.guru99.com/V4";

  private static final String USER_ID = "mngr588662";
  private static final String USER_PASSWORD = "ApYvAjA";

  private static final String TXT_USERID = "//input[@name='uid']";
  private static final String TXT_PASSWORD = "//input[@name='password']";
  private static final String BTN_LOGIN = "//input[@name='btnLogin']";
  private static final String LNK_EDIT_CUSTOMER = "//a[normalize-space()='Edit Customer']";

  private WebUI webUI;

  @BeforeTest
  public void setUp() {
    webUI = new WebUI();
    webUI.openBrowser(BROWSER, URL);
    webUI.maximizeWindow();
  }

  @Step("Input user id: {0}")
  public void inputUserId(String userId) {
    webUI.sendKeys(TXT_USERID, userId);
    webUI.delayInSecond(3);
  }

  @Step("Input password: {0}")
  public void inputPassword(String password) {
    webUI.sendKeys(TXT_PASSWORD, password);
    webUI.delayInSecond(3);
  }

  @Step("Click Login button")
  public void clickLogin() {
    webUI.click(BTN_LOGIN);
    webUI.delayInSecond(3);
  }

  @Step("Login Guru99 with user id '{0}' and password '{1}'")
  public void login_Guru99_with(String userId, String password) {
    inputUserId(userId);
    inputPassword(password);
    clickLogin();
  }

  @Step("Move to Edit Customer")
  public void move_to_New_Customer() {
    webUI.click(LNK_EDIT_CUSTOMER);
    webUI.delayInSecond(3);
  }

  @Test(description = "NC001: Name cannot be empty")
  public void NC001_Name_cannot_be_empty() {
    login_Guru99_with(USER_ID, USER_PASSWORD);
    move_to_New_Customer();
//    input_Customer_Name("");
//
//    assertTrue(should_show_customer_name_error_message("Customer name must not be blank"));
  }

  @Test(description = "NC002: Name cannot be numeric")
  public void NC002_Name_cannot_be_numeric() {
//    input_Customer_Name("1234");
//    assertTrue(should_show_customer_name_error_message("Numbers are not allowed"));
  }

  @Test(description = "NC003: Name cannot have special characters")
  public void NC003_Name_cannot_have_special_characters() {
//    input_Customer_Name("@s123");
//    assertTrue(should_show_customer_name_error_message("Special characters are not allowed"));
  }

  @AfterTest
  public void tearDown() {
    webUI.closeBrowser();
  }
}
