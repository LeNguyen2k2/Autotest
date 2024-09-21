package Autotest.demo;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Autotest.keywords.WebUI;

import static org.testng.Assert.assertTrue;


public class TestCaseSuite {

    private static final String BROWSER = "CHROME";
    private static final String URL = "https://demo.guru99.com/V4";
//    private static final String URL = "https://demo.guru99.com/v4/manager/addcustomerpage.php";

    private static final String USER_ID = "mngr588638";
    private static final String USER_PASSWORD = "sYgEpeg";

    private static final String TXT_USERID = "//input[@name='uid']";
    private static final String TXT_PASSWORD = "//input[@name='password']";
    private static final String BTN_LOGIN = "//input[@name='btnLogin']";
    private static final String LNK_NEW_CUSTOMER = "//a[normalize-space()='New Customer']";
    private static final String TXT_CUSTOMER_NAME = "//input[@name='name']";
    private static final String TXT_ADDRESS ="//textarea[@name='addr']";
    private static final String TXT_CITY ="//input[@name='city']";
    private static final String TXT_STATE ="//input[@name='state']";
    private static final String TXT_PIN ="//input[@name='pinno']";
    private static final String TXT_TELEPHONE ="//input[@name='telephoneno']";
    private static final String TXT_EMAIL ="//input[@name='emailid']";
    private static final String LBL_CUSTOMER_NAME_ERROR_MESSAGE = "//label[@id='message']";
    private static final String LBL_ADDRESS_ERROR_MESSAGE = "//label[@id='message3']";
    private static final String LBL_CITY_ERROR_MESSAGE = "//label[@id='message4']";
    private static final String LBL_STATE_ERROR_MESSAGE = "//label[@id='message5']";
    private static final String LBL_PIN_ERROR_MESSAGE = "//label[@id='message6']";
    private static final String LBL_TELEPHONE_ERROR_MESSAGE = "//label[@id='message7']";
    private static final String LBL_EMAIL_ERROR_MESSAGE = "//label[@id='message9']";

    private WebUI webUI;

    @BeforeTest
    public void setUp() {
        webUI = new WebUI();
        webUI.openBrowser(BROWSER, URL);
        webUI.maximizeWindow();
    }

    @Step("Input user id: {0}")
    public void inputUserId(String userId) {
        webUI.sendKeys(TXT_USERID, userId);
        webUI.delayInSecond(3);
    }

    @Step("Input password: {0}")
    public void inputPassword(String password) {
        webUI.sendKeys(TXT_PASSWORD, password);
        webUI.delayInSecond(3);
    }

    @Step("Click Login button")
    public void clickLogin() {
        webUI.click(BTN_LOGIN);
        webUI.delayInSecond(3);
    }

    @Step("Login Guru99 with user id '{0}' and password '{1}'")
    public void login_Guru99_with(String userId, String password) {
        inputUserId(userId);
        inputPassword(password);
        clickLogin();
    }

    @Step("Move to New Customer")
    public void move_to_New_Customer() {
        webUI.click(LNK_NEW_CUSTOMER);
        webUI.delayInSecond(3);
    }


//    @Step("Input Customer name: {0}")
//    public void input_Customer_Name(String customerName) {
//        if(customerName.isEmpty()) {
//            webUI.sendKeys(TXT_CUSTOMER_NAME, Keys.chord(Keys.TAB));
//        } else {
//            webUI.sendKeys(TXT_CUSTOMER_NAME, customerName);
//        }
//        webUI.delayInSecond(3);
//    }

    @Step("Input Customer name: {0}")
    public void input_Customer_Name(String customerName) {
        webUI.clearText(TXT_CUSTOMER_NAME);
        if(customerName.isEmpty()) {
            webUI.sendKeys(TXT_CUSTOMER_NAME, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(TXT_CUSTOMER_NAME, customerName);
        }
        webUI.delayInSecond(3);
    }


    @Step("Input Address: {0}")
    public void input_Address(String Address) {
        if(Address.isEmpty()) {
            webUI.sendKeys(TXT_ADDRESS, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(TXT_ADDRESS, Address);
        }
        webUI.delayInSecond(3);
    }

    @Step("Input City: {0}")
    public void input_City(String City) {
        webUI.clearText(TXT_CITY);
        if(City.isEmpty()) {
            webUI.sendKeys(TXT_CITY, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(TXT_CITY, City);
        }
        webUI.delayInSecond(3);
    }

    @Step("Input State: {0}")
    public void input_State(String State) {
        webUI.clearText(TXT_STATE);
        if(State.isEmpty()) {
            webUI.sendKeys(TXT_STATE, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(TXT_STATE, State);
        }
        webUI.delayInSecond(3);
    }

    @Step("Input PIN: {0}")
    public void input_PIN(String Pin) {
        webUI.clearText(TXT_PIN);
        if(Pin.isEmpty()) {
            webUI.sendKeys(TXT_PIN, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(TXT_PIN, Pin);
        }
        webUI.delayInSecond(3);
    }

    @Step("Input State: {0}")
    public void input_TELEPHONE(String Telephone) {
        webUI.clearText(TXT_TELEPHONE);
        if(Telephone.isEmpty()) {
            webUI.sendKeys(TXT_TELEPHONE, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(TXT_TELEPHONE, Telephone);
        }
        webUI.delayInSecond(3);
    }


    @Step("Input EMAIL: {0}")
    public void input_EMAIL(String Email) {
        webUI.clearText(TXT_EMAIL);
        if(Email.isEmpty()) {
            webUI.sendKeys(TXT_EMAIL, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(TXT_EMAIL, Email);
        }
        webUI.delayInSecond(3);
    }

    @Step("Should show customer name error message: {0}")
    public boolean should_show_customer_name_error_message(String errorMessage) {
        return webUI.verifyElementText(LBL_CUSTOMER_NAME_ERROR_MESSAGE, errorMessage);
    }

    @Step("An error message address is shown: {0}")
    public boolean An_error_message_address_is_shown (String errorMessage) {
        return  webUI.verifyElementText(LBL_ADDRESS_ERROR_MESSAGE, errorMessage);
    }

    @Step("An error message city is shown: {0}")
    public boolean An_error_message_city_is_shown (String errorMessage) {
        return  webUI.verifyElementText(LBL_CITY_ERROR_MESSAGE, errorMessage);
    }

    @Step("An error message city is shown: {0}")
    public boolean An_error_message_state_is_shown (String errorMessage) {
        return  webUI.verifyElementText(LBL_STATE_ERROR_MESSAGE, errorMessage);
    }

    @Step("An error message city is shown: {0}")
    public boolean An_error_message_pin_is_shown (String errorMessage) {
        return  webUI.verifyElementText(LBL_PIN_ERROR_MESSAGE, errorMessage);
    }

    @Step("An error message city is shown: {0}")
    public boolean An_error_message_telephone_is_shown (String errorMessage) {
        return  webUI.verifyElementText(LBL_TELEPHONE_ERROR_MESSAGE, errorMessage);
    }

    @Step("An error message city is shown: {0}")
    public boolean An_error_message_email_is_shown (String errorMessage) {
        return  webUI.verifyElementText(LBL_EMAIL_ERROR_MESSAGE, errorMessage);
    }

    @Test(description = "NC001: Name cannot be empty")
    public void NC001_Name_cannot_be_empty() {
        login_Guru99_with(USER_ID, USER_PASSWORD);
        move_to_New_Customer();
        input_Customer_Name("");
        assertTrue(should_show_customer_name_error_message("Customer name must not be blank"));
    }

    @Test(description = "NC002: Name cannot be numeric")
    public void NC002_Name_cannot_be_numeric() {
        input_Customer_Name("1234");
        assertTrue(should_show_customer_name_error_message("Numbers are not allowed"));
    }

    @Test(description = "NC003: Name cannot have special characters")
    public void NC003_Name_cannot_have_special_characters() {
        input_Customer_Name("@s123");
        assertTrue(should_show_customer_name_error_message("Special characters are not allowed"));
    }

    @Test(description = "NC004: Name cannot have first character as blank space")
    public void NC004_Name_cannot_have_first_characters_as_blank_space() {
        input_Customer_Name(" Nguyen");
        assertTrue(should_show_customer_name_error_message("First character can not have space"));
    }

    @Test(description = "NC005: Address cannot be empty")
    public void NC005_Address_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='Address']");
        input_Address("");
        assertTrue(An_error_message_address_is_shown("Address Field must not be blank"));
    }

    @Test(description = "NC006: Address cannot have first blank space")
    public void NC006_Address_cannot_have_first_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='Address']");
        input_Address(" Address");
        assertTrue(An_error_message_address_is_shown("First character can not have space"));
    }

    @Test(description = "NC008: City cannot be empty")
    public void NC008_City_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='City']");
        input_City("");
        assertTrue(An_error_message_city_is_shown("City Field must not be blank"));
    }

    @Test(description = "NC009: City cannot be numeric")
    public void NC009_City_cannot_be_numeric() {
        webUI.scrollIntoView("//td[normalize-space()='City']");
        input_City("city123");
        assertTrue(An_error_message_city_is_shown("Numbers are not allowed"));
    }

    @Test(description = "NC010: City cannot have special characters")
    public void NC010_City_cannot_have_special_characters() {
        webUI.scrollIntoView("//td[normalize-space()='City']");
        input_City("City!@#");
        assertTrue(An_error_message_city_is_shown("Special characters are not allowed"));
    }

    @Test(description = "NC011: City cannot have first blank space")
    public void NC011_City_cannot_have_first_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='City']");
        input_City(" City");
        assertTrue(An_error_message_city_is_shown("First character can not have space"));
    }

    @Test(description = "NC012: State cannot be empty")
    public void NC012_State_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='State']");
        input_State("");
        assertTrue(An_error_message_state_is_shown("State must not be blank"));
    }

    @Test(description = "NC013: State cannot be numeric")
    public void NC013_State_cannot_be_numeric() {
        webUI.scrollIntoView("//td[normalize-space()='State']");
        input_State("state123");
        assertTrue(An_error_message_state_is_shown("Numbers are not allowed"));
    }

    @Test(description = "NC014: State cannot have special characters")
    public void NC014_State_cannot_have_special_characters() {
        webUI.scrollIntoView("//td[normalize-space()='State']");
        input_State("State!@#");
        assertTrue(An_error_message_state_is_shown("Special characters are not allowed"));
    }

    @Test(description = "NC015: City cannot have first blank space")
    public void NC015_City_cannot_have_first_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='State']");
        input_State(" State");
        assertTrue(An_error_message_state_is_shown("First character can not have space"));
    }

    @Test(description = "NC016: PIN cannot be numeric")
    public void NC016_Name_cannot_be_numeric() {
        input_PIN("1234PIN");
        assertTrue(An_error_message_pin_is_shown("Characters are not allowed"));
    }

    @Test(description = "NC017: PIN cannot be empty")
    public void NC017_Name_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        input_PIN("");
        assertTrue(An_error_message_pin_is_shown("PIN Code must not be blank"));
    }

    @Test(description = "NC018: PIN must have 6 digits")
    public void NC018_PIN_must_have_6_digits() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        input_PIN("");
        assertTrue(An_error_message_pin_is_shown("PIN Code must not be blank"));
    }

    @Test(description = "NC019: PIN cannot have special character")
    public void NC019_PIN_cannot_have_special_character() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        input_PIN("123!@#");
        assertTrue(An_error_message_pin_is_shown("Special characters are not allowed"));
    }

    @Test(description = "NC020: PIN cannot have first blank space")
    public void NC020_PIN_cannot_have_first_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        input_PIN(" 123");
        assertTrue(An_error_message_pin_is_shown("First character can not have space"));
    }

    @Test(description = "NC021: PIN cannot have blank space")
    public void NC021_PIN_cannot_have_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        input_PIN("12 3");
        assertTrue(An_error_message_pin_is_shown("Characters are not allowed"));
    }

    @Test(description = "NC022: Telephone cannot be empty")
    public void NC022_Telephone_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='Mobile Number']");
        input_TELEPHONE("");
        assertTrue(An_error_message_telephone_is_shown("Mobile no must not be blank"));
    }

    @Test(description = "NC023: Telephone cannot have first characters blank space")
    public void NC023_Telephone_cannot_have_first_characters_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='Mobile Number']");
        input_TELEPHONE(" 099");
        assertTrue(An_error_message_telephone_is_shown("First character can not have space"));
    }

    @Test(description = "NC024: Telephone cannot have blank space")
    public void NC024_Telephone_cannot_have_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='Mobile Number']");
        input_TELEPHONE("09 9");
        assertTrue(An_error_message_telephone_is_shown("Characters are not allowed"));
    }

    @Test(description = "NC025: Telephone cannot have special character")
    public void NC025_Telephone_cannot_have_special_character() {
        webUI.scrollIntoView("//td[normalize-space()='Mobile Number']");
        input_TELEPHONE("099!@#");
        assertTrue(An_error_message_telephone_is_shown("Special characters are not allowed"));
    }

    @Test(description = "NC026: Email cannot be empty")
    public void NC026_Email_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='E-mail']");
        input_EMAIL("");
        assertTrue(An_error_message_email_is_shown("Email-ID must not be blank"));
    }

    @Test(description = "NC027: Email must be in correct format ")
    public void NC027_Email_must_be_in_correct_format () {
        webUI.scrollIntoView("//td[normalize-space()='E-mail']");
        input_EMAIL("guru99gmail.com");
        assertTrue(An_error_message_email_is_shown("Email-ID is not valid"));
    }

    @Test(description = "NC029: Email cannot have space ")
    public void NC029_Email_cannot_have_space () {
        webUI.scrollIntoView("//td[normalize-space()='E-mail']");
        input_EMAIL("Guru 99@");
        assertTrue(An_error_message_email_is_shown("Email-ID is not valid"));
    }

//    @Test(description = "NC030: Verify Field Labels")
//    public void NC030_Verify_Field_Labels() {
//        assertTrue(webUI.verifyElementText("//td[normalize-space()='Customer Name']", "Customer Name"));
//
//        assertTrue(webUI.verifyElementText("//td[normalize-space()='Date of Birth']", "Date of Birth"));
//
//        assertTrue(webUI.verifyElementText("//td[normalize-space()='Address']", "Address"));
//
//        assertTrue(webUI.verifyElementText("//td[normalize-space()='City']", "City"));
//
//        assertTrue(webUI.verifyElementText("//td[normalize-space()='State']", "State"));
//
//        assertTrue(webUI.verifyElementText("//td[normalize-space()='PIN']", "PIN"));
//
//        assertTrue(webUI.verifyElementText("//td[normalize-space()='Mobile Number']", "Mobile Number"));
//
//        assertTrue(webUI.verifyElementText("//td[normalize-space()='E-mail']", "E-mail"));
//
//        assertTrue(webUI.verifyElementText("//td[normalize-space()='Password']", "Password"));
//
//        System.out.println("All field labels match the SRS documentation.");
//    }
    @AfterTest
    public void tearDown() {
        webUI.closeBrowser();
    }
}

