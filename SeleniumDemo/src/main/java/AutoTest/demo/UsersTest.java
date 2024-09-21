package Autotest.demo;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Autotest.keywords.WebUI;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class UsersTest {

  private static final String BROWSER = "chrome";
  private static final String URL = "https://demoqa.com/webtables";
  private WebUI webUI;

  private static final String BTN_ADD = "//button[@id='addNewRecordButton']";
  private static final String TXT_FIRST_NAME = "//input[@id='firstName']";
  private static final String TXT_LAST_NAME = "//input[@id='lastName']";
  private static final String TXT_EMAIL = "//input[@id='userEmail']";
  private static final String TXT_AGE = "//input[@id='age']";
  private static final String TXT_SALARY = "//input[@id='salary']";
  private static final String TXT_DEPARTMENT = "//input[@id='department']";
  private static final String BTN_SUBMIT = "//button[@id='submit']";

  private static final String USER_TABLE_LBL_FIRST_NAMES = "//div[@role='grid']//div[@role='row']/div[@role='gridcell'][1]";
  private static final String USER_TABLE_LBL_LAST_NAMES = "//div[@role='grid']//div[@role='row']/div[@role='gridcell'][2]";
  private static final String USER_TABLE_LBL_AGES = "//div[@role='grid']//div[@role='row']/div[@role='gridcell'][3]";
  private static final String USER_TABLE_LBL_EMAILS = "//div[@role='grid']//div[@role='row']/div[@role='gridcell'][4]";
  private static final String USER_TABLE_LBL_SALARIES = "//div[@role='grid']//div[@role='row']/div[@role='gridcell'][5]";
  private static final String USER_TABLE_LBL_DEPARTMENTS = "//div[@role='grid']//div[@role='row']/div[@role='gridcell'][6]";
  private static final String USER_TABLE_BTN_EDITS = "//span[@title='Edit' and contains(@id,'edit-record')]";
  private static final String USER_TABLE_BTN_DELETES = "//span[@title='Delete' and contains(@id,'delete-record')]";

  @Step("Click Add button")
  private void clickAddButton() {
    webUI.takeScreenShot();
    webUI.click(BTN_ADD);
    webUI.takeScreenShot();
  }

  @Step("Input First name: {0}")
  public void inputFirstName(String firstName) {
    webUI.takeScreenShot();
    webUI.sendKeys(TXT_FIRST_NAME, firstName);
    webUI.takeScreenShot();
  }

  @Step("Input Last name: {0}")
  public void inputLastName(String lastName) {
    webUI.takeScreenShot();
    webUI.sendKeys(TXT_LAST_NAME, lastName);
    webUI.takeScreenShot();
  }

  @Step("Input email: {0}")
  public void inputEmail(String email) {
    webUI.takeScreenShot();
    webUI.sendKeys(TXT_EMAIL, email);
    webUI.takeScreenShot();
  }

  @Step("Input Age: {0}")
  public void inputAge(String age) {
    webUI.takeScreenShot();
    webUI.sendKeys(TXT_AGE, age);
    webUI.takeScreenShot();
  }

  @Step("Input Salary: {0}")
  public void inputSalary(String salary) {
    webUI.takeScreenShot();
    webUI.sendKeys(TXT_SALARY, salary);
    webUI.takeScreenShot();
  }

  @Step("Input Department: {0}")
  public void inputDepartment(String department) {
    webUI.takeScreenShot();
    webUI.sendKeys(TXT_DEPARTMENT, department);
    webUI.takeScreenShot();
  }

  @Step("Click Submit button")
  public void clickSubmitButton() {
    webUI.takeScreenShot();
    webUI.click(BTN_SUBMIT);
    webUI.takeScreenShot();
  }

  @Step("Click edit button of email '{0}'")
  public void clickEditButtonOfEmail(String email) {
    webUI.takeScreenShot();
    List<WebElement> lblEmails = webUI.findWebElements(USER_TABLE_LBL_EMAILS);
    List<WebElement> btnEdits = webUI.findWebElements(USER_TABLE_BTN_EDITS);
    for(int i = 0; i < lblEmails.size(); i++) {
      if(webUI.verifyElementText(lblEmails.get(i), email)) {
        webUI.click(btnEdits.get(i));
        webUI.takeScreenShot();
        break;
      }
    }
  }

  @Step("Click delete button of email '{0}'")
  public void clickDeleteButtonOfEmail(String email) {
    webUI.takeScreenShot();
    List<WebElement> lblEmails = webUI.findWebElements(USER_TABLE_LBL_EMAILS);
    List<WebElement> btnDeletes = webUI.findWebElements(USER_TABLE_BTN_DELETES);
    for(int i = 0; i < lblEmails.size(); i++) {
      if(webUI.verifyElementText(lblEmails.get(i), email)) {
        webUI.click(btnDeletes.get(i));
        webUI.takeScreenShot();
        break;
      }
    }
  }

  @Step("Add new user with first name '{0}', last name '{1}', email '{2}', age '{3}', salary '{4}', department '{5}'")
  public void addNewUser(String firstName, String lastName, String email, String age, String salary, String department) {
    clickAddButton();
    inputFirstName(firstName);
    inputLastName(lastName);
    inputEmail(email);
    inputAge(age);
    inputSalary(salary);
    inputDepartment(department);
    clickSubmitButton();
  }

  @Step("Edit user with first name '{0}', last name '{1}', email '{2}', age '{3}', salary '{4}', department '{5}'")
  public void editUser(String firstName, String lastName, String email, String age, String salary, String department) {
    clickEditButtonOfEmail(email);
    inputFirstName(firstName);
    inputLastName(lastName);
    inputAge(age);
    inputSalary(salary);
    inputDepartment(department);
    clickSubmitButton();
  }

  @Step("Delete user which has email '{0}'")
  public void deleteUser(String email) {
    clickDeleteButtonOfEmail(email);
  }

  @Step("Should show first name '{0}' of user in User table")
  public boolean should_show_first_name_of_user_in_user_table(String firstName) {
    List<WebElement> lblFirsNames = webUI.findWebElements(USER_TABLE_LBL_FIRST_NAMES);
    for(WebElement lblFirstName: lblFirsNames) {
      if(webUI.verifyElementText(lblFirstName, firstName)) {
        webUI.takeScreenShot();
        return true;
      }
    }
    return false;
  }

  @Step("Should show last name '{0}' of user in User table")
  public boolean should_show_last_name_of_user_in_user_table(String lastName) {
    List<WebElement> lblLastNames = webUI.findWebElements(USER_TABLE_LBL_LAST_NAMES);
    for(WebElement lblLastName: lblLastNames) {
      if(webUI.verifyElementText(lblLastName , lastName)) {
        webUI.takeScreenShot();
        return true;
      }
    }
    return false;
  }

  @Step("Should not show first name '{0}' of user in User table")
  public boolean should_not_show_first_name_of_user_in_user_table(String firstName) {
    List<WebElement> lblFirsNames = webUI.findWebElements(USER_TABLE_LBL_FIRST_NAMES);
    for(WebElement lblFirstName: lblFirsNames) {
      if(webUI.verifyElementText(lblFirstName, firstName)) {
        return false;
      }
    }
    webUI.takeScreenShot();
    return true;
  }

  @Step("Should not show last name '{0}' of user in User table")
  public boolean should_not_show_last_name_of_user_in_user_table(String lastName) {
    List<WebElement> lblLastNames = webUI.findWebElements(USER_TABLE_LBL_LAST_NAMES);
    for(WebElement lblLastName: lblLastNames) {
      if(webUI.verifyElementText(lblLastName , lastName)) {
        return false;
      }
    }
    webUI.takeScreenShot();
    return true;
  }

  @BeforeTest
  public void setUp() {
    webUI = new WebUI();
    webUI.openBrowser(BROWSER, URL);
    webUI.maximizeWindow();
  }

  @Test(description = "US001: Create/Add a new user successfully")
  public void US001_Create_or_add_a_new_user_successfully() {
//    clickAddButton();
//    inputFirstName("John");
//    inputLastName("Doe");
//    inputEmail("john@doe.com");
//    inputAge("45");
//    inputSalary("4000");
//    inputDepartment("Computer Science");
//    clickSubmitButton();
    addNewUser("John", "Doe", "john@doe.com", "45", "4000", "Computer Science");
    assertTrue(should_show_first_name_of_user_in_user_table("John"));
    assertTrue(should_show_last_name_of_user_in_user_table("Doe"));
  }

  @Test(description = "US002: Edit/Update the user successfully")
  public void US002_Edit_or_update_the_user_successfully() {
    editUser("John", "Ho", "john@doe.com", "34", "40000", "Computer Science");
    assertTrue(should_show_first_name_of_user_in_user_table("John"));
    assertTrue(should_show_last_name_of_user_in_user_table("Ho"));
  }

  @Test(description = "US003: Delete the user successfully")
  public void US003_delete_the_user_successfully() {
    deleteUser("john@doe.com");
    assertTrue(should_not_show_first_name_of_user_in_user_table("John"));
    assertTrue(should_not_show_last_name_of_user_in_user_table("Ho"));
  }

  @AfterTest
  public void tearDown() {
    webUI.closeBrowser();
  }
}
