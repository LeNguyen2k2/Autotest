package Autotest.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import Autotest.keywords.WebUI;
import Autotest.object_repo.LoginRepo;

public class Login extends BasePage {

  public Login(WebUI webUI) {
    super(webUI);
    setRepoName(Login.class.getSimpleName()); // Login.class.getSimpleName() = "Login"
  }

  @Step("Input user id: {0}")
  public void inputUserId(String userId) {
    if(userId.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_USER_ID"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_USER_ID"), userId);
    }
  }

  @Step("Input password: {0}")
  public void inputPassword(String password) {
    if(password.isEmpty()) {
      webUI.sendKeys(findElementObject("TXT_PASSWORD"), Keys.chord(Keys.TAB));
    } else {
      webUI.sendKeys(findElementObject("TXT_PASSWORD"), password);
    }
  }

  @Step("Click Login button")
  private void clickLogin() {
    webUI.click(findElementObject("BTN_LOGIN"));
    webUI.delayInSecond(3);
  }

  @Step("Should show user id error message: {0}")
  public boolean should_show_user_id_error_message(String message) {
    return webUI.verifyElementText(findElementObject("LBL_USER_ID_ERROR_MESSAGE"), message);
  }

  @Step("Should show password error message: {0}")
  public boolean should_show_password_error_message(String message) {
    return webUI.verifyElementText(findElementObject("LBL_PASSWORD_ERROR_MESSAGE"), message);
  }

  @Step("Login Guru99 with user id '{0}' and password '{1}'")
  public Manager login_Guru99_with(String userId, String password) {
    inputUserId(userId);
    inputPassword(password);
    clickLogin();
    return new Manager(webUI);
  }

}
