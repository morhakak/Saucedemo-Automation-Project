package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constants;

@Feature("Navbar")
public class NavbarTests extends BaseTest {

    @Test(description = "Successful logout test", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can successfully logout from the site")
    public void logoutTest() {
        productsPage.clickOnMenuOption(Constants.LOGOUT);
        Assert.assertTrue(loginPage.isLoginButtonVisible());
        initialLogin();
    }

    @Test(description = "Go to products page via navbar test", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user can navigate to products page via navbar")
    public void viewAllItemsTest() {
        productsPage.clickOnItem("Sauce Labs Backpack").clickOnMenuOption(Constants.ALL_ITEMS);
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

    @Test(description = "Go to about page via navbar test", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user can navigate to about page via navbar")
    public void goToAboutPageTest() {
        productsPage.clickOnMenuOption(Constants.ABOUT);
        Assert.assertTrue(saucelabsPage.getPageUrl().contains("saucelabs"));
    }

    @Test(description = "Resting app state test", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user can reset app state via navbar")
    public void resetAppStateTest() {
        productsPage.addItemToCart("Sauce Labs Backpack").clickOnMenuOption(Constants.RESET_APP_STATE);
        Assert.assertTrue(navbar.isCartEmpty());
    }
}
