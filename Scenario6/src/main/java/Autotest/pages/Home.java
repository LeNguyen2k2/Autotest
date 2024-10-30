package Autotest.pages;

import Autotest.common.keywords.WebUI;
import Autotest.components.CategoryMenu;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Home extends BasePage {
  Actions actions;

  public Home(WebUI webUI) {
    super(webUI);
    setRepoName(Home.class.getSimpleName());
    actions = new Actions(webUI.getDriver());
  }

  public Home mouse_to_Category() {
    WebElement categoryButton = findElementObject("BTN_CATEGORY");
    actions.moveToElement(categoryButton).perform();
    return new Home(webUI);

  }

  public Home mouse_to_Electronics() {
    WebElement categoryButton = findElementObject("BTN_PRODUCT");
    actions.moveToElement(categoryButton).perform();
    return new Home(webUI);

  }

  public CategoryMenu getCategoryPage() {
    return new CategoryMenu(webUI);
  }
}
