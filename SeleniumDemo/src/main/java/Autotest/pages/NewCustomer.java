package Autotest.pages;

import Autotest.excel_object_repo.ExcelObjectRepository;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import Autotest.common.keywords.WebUI;
import Autotest.model.Customer;
import Autotest.components.LeftMenu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class NewCustomer extends BasePage {

  private ExcelObjectRepository objectRepo;
  protected static final String DATA_FILE_PATH =
          System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                  + File.separator + "resources" + File.separator + "datafiles" + File.separator
                  + "TestData.xlsx";

  public NewCustomer(WebUI webUI) {
    super(webUI);
    setRepoName(NewCustomer.class.getSimpleName());
    objectRepo = new ExcelObjectRepository(DATA_FILE_PATH);
  }

  @Step("Input Customer name: {0}")
  public void input_Customer_Name(String customerName) {
    if (customerName.isEmpty()) {
      webUI.sendKeys(objectRepo.findElementObject("TXT_CUSTOMER_NAME"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(objectRepo.findElementObject("TXT_CUSTOMER_NAME"), customerName);
    }
//    webUI.delayInSecond(2);
  }

  @Step("Input Address: {0}")
  public void input_Address(String address) {
    if (address.isEmpty()) {
      webUI.sendKeys(objectRepo.findElementObject("TXT_ADDRESS"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(objectRepo.findElementObject("TXT_ADDRESS"), address);
    }
//    webUI.delayInSecond(2);
  }

  @Step("Input City: {0}")
  public void input_City(String city) {
    if (city.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_CITY"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_CITY"), city);
    }
//    webUI.delayInSecond(2);
  }

  @Step("Input State: {0}")
  public void input_State(String state) {
    if (state.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_STATE"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_STATE"), state);
    }
//    webUI.delayInSecond(2);
  }

  @Step("Input PIN: {0}")
  public void input_PIN(String pin) {
    if (pin.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_PIN"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_PIN"), pin);
    }
//    webUI.delayInSecond(2);
  }

  @Step("Input Telephone: {0}")
  public void input_Telephone(String telephone) {
    if (telephone.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_TELEPHONE"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_TELEPHONE"), telephone);
    }
//    webUI.delayInSecond(2);
  }

  @Step("Input Email: {0}")
  public void input_Email(String email) {
    if (email.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_EMAIL"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_EMAIL"), email);
    }
//    webUI.delayInSecond(2);
  }

  @Step("Should show customer name error message: {0}")
  public boolean should_Show_Customer_Name_Error_Message(String errorMessage) {
    return webUI.verifyElementText(findElementObject("LBL_CUSTOMER_NAME_ERROR_MESSAGE"), errorMessage);
  }

  @Step("An error message for address is shown: {0}")
  public boolean an_Error_Message_Address_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(findElementObject("LBL_ADDRESS_ERROR_MESSAGE"), errorMessage);
  }

  @Step("An error message for city is shown: {0}")
  public boolean an_Error_Message_City_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(findElementObject("LBL_CITY_ERROR_MESSAGE"), errorMessage);
  }

  @Step("An error message for state is shown: {0}")
  public boolean an_Error_Message_State_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(findElementObject("LBL_STATE_ERROR_MESSAGE"), errorMessage);
  }

  @Step("An error message for pin is shown: {0}")
  public boolean an_Error_Message_Pin_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(findElementObject("LBL_PIN_ERROR_MESSAGE"), errorMessage);
  }

  @Step("An error message for telephone is shown: {0}")
  public boolean an_Error_Message_Telephone_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(findElementObject("LBL_TELEPHONE_ERROR_MESSAGE"), errorMessage);
  }

  @Step("An error message for email is shown: {0}")
  public boolean an_Error_Message_Email_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(findElementObject("LBL_EMAIL_ERROR_MESSAGE"), errorMessage);
  }

  @Step("Input password: {0}")
  public void input_Password(String password) {
    if (password.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_PASSWORD"), password);
    } else {
      webUI.sendKeys(findElementObject("TXT_PASSWORD"), password);
    }
  }

  @Step("Should show password error message: {0}")
  public boolean should_show_password_error_message(String errorMessage) {
    return webUI.verifyElementText(findElementObject("LBL_PASSWORD_ERROR_MESSAGE"), errorMessage);
  }

  @Step("Input date of birth: {0}")
  public void input_date_of_birth(String dateOfBirth) {
    webUI.takeScreenShot();
    String browserName = webUI.getBrowserName();
    if (dateOfBirth.isEmpty()) {
      if (browserName.equalsIgnoreCase("FIREFOX")) {
        webUI.click(findElementObject("DTP_DATE_OF_BIRTH"));
        try {
          Robot robot = new Robot();
          robot.keyPress(KeyEvent.VK_TAB);
          Thread.sleep(200);
          robot.keyRelease(KeyEvent.VK_TAB);
        } catch (Exception e) {
//          logger.error("Failed to TAB. Root cause: {}", e.getMessage());
        }
      } else {
        webUI.sendKeys(findElementObject("DTP_DATE_OF_BIRTH"), Keys.chord(Keys.TAB));
      }
    } else {
      if (browserName.equalsIgnoreCase("FIREFOX")) {
        webUI.click(findElementObject("DTP_DATE_OF_BIRTH"));
        try {
          Robot robot = new Robot();
          for (char c : dateOfBirth.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
              throw new RuntimeException(
                      "Key code not found for character '" + c + "'");
            }
            robot.keyPress(keyCode);
            robot.delay(100);
            robot.keyRelease(keyCode);
            robot.delay(100);
          }
        } catch (Exception e) {
//          logger.error("Failed to send key date of birth. Root cause: {}", e.getMessage());
        }
      } else {
        webUI.sendKeys(findElementObject("DTP_DATE_OF_BIRTH"), dateOfBirth);
      }
    }
    webUI.takeScreenShot();
  }

  @Step("Should show date of birth error message")
  public boolean should_show_date_of_birth_error_message(String errorMessage) {
    return webUI.verifyElementText(findElementObject("LBL_DATE_OF_BIRTH_ERROR_MESSAGE"),
            errorMessage);
  }

  @Step("Click submit button in New Customer")
  public CustomerRegMsg clickSubmitNewCustomer() {
    webUI.click(findElementObject("NEW_CUSTOMER_BTN_SUBMIT"));
    return new CustomerRegMsg(webUI);
  }

  @Step("Should show error alert message")
  public boolean should_show_error_alert_message() {
    return webUI.verifyAlertPresent();
  }

  @Step("Click reset button in New Customer")
  public void clickResetNewCustomer() {
    webUI.click(findElementObject("NEW_CUSTOMER_BTN_RESET"));
  }

  @Step("Choose gender: {0}")
  public void chooseGender(String gender) {
    if (gender.equalsIgnoreCase("female")) {
      webUI.click(findElementObject("CHK_FEMALE"));
    } else {
      webUI.click(findElementObject("CHK_MALE"));
    }
  }

  @Step("Create new customer: {0}")
  public void create_new_customer(Customer customer) {
    input_Customer_Name(customer.getCustomerName());
    chooseGender(customer.getGender());
    input_date_of_birth(customer.getDateOfBirth());
    input_Address(customer.getAddress());
    input_City(customer.getCity());
    input_State(customer.getState());
    input_PIN(customer.getPin());
    input_Telephone(customer.getTelephone());
    input_Email(customer.getEmail());
    input_Password(customer.getPassword());
  }

  @Step("get Customer id: {0}")
  public String getCustomerID(String customerID) {
    return getCustomerID(customerID);
  }

  @Step("Get the value of customer name")
  public String customerName() {
    return webUI.getText(objectRepo.getWebElement("TXT_CUSTOMER_NAME"));
  }

  @Step("Get the value of address")
  public String address() {
    return webUI.getText(findElementObject("TXT_ADDRESS"));
  }

  @Step("Get the value of city")
  public String city() {
    return webUI.getText(findElementObject("TXT_CITY"));
  }

  @Step("Get the value of state")
  public String state() {
    return webUI.getText(findElementObject("TXT_STATE"));
  }

  @Step("Get the value of pin")
  public String pin() {
    return webUI.getText(findElementObject("TXT_PIN"));
  }

  @Step("Get the value of telephone")
  public String telephone() {
    return webUI.getText(findElementObject("TXT_TELEPHONE"));
  }

  @Step("Get the value of telephone")
  public String email() {
    return webUI.getText(findElementObject("TXT_EMAIL"));
  }

  @Step("Get the value of password")
  public String password() {
    return webUI.getText(findElementObject("TXT_PASSWORD"));
  }

  @Step("Get the value of date of birth")
  public String dateOfBirth() {
    return webUI.getText(findElementObject("DTP_DATE_OF_BIRTH"));
  }

  @Step("Click continue")
  public void clickContinue() {
    webUI.click(findElementObject("LNK_CONTINUE"));
  }

  @Step("Click home")
  public Manager clickHome() {
    webUI.click(findElementObject("LNK_HOME"));
    return new Manager(webUI);
  }

  public LeftMenu leftMenu() {
    return new LeftMenu(webUI);
  }

}
