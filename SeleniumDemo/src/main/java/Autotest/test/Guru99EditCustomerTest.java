package Autotest.test;

import Autotest.pages.CustomerRegMsg;
import Autotest.pages.Manager;
import Autotest.pages.EditCustomer;
import Autotest.test.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Guru99EditCustomerTest extends BaseTest {

    private Manager objManager;
    private EditCustomer objEditCustomer;

    @Test(description = "NC001: Edit customer form")
    public void NC001_Edit_customer_form() {
        Manager objManager = objLogin.login_Guru99_with(excelUtils.getCellData(1, 0),
                excelUtils.getCellData(1, 1));
        objEditCustomer = objManager.leftMenu().move_to_Edit_Customer();
        objEditCustomer.inputCustomerId(excelUtils.getCellData(1, 19));
        CustomerRegMsg objCustomerRegMsg = objEditCustomer.clickSubmitEditForm();
        assertTrue(
                objCustomerRegMsg.should_show_text_of_heading("Edit Customer"));
//        webUI.delayInSecond(5);
    }

    @Test(description = "NC002: Change information and submit")
    public void NC002_Change_information_and_submit() {
        customer.setAddress("1 Hoang Dieu");
        customer.setState("Thu Duc");
        customer.setCity("Ho Chi Minh");
        customer.setPin("012345");
        customer.setTelephone("0991234567");
        webUI.delayInSecond(3);
        objEditCustomer.edit_information(customer);
        CustomerRegMsg objCustomerRegMsg = objEditCustomer.clickSubmitEditCustomer();
        webUI.delayInSecond(3);
        webUI.acceptAlert();
        assertFalse(objEditCustomer.should_show_error_alert_message());
        assertTrue(
                objCustomerRegMsg.should_show_text_of_heading("Edit Customer Form"));
    }


}
