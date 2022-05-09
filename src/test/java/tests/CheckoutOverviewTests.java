package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutOverviewTests extends BaseTest {

    @Test(description = "Verify that a single ordered item is on the overview page", groups = {"sanity", "regression"})
    @Feature("Checkout Summary")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user's single ordered item is exist on overview page")
    public void validateSingleItemTest() {
        productsPage.addItemToCart("Sauce Labs Bike Light")
                .goToCart().proceedToCheckoutInfoPage()
                .setFirstName("Test")
                .setLastName("Test2")
                .setZipCode("123456789")
                .proceedToOverviewPage();
        Assert.assertTrue(checkoutOverviewPage.isOrderedItemExist("Sauce Labs Bike Light"));
        Assert.assertEquals(checkoutOverviewPage.calculateTotalPrice(), "10.79");
    }

    @Test(description = "Verify that multiple ordered items are on the overview page", groups = {"sanity", "regression"})
    @Feature("Checkout Summary")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user's multiple ordered items are exist on overview page")
    public void validateMultipleItemsTest() {
        productsPage.addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .goToCart().proceedToCheckoutInfoPage()
                .setFirstName("Test")
                .setLastName("Test2").setZipCode("123456789")
                .proceedToOverviewPage();
        Assert.assertTrue(checkoutOverviewPage.isOrderedItemExist("Sauce Labs Backpack"));
        Assert.assertTrue(checkoutOverviewPage.isOrderedItemExist("Sauce Labs Fleece Jacket"));
        Assert.assertEquals(checkoutOverviewPage.calculateTotalPrice(), "86.38");
    }

    @Test(description = "Order finishing test", groups = {"sanity", "regression"})
    @Feature("Checkout Summary")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can finish his order")
    public void finishOverviewOrderTest() {
        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt")
                .goToCart().proceedToCheckoutInfoPage()
                .setFirstName("Test").setLastName("Test2").setZipCode("123456789")
                .proceedToOverviewPage().finishOverviewOrder();
        Assert.assertEquals(checkoutCompletePage.getPageHeader(), "CHECKOUT: COMPLETE!");
        Assert.assertEquals(checkoutCompletePage.getCompleteHeader(), "THANK YOU FOR YOUR ORDER");
    }

}
