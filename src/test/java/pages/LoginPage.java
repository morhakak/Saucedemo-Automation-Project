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

    @Step("Login page: Set user name as: {userName}")
    public LoginPage setUserName(String userName) {
        type(userNameInputField, userName);
        return this;
    }

    @Step("Login page: Set password as: {password}")
    public LoginPage setPassword(String password) {
        type(passwordInputField, password);
        return this;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    @Step("Login page: Click on login button")
    public ProductsPage clickLogin() {
        click(loginButton);
        return new ProductsPage(driver);
    }

    @Step("Login page: Login with user: {userName} and password: {password}")
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

    public boolean isLoginButtonVisible() {
        return loginButton.isDisplayed();
    }


}
