package Autotest.test;

import org.testng.annotations.Test;
import Autotest.pages.Manager;

import static org.testng.Assert.assertTrue;

public class GuruLoginTest extends BaseTest {

    @Test(description = "LG001: User Id is not empty")
    public void LG001_User_Id_is_not_empty() {
        objLogin.inputUserId("");
        assertTrue(objLogin.should_show_user_id_error_message("User-ID must not be blank"));
    }

    @Test(description = "LG002: Password is not empty")
    public void LG002_Password_is_not_empty() {
        objLogin.inputPassword("");
        assertTrue(objLogin.should_show_password_error_message("Password must not be blank"));
    }

//    @Test(description = "LG003: Login Guru99 successfully")
//    public void LG003_Login_Guru99_successfully() {
//        Manager objManager = objLogin.login_Guru99_with(USER_ID, USER_PASSWORD);
//        assertTrue(objManager.should_show_Manager_ID(USER_ID));
//    }

    @Test
    public void LG004_Login_Guru99_successfully() {
        String userId = excelUtils.getCellData(1, 0);
        System.out.println("Username from Excel: " + userId);

        String password = excelUtils.getCellData(1, 1);
        System.out.println("Password from Excel: " + password);

        Manager objManager = objLogin.login_Guru99_with(userId, password);
        assertTrue(objManager.should_show_Manager_ID(userId));
    }

}
