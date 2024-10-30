package Autotest.pages;

import Autotest.common.keywords.WebUI;
import Autotest.components.CategoryMenu;
import Autotest.test.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class FirstTV extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(FirstTV.class);

    public FirstTV(WebUI webUI) {
        super(webUI);
        setRepoName(FirstTV.class.getSimpleName());
    }

    public void closePopupIfVisible() {
        webUI.delayInSecond(2);
        webUI.click(findElementObject("CHK_NONE"));
        webUI.delayInSecond(2);
        webUI.click(findElementObject("POPUP_CLOSE_BUTTON"));
        webUI.delayInSecond(2);
    }

    public void checkOverlayDisplayed() {
        WebElement overlay = findElementObject("LOCATIONBOX_OVERLAY");
        String displayStyle = overlay.getCssValue("display");
        if ("none".equals(displayStyle)) {
            System.out.println("Overlay is not displayed.");
        } else if ("block".equals(displayStyle)) {
            System.out.println("Overlay is displayed.");
        } else {
            System.out.println("Overlay display style is: " + displayStyle);
        }
    }

    public void checkCartItemCount() {
        try {
            WebElement cartItemCountElement = findElementObject("CART_ITEM_COUNT"); // Đảm bảo bạn đã định nghĩa locator này
            if (cartItemCountElement.isDisplayed()) {
                String itemCountText = cartItemCountElement.getText().trim();
                int actualCount = Integer.parseInt(itemCountText);
                System.out.println("Current cart item count: " + actualCount);
            } else {
                System.out.println("Cart item count element is not displayed.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Cart item count element not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing the item count: " + e.getMessage());
        }
        webUI.delayInSecond(2);
    }



    @Step("Add to Cart")
    public void add_to_Cart() {
        WebElement addToCartButton = findElementObject("BTN_CART");
        webUI.delayInSeconds(2);
        try {
            addToCartButton.click();
        } catch (Exception e) {
            logger.warn("Standard click failed, attempting JavaScript click. Root cause: {}", e.getMessage());
            webUI.clickUsingJavaScript(addToCartButton);
        }
//        checkOverlayDisplayed();
        closePopupIfVisible();
        webUI.takeScreenShot();
    }


}
