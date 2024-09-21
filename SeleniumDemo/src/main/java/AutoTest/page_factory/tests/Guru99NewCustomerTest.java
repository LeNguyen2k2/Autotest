package Autotest.page_factory.tests;

import Autotest.page_factory.pages.Manager;
import Autotest.page_factory.pages.NewCustomer;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Guru99NewCustomerTest extends BaseTest {

    private Manager objManager;
    private NewCustomer objNewCustomer;

    @Test(description = "NC001: Name cannot be empty")
    public void NC001_Name_cannot_be_empty() {
//        objManager = objLogin.login_Guru99_with(USER_ID, USER_PASSWORD);
        String userId = objdata.getCellData("Username", 1);
        System.out.println("Username from Excel: " + userId);

        String password = objdata.getCellData("Password", 1);
        System.out.println("Password from Excel: " + password);

        objManager = objLogin.login_Guru99_with(userId, password);
        objNewCustomer = objManager.move_to_New_Customer();
        objNewCustomer.input_Customer_Name("");
        assertTrue(objNewCustomer.should_Show_Customer_Name_Error_Message("Customer name must not be blank"));
    }

    @Test(description = "NC002: Name cannot be numeric")
    public void NC002_Name_cannot_be_numeric() {
        objNewCustomer.input_Customer_Name("1234");
        assertTrue(objNewCustomer.should_Show_Customer_Name_Error_Message("Numbers are not allowed"));
    }

    @Test(description = "NC003: Name cannot have special characters")
    public void NC003_Name_cannot_have_special_characters() {
        objNewCustomer.input_Customer_Name("@s123");
        assertTrue(objNewCustomer.should_Show_Customer_Name_Error_Message("Special characters are not allowed"));
    }


    @Test(description = "NC004: Name cannot have first character as blank space")
    public void NC004_Name_cannot_have_first_characters_as_blank_space() {
        objNewCustomer.input_Customer_Name(" Nguyen");
        assertTrue(objNewCustomer.should_Show_Customer_Name_Error_Message("First character can not have space"));
    }

    @Test(description = "NC005: Address cannot be empty")
    public void NC005_Address_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='Address']");
        objNewCustomer.input_Address("");
        assertTrue(objNewCustomer.an_Error_Message_Address_Is_Shown("Address Field must not be blank"));
    }

    @Test(description = "NC006: Address cannot have first blank space")
    public void NC006_Address_cannot_have_first_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='Address']");
        objNewCustomer.input_Address(" Address");
        assertTrue(objNewCustomer.an_Error_Message_Address_Is_Shown("First character can not have space"));
    }

    @Test(description = "NC008: City cannot be empty")
    public void NC008_City_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='City']");
        objNewCustomer.input_City("");
        assertTrue(objNewCustomer.an_Error_Message_City_Is_Shown("City Field must not be blank"));
    }

    @Test(description = "NC009: City cannot be numeric")
    public void NC009_City_cannot_be_numeric() {
        webUI.scrollIntoView("//td[normalize-space()='City']");
        objNewCustomer.input_City("city123");
        assertTrue(objNewCustomer.an_Error_Message_City_Is_Shown("Numbers are not allowed"));
    }

    @Test(description = "NC010: City cannot have special characters")
    public void NC010_City_cannot_have_special_characters() {
        webUI.scrollIntoView("//td[normalize-space()='City']");
        objNewCustomer.input_City("City!@#");
        assertTrue(objNewCustomer.an_Error_Message_City_Is_Shown("Special characters are not allowed"));
    }

    @Test(description = "NC011: City cannot have first blank space")
    public void NC011_City_cannot_have_first_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='City']");
        objNewCustomer.input_City(" City");
        assertTrue(objNewCustomer.an_Error_Message_City_Is_Shown("First character can not have space"));
    }

    @Test(description = "NC012: State cannot be empty")
    public void NC012_State_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='State']");
        objNewCustomer.input_State("");
        assertTrue(objNewCustomer.an_Error_Message_State_Is_Shown("State must not be blank"));
    }

    @Test(description = "NC013: State cannot be numeric")
    public void NC013_State_cannot_be_numeric() {
        webUI.scrollIntoView("//td[normalize-space()='State']");
        objNewCustomer.input_State("state123");
        assertTrue(objNewCustomer.an_Error_Message_State_Is_Shown("Numbers are not allowed"));
    }

    @Test(description = "NC014: State cannot have special characters")
    public void NC014_State_cannot_have_special_characters() {
        webUI.scrollIntoView("//td[normalize-space()='State']");
        objNewCustomer.input_State("State!@#");
        assertTrue(objNewCustomer.an_Error_Message_State_Is_Shown("Special characters are not allowed"));
    }

    @Test(description = "NC015: City cannot have first blank space")
    public void NC015_City_cannot_have_first_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='State']");
        objNewCustomer.input_State(" State");
        assertTrue(objNewCustomer.an_Error_Message_State_Is_Shown("First character can not have space"));
    }

    @Test(description = "NC016: PIN cannot be numeric")
    public void NC016_Name_cannot_be_numeric() {
        objNewCustomer.input_PIN("1234PIN");
        assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown("Characters are not allowed"));
    }

    @Test(description = "NC017: PIN cannot be empty")
    public void NC017_Name_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        objNewCustomer.input_PIN("");
        assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown("PIN Code must not be blank"));
    }

    @Test(description = "NC018: PIN must have 6 digits")
    public void NC018_PIN_must_have_6_digits() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        objNewCustomer.input_PIN("");
        assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown("PIN Code must not be blank"));
    }

    @Test(description = "NC019: PIN cannot have special character")
    public void NC019_PIN_cannot_have_special_character() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        objNewCustomer.input_PIN("123!@#");
        assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown("Special characters are not allowed"));
    }

    @Test(description = "NC020: PIN cannot have first blank space")
    public void NC020_PIN_cannot_have_first_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        objNewCustomer.input_PIN(" 123");
        assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown("First character can not have space"));
    }

    @Test(description = "NC021: PIN cannot have blank space")
    public void NC021_PIN_cannot_have_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='PIN']");
        objNewCustomer.input_PIN("12 3");
        assertTrue(objNewCustomer.an_Error_Message_Pin_Is_Shown("Characters are not allowed"));
    }

    @Test(description = "NC022: Telephone cannot be empty")
    public void NC022_Telephone_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='Mobile Number']");
        objNewCustomer.input_Telephone("");
        assertTrue(objNewCustomer.an_Error_Message_Telephone_Is_Shown("Mobile no must not be blank"));
    }

    @Test(description = "NC023: Telephone cannot have first characters blank space")
    public void NC023_Telephone_cannot_have_first_characters_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='Mobile Number']");
        objNewCustomer.input_Telephone(" 099");
        assertTrue(objNewCustomer.an_Error_Message_Telephone_Is_Shown("First character can not have space"));
    }

    @Test(description = "NC024: Telephone cannot have blank space")
    public void NC024_Telephone_cannot_have_blank_space() {
        webUI.scrollIntoView("//td[normalize-space()='Mobile Number']");
        objNewCustomer.input_Telephone("09 9");
        assertTrue(objNewCustomer.an_Error_Message_Telephone_Is_Shown("Characters are not allowed"));
    }

    @Test(description = "NC025: Telephone cannot have special character")
    public void NC025_Telephone_cannot_have_special_character() {
        webUI.scrollIntoView("//td[normalize-space()='Mobile Number']");
        objNewCustomer.input_Telephone("099!@#");
        assertTrue(objNewCustomer.an_Error_Message_Telephone_Is_Shown("Special characters are not allowed"));
    }

    @Test(description = "NC026: Email cannot be empty")
    public void NC026_Email_cannot_be_empty() {
        webUI.scrollIntoView("//td[normalize-space()='E-mail']");
        objNewCustomer.input_Email("");
        assertTrue(objNewCustomer.an_Error_Message_Email_Is_Shown("Email-ID must not be blank"));
    }

    @Test(description = "NC027: Email must be in correct format ")
    public void NC027_Email_must_be_in_correct_format() {
        webUI.scrollIntoView("//td[normalize-space()='E-mail']");
        objNewCustomer.input_Email("guru99gmail.com");
        assertTrue(objNewCustomer.an_Error_Message_Email_Is_Shown("Email-ID is not valid"));
    }

    @Test(description = "NC029: Email cannot have space ")
    public void NC029_Email_cannot_have_space() {
        webUI.scrollIntoView("//td[normalize-space()='E-mail']");
        objNewCustomer.input_Email("Guru 99@");
        assertTrue(objNewCustomer.an_Error_Message_Email_Is_Shown("Email-ID is not valid"));
    }

}
