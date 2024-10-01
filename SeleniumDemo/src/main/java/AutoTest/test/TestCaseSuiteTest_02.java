package Autotest.test;

import Autotest.keywords.ExcelUtils;
import org.testng.annotations.Test;
import Autotest.model.Customer;
import Autotest.pages.CustomerRegMsg;
import Autotest.pages.Manager;
import Autotest.pages.NewCustomer;

import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.*;

public class TestCaseSuiteTest_02 extends BaseTest {

    private NewCustomer objNewCustomer;

    public TestCaseSuiteTest_02() {
        setSheetName(TestCaseSuiteTest_02.class.getSimpleName());
    }

    @Test(description = "NC001: Name cannot be empty")
    public void NC001_Name_cannot_be_empty() {
        List<HashMap<String, String>> data = findTestData("NC001");
        Manager objManager = objLogin.login_Guru99_with(data.get(0).get("UserId"), data.get(0).get("password"));
        objNewCustomer = objManager.leftMenu().move_to_New_Customer();
        objNewCustomer.input_Customer_Name(data.get(0).get("CustomerName"));
        assertTrue(
                objNewCustomer.should_Show_Customer_Name_Error_Message(data.get(0).get("CustomerErrorMessage")));
    }

    @Test(description = "NC002: Name cannot be numeric")
    public void NC002_Name_cannot_be_numeric() {
        List<HashMap<String, String>> data = findTestData("NC002");
        objNewCustomer.input_Customer_Name(data.get(0).get("CustomerName"));
        assertTrue(
                objNewCustomer.should_Show_Customer_Name_Error_Message(data.get(0).get("CustomerErrorMessage")));
    }

    @Test(description = "NC003: Name cannot have special characters")
    public void NC003_Name_cannot_have_special_characters() {
        List<HashMap<String, String>> data = findTestData("NC003");
        objNewCustomer.input_Customer_Name(data.get(0).get("CustomerName"));
        assertTrue(
                objNewCustomer.should_Show_Customer_Name_Error_Message(data.get(0).get("CustomerErrorMessage")));
    }

    @Test(description = "NC004: Name cannot have first character as blank space")
    public void NC004_Name_cannot_have_first_characters_as_blank_space() {
        List<HashMap<String, String>> data = findTestData("NC004");
        objNewCustomer.input_Customer_Name(data.get(0).get("CustomerName"));
        assertTrue(
                objNewCustomer.should_Show_Customer_Name_Error_Message(data.get(0).get("CustomerErrorMessage")));
    }

    @Test(description = "NC005: Address cannot be empty")
    public void NC005_Address_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='Address']");
        List<HashMap<String, String>> data = findTestData("NC005");
        objNewCustomer.input_Address(data.get(0).get("Address"));
        assertTrue(
                objNewCustomer.an_Error_Message_Address_Is_Shown(data.get(0).get("AddressErrorMessage")));
    }

    @Test(description = "NC006: Address cannot have first blank space")
    public void NC006_Address_cannot_have_first_blank_space() {
        List<HashMap<String, String>> data = findTestData("NC006");
        objNewCustomer.input_Address(data.get(0).get("Address"));
        assertTrue(
                objNewCustomer.an_Error_Message_Address_Is_Shown(data.get(0).get("AddressErrorMessage")));
    }

    @Test(description = "NC008: City cannot be empty")
    public void NC008_City_cannot_be_empty() {
        List<HashMap<String, String>> data = findTestData("NC008");
        objNewCustomer.input_City(data.get(0).get("City"));
        assertTrue(
                objNewCustomer.an_Error_Message_City_Is_Shown(data.get(0).get("CityErrorMessage")));
    }

    @Test(description = "NC009: City cannot be numeric")
    public void NC009_City_cannot_be_numeric() {
        List<HashMap<String, String>> data = findTestData("NC009");
        objNewCustomer.input_City(data.get(0).get("City"));
        assertTrue(
                objNewCustomer.an_Error_Message_City_Is_Shown(data.get(0).get("CityErrorMessage")));
    }

    @Test(description = "NC010: City cannot have special characters")
    public void NC010_City_cannot_have_special_characters() {
        List<HashMap<String, String>> data = findTestData("NC010");
        objNewCustomer.input_City(data.get(0).get("City"));
        assertTrue(
                objNewCustomer.an_Error_Message_City_Is_Shown(data.get(0).get("CityErrorMessage")));
    }

    @Test(description = "NC011: City cannot have first blank space")
    public void NC011_City_cannot_have_first_blank_space() {
        List<HashMap<String, String>> data = findTestData("NC011");
        objNewCustomer.input_City(data.get(0).get("City"));
        assertTrue(
                objNewCustomer.an_Error_Message_City_Is_Shown(data.get(0).get("CityErrorMessage")));
    }

    @Test(description = "NC012: State cannot be empty")
    public void NC012_State_cannot_be_empty() {
        List<HashMap<String, String>> data = findTestData("NC012");
        objNewCustomer.input_State(data.get(0).get("State"));
        assertTrue(
                objNewCustomer.an_Error_Message_State_Is_Shown(data.get(0).get("StateErrorMessage")));
    }

    @Test(description = "NC013: State cannot be numeric")
    public void NC013_State_cannot_be_numeric() {
        List<HashMap<String, String>> data = findTestData("NC013");
        objNewCustomer.input_State(data.get(0).get("State"));
        assertTrue(
                objNewCustomer.an_Error_Message_State_Is_Shown(data.get(0).get("StateErrorMessage")));
    }

    @Test(description = "NC014: State cannot have special characters")
    public void NC014_State_cannot_have_special_characters() {
        List<HashMap<String, String>> data = findTestData("NC014");
        objNewCustomer.input_State(data.get(0).get("State"));
        assertTrue(
                objNewCustomer.an_Error_Message_State_Is_Shown(data.get(0).get("StateErrorMessage")));
    }

    @Test(description = "NC015: State cannot have first blank space")
    public void NC015_State_cannot_have_first_blank_space() {
        List<HashMap<String, String>> data = findTestData("NC015");
        objNewCustomer.input_State(data.get(0).get("State"));
        assertTrue(
                objNewCustomer.an_Error_Message_State_Is_Shown(data.get(0).get("StateErrorMessage")));
    }

    @Test(description = "NC016: PIN cannot be numeric")
    public void NC016_PIN_cannot_be_numeric() {
        List<HashMap<String, String>> data = findTestData("NC016");
        objNewCustomer.input_PIN(data.get(0).get("Pin"));
        assertTrue(
                objNewCustomer.an_Error_Message_Pin_Is_Shown(data.get(0).get("PinErrorMessage")));
    }

    @Test(description = "NC017: PIN cannot be empty")
    public void NC017_PIN_cannot_be_empty() {
        List<HashMap<String, String>> data = findTestData("NC017");
        objNewCustomer.input_PIN(data.get(0).get("Pin"));
        assertTrue(
                objNewCustomer.an_Error_Message_Pin_Is_Shown(data.get(0).get("PinErrorMessage")));
    }

    @Test(description = "NC018: PIN must have 6 digits")
    public void NC018_PIN_must_have_6_digits() {
        List<HashMap<String, String>> data = findTestData("NC018");
        objNewCustomer.input_PIN(data.get(0).get("Pin"));
        assertTrue(
                objNewCustomer.an_Error_Message_Pin_Is_Shown(data.get(0).get("PinErrorMessage")));
    }

    @Test(description = "NC019: PIN cannot have special character")
    public void NC019_PIN_cannot_have_special_character() {
        List<HashMap<String, String>> data = findTestData("NC019");
        objNewCustomer.input_PIN(data.get(0).get("Pin"));
        assertTrue(
                objNewCustomer.an_Error_Message_Pin_Is_Shown(data.get(0).get("PinErrorMessage")));
    }

    @Test(description = "NC020: PIN cannot have first blank space")
    public void NC020_PIN_cannot_have_first_blank_space() {
        List<HashMap<String, String>> data = findTestData("NC020");
        objNewCustomer.input_PIN(data.get(0).get("Pin"));
        assertTrue(
                objNewCustomer.an_Error_Message_Pin_Is_Shown(data.get(0).get("PinErrorMessage")));
    }

    @Test(description = "NC021: PIN cannot have blank space")
    public void NC021_PIN_cannot_have_blank_space() {
        List<HashMap<String, String>> data = findTestData("NC021");
        objNewCustomer.input_PIN(data.get(0).get("Pin"));
        assertTrue(
                objNewCustomer.an_Error_Message_Pin_Is_Shown(data.get(0).get("PinErrorMessage")));
    }

    @Test(description = "NC022: Telephone cannot be empty")
    public void NC022_Telephone_cannot_be_empty() {
        List<HashMap<String, String>> data = findTestData("NC022");
        objNewCustomer.input_Telephone(data.get(0).get("PhoneNumber"));
        assertTrue(
                objNewCustomer.an_Error_Message_Telephone_Is_Shown(data.get(0).get("PhoneErrorMessage")));
    }

    @Test(description = "NC023: Telephone cannot have first characters blank space")
    public void NC023_Telephone_cannot_have_first_characters_blank_space() {
        List<HashMap<String, String>> data = findTestData("NC023");
        objNewCustomer.input_Telephone(data.get(0).get("PhoneNumber"));
        assertTrue(
                objNewCustomer.an_Error_Message_Telephone_Is_Shown(data.get(0).get("PhoneErrorMessage")));
    }

    @Test(description = "NC024: Telephone cannot have blank space")
    public void NC024_Telephone_cannot_have_blank_space() {
        List<HashMap<String, String>> data = findTestData("NC024");
        objNewCustomer.input_Telephone(data.get(0).get("PhoneNumber"));
        assertTrue(
                objNewCustomer.an_Error_Message_Telephone_Is_Shown(data.get(0).get("PhoneErrorMessage")));
    }

    @Test(description = "NC025: Telephone cannot have special character")
    public void NC025_Telephone_cannot_have_special_character() {
        List<HashMap<String, String>> data = findTestData("NC025");
        objNewCustomer.input_Telephone(data.get(0).get("PhoneNumber"));
        assertTrue(
                objNewCustomer.an_Error_Message_Telephone_Is_Shown(data.get(0).get("PhoneErrorMessage")));
    }

    @Test(description = "NC026: Email cannot be empty")
    public void NC026_Email_cannot_be_empty() {
        List<HashMap<String, String>> data = findTestData("NC026");
        objNewCustomer.input_Email(data.get(0).get("Email"));
        assertTrue(
                objNewCustomer.an_Error_Message_Email_Is_Shown(data.get(0).get("EmailErrorMessage")));
    }

    @Test(description = "NC027: Email must be in correct format ")
    public void NC027_Email_must_be_in_correct_format() {
        List<HashMap<String, String>> data = findTestData("NC027");
        objNewCustomer.input_Email(data.get(0).get("Email"));
        assertTrue(
                objNewCustomer.an_Error_Message_Email_Is_Shown(data.get(0).get("EmailErrorMessage")));
    }

    @Test(description = "NC029: Email cannot have space ")
    public void NC029_Email_cannot_have_space() {
        List<HashMap<String, String>> data = findTestData("NC029");
        objNewCustomer.input_Email(data.get(0).get("Email"));
        assertTrue(
                objNewCustomer.an_Error_Message_Email_Is_Shown(data.get(0).get("EmailErrorMessage")));
    }

    @Test(description = "NC032: Reset all field in New Customer")
    public void NC032_Reset_all_field_in_New_customer() {
        List<HashMap<String, String>> data = findTestData("NC032");
        Customer customer = Customer.Builder.aCustomer()
                .withCustomerName(data.get(0).get("CustomerName"))
                .withGender(data.get(0).get("Gender"))
                .withDateOfBirth(data.get(0).get("DOB"))
                .withAddress(data.get(0).get("Address"))
                .withState(data.get(0).get("State"))
                .withCity(data.get(0).get("City"))
                .withPin(data.get(0).get("Pin"))
                .withTelephone(data.get(0).get("Telephone"))
                .withEmail(generateRandomValidEmail(data.get(0).get("PrefixEmail"), data.get(0).get("Suffix")))
                .withPassword(data.get(0).get("Password")).build();
        objNewCustomer.create_new_customer(customer);
        objNewCustomer.clickResetNewCustomer();
        assertEquals("", objNewCustomer.customerName());
        assertEquals("", objNewCustomer.dateOfBirth());
        assertEquals("", objNewCustomer.address());
        assertEquals("", objNewCustomer.city());
        assertEquals("", objNewCustomer.state());
        assertEquals("", objNewCustomer.pin());
        assertEquals("", objNewCustomer.telephone());
        assertEquals("", objNewCustomer.email());
        assertEquals("", objNewCustomer.password());
    }

//  @Test(description = "NC033: Check submit with no enter value in all field")
//  public void NC033_Check_submit_with_no_enter_value_in_all_field() {
//    webUI.delayInSeconds(5);
//    objNewCustomer.clickSubmitNewCustomer();
//    webUI.delayInSeconds(5);
//    assertTrue(objNewCustomer.should_show_error_alert_message());
//    assertEquals(webUI.getAlertText(), "Email Address Already Exist !!");
//  }

    @Test(description = "NC033: Check submit with no enter value in all field (F5)")
    public void NC033_Check_submit_with_no_enter_value_in_all_field_F5() {
        webUI.delayInSeconds(2);
        webUI.refresh();
        objNewCustomer.clickSubmitNewCustomer();
        webUI.delayInSeconds(2);
    }

    @Test(description = "NC034: Accept alert")
    public void NC034_Accept_alert() {
        webUI.acceptAlert();
        assertFalse(objNewCustomer.should_show_error_alert_message());
    }

    @Test(description = "NC035: Submit form with all fields entered and retrieve Customer ID")
    public void NC035_Check_submit_and_get_CustomerId() {
        List<HashMap<String, String>> data = findTestData("NC035");
        Customer customer = Customer.Builder.aCustomer()
                .withCustomerName(data.get(0).get("CustomerName"))
                .withGender(data.get(0).get("Gender"))
                .withDateOfBirth(data.get(0).get("DOB"))
                .withAddress(data.get(0).get("Address"))
                .withState(data.get(0).get("State"))
                .withCity(data.get(0).get("City"))
                .withPin(data.get(0).get("Pin"))
                .withTelephone(data.get(0).get("Telephone"))
                .withEmail(generateRandomValidEmail(data.get(0).get("PrefixEmail"), data.get(0).get("Suffix")))
                .withPassword(data.get(0).get("Password")).build();

        webUI.delayInSecond(3);
        objNewCustomer.create_new_customer(customer);

        CustomerRegMsg objCustomerRegMsg = objNewCustomer.clickSubmitNewCustomer();
        webUI.delayInSecond(5);
        assertTrue(objCustomerRegMsg.should_show_text_of_heading("Customer Registered Successfully!!!"));

        String customerId = objCustomerRegMsg.getCustomerId();
        assertNotNull(customerId, "Customer ID should not be null");
        assertFalse(customerId.isEmpty(), "Customer ID should not be empty");
        excelUtils.setCellValue(2, 1, customerId, DATA_FILE_PATH);
    }
}
