package Autotest.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import Autotest.keywords.WebUI;
import Autotest.object_repo.NewCustomerRepo;

public class NewCustomer extends BasePage {

  public NewCustomer(WebUI webUI) {
    super(webUI);
    setRepoName(NewCustomer.class.getSimpleName());
  }

  @Step("Input Customer name: {0}")
  public void input_Customer_Name(String customerName) {
    if (customerName.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_CUSTOMER_NAME"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_CUSTOMER_NAME"), customerName);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input Address: {0}")
  public void input_Address(String address) {
    if (address.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_ADDRESS"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_ADDRESS"), address);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input City: {0}")
  public void input_City(String city) {
    if (city.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_CITY"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_CITY"), city);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input State: {0}")
  public void input_State(String state) {
    if (state.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_STATE"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_STATE"), state);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input PIN: {0}")
  public void input_PIN(String pin) {
    if (pin.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_PIN"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_PIN"), pin);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input Telephone: {0}")
  public void input_Telephone(String telephone) {
    if (telephone.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_TELEPHONE"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_TELEPHONE"), telephone);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input Email: {0}")
  public void input_Email(String email) {
    if (email.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_EMAIL"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_EMAIL"), email);
    }
    webUI.delayInSecond(3);
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
}
