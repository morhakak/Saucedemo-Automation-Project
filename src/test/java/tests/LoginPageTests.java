package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DataProviders;

@Feature("Login")
public class LoginPageTests extends BaseTest {

    @Test(description = "Valid Login with valid credentials", groups = {"sanity", "regression"}, dataProvider = "ValidUsers", dataProviderClass = DataProviders.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login with valid credentials")
    public void validLoginTest(String user, String password) {
        loginPage.login(user, password);
        Assert.assertTrue(productsPage.isOnProductsPage());
        goToBaseUrl();
    }

    @Test(description = "Invalid login - Blank user field, valid password", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Security")
    @Description("Login with blank username and valid password")
    public void loginWithBlankUserNameTest() {
        loginPage.setUserName("")
                .setPassword("secret_sauce")
                .clickLogin();
        Assert.assertTrue(loginPage.getGeneralErrorMessage().contains("Username is required"));
    }

    @Test(description = "Invalid login - Invalid user, valid password", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Security")
    @Description("Login with invalid username and valid password")
    public void loginWithInvalidUserNameTest() {
        loginPage.setUserName("123")
                .setPassword("secret_sauce")
                .clickLogin();
        Assert.assertTrue(loginPage.getGeneralErrorMessage().contains("Username and password do not match"));
    }

    @Test(description = "Invalid login - Valid user, blank password field", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Security")
    @Description("Login with valid username and blank password")
    public void loginWithBlankPasswordTest() {
        loginPage.setUserName("standard_user")
                .setPassword("")
                .clickLogin();
        Assert.assertTrue(loginPage.getGeneralErrorMessage().contains("Password is required"));
    }

    @Test(description = "Invalid Login - Valid user name, invalid password", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Security")
    @Description("Login with valid username and invalid password")
    public void loginWithInvalidPasswordTest() {
        loginPage.setUserName("standard_user")
                .setPassword("123")
                .clickLogin();
        Assert.assertTrue(loginPage.getGeneralErrorMessage().contains("Username and password do not match"));
    }

    @Test(description = "Invalid login with invalid credentials", dataProvider = "InValidUsers", dataProviderClass = DataProviders.class, groups = {"sanity", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Security")
    @Description("Login - invalid credentials")
    public void loginWithInvalidCredentials(String user, String password) {
        loginPage.login(user, password);
        Assert.assertTrue(loginPage.getGeneralErrorMessage().contains("Username and password do not match"));
    }

    @Test(description = "Invalid login - Blank user field, blank password", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Security")
    @Description("Login with blank username and password fields")
    public void loginWithoutCredentialsTest() {
        loginPage.setUserName("")
                .setPassword("")
                .clickLogin();
        Assert.assertTrue(loginPage.getGeneralErrorMessage().contains("Username is required"));
    }

    @Test(description = "Invalid login - blocked user test", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Security")
    @Description("Verify that blocked user can not login to the site")
    public void blockedUserLoginTest() {
        loginPage.setUserName("locked_out_user")
                .setPassword("secret_sauce")
                .clickLogin();
        Assert.assertTrue(loginPage.getGeneralErrorMessage().contains("this user has been locked out"));
    }

//    @BeforeMethod
//    public void home() {
//        driver.get("https://www.saucedemo.com/");
//    }


}
