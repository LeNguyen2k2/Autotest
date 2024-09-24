package Autotest.pages;

import Autotest.model.Customer;
import io.qameta.allure.Step;
import Autotest.keywords.WebUI;
import org.openqa.selenium.Keys;

public class EditCustomer extends BasePage {

  public EditCustomer(WebUI webUI) {
    super(webUI);
    setRepoName(EditCustomer.class.getSimpleName());
  }

  @Step("Input Customer ID: {0}")
  public void inputCustomerId(String customerId) {
    webUI.sendKeys(findElementObject("TXT_CUSTOMER_ID"), customerId);
  }

  @Step("Click submit button in Edit Customer")
  public CustomerRegMsg clickSubmitEditForm() {
    webUI.click(findElementObject("EDIT_CUSTOMER_FORM_BTN_SUBMIT"));
    return new CustomerRegMsg(webUI);
  }

  @Step("Should show error alert message")
  public boolean should_show_error_alert_message() {
    return webUI.verifyAlertPresent();
  }

  @Step("Click submit button in Edit Customer")
  public CustomerRegMsg clickSubmitEditCustomer() {
    webUI.click(findElementObject("EDIT_CUSTOMER_BTN_SUBMIT"));
    return new CustomerRegMsg(webUI);
  }

  @Step("Input Address: {0}")
  public void input_Address(String address) {
    if (address.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_ADDRESS"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_ADDRESS"), address);
    }
//    webUI.delayInSecond(1);
  }

  @Step("Input City: {0}")
  public void input_City(String city) {
    if (city.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_CITY"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_CITY"), city);
    }
//    webUI.delayInSecond(1);
  }

  @Step("Input State: {0}")
  public void input_State(String state) {
    if (state.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_STATE"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_STATE"), state);
    }
//    webUI.delayInSecond(1);
  }

  @Step("Input PIN: {0}")
  public void input_PIN(String pin) {
    if (pin.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_PIN"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_PIN"), pin);
    }
//    webUI.delayInSecond(1);
  }

  @Step("Input Telephone: {0}")
  public void input_Telephone(String telephone) {
    if (telephone.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_TELEPHONE"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_TELEPHONE"), telephone);
    }
//    webUI.delayInSecond(1);
  }


  @Step("Edit information: {0}")
  public void edit_information(Customer customer) {
    input_Address(customer.getAddress());
    input_City(customer.getCity());
    input_State(customer.getState());
    input_PIN(customer.getPin());
    input_Telephone(customer.getTelephone());
  }

}
