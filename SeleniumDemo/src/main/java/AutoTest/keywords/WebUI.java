package Autotest.keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
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
    private WebDriver driver;

    public void openBrowser(String browser, String... url) {
        try {
            logger.info("Opening browser: {}", browser);
            switch (browser.toUpperCase()) {
                case "CHROME":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(chromeOptions);
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
        driver.manage().window().maximize();
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

    public WebElement findWebElement(String locator) {
        WebElement element = null;
        try {
            logger.info("Finding element with locator '{}'", locator);

            String[] locatorParts = locator.split(":", 2);
            String type = locatorParts[0];
            String value = locatorParts[1];

            switch (type.toLowerCase()) {
                case "id":
                    element = driver.findElement(By.id(value));
                    break;
                case "name":
                    element = driver.findElement(By.name(value));
                    break;
                case "css":
                case "cssselector":
                    element = driver.findElement(By.cssSelector(value));
                    break;
                case "xpath":
                    element = driver.findElement(By.xpath(value));
                    break;
                case "classname":
                    element = driver.findElement(By.className(value));
                    break;
                case "tagname":
                    element = driver.findElement(By.tagName(value));
                    break;
                case "linktext":
                    element = driver.findElement(By.linkText(value));
                    break;
                case "partiallinktext":
                    element = driver.findElement(By.partialLinkText(value));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid locator type: " + type);
            }

            logger.info("Found element with locator '{}'", locator);
        } catch (Exception e) {
            logger.error("Failed to find element with locator '{}'. Root cause: {}", locator, e.getMessage());
        }
        return element;
    }


    public void sendKeys(String locator, String text) {
        WebElement we = findWebElement(locator);
        try {
            logger.info("Sending keys '{}'", text);
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

    public long[] getPageDimensions() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long pageWidth = (Long) js.executeScript("return document.body.scrollWidth");
        Long pageHeight = (Long) js.executeScript("return document.body.scrollHeight");

        js.executeScript("window.scrollTo(0, arguments[0] / 2);", pageHeight);

        return new long[]{pageWidth, pageHeight};
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

    public void back() {
        try {
            logger.info("Navigating back");
            driver.navigate().back();
            logger.info("Navigated back successfully");
        } catch (Exception e) {
            logger.error("Failed to navigate back. Root cause: {}", e.getMessage());
        }
    }

    public void forward() {
        try {
            logger.info("Navigating forward");
            driver.navigate().forward();
            logger.info("Navigated forward successfully");
        } catch (Exception e) {
            logger.error("Failed to navigate forward. Root cause: {}", e.getMessage());
        }
    }

    public boolean isElementDisplayed(String locator) {
        WebElement element = findWebElement(locator);
        boolean isDisplayed = false;
        try {
            logger.info("Checking if element with locator '{}' is displayed", locator);
            isDisplayed = element.isDisplayed();
            logger.info("Element with locator '{}' is displayed: {}", locator, isDisplayed);
        } catch (Exception e) {
            logger.error("Failed to check if element with locator '{}' is displayed. Root cause: {}", locator, e.getMessage());
        }
        return isDisplayed;
    }

    public boolean isElementEnabled(String locator) {
        return driver.findElement(By.xpath(locator)).isEnabled();
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

    public void executeJavaScript(String script) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script);
            logger.info("Executed JavaScript: {}", script);
        } catch (Exception e) {
            logger.error("Failed to execute JavaScript. Root cause: {}", e.getMessage());
        }
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

    public void acceptAlert (){
        try{
            logger.info("Accepting alert...");
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.info("Accepted alert.");
        } catch (Exception e){
            logger.error("Failed to accept alert. Root cause: {}",e.getMessage());
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

    public void switchtoFrame (String locator){
        WebElement we = findWebElement(locator);
        try{
            logger.info("Switching to frame located by '{}'", locator);
            driver.switchTo().frame(we);
            logger.info("Switched to frame '{}' successfully", locator);
        }catch (Exception e){
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



}


