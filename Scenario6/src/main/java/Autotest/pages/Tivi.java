package Autotest.pages;

import Autotest.common.keywords.WebUI;
import Autotest.components.CategoryMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Tivi extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(Tivi.class);

    public Tivi(WebUI webUI) {
        super(webUI);
        setRepoName(Tivi.class.getSimpleName());

    }

    @Step("clickFilter")
    public void clickFilter() {
            webUI.click(findElementObject("BTN_FILTER"));
            webUI.delayInSecond(3);
            webUI.takeScreenShot();
    }


    public void chooseScreenSize(int screenSize) {
        String optionXPath = "";

        switch (screenSize) {
            case 43:
                optionXPath = "OPT_43inch";
                break;
            case 50:
                optionXPath = "OPT_50inch";
                break;
            case 55:
                optionXPath = "OPT_55inch";
                break;
            case 65:
                optionXPath = "OPT_65inch";
                break;
            case 70:
                optionXPath = "OPT_70inch";
                break;
            case 7585:
                optionXPath = "OPT_75to85inch";
                break;
            default:
                logger.warn("Invalid screen size: " + screenSize);
                return;
        }

        webUI.click(findElementObject(optionXPath));
        logger.info("Selected screen size: " + screenSize + " inch");
    }


    @Step("Filter TVs by screen sizes: {screenSizes}")
    public void filter_TV_By_Screen_Size(int... screenSizes) {
        try {
            for (int screenSize : screenSizes) {
                chooseScreenSize(screenSize);
            }
            logger.info("Filtered TVs by screen sizes: " + Arrays.toString(screenSizes));
        } catch (Exception e) {
            logger.error("Failed to filter TVs by screen sizes: " + Arrays.toString(screenSizes) + ". Root cause: " + e.getMessage());
        }
        webUI.delayInSecond(3);
        webUI.takeScreenShot();
    }

    @Step("Click and get Total Results")
    public void ClickAndGetTotalResults() {
        String totalResultsText = webUI.getText(findElementObject("BTN_TOTAL_RESULTS"));
        System.out.println("Total Results: " + totalResultsText);
        webUI.delayInSecond(3);
        webUI.click(findElementObject("BTN_TOTAL_RESULTS"));
        webUI.delayInSecond(3);
        webUI.takeScreenShot();
    }
    @Step("Click on the first product after filtering")
    public void clickFirstTV() {
        webUI.click(findElementObject("FIR_TV"));
        webUI.delayInSecond(2);
        webUI.takeScreenShot();
    }

    @Step("Clear all results")
    public void clearResults() {
        webUI.delayInSecond(2);
        webUI.click(findElementObject("BTN_CLEAR_RESULTS"));
        webUI.delayInSecond(2);
    }

    public CategoryMenu getCategoryPage() {
        return new CategoryMenu(webUI);
    }
}
