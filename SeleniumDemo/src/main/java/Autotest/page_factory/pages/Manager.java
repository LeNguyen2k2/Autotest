package Autotest.page_factory.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Autotest.common.keywords.WebUI;
import Autotest.page_factory.components.LeftMenu;

public class Manager extends BasePage {

  @FindBy(xpath = "//td[contains(text(),'Manger Id')]")
  WebElement lblManagerId;

  @FindBy(xpath = "//a[normalize-space()='New Customer']")
  WebElement lnkNewCustomer;

  public Manager(WebUI webUI) {
    super(webUI);
  }

  public LeftMenu leftMenu() {
    return new LeftMenu(webUI);
  }

  @Step("Should show Manager id: {0}")
  public boolean should_show_Manager_ID(String id) {
    return webUI.verifyElementText(lblManagerId, "Manger Id : " + id);
  }

  @Step("Move to New Customer")
  public NewCustomer move_to_New_Customer() {
    webUI.click(lnkNewCustomer);
    webUI.delayInSecond(3);
    return new NewCustomer(webUI);
  }

}
