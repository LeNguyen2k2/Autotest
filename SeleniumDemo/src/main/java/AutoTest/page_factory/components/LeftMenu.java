package Autotest.page_factory.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Autotest.common.keywords.WebUI;
import Autotest.page_factory.pages.BasePage;
import Autotest.pages.Manager;
import Autotest.pages.NewCustomer;

public class LeftMenu extends BasePage {

  @FindBy(xpath = "//a[normalize-space()='Manager']")
  WebElement lnkManager;

  @FindBy(xpath = "//a[normalize-space()='New Customer']")
  WebElement lnkNewCustomer;

  public LeftMenu(WebUI webUI) {
    super(webUI);
  }

  public Manager move_to_Manager() {
    webUI.click(lnkManager);
    return new Manager(webUI);
  }

  public NewCustomer move_to_New_Customer() {
    webUI.click(lnkNewCustomer);
    return new NewCustomer(webUI);
  }
}
