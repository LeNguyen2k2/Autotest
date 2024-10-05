package Autotest.test;

import Autotest.common.utils.ExcelUtils;
import org.testng.annotations.Test;
import Autotest.pages.Manager;
import Autotest.pages.NewCustomer;
import Autotest.pages.CustomerRegMsg;
import Autotest.model.Customer;

import static org.testng.Assert.*;

public class TestCaseSuiteTest extends BaseTest {

//  private static final String USER_ID = "mngr588662";
//  private static final String USER_PASSWORD = "ApYvAjA";

  private Manager objManager;
  private NewCustomer objNewCustomer;

  public TestCaseSuiteTest() {
    excelUtils = new ExcelUtils();
    excelUtils.setExcelFile(DATA_FILE_PATH, TestCaseSuiteTest.class.getSimpleName());
  }


  @Test(description = "NC001: Name cannot be empty")
  public void NC001_Name_cannot_be_empty() {
    Manager objManager = objLogin.login_Guru99_with(excelUtils.getCellData(1, 0),
            excelUtils.getCellData(1, 1));
    objNewCustomer = objManager.leftMenu().move_to_New_Customer();
    objNewCustomer.input_Customer_Name(excelUtils.getCellData(1, 2));
    assertTrue(
            objNewCustomer.should_Show_Customer_Name_Error_Message(excelUtils.getCellData(1, 3)));
  }

  @Test(description = "NC002: Name cannot be numeric")
  public void NC002_Name_cannot_be_numeric() {
    objNewCustomer.input_Customer_Name(excelUtils.getCellData(2, 2));
    assertTrue(objNewCustomer.should_Show_Customer_Name_Error_Message(excelUtils.getCellData(2, 3)));
  }

  @Test(description = "NC003: Name cannot have special characters")
  public void NC003_Name_cannot_have_special_characters() {
    objNewCustomer.input_Customer_Name(excelUtils.getCellData(3, 2));
    assertTrue(objNewCustomer.should_Show_Customer_Name_Error_Message(excelUtils.getCellData(3, 3)));
  }


  @Test(description = "NC004: Name cannot have first character as blank space")
  public void NC004_Name_cannot_have_first_characters_as_blank_space() {
    objNewCustomer.input_Customer_Name(excelUtils.getCellData(4, 2));
    assertTrue(objNewCustomer.should_Show_Customer_Name_Error_Message(excelUtils.getCellData(4, 3)));
  }

  @Test(description = "NC005: Address cannot be empty")
  public void NC005_Address_cannot_be_empty() {
    webUI.scrollIntoView("//td[normalize-space()='Address']");
    objNewCustomer.input_Address(excelUtils.getCellData(1, 6));
    assertTrue(objNewCustomer.an_Error_Message_Address_Is_Shown(excelUtils.getCellData(1 , 7)));
  }

  @Test(description = "NC006: Address cannot have first blank space")
  public void NC006_Address_cannot_have_first_blank_space() {
    objNewCustomer.input_Address(excelUtils.getCellData(2, 6));
    assertTrue(objNewCustomer.an_Error_Message_Address_Is_Shown(excelUtils.getCellData(2, 7)));
  }

  @Test(description = "NC008: City cannot be empty")
  public void NC008_City_cannot_be_empty() {
    objNewCustomer.input_City(excelUtils.getCellData(1, 8));
    assertTrue(objNewCustomer.an_Error_Message_City_Is_Shown(excelUtils.getCellData(1, 9)));
  }

  @Test(description = "NC009: City cannot be numeric")
  public void NC009_City_cannot_be_numeric() {
    objNewCustomer.input_City(excelUtils.getCellData(2, 8));
    assertTrue(objNewCustomer.an_Error_Message_City_Is_Shown(excelUtils.getCellData(2, 9)));
  }

  @Test(description = "NC010: City cannot have special characters")
  public void NC010_City_cannot_have_special_characters() {
    objNewCustomer.input_City(excelUtils.getCellData(3, 8));
    assertTrue(objNewCustomer.an_Error_Message_City_Is_Shown(excelUtils.getCellData(3, 9)));
  }

  @Test(description = "NC011: City cannot have first blank space")
  public void NC011_City_cannot_have_first_blank_space() {
    objNewCustomer.input_City(excelUtils.getCellData(4, 8));
    assertTrue(objNewCustomer.an_Error_Message_City_Is_Shown(excelUtils.getCellData(4, 9)));
  }

  @Test(description = "NC012: State cannot be empty")
  public void NC012_State_cannot_be_empty() {
    objNewCustomer.input_State(excelUtils.getCellData(1, 10));
    assertTrue(objNewCustomer.an_Error_Message_State_Is_Shown(excelUtils.getCellData(1, 11)));
  }

  @Test(description = "NC013: State cannot be numeric")
  public void NC013_State_cannot_be_numeric() {
    objNewCustomer.input_State(excelUtils.getCellData(2, 10));
    assertTrue(objNewCustomer.an_Error_Message_State_Is_Shown(excelUtils.getCellData(2, 11)));
  }

  @Test(description = "NC014: State cannot have special characters")
  public void NC014_State_cannot_have_special_characters() {
    objNewCustomer.input_State(excelUtils.getCellData(3, 10));
    assertTrue(objNewCustomer.an_Error_Message_State_Is_Shown(excelUtils.getCellData(3, 11)));
  }

  @Test(description = "NC015: State cannot have first blank space")
  public void NC015_State_cannot_have_first_blank_space() {
    objNewCustomer.input_State(excelUtils.getCellData(4, 10));
    assertTrue(objNewCustomer.an_Error_Message_State_Is_Shown(excelUtils.getCellData(4, 11)));
  }

  @Test(description = "NC016: PIN cannot be numeric")
  public void NC016_PIN_cannot_be_numeric() {
    objNewCustomer.input_PIN(excelUtils.getCellData(1, 12));
    assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown(excelUtils.getCellData(1, 13)));
  }

  @Test(description = "NC017: PIN cannot be empty")
  public void NC017_PIN_cannot_be_empty() {
    objNewCustomer.input_PIN(excelUtils.getCellData(2, 12));
    assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown(excelUtils.getCellData(2, 13)));
  }

  @Test(description = "NC018: PIN must have 6 digits")
  public void NC018_PIN_must_have_6_digits() {
    objNewCustomer.input_PIN(excelUtils.getCellData(3, 12));
    assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown(excelUtils.getCellData(3, 13)));
  }

  @Test(description = "NC019: PIN cannot have special character")
  public void NC019_PIN_cannot_have_special_character() {
    objNewCustomer.input_PIN(excelUtils.getCellData(4, 12));
    assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown(excelUtils.getCellData(4, 13)));
  }

  @Test(description = "NC020: PIN cannot have first blank space")
  public void NC020_PIN_cannot_have_first_blank_space() {
    objNewCustomer.input_PIN(excelUtils.getCellData(5, 12));
    assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown(excelUtils.getCellData(5, 13)));
  }

  @Test(description = "NC021: PIN cannot have blank space")
  public void NC021_PIN_cannot_have_blank_space() {
    webUI.scrollIntoView("//td[normalize-space()='PIN']");
    objNewCustomer.input_PIN(excelUtils.getCellData(6, 12));
    assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown(excelUtils.getCellData(6, 13)));
  }

  @Test(description = "NC022: Telephone cannot be empty")
  public void NC022_Telephone_cannot_be_empty() {
    objNewCustomer.input_Telephone(excelUtils.getCellData(1, 14));
    assertTrue(objNewCustomer.an_Error_Message_Telephone_Is_Shown(excelUtils.getCellData(1, 15)));
  }

  @Test(description = "NC023: Telephone cannot have first characters blank space")
  public void NC023_Telephone_cannot_have_first_characters_blank_space() {
    objNewCustomer.input_Telephone(excelUtils.getCellData(2, 14));
    assertTrue(objNewCustomer.an_Error_Message_Telephone_Is_Shown(excelUtils.getCellData(2, 15)));
  }

  @Test(description = "NC024: Telephone cannot have blank space")
  public void NC024_Telephone_cannot_have_blank_space() {
    objNewCustomer.input_Telephone(excelUtils.getCellData(3, 14));
    assertTrue(objNewCustomer.an_Error_Message_Telephone_Is_Shown(excelUtils.getCellData(3, 15)));
  }

  @Test(description = "NC025: Telephone cannot have special character")
  public void NC025_Telephone_cannot_have_special_character() {
    objNewCustomer.input_Telephone(excelUtils.getCellData(4, 14));
    assertTrue(objNewCustomer.an_Error_Message_Telephone_Is_Shown(excelUtils.getCellData(4, 15)));
  }

  @Test(description = "NC026: Email cannot be empty")
  public void NC026_Email_cannot_be_empty() {
    objNewCustomer.input_Email(excelUtils.getCellData(1, 16));
    assertTrue(objNewCustomer.an_Error_Message_Email_Is_Shown(excelUtils.getCellData(1, 17)));
  }

  @Test(description = "NC027: Email must be in correct format ")
  public void NC027_Email_must_be_in_correct_format () {
    objNewCustomer.input_Email(excelUtils.getCellData(2, 16));
    assertTrue(objNewCustomer.an_Error_Message_Email_Is_Shown(excelUtils.getCellData(2, 17)));
  }

  @Test(description = "NC029: Email cannot have space ")
  public void NC029_Email_cannot_have_space () {
    objNewCustomer.input_Email(excelUtils.getCellData(3, 16));
    assertTrue(objNewCustomer.an_Error_Message_Email_Is_Shown(excelUtils.getCellData(3, 17)));
  }

  @Test(description = "NC032: Reset all field in New Customer")
  public void NC032_Reset_all_field_in_New_customer() {
    Customer customer = Customer.Builder.aCustomer()
            .withCustomerName("Thanh Hằng")
            .withGender("Female")
            .withDateOfBirth("09/05/2022")
            .withAddress("123 Nguyen Trai")
            .withState("Thanh Xuan")
            .withCity("Ha Noi")
            .withPin("123456")
            .withTelephone("0912345657")
            .withEmail(generateRandomValidEmail("abc", "@gmail.com"))
            .withPassword("123@123").build();
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
    Customer customer = Customer.Builder.aCustomer()
            .withCustomerName("Thien Nguyen")
            .withGender("Female")
            .withDateOfBirth("01/02/2022")
            .withAddress("123 Phan Van Tri")
            .withState("Go Vap")
            .withCity("Ho Chi Minh")
            .withPin("123456")
            .withTelephone("0912345657")
            .withEmail(generateRandomValidEmail("abc", "@gmail.com"))
            .withPassword("123@123").build();

    // Step 2: Create new customer
    webUI.delayInSecond(3);
    objNewCustomer.create_new_customer(customer);

    // Step 3: Submit the form and verify success message
    CustomerRegMsg objCustomerRegMsg = objNewCustomer.clickSubmitNewCustomer();
    webUI.delayInSecond(3);
    assertTrue(
            objCustomerRegMsg.should_show_text_of_heading("Customer Registered Successfully!!!"));

    // Step 4: Retrieve the Customer ID
    String customerId = objCustomerRegMsg.getCustomerId();

    // Step 5: Assert that the customer ID is valid
    assertNotNull(customerId, "Customer ID should not be null");
    assertFalse(customerId.isEmpty(), "Customer ID should not be empty");

    // Step 6: Set the Customer ID and write it to the Excel file
    customer.setCustomerId(customerId);
    excelUtils.setCellValue(1, 19, "", DATA_FILE_PATH);
    excelUtils.setCellValue(1, 19, customerId, DATA_FILE_PATH); // Write customerId into cell (row 1, column 19)

    // Optional: Print the customerId for debugging purposes
    System.out.println("Customer ID retrieved and written to Excel: " + customerId);
  }



}
