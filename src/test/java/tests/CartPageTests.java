package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTests extends BaseTest {

    @Test(description = "Removing single item via cart page", groups = {"regression"})
    @Feature("Remove From Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user can remove a single item from cart")
    public void removeSingleItemFromCartTest() {
        productsPage.addItemToCart("Sauce Labs Backpack")
                .goToCart().removeItemFromCart("Sauce Labs Backpack");
        Assert.assertTrue(cartPage.isItemRemoved("Sauce Labs Backpack"));
    }

    @Test(description = "Removing multiple items via cart page", groups = {"regression"})
    @Feature("Remove From Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user can remove multiple items from cart")
    public void removeMultipleItemsFromCartTest() {
        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .goToCart()
                .removeItemFromCart("Sauce Labs Bolt T-Shirt")
                .removeItemFromCart("Sauce Labs Bike Light")
                .removeItemFromCart("Sauce Labs Fleece Jacket");
        Assert.assertTrue(cartPage.isItemRemoved("Sauce Labs Bolt T-Shirt"));
        Assert.assertTrue(cartPage.isItemRemoved("Sauce Labs Bike Light"));
        Assert.assertTrue(cartPage.isItemRemoved("Sauce Labs Fleece Jacket"));
    }

    @Test(description = "Proceeding to checkout info page", groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user can proceed to checkout info page via cart page")
    public void proceedToCheckoutInfoPageTest() {
        productsPage.goToCart().proceedToCheckoutInfoPage();
        Assert.assertEquals(checkoutInfoPage.getPageHeader(), "CHECKOUT: YOUR INFORMATION");
    }

    @Test(description = "Navigate to products page via cart page", groups = {"regression"})
    @Feature("Remove From Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user can navigate back to products page via cart page")
    public void continueShoppingTest() {
        productsPage.addItemToCart("Test.allTheThings() T-Shirt (Red)")
                .goToCart().continueShopping();
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

}
