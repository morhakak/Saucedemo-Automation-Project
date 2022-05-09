package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CheckoutInfoPageTests extends BaseTest {

    @Test(description = "Testing checkout info form with valid info", groups = {"sanity", "regression"})
    @Feature("Info Form")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can be added to cart via item page")
    public void validInfoTest() {
        productsPage.addItemToCart("Sauce Labs Backpack")
                .goToCart().proceedToCheckoutInfoPage()
                .setFirstName("Test").setLastName("Test2").setZipCode("123456789")
                .proceedToOverviewPage();
        Assert.assertTrue(checkoutOverviewPage.getPageHeaderTitle().contains("CHECKOUT: OVERVIEW"), "Title mismatch");
    }

    @Test(description = "Testing checkout info form with invalid first name", groups = {"sanity", "regression"})
    @Feature("Info Form")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user cannot proceed to next step with invalid first name")
    public void invalidFirstNameInfoTest() {
        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt")
                .goToCart().proceedToCheckoutInfoPage()
                .setFirstName("").setLastName("Test2").setZipCode("123456789")
                .proceedToOverviewPage();
        Assert.assertTrue(checkoutInfoPage.getFirstNameErrorMsg().contains("First Name"), "Incorrect error message");
    }

    @Test(description = "Testing checkout info form with invalid last name", groups = {"sanity", "regression"})
    @Feature("Info Form")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user cannot proceed to next step with invalid last name")
    public void invalidLastNameInfoTest() {
        productsPage.addItemToCart("Sauce Labs Onesie")
                .goToCart().proceedToCheckoutInfoPage()
                .setFirstName("Test").setLastName("").setZipCode("123456789")
                .proceedToOverviewPage();
        Assert.assertTrue(checkoutInfoPage.getLastNameErrorMsg().contains("Last Name"), "Incorrect error message");
    }

    @Test(description = "Testing checkout info form with invalid zipcode", groups = {"sanity", "regression"})
    @Feature("Info Form")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user cannot proceed to next step with invalid zipcode")
    public void invalidZipCodeInfoTest() {
        productsPage.addItemToCart("Sauce Labs Bike Light")
                .goToCart().proceedToCheckoutInfoPage()
                .setFirstName("Test").setLastName("Test2").setZipCode("")
                .proceedToOverviewPage();
        Assert.assertTrue(checkoutInfoPage.getZipCodeErrorMsg().contains("Postal Code"), "Incorrect error message");
    }

    @Test(description = "Testing checkout info page cancellation", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can go back to products page")
    public void cancelCheckoutTest() {
        productsPage.addItemToCart("Test.allTheThings() T-Shirt (Red)")
                .goToCart().proceedToCheckoutInfoPage().cancelCheckout();
        Assert.assertEquals(cartPage.getCartPageHeader(), "YOUR CART", "checkout cancellation failed");
    }
}
