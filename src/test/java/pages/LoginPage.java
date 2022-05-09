package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy (id = "user-name")
    private WebElement userNameInputField;

    @FindBy (id = "password")
    private WebElement passwordInputField;

    @FindBy (id = "login-button")
    private WebElement loginButton;

    @FindBy (css = "h3[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("login with user: {userName}")
    public LoginPage setUserName(String userName) {
        type(userNameInputField, userName);
        return this;
    }

    @Step("login with password: {password}")
    public LoginPage setPassword(String password) {
        type(passwordInputField, password);
        return this;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public void clickLogin() {
        click(loginButton);
    }

    @Step("login with user: {userName} and password: {password}")
    public ProductsPage login(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        clickLogin();
        return new ProductsPage(driver);
    }

    public String getGeneralErrorMessage() {
        waitForElementToBeVisible(errorMessage);
        return getText(errorMessage);
    }

//    public String getErrorMessage() {
//        if(getGeneralErrorMessage().contains("Username is required"))
//            return "Epic sadface: Username is required";
//        if (getGeneralErrorMessage())
//    }

    public boolean checkErrorMessage() {
        if (getGeneralErrorMessage().contains("do not match") ||
            getGeneralErrorMessage().contains("Password is required") ||
            getGeneralErrorMessage().contains("Username is required") ||
            getGeneralErrorMessage().contains("Sorry, this user has been locked out")){
            return true;
        }
       return false;
    }

    public boolean isLoginButtonVisible() {
        return loginButton.isDisplayed();
    }


}
