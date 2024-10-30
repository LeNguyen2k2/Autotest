package Autotest.common.keywords;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WebUI {

    private static final Logger logger = LoggerFactory.getLogger(WebUI.class);
    private final int DEFAUT_TIMEOUT = 60;
    private WebDriver driver;

    public void openBrowser(String browser, String... url) {
        try {
            logger.info("Opening browser: {}", browser);
            switch (browser.toUpperCase()) {
                case "CHROME":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-infobars");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "FIREFOX": // Updated for Firefox
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "EDGE": // Cập nhật cho Edge
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
            logger.info("Opened browser '{}' successfully", browser);

            if (url.length > 0) {
                String rawUrl = url[0];
                logger.info("Navigating to {}", rawUrl);
                driver.get(rawUrl);
            }
        } catch (Exception e) {
            logger.error("Failed to open browser '{}'. Root cause: {}", browser, e.getMessage());
        }
        // driver.manage().window().maximize();
    }



    public void maximizeWindow() {
        try {
            logger.info("Maximizing window");
            driver.manage().window().maximize();
            logger.info("Maximized window");
        } catch (Exception e) {
            logger.error("Failed to maximize window. Root cause: {}", e.getMessage());
        }
    }

    public String getTitle() {
        String actualTitle = null;
        try {
            logger.info("Getting title");
            actualTitle = driver.getTitle();
            logger.info("Title of the page is '{}'", actualTitle);
            return actualTitle;
        } catch (Exception e) {
            logger.error("Failed to get title. Root cause: {}", e.getMessage());
        }
        return null;
    }

    public String getCurrentUrl() {
        String currentUrl = "";
        try {
            currentUrl = driver.getCurrentUrl();
            logger.info("Current URL: " + currentUrl);
        } catch (Exception e) {
            logger.error("Failed to retrieve the current URL. Root cause: " + e.getMessage());
        }
        return currentUrl;
    }


    public void closeBrowser() {
        try {
            logger.info("Closing the browser");
            driver.quit();
            logger.info("Closed browser");
        } catch (Exception e) {
            logger.error("Failed to close browser. Root cause: {}", e.getMessage());
        }
    }

    public boolean verifyTitle(String expectedTitle) {
        String actualTitle = null;
        try {
            logger.info("Getting title");
            actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                logger.info("Title of the page is '{}'", actualTitle);
                return true;
            }
            logger.error("Actual title '{}' and Expected title '{}' are not match", actualTitle, expectedTitle);
        } catch (Exception e) {
            logger.error("Failed to get title. Root cause: {}", e.getMessage());
        }
        return false;
    }

    public void navigateTo(String url) {
        try {
            logger.info("Navigating to {}", url);
            driver.navigate().to(url);
            logger.info("Navigated to {} successfully", url);
        } catch (Exception e) {
            logger.error("Failed to navigate to {}. Root cause: {}", url, e.getMessage());
        }
    }

    public void goBack() {
        try {
            driver.navigate().back();
            logger.info("Navigated back to the previous page.");
            logger.info("Element is visible after going back.");
        } catch (Exception e) {
            logger.error("Failed to navigate back. Root cause: {}", e.getMessage());
        }
        takeScreenShot();
    }


//    public WebElement findWebElement(String locator) {
//        WebElement element = null;
//        try {
//            logger.info("Finding element with locator '{}'", locator);
//
//            String[] locatorParts = locator.split(":", 2);
//            String type = locatorParts[0];
//            String value = locatorParts[1];
//
//            switch (type.toLowerCase()) {
//                case "id":
//                    element = driver.findElement(By.id(value));
//                    break;
//                case "name":
//                    element = driver.findElement(By.name(value));
//                    break;
//                case "css":
//                case "cssselector":
//                    element = driver.findElement(By.cssSelector(value));
//                    break;
//                case "xpath":
//                    element = driver.findElement(By.xpath(value));
//                    break;
//                case "classname":
//                    element = driver.findElement(By.className(value));
//                    break;
//                case "tagname":
//                    element = driver.findElement(By.tagName(value));
//                    break;
//                case "linktext":
//                    element = driver.findElement(By.linkText(value));
//                    break;
//                case "partiallinktext":
//                    element = driver.findElement(By.partialLinkText(value));
//                    break;
//                default:
//                    throw new IllegalArgumentException("Invalid locator type: " + type);
//            }
//
//            logger.info("Found element with locator '{}'", locator);
//        } catch (Exception e) {
//            logger.error("Failed to find element with locator '{}'. Root cause: {}", locator, e.getMessage());
//        }
//        return element;
//    }


    private By findBy(String locator) {
        String prefix = StringUtils.substringBefore(locator, ":");
        String locatorValue = StringUtils.substringAfter(locator, ":");
        return switch (prefix.toLowerCase()) {
            case "xpath" -> By.xpath(locatorValue);
            case "css" -> By.cssSelector(locatorValue);
            case "id" -> By.id(locatorValue);
            case "link" -> By.linkText(locatorValue);
            case "name" -> By.name(locatorValue);
            case "partialLinkText" -> By.partialLinkText(locatorValue);
            case "tag" -> By.tagName(locatorValue);
            default -> By.xpath(locator);
        };
    }

    public WebElement findWebElement(String locator) {
        WebElement we = null;
        long startTime = 0;
        long endTime = 0;
        double totalTime = 0;
//    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        try {
            logger.info("Finding web element located by '{}'", locator);
            startTime = System.currentTimeMillis();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAUT_TIMEOUT));
            we = wait.until(ExpectedConditions.presenceOfElementLocated(findBy(locator)));
            endTime = System.currentTimeMillis();
            logger.info("Found 1 web element located by '{}'", locator);
        } catch (Exception e) {
            endTime = System.currentTimeMillis();
            logger.error("Failed to find web element located by '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
        totalTime = (double) (endTime - startTime) / 1000;
        logger.info("Total time: {}", totalTime);
        return we;
    }

    public WebElement findWebElement(By by) {
        WebElement we = null;
        long startTime = 0;
        long endTime = 0;
        double totalTime = 0;
        try {
            logger.info("Finding web element located by '{}'", by);
            startTime = System.currentTimeMillis();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAUT_TIMEOUT));
            we = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            endTime = System.currentTimeMillis();
            logger.info("Found 1 web element located by '{}'", by);
        } catch (Exception e) {
            endTime = System.currentTimeMillis();
            logger.error("Failed to find web element located by '{}'. Root cause: {}", by,
                    e.getMessage());
        }
        totalTime = (double) (endTime - startTime) / 1000;
        logger.info("Total time: {}", totalTime);
        return we;
    }

    public List<WebElement> findWebElements(String locator) {
        List<WebElement> wes = null;
        try {
            logger.info("Finding element with locator '{}'", locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAUT_TIMEOUT));
            wes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
            logger.info("Found '{}' element(s) with locator '{}'", wes.size(), locator);
        } catch (Exception e) {
            logger.error("Failed to find element with locator '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
        return wes;
    }

    public void sendKeys(String locator, String text) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Sending keys '{}'", text);
            we.clear();
            we.sendKeys(text);
            logger.info("Sent keys '{}' successfully", text);
        } catch (Exception e) {
            logger.error("Failed to send keys. Root cause: {}", e.getMessage());
        }
    }

    public void sendKeys(WebElement we, String text) {
        try {
            logger.info("Sending keys '{}'", text);
            we.clear();
            we.sendKeys(text);
            logger.info("Sent keys '{}' successfully", text);
        } catch (Exception e) {
            logger.error("Failed to send keys. Root cause: {}", e.getMessage());
        }
    }

    public void clearText(String locator) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Clearing text in web element located by '{}'", locator);
            we.clear();
            logger.info("Cleared text in web element located by '{}' successfully", locator);
        } catch (Exception e) {
            logger.error("Failed to clear text. Root cause: {}", e.getMessage());
        }
    }


    public void delayInSecond(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.info("Delayed {} seconds", seconds);
        } catch (InterruptedException e) {
            logger.error("Failed to delay {} second(s). Root cause: {}", seconds, e.getMessage());
        }
    }

    public String getAttributeValue(String locator, String attributeName) {
        WebElement we = findWebElement(locator);
        String actualAttributeValue = "";
        try {
            logger.info("Getting attribute value of web element located by '{}' via attribute name '{}'", locator, attributeName);
            actualAttributeValue = we.getAttribute(attributeName);
            logger.info("Attribute value of web element located by '{}' via attribute name '{}' is '{}'", locator, attributeName, actualAttributeValue);
        } catch (Exception e) {
            logger.error("Failed to get attribute value of web element located by '{}'. Root cause: {}", locator, e.getMessage());
        }
        return actualAttributeValue;
    }


    public void waitForElementToBeVisible(String locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            logger.info("Waiting for element with locator '{}' to be visible", locator);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            logger.info("Element with locator '{}' is visible", locator);
        } catch (TimeoutException e) {
            logger.error("Element with locator '{}' was not visible after {} seconds. Root cause: {}", locator, timeoutInSeconds, e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to wait for element with locator '{}' to be visible. Root cause: {}", locator, e.getMessage());
        }
    }


    public void executeJavaScript(String script, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver; // Lấy driver từ WebUI
            js.executeScript(script, element);
            logger.info("Executed JavaScript: {}", script);
        } catch (Exception e) {
            logger.error("Failed to execute JavaScript. Root cause: {}", e.getMessage());
        }
    }

    public void clickUsingJavaScript(WebElement element) {
        // Gọi phương thức executeJavaScript để thực hiện click
        executeJavaScript("arguments[0].click();", element);
    }

    public void selectOptionByIndex(String locator, int index) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Selecting option of web element located by '{}' via index '{}'", locator, index);
            Select select = new Select(we);
            select.selectByIndex(index);
            logger.info("Selected option of web element located by '{}' via index '{}' successfully",
                    locator, index);
        } catch (Exception e) {
            logger.error("Failed to select option of web element located by '{}' via index '{}'. Root cause: {}", locator, index, e.getMessage());
        }
    }

    public void selectOptionByValue(String locator, String value) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Selecting option of web element located by '{}' via value '{}'", locator, value);
            Select select = new Select(we);
            select.selectByValue(value);
            logger.info("Selected option of web element located by '{}' via value '{}' successfully", locator, value);
        } catch (Exception e) {
            logger.error("Failed to select option of web element located by '{}' via value '{}'. Root cause: {}", locator, value, e.getMessage());
        }
    }

    public void selectByVisibleText(String locator, String text) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Selecting option of web element located by '{}' via visible text '{}'", locator, text);
            Select select = new Select(we);
            select.selectByVisibleText(text);
            logger.info("Selected option of web element located by '{}' via visible text '{}' successfully",
                    locator, text);
        } catch (Exception e) {
            logger.error("Failed to select option of web element located by '{}' via visible text '{}'. Root cause: {}", locator, text, e.getMessage());
        }
    }

    public void deselectByIndex(String locator, int index) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Deselecting option of web element located by '{}' via index '{}'", locator, index);
            Select select = new Select(we);
            select.deselectByIndex(index);
            logger.info("Deselected option of web element located by '{}' via index '{}' successfully",
                    locator, index);
        } catch (Exception e) {
            logger.error("Failed to deselect option of web element located by '{}' via index '{}'. Root cause: {}", locator, index, e.getMessage());
        }
    }

    public void deselectByValue(String locator, String value) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Deselecting option of web element located by '{}' via value '{}'", locator, value);
            Select select = new Select(we);
            select.deselectByValue(value);
            logger.info("Deselected option of web element located by '{}' via value '{}' successfully",
                    locator, value);
        } catch (Exception e) {
            logger.error("Failed to deselect option of web element located by '{}' via value '{}'. Root cause: {}", locator, value, e.getMessage());
        }
    }

    public void deselectByVisibleText(String locator, String visibleText) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Deselecting option of web element located by '{}' via visible text '{}'", locator, visibleText);
            Select select = new Select(we);
            select.deselectByVisibleText(visibleText);
            logger.info("Deselected option of web element located by '{}' via visible text '{}' successfully",
                    locator, visibleText);
        } catch (Exception e) {
            logger.error("Failed to deselect option of web element located by '{}' via visible text '{}'. Root cause: {}", locator, visibleText, e.getMessage());
        }
    }

    public void deselectAll(String locator) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Deselecting all options of web element located by '{}'", locator);
            Select select = new Select(we);
            select.deselectAll();
            logger.info("Deselected all options of web element located by '{}' successfully", locator);
        } catch (Exception e) {
            logger.error("Failed to deselect all options of web element located by '{}'. Root cause: {}", locator, e.getMessage());
        }
    }

    public void selectMultipleByIndex(String locator, List<Integer> indices) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Selecting multiple options of web element located by '{}' via indices '{}'", locator, indices);
            Select select = new Select(we);
            for (int index : indices) {
                select.selectByIndex(index);
            }
            logger.info("Selected multiple options of web element located by '{}' via indices '{}' successfully", locator, indices);
        } catch (Exception e) {
            logger.error("Failed to select multiple options of web element located by '{}' via indices '{}'. Root cause: {}", locator, indices, e.getMessage());
        }
    }

    public void selectMultipleByValue(String locator, List<String> values) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Selecting multiple options of web element located by '{}' via values '{}'", locator, values);
            Select select = new Select(we);
            for (String value : values) {
                select.selectByValue(value);
            }
            logger.info("Selected multiple options of web element located by '{}' via values '{}' successfully", locator, values);
        } catch (Exception e) {
            logger.error("Failed to select multiple options of web element located by '{}' via values '{}'. Root cause: {}", locator, values, e.getMessage());
        }
    }

    public void selectMultipleByVisibleText(String locator, List<String> visibleTexts) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Selecting multiple options of web element located by '{}' via visible texts '{}'", locator, visibleTexts);
            Select select = new Select(we);
            for (String visibleText : visibleTexts) {
                select.selectByVisibleText(visibleText);
            }
            logger.info("Selected multiple options of web element located by '{}' via visible texts '{}' successfully", locator, visibleTexts);
        } catch (Exception e) {
            logger.error("Failed to select multiple options of web element located by '{}' via visible texts '{}'. Root cause: {}", locator, visibleTexts, e.getMessage());
        }
    }

    public List<WebElement> getSelectedOptions(String locator) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Getting selected options of web element located by '{}'", locator);
            Select select = new Select(we);
            List<WebElement> selectedOptions = select.getAllSelectedOptions();
            logger.info("Retrieved selected options of web element located by '{}' successfully", locator);
            return selectedOptions;
        } catch (Exception e) {
            logger.error("Failed to get selected options of web element located by '{}'. Root cause: {}", locator, e.getMessage());
            return null;
        }
    }

    public void switchToWindowByIndex(int index) {
        try {
            logger.info("Switching to window with index '{}'", index);
            Set<String> windowHandles = driver.getWindowHandles(); // Lấy tất cả các handle của cửa sổ
            Iterator<String> iterator = windowHandles.iterator(); // Tạo iterator để duyệt qua các handle
            int actualIndex = 0;
            while (iterator.hasNext()) {
                String windowHandle = iterator.next();
                if (actualIndex == index) {
                    driver.switchTo().window(windowHandle); // Chuyển đổi sang cửa sổ với handle hiện tại
                    logger.info("Switched to window with index '{}' successfully", index);
                    return;
                }
                actualIndex++;
            }
            logger.warn("No window found with index '{}'", index); // Cảnh báo nếu không tìm thấy cửa sổ với chỉ số chỉ định
        } catch (Exception e) {
            logger.error("Failed to switch to window with index '{}'. Root cause: {}", index, e.getMessage());
        }
    }

    public void switchToWindowByTitle(String title) {
        try {
            logger.info("Switching to window with title '{}'", title);
            Set<String> windowHandles = driver.getWindowHandles(); // Lấy tất cả các handle của cửa sổ
            for (String handle : windowHandles) {
                driver.switchTo().window(handle); // Chuyển đến cửa sổ với handle hiện tại
                String currentTitle = driver.getTitle(); // Lấy tiêu đề của cửa sổ hiện tại
                if (currentTitle.equals(title)) {
                    logger.info("Switched to window with title '{}' successfully", title);
                    return;
                }
            }
            logger.warn("No window found with title '{}'", title); // Cảnh báo nếu không tìm thấy cửa sổ với tiêu đề chỉ định
        } catch (Exception e) {
            logger.error("Failed to switch to window with title '{}'. Root cause: {}", title, e.getMessage());
        }
    }

    public void switchToWindowByUrl(String url) {
        try {
            logger.info("Switching to window with URL '{}'", url);
            Set<String> windowHandles = driver.getWindowHandles(); // Lấy tất cả các handle của cửa sổ
            for (String handle : windowHandles) {
                driver.switchTo().window(handle); // Chuyển đến cửa sổ với handle hiện tại
                String currentUrl = driver.getCurrentUrl(); // Lấy URL của cửa sổ hiện tại
                if (currentUrl.equals(url)) {
                    logger.info("Switched to window with URL '{}' successfully", url);
                    return;
                }
            }
            logger.warn("No window found with URL '{}'", url); // Cảnh báo nếu không tìm thấy cửa sổ với URL chỉ định
        } catch (Exception e) {
            logger.error("Failed to switch to window with URL '{}'. Root cause: {}", url, e.getMessage());
        }
    }

    public boolean isWindowOpen(String url) {
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (driver.getCurrentUrl().equals(url)) {
                    logger.info("Window with URL '{}' is open", url);
                    return true;
                }
            }
            logger.info("Window with URL '{}' is not open", url);
            return false;
        } catch (Exception e) {
            logger.error("Failed to check if window with URL '{}' is open. Root cause: {}", url, e.getMessage());
            return false;
        }
    }

    public void closeWindowByIndex(int index) {
        try {
            logger.info("Closing window with index '{}'", index);
            Set<String> windowHandles = driver.getWindowHandles();
            Iterator<String> iterator = windowHandles.iterator();
            int currentIndex = 0;
            while (iterator.hasNext()) {
                String windowHandle = iterator.next();
                if (currentIndex == index) {
                    driver.switchTo().window(windowHandle);
                    driver.close();
                    logger.info("Closed window with index '{}' successfully", index);
                    return;
                }
                currentIndex++;
            }
            throw new NoSuchWindowException("No window found with index '" + index + "'");
        } catch (Exception e) {
            logger.error("Failed to close window with index '{}'. Root cause: {}", index, e.getMessage());
        }
    }

    public void closeWindowByTitle(String title) {
        try {
            logger.info("Closing window with title '{}'", title);
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (driver.getTitle().equals(title)) {
                    driver.close();
                    logger.info("Closed window with title '{}' successfully", title);
                    return;
                }
            }
            throw new NoSuchWindowException("No window found with title '" + title + "'");
        } catch (Exception e) {
            logger.error("Failed to close window with title '{}'. Root cause: {}", title, e.getMessage());
        }
    }

    public void closeWindowByUrl(String url) {
        try {
            logger.info("Closing window with URL '{}'", url);
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (driver.getCurrentUrl().equals(url)) {
                    driver.close();
                    logger.info("Closed window with URL '{}' successfully", url);
                    return;
                }
            }
            throw new NoSuchWindowException("No window found with URL '" + url + "'");
        } catch (Exception e) {
            logger.error("Failed to close window with URL '{}'. Root cause: {}", url, e.getMessage());
        }
    }

    public void acceptAlert() {
        try {
            logger.info("Accepting alert...");
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.info("Accepted alert.");
        } catch (Exception e) {
            logger.error("Failed to accept alert. Root cause: {}", e.getMessage());
        }
    }

    public void dismissAlert() {
        try {
            logger.info("Dismissing alert...");
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            logger.info("Dismissed alert.");
        } catch (Exception e) {
            logger.error("Failed to dismiss alert. Root cause: {}", e.getMessage());
        }
    }

    public String getAlertText() {
        try {
            logger.info("Getting text from alert...");
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            logger.info("Alert text retrieved: '{}'", alertText);
            return alertText;
        } catch (Exception e) {
            logger.error("Failed to get text from alert. Root cause: {}", e.getMessage());
            return null;
        }
    }

    public void sendKeysToAlert(String text) {
        try {
            logger.info("Switching to alert...");
            Alert alert = driver.switchTo().alert();
            logger.info("Sending keys to alert: '{}'", text);
            alert.sendKeys(text);
            logger.info("Keys sent to alert successfully.");
        } catch (Exception e) {
            logger.error("Failed to send keys to alert. Root cause: {}", e.getMessage());
        }
    }

    public void switchtoFrame(String locator) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Switching to frame located by '{}'", locator);
            driver.switchTo().frame(we);
            logger.info("Switched to frame '{}' successfully", locator);
        } catch (Exception e) {
            logger.error("Failed to switch to frame located by '{}'. Root cause: {}", locator, e.getMessage());
        }
    }

    public void scrollToElement(String locator) {
        try {
            WebElement element = findWebElement(locator);  // Locate the element using the custom findWebElement method
            if (element != null) {
                logger.info("Scrolling to element located by '{}'", locator);
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);  // Scroll until the element is in view
                logger.info("Successfully scrolled to element '{}'", locator);
            } else {
                logger.warn("Element not found with locator '{}'", locator);
            }
        } catch (Exception e) {
            logger.error("Failed to scroll to element located by '{}'. Root cause: {}", locator, e.getMessage());
        }
    }

    public void click(String locator) {
        WebElement we = findWebElement(locator);
        try {
            scrollToElementAtCenterOfPage(locator);
            logger.info("Clicking web element located by '{}'", locator);
            we.click();
            logger.info("Web element located by '{}' is clicked", locator);
        } catch (Exception e) {
            logger.error("Failed to click web element located by '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
    }

    public void click(WebElement webElement) {
        try {
            scrollToElementAtCenterOfPage(webElement);
            logger.info("Clicking web element located by '{}'", webElement);
            webElement.click();
            logger.info("Web element located by '{}' is clicked", webElement);
        } catch (Exception e) {
            logger.error("Failed to click web element located by '{}'. Root cause: {}", webElement,
                    e.getMessage());
        }
    }

    public void leftClick(String locator) {
        try {
            logger.info("Left clicking on web element located by '{}'", locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(findWebElement(locator)).click().build().perform();
            logger.info("Left clicked on web element located by '{}' successfully", locator);
        } catch (Exception e) {
            logger.error("Failed to Left click on web element located by '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
    }

    public void rightClick(String locator) {
        try {
            logger.info("Right clicking on web element located by '{}'", locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(findWebElement(locator)).contextClick().build().perform();
            logger.info("Right clicked on web element located by '{}' successfully", locator);
        } catch (Exception e) {
            logger.error("Failed to right click on web element located by '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
    }

    public void mouseOver(String locator) {
        try {
            logger.info("Mouse overing on web element located by '{}'", locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(findWebElement(locator)).build().perform();
            logger.info("Mouse overed on web element located by '{}' successfully", locator);
        } catch (Exception e) {
            logger.error("Failed to mouse over on web element located by '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
    }

    public void dragAndDrop(String sourceLocator, String targetLocator) {
        try {
            logger.info(
                    "Dragging source web element located by '{}' to target web element located by '{}'",
                    sourceLocator, targetLocator);
            Actions actions = new Actions(driver);
            actions.dragAndDrop(findWebElement(sourceLocator), findWebElement(targetLocator)).build()
                    .perform();
            logger.info(
                    "Dropped source web element located by '{}' to target web element located by '{}' successfully",
                    sourceLocator, targetLocator);
        } catch (Exception e) {
            logger.error(
                    "Failed to drag and drop source web element located by '{}' to target web element located by '{}'. Root cause: {}",
                    sourceLocator, targetLocator, e.getMessage());
        }
    }

    public void copy(String locator) {
        try {
            logger.info("Copying text of web element located by '{}'", locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(findWebElement(locator)).click()
                    .sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.CONTROL, "c")).build().perform();
            logger.info("Copied text of web element located by '{}' successfully", locator);
        } catch (Exception e) {
            logger.error("Failed to copy text of web element located by '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
    }

    public void enhancedClick(String locator) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Clicking web element located by '{}'", locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", we);
            logger.info("Web element located by '{}' is clicked", locator);
        } catch (Exception e) {
            logger.error("Failed to click web element located by '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
    }

    public void scrollIntoView(String locator) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Scrolling to web element located by '{}'", locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", we);
            logger.info("Scrolled to web element located by '{}' successfully", locator);
        } catch (Exception e) {
            logger.error("Failed to scroll to web element located by '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
    }

    public void scrollToElementAtCenterOfPage(String locator) {
        WebElement element = findWebElement(locator);
        try {
            logger.info("Scrolling to web element located by '{}' at the center of page", locator);
            int x = element.getLocation().getX();
            int y = element.getLocation().getY();
            int heightOfBrowser = driver.manage().window().getSize().getHeight();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(" + x + "," + (y - heightOfBrowser / 2) + ");");
            logger.info("Scrolled to web element located by '{}' at the center of page successfully", locator);
        } catch (Exception e) {
            logger.error("Failed to scroll to web element located by '{}' at the center of page. Root cause: {}", locator,
                    e.getMessage());
        }
    }

    public void scrollToElementAtCenterOfPage(WebElement we) {
        String scrollElementIntoMiddle =
                "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                        + "var elementTop = arguments[0].getBoundingClientRect().top;"
                        + "window.scrollBy(0, elementTop - (viewPortHeight/2));";
        try {
            logger.info("Scrolling to web element located by '{}' at the center of page", we);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(scrollElementIntoMiddle, we);
            logger.info("Scrolled to web element located by '{}' at the center of page successfully", we);
        } catch (Exception e) {
            logger.error("Failed to scroll to web element located by '{}' at the center of page. Root cause: {}", we,
                    e.getMessage());
        }
    }

    public boolean verifyOptionSelectedByIndex(String locator, int index) {
        WebElement element = findWebElement(locator);
        try {
            logger.info("Verifying option of web element located by '{}' selected by index '{}'", locator, index);
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            int count = 0;
            for (WebElement option : options) {
                if (option.isSelected()) {
                    count++;
                }
            }
            if (count == index) {
                logger.info("Option of web element located by '{}' is selected by index '{}'", locator, index);
                return true;
            } else {
                logger.error("Option of web element located by '{}' is not selected by index '{}'", locator, index);
            }
        } catch (Exception e) {
            logger.error("Failed to verify option selected by index '{}'. Root cause: {}", locator, e.getMessage());
        }
        return false;
    }

    public boolean verifyElementText(String locator, String expectedText) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Verifying text of web element located by '{}'", locator);
            String actualText = we.getText();
            if (actualText.equals(expectedText)) {
                logger.info("Text of web element located by '{}' is '{}'", locator, expectedText);
                return true;
            }
            logger.error("Text of web element located by '{}' is '{}', not '{}'", locator, actualText, expectedText);
        } catch (Exception e) {
            logger.error("Failed to verify text of web element located by '{}'. Root cause: {}",
                    locator, e.getMessage());
        }
        return false;
    }

    public boolean verifyElementText(WebElement we, String expectedText) {
        try {
            logger.info("Verifying text of web element located by '{}'", we);
            String actualText = we.getText();
            if (actualText.equals(expectedText)) {
                logger.info("Text of web element located by '{}' is '{}'", we, expectedText);
                return true;
            }
            logger.error("Text of web element located by '{}' is '{}', not '{}'", we, actualText, expectedText);
        } catch (Exception e) {
            logger.error("Failed to verify text of web element located by '{}'. Root cause: {}",
                    we, e.getMessage());
        }
        return false;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenShot() {
        try {
            logger.info("Taking a screen shot");
            byte[] image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            if (image != null) {
                logger.info("Screen shot taken");
                return image;
            }
            logger.error("Take screen shot failed");
        } catch (Exception e) {
            logger.error("Failed to take screen shot. Root cause: {}", e.getMessage());
        }
        return null;
    }

    public WebDriver getDriver() {
        return driver;
    }


    public String getBrowserName() {
        try {
            Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
            return capabilities.getBrowserName();
        } catch (Exception e) {

        }
        return null;
    }

    public boolean verifyAlertPresent() {
        try {
            logger.info("Verifying alert is present");
            Alert alert = driver.switchTo().alert();
            if (alert != null) {
                logger.info("Verified alert is present successfully");
                return true;
            }
            logger.error("Alert is not present");
        } catch (Exception e) {
            logger.error("Failed to verify alert is present. Root cause: {}", e.getMessage());
        }
        return false;
    }


    public String getText(String locator) {
        WebElement we = findWebElement(locator);
        String actualText = null;
        try {
            logger.info("Getting text of web element located by '{}'", locator);
            actualText = we.getText();
            logger.info("Text of web element located by '{}' is '{}'", locator, actualText);
            return actualText;
        } catch (Exception e) {
            logger.error("Failed to get text of web element located by '{}'. Root cause: {}", locator,
                    e.getMessage());
        }
        return null;
    }

    public String getText(WebElement webElement) {
        String actualText = null;
        try {
            logger.info("Getting text of web element located by '{}'", webElement);
            actualText = webElement.getText();
            logger.info("Text of web element located by '{}' is '{}'", webElement, actualText);
            return actualText;
        } catch (Exception e) {
            logger.error("Failed to get text of web element located by '{}'. Root cause: {}", webElement,
                    e.getMessage());
        }
        return null;
    }

    public void refresh() {
        try {
            logger.info("Refreshing the page");
            driver.navigate().refresh();
            logger.info("Refreshed the page successfully");
        } catch (Exception e) {
            logger.error("Failed to refresh the page. Root cause: {}", e.getMessage());
        }
    }

    public void delayInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.info("Delayed {} seconds", seconds);
        } catch (Exception e) {
            logger.error("Failed to delay {} second(s).Root cause: {}", seconds, e.getMessage());
        }
    }
}


