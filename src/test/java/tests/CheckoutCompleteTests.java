package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutCompleteTests extends BaseTest {

    @Test(description = "Successful single item order completion test", groups = {"sanity", "regression", "E2E"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user has successfully completed his order")
    public void singleItemOrderCompletionTest() {
        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt")
                .goToCart().proceedToCheckoutInfoPage()
                .setFirstName("Test").setLastName("Test2").setZipCode("123456789")
                .proceedToOverviewPage().finishOverviewOrder();
        Assert.assertTrue(checkoutCompletePage.isOrderCompleted());
    }

    @Test(description = "Successful single item order completion test", groups = {"sanity", "regression", "E2E"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user has successfully completed his order")
    public void multipleItemsOrderCompletionTest() {
        productsPage.addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .goToCart().proceedToCheckoutInfoPage()
                .setFirstName("Test").setLastName("Test2").setZipCode("123456789")
                .proceedToOverviewPage().finishOverviewOrder();
        Assert.assertTrue(checkoutCompletePage.isOrderCompleted());
    }
}
