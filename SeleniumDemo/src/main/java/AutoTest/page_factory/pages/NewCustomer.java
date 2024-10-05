package Autotest.page_factory.pages;

import Autotest.common.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewCustomer extends BasePage {
  @FindBy(name = "name")
  WebElement txtCustomerName;

  @FindBy(name = "addr")
  WebElement txtAddress;

  @FindBy(name = "city")
  WebElement txtCity;

  @FindBy(name = "state")
  WebElement txtState;

  @FindBy(name = "pinno")
  WebElement txtPin;

  @FindBy(name = "telephoneno")
  WebElement txtTelephone;

  @FindBy(name = "emailid")
  WebElement txtEmail;

  @FindBy(xpath = "//label[@id='message']")
  WebElement lblCustomerNameErrorMessage;

  @FindBy(xpath = "//label[@id='message3']")
  WebElement lblAddressErrorMessage;

  @FindBy(xpath = "//label[@id='message4']")
  WebElement lblCityErrorMessage;

  @FindBy(xpath = "//label[@id='message5']")
  WebElement lblStateErrorMessage;

  @FindBy(xpath = "//label[@id='message6']")
  WebElement lblPinErrorMessage;

  @FindBy(xpath = "//label[@id='message7']")
  WebElement lblTelephoneErrorMessage;

  @FindBy(xpath = "//label[@id='message9']")
  WebElement lblEmailErrorMessage;

  // Constructor
  public NewCustomer(WebUI webUI) {
    super(webUI);
  }

  @Step("Input Customer name: {0}")
  public void input_Customer_Name(String customerName) {
    if (customerName.isEmpty()) {
      webUI.sendKeys(txtCustomerName, Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(txtCustomerName, customerName);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input Address: {0}")
  public void input_Address(String address) {
    if (address.isEmpty()) {
      webUI.sendKeys(txtAddress, Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(txtAddress, address);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input City: {0}")
  public void input_City(String city) {
    if (city.isEmpty()) {
      webUI.sendKeys(txtCity, Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(txtCity, city);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input State: {0}")
  public void input_State(String state) {
    if (state.isEmpty()) {
      webUI.sendKeys(txtState, Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(txtState, state);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input PIN: {0}")
  public void input_PIN(String pin) {
    if (pin.isEmpty()) {
      webUI.sendKeys(txtPin, Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(txtPin, pin);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input Telephone: {0}")
  public void input_Telephone(String telephone) {
    if (telephone.isEmpty()) {
      webUI.sendKeys(txtTelephone, Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(txtTelephone, telephone);
    }
    webUI.delayInSecond(3);
  }

  @Step("Input Email: {0}")
  public void input_Email(String email) {
    if (email.isEmpty()) {
      webUI.sendKeys(txtEmail, Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(txtEmail, email);
    }
    webUI.delayInSecond(3);
  }

  @Step("Should show customer name error message: {0}")
  public boolean should_Show_Customer_Name_Error_Message(String errorMessage) {
    return webUI.verifyElementText(lblCustomerNameErrorMessage, errorMessage);
  }

  @Step("An error message for address is shown: {0}")
  public boolean an_Error_Message_Address_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(lblAddressErrorMessage, errorMessage);
  }

  @Step("An error message for city is shown: {0}")
  public boolean an_Error_Message_City_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(lblCityErrorMessage, errorMessage);
  }

  @Step("An error message for state is shown: {0}")
  public boolean an_Error_Message_State_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(lblStateErrorMessage, errorMessage);
  }

  @Step("An error message for pin is shown: {0}")
  public boolean an_Error_Message_Pin_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(lblPinErrorMessage, errorMessage);
  }

  @Step("An error message for telephone is shown: {0}")
  public boolean an_Error_Message_Telephone_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(lblTelephoneErrorMessage, errorMessage);
  }

  @Step("An error message for email is shown: {0}")
  public boolean an_Error_Message_Email_Is_Shown(String errorMessage) {
    return webUI.verifyElementText(lblEmailErrorMessage, errorMessage);
  }
}
