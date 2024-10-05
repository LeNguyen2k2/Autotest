package Autotest.page_factory.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Autotest.common.keywords.WebUI;

public class BasePage {

  private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

  protected WebUI webUI;

//  protected LeftMenu objLeftMenu;

  private String repoName;

  public BasePage(WebUI webUI) {
    this.webUI = webUI;
    PageFactory.initElements(new AjaxElementLocatorFactory(webUI.getDriver(), 5), this);
  }
}
