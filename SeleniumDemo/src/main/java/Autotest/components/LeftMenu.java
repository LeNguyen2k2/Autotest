package Autotest.components;

import Autotest.common.keywords.WebUI;
import Autotest.pages.*;

public class LeftMenu extends BasePage {

  public LeftMenu(WebUI webUI) {
    super(webUI);
    setRepoName(LeftMenu.class.getSimpleName());
  }

  public Manager move_to_Manager() {
    webUI.click(findElementObject("LNK_MANAGER"));
    return new Manager(webUI);
  }

  public NewCustomer move_to_New_Customer() {
    webUI.click(findElementObject("LNK_NEW_CUSTOMER"));
    return new NewCustomer(webUI);
  }
  public EditCustomer move_to_Edit_Customer() {
    webUI.click(findElementObject("LNK_EDIT_CUSTOMER"));
    return new EditCustomer(webUI);
  }

  public Login logout() {
    webUI.click(findElementObject("LNK_LOG_OUT"));
    webUI.delayInSecond(2);
    webUI.acceptAlert();
    return new Login(webUI);
  }
}
