package Autotest.components;

import Autotest.common.keywords.WebUI;
import Autotest.pages.BasePage;
import Autotest.pages.Home;
import Autotest.pages.Tivi;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CategoryMenu extends BasePage {
    Actions actions;

    public CategoryMenu(WebUI webUI) {
        super(webUI);
        setRepoName(CategoryMenu.class.getSimpleName());
        actions = new Actions(webUI.getDriver());
    }

    public Home mouse_to_Category() {
        WebElement categoryButton = findElementObject("BTN_CATEGORY");
        actions.moveToElement(categoryButton).perform();
        return new Home(webUI);

    }

    public Home mouse_to_Electronics() {
        WebElement categoryButton = findElementObject("BTN_ELECTRONICS");
        actions.moveToElement(categoryButton).perform();
        return new Home(webUI);

    }

    public Tivi navigate_to_Tivi() {
        WebElement productButton = findElementObject("BTN_TIVI");
        webUI.click(productButton);
        return new Tivi(webUI);
    }
}
