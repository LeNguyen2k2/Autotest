package Autotest.demo;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Autotest.keywords.WebUI;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class WebDriverDemo {

    private static final String BROWSER = "CHROME";
    private static final String DANTRI_URL = "https://dantri.com.vn";
    private static final String VNEXPRESS_URL = "https://vnexpress.net/";
    private static final String DEMOQA_TEXTBOX_URL = "https://demoqa.com/text-box";
    private static final String DEMOQA_SELECTMENU_URL = "https://demoqa.com/select-menu";
    private static final String DEMOQA_WEBTABLE_URL = "https://demoqa.com/webtables";
    private static final String DEMOQA_BUTTON_URL = "https://demoqa.com/buttons";
    private static final String GURU99_URL ="https://demo.guru99.com/test/guru99home/";
    private static final String DEMOQA_ALERT_URL = "https://demoqa.com/alerts";
    private static final String TXT_FULL_NAME = "//input[@id='userName']";
    private static final String FIRST_TITLE_LOCATOR = "//div[@class='article-content']//h3[@class='article-title']/a";


    private WebUI webUI;

    @BeforeMethod
    public void setUp() {
        webUI = new WebUI();
        webUI.openBrowser(BROWSER, DANTRI_URL);
    }


    @Test(description = "TC001: Verify title of the page")
    public void TC001_Verify_title_of_the_page() {
        String actualTitle = webUI.getTitle();
        String expectedTitle = "Tin tức Việt Nam và quốc tế nóng, nhanh, cập nhật 24h | Báo Dân trí";
        assertEquals(actualTitle, expectedTitle);
    }

    @Test(description = "TC002: Verify title of the page")
    public void TC002_Verify_title_of_the_page() {
        String expectedTitle = "Tin tức Việt Nam và quốc tế nóng, nhanh, cập nhật 24h | Báo Dân trí";
        assertTrue(webUI.verifyTitle(expectedTitle));
    }

    @Test(description = "TC003: Verify title of the page after navigating to VnExpress")
    public void TC003_Verify_title_of_the_page_after_navigation() {
        webUI.navigateTo(VNEXPRESS_URL);
        String expectedTitle = "Báo VnExpress - Báo tiếng Việt nhiều người xem nhất";
        assertTrue(webUI.verifyTitle(expectedTitle));
    }

    @Test(description = "TC004: Clear text successfully")
    public void TC004_Clear_text_successfully() {
        webUI.navigateTo(DEMOQA_TEXTBOX_URL);
        webUI.sendKeys(TXT_FULL_NAME, "Nguyen");
        String actualFullName = webUI.getAttributeValue(TXT_FULL_NAME, "value");
        assertEquals(actualFullName, "Nguyen");
        webUI.clearText(TXT_FULL_NAME);
        actualFullName = webUI.getAttributeValue(TXT_FULL_NAME, "value");
        assertEquals(actualFullName, "");
    }

    @Test(description = "TC009: Find element using different locators")
    public void TC009_Find_Element_Using_Different_Locators() {
        webUI.navigateTo(DEMOQA_TEXTBOX_URL);
        WebElement elementById = webUI.findWebElement("id:userName");
        Assert.assertTrue(elementById.isDisplayed(), "Element should be found using ID locator.");

        WebElement elementByName = webUI.findWebElement("id:userEmail");
        Assert.assertTrue(elementByName.isDisplayed(), "Element should be found using Name locator.");

        WebElement elementByCss = webUI.findWebElement("css:input#userName");
        Assert.assertTrue(elementByCss.isDisplayed(), "Element should be found using CSS Selector.");

        WebElement elementByXpath = webUI.findWebElement("xpath://input[@id='userEmail']");
        Assert.assertTrue(elementByXpath.isDisplayed(), "Element should be found using XPath.");
    }

    @Test(description = "TC008: Select & Deselect Option By Index")
    public void TC008_Select_and_Deselect_Option_By_Index() throws InterruptedException {
        webUI.navigateTo(DEMOQA_SELECTMENU_URL);
        webUI.scrollToElement("id:selectMenuContainer");
        Thread.sleep(3000);
        WebElement dropdownElement = webUI.findWebElement("id:oldSelectMenu");
        webUI.selectOptionByIndex("id:oldSelectMenu", 2);
        Thread.sleep(3000);
        webUI.deselectByIndex("id:oldSelectMenu", 2);
        Thread.sleep(3000);

        String selectedValue = dropdownElement.getAttribute("value");
        assertEquals(selectedValue, "2", "The option should not be selected");
    }

    @Test(description = "TC009: Select & Deselect Option By Value")
    public void TC009_Select_and_Deselect_Option_By_Value() throws InterruptedException{
        webUI.navigateTo(DEMOQA_SELECTMENU_URL);
        webUI.scrollToElement("id:selectMenuContainer");
        Thread.sleep(3000);

        webUI.selectOptionByValue("id:oldSelectMenu", "3");
        Thread.sleep(3000);
        webUI.deselectByValue("id:oldSelectMenu", "3");
        Thread.sleep(3000);

        WebElement dropdownElement = webUI.findWebElement("id:oldSelectMenu");
        String selectedValue = dropdownElement.getAttribute("value");
        assertEquals(selectedValue, "3", "The option should not be selected");
    }

    @Test(description = "TC010: Select & Deselect Option By Visible Text")
    public void TC010_Select_and_Deselect_Option_By_Visible_Text() throws InterruptedException {
        webUI.navigateTo(DEMOQA_SELECTMENU_URL);
        webUI.scrollToElement("id:selectMenuContainer");
        Thread.sleep(3000);

        webUI.selectByVisibleText("id:oldSelectMenu", "Purple");
        Thread.sleep(3000);
        webUI.deselectByVisibleText("id:oldSelectMenu", "Purple");
        Thread.sleep(3000);

        WebElement dropdownElement = webUI.findWebElement("id:oldSelectMenu");
        String selectedValue = dropdownElement.getAttribute("value");
        assertEquals(selectedValue, "4", "The option should not be selected");
    }

    @Test(description = "TC011: Multi-select Options by Index, Value, and Visible Text")
    public void TC011_Multi_Select_Options() throws InterruptedException {
        webUI.navigateTo(DEMOQA_SELECTMENU_URL);
        webUI.scrollToElement("xpath://b[text()='Standard Multi Select']");
        Thread.sleep(3000);

        // 1. Select multiple options by index
        webUI.selectMultipleByIndex("id:cars", Arrays.asList(0, 1)); // Select multiple options by index (0: Volvo, 1: Saab)
        Thread.sleep(3000); // Wait to ensure the selection is effective

        // Verify selected options by index
        List<WebElement> selectedOptions = webUI.getSelectedOptions("id:cars");
        assertTrue(selectedOptions.stream().anyMatch(option -> "volvo".equals(option.getAttribute("value"))), "Option with value 'volvo' should be selected");
        assertTrue(selectedOptions.stream().anyMatch(option -> "saab".equals(option.getAttribute("value"))), "Option with value 'saab' should be selected");

        // 2. Deselect previously selected options (if necessary) and select multiple options by value
        webUI.deselectAll("id:cars"); // Deselect all currently selected options
        webUI.selectMultipleByValue("id:cars", Arrays.asList("saab","opel")); // Select options by value
        Thread.sleep(3000); // Wait to ensure the selection is effective

        // Verify selected options by value
        selectedOptions = webUI.getSelectedOptions("id:cars");
        assertTrue(selectedOptions.stream().anyMatch(option -> "saab".equals(option.getAttribute("value"))), "Option with value 'saab' should be selected");
        assertTrue(selectedOptions.stream().anyMatch(option -> "opel".equals(option.getAttribute("value"))), "Option with value 'opel' should be selected");

        // 3. Deselect previously selected options (if necessary) and select multiple options by visible text
        webUI.deselectAll("id:cars"); // Deselect all currently selected options
        webUI.selectMultipleByVisibleText("id:cars", Arrays.asList("Opel", "Audi")); // Select options by visible text
        Thread.sleep(3000); // Wait to ensure the selection is effective

        // Verify selected options by visible text
        selectedOptions = webUI.getSelectedOptions("id:cars");
        assertTrue(selectedOptions.stream().anyMatch(option -> "Opel".equals(option.getText())), "Option with visible text 'Opel' should be selected");
        assertTrue(selectedOptions.stream().anyMatch(option -> "Audi".equals(option.getText())), "Option with visible text 'Audi' should be selected");
    }


    @Test(description = "TC012: Switch to Window by Index")
    public void TC012_Switch_To_Window_By_Index() throws InterruptedException {
        webUI.executeJavaScript("window.open('" + VNEXPRESS_URL + "', '_blank');");
        Thread.sleep(3000);

        // Open a new tab and navigate to DEMOQA_TEXTBOX_URL
        webUI.executeJavaScript("window.open('" + DEMOQA_TEXTBOX_URL + "', '_blank');");
        Thread.sleep(3000);

        webUI.switchToWindowByIndex(1);
        Thread.sleep(3000);
        String actualTitle = webUI.getTitle();
        String expectedTitle = "Báo VnExpress - Báo tiếng Việt nhiều người xem nhất";
        assertEquals(actualTitle, expectedTitle);


        webUI.closeWindowByIndex(2);
        Thread.sleep(3000);
        Assert.assertFalse(webUI.isWindowOpen(DEMOQA_TEXTBOX_URL), "The window with URL '" + DEMOQA_TEXTBOX_URL + "' should be closed.");
    }


    @Test(description = "TC013: Switch & Close Window by Title")
    public void TC013_Switch_and_Close_Window_By_Title() throws InterruptedException {
        webUI.executeJavaScript("window.open('" + VNEXPRESS_URL + "', '_blank');");
        Thread.sleep(3000);

        webUI.executeJavaScript("window.open('" + DEMOQA_TEXTBOX_URL + "', '_blank');");
        Thread.sleep(3000);

        webUI.switchToWindowByTitle("Báo VnExpress - Báo tiếng Việt nhiều người xem nhất");
        Thread.sleep(3000);
        String actualTitle = webUI.getTitle();
        String expectedTitle = "Báo VnExpress - Báo tiếng Việt nhiều người xem nhất";
        assertEquals(actualTitle, expectedTitle);

        webUI.closeWindowByTitle("DEMOQA");
        Thread.sleep(3000);
        Assert.assertFalse(webUI.isWindowOpen(DEMOQA_TEXTBOX_URL), "The window with title '" + DEMOQA_TEXTBOX_URL + "' should be closed.");
    }

    @Test(description = "TC014: Switch & Close to Window by URL")
    public void TC014_Switch_and_Close_Window_By_Url() throws InterruptedException{
        webUI.executeJavaScript("window.open('" + VNEXPRESS_URL + "', '_blank');");
        Thread.sleep(3000);

        webUI.executeJavaScript("window.open('" + DEMOQA_TEXTBOX_URL + "', '_blank');");
        Thread.sleep(3000);

        // Switch to the new tab by URL
        webUI.switchToWindowByUrl(VNEXPRESS_URL);
        String actualTitle = webUI.getTitle();
        String expectedTitle = "Báo VnExpress - Báo tiếng Việt nhiều người xem nhất";
        assertEquals(actualTitle, expectedTitle);

        webUI.closeWindowByUrl(DEMOQA_TEXTBOX_URL);
        Thread.sleep(3000);
        Assert.assertFalse(webUI.isWindowOpen(DEMOQA_TEXTBOX_URL), "The window with title '" + DEMOQA_TEXTBOX_URL + "' should be closed.");
    }

    @Test(description = "TC015: Accept Alert")
    public void TC015_Accept_Alert() throws InterruptedException {
        webUI.navigateTo(DEMOQA_ALERT_URL);
        Thread.sleep(3000);
        webUI.scrollToElement("id:confirmButton");
        Thread.sleep(3000);

        WebElement alertButton = webUI.findWebElement("id:confirmButton");
        alertButton.click();
        Thread.sleep(3000);

        webUI.acceptAlert();
        Thread.sleep(2000);

        // Verify the result text that appears after alert acceptance (if any)
        WebElement resultMessage = webUI.findWebElement("id:confirmResult");
        String actualMessage = resultMessage.getText();
        String expectedMessage = "You selected Ok";
        assertEquals(actualMessage, expectedMessage, "Alert acceptance should trigger a specific message.");
    }

    @Test(description = "TC016: Dismiss Alert")
    public void TC016_Dismiss_Alert() throws InterruptedException {
        webUI.navigateTo(DEMOQA_ALERT_URL);
        Thread.sleep(3000);
        webUI.scrollToElement("id:confirmButton");
        Thread.sleep(3000);

        WebElement alertButton = webUI.findWebElement("id:confirmButton");
        alertButton.click();
        Thread.sleep(3000);

        webUI.dismissAlert();
        Thread.sleep(2000);

        // Verify the result text that appears after alert acceptance (if any)
        WebElement resultMessage = webUI.findWebElement("id:confirmResult");
        String actualMessage = resultMessage.getText();
        String expectedMessage = "You selected Cancel";
        assertEquals(actualMessage, expectedMessage, "Alert acceptance should trigger a specific message.");
    }

    @Test(description = "TC017: Get Alert Text")
    public void TC017_Get_Alert_Text() throws InterruptedException {
        webUI.navigateTo(DEMOQA_ALERT_URL);
        Thread.sleep(3000);
        webUI.scrollToElement("id:promtButton");
        Thread.sleep(3000);

        WebElement promtButton = webUI.findWebElement("id:promtButton");
        promtButton.click();
        Thread.sleep(3000);

        // Get text from the alert
        String alertText = webUI.getAlertText();
        Assert.assertNotNull(alertText, "Alert text should not be null");
        Assert.assertEquals(alertText, "Please enter your name", "Alert text is not as expected");
    }

    @Test(description = "TC018: Send Keys to Alert")
    public void TC018_Send_Keys_To_Alert() throws InterruptedException {
        webUI.navigateTo(DEMOQA_ALERT_URL);
        Thread.sleep(3000);
        webUI.scrollIntoView("id:confirmButton");
        Thread.sleep(3000);

        WebElement promtButton = webUI.findWebElement("id:promtButton");
        promtButton.click();
        Thread.sleep(3000);

        webUI.sendKeysToAlert("hello");
        Thread.sleep(5000);
        webUI.acceptAlert();
        Thread.sleep(5000);

        WebElement resultElement = webUI.findWebElement("id:promptResult");
        String resultText = resultElement.getText();
        String expectedText = "You entered hello";
        Assert.assertEquals(resultText, expectedText, "The result text should be 'You entered hello'");
    }

    @Test(description = "TC019: Switch to an iframe and interact with elements inside it")
    public void TC019_Switch_To_Iframe_And_Interact() throws InterruptedException {
        webUI.navigateTo(GURU99_URL);
        Thread.sleep(3000);

        webUI.scrollToElement("xpath://h3[text()='iFrame will not show if you have adBlock extension enabled']");
        Thread.sleep(3000);
        //body//a
        WebElement iframeElement = webUI.findWebElement("//iframe[@id='a077aa5e']");
        iframeElement.click();
        Thread.sleep(3000);

        webUI.switchtoFrame("//body//a");
        Thread.sleep(5000);

        WebElement insideIframeElement = webUI.findWebElement("//body//a");
        Assert.assertNotNull(insideIframeElement, "Failed to switch to the iframe or the element inside iframe was not found.");
        insideIframeElement.click();
    }


    @Test(description = "TC020 Add a new record to the web table")
    public void TC020_Add_a_new_record_to_the_web_table() throws InterruptedException{
        // Navigate to the web tables page
        webUI.navigateTo(DEMOQA_WEBTABLE_URL);
        Thread.sleep(3000);

        webUI.scrollToElementAtCenterOfPage("//div[contains(text(),'First Name')]");

        webUI.findWebElement("id:addNewRecordButton").click();

        webUI.sendKeys("id:firstName", "Nguyen");
        webUI.sendKeys("id:lastName", "Le");
        webUI.sendKeys("id:userEmail", "lenguyen@example.com");
        webUI.sendKeys("id:age", "22");
        webUI.sendKeys("id:salary", "50000");
        webUI.sendKeys("id:department", "Engineering");

        webUI.findWebElement("id:submit").click();

        WebElement newRecord = webUI.findWebElement("xpath://div[@role='gridcell' and contains(text(),'Nguyen')]");
        Assert.assertNotNull(newRecord, "The new record should be present in the table.");
    }

    @Test(description = "TC021 Edit and delete any record")
    public void TC021_Edit_And_Delete_Any_Record() throws InterruptedException{
        webUI.navigateTo(DEMOQA_WEBTABLE_URL);
        Thread.sleep(3000);

        webUI.scrollIntoView("//h1[normalize-space()='Web Tables']");
        String nameToEdit = "Cierra";
        String newFirstName = "Jane";
        String nameToDelete = "Alden";

        webUI.editRecordByName(nameToEdit, newFirstName);
        Thread.sleep(3000);

        webUI.deleteRecordByName(nameToDelete);
        Thread.sleep(3000);
    }

    @AfterMethod
    public void tearDown() {
        webUI.closeBrowser();
    }
}

