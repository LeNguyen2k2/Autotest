package Autotest.pages;

import io.qameta.allure.Step;
import Autotest.common.keywords.WebUI;

public class CustomerRegMsg extends BasePage {


  public CustomerRegMsg(WebUI webUI) {
    super(webUI);
    setRepoName(CustomerRegMsg.class.getSimpleName());
  }

  @Step("Should show text of heading: {0}")
  public boolean should_show_text_of_heading(String expectedText) {
    webUI.takeScreenShot();
    return webUI.verifyElementText(findElementObject("LBL_HEADING"), expectedText);
  }

  @Step("Get Customer ID")
  public String getCustomerId() {
    return webUI.getText(findElementObject("LBL_CUSTOMER_ID"));
  }
}
