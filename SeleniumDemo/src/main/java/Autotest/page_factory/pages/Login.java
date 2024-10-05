package Autotest.page_factory.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Autotest.common.keywords.WebUI;

public class Login extends BasePage {

    @FindBy(xpath = "//input[@name='uid']")
    WebElement txtUserId;

    @FindBy(xpath = "//input[@name='password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@name='btnLogin']")
    WebElement btnLogin;

    @FindBy(xpath = "//label[@id='message23']")
    WebElement txtUserIdErrorMessage;

    @FindBy(xpath = "//label[@id='message18']")
    WebElement txtPasswordErrorMessage;

    public Login(WebUI webUI) {
        super(webUI);
    }

    @Step("Input user id: {0}")
    public void inputUserId(String userId) {
        if (userId.isEmpty()) {
            webUI.sendKeys(txtUserId, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(txtUserId, userId);
        }
    }

    @Step("Input password: {0}")
    public void inputPassword(String password) {
        if (password.isEmpty()) {
            webUI.sendKeys(txtPassword, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(txtPassword, password);
        }
    }

    @Step("Click Login button")
    private void clickLogin() {
        webUI.click(btnLogin);
        webUI.delayInSecond(3);
    }

    @Step("Should show user id error message: {0}")
    public boolean should_show_user_id_error_message(String message) {
        return webUI.verifyElementText(txtUserIdErrorMessage, message);
    }

    @Step("Should show password error message: {0}")
    public boolean should_show_password_error_message(String message) {
        return webUI.verifyElementText(txtPasswordErrorMessage, message);
    }

    @Step("Login Guru99 with user id '{0}' and password '{1}'")
    public Manager login_Guru99_with(String userId, String password) {
        inputUserId(userId);
        inputPassword(password);
        clickLogin();
        return new Manager(webUI);
    }

}
