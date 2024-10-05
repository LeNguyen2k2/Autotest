package Autotest.pages;

import io.qameta.allure.Step;
import Autotest.common.keywords.WebUI;
import Autotest.components.LeftMenu;

public class Manager extends BasePage {

  public Manager(WebUI webUI) {
    super(webUI);
    setRepoName(Manager.class.getSimpleName());
  }

//  @Step("Move to New Customer")
//  public NewCustomer move_to_New_Customer() {
//    webUI.click(findElementObject("LNK_NEW_CUSTOMER"));
//    webUI.delayInSecond(3);
//    return new NewCustomer(webUI);
//  }

  public LeftMenu leftMenu() {
    return new LeftMenu(webUI);
  }

  @Step("Should show Manager id: {0}")
  public boolean should_show_Manager_ID(String id) {
    return webUI.verifyElementText(findElementObject("LBL_MANGER_ID"), "Manger Id : " + id);
  }
}
