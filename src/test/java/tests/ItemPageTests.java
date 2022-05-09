package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemPageTests extends BaseTest {

    @Test(description = "Adding item to cart via item page", groups = {"sanity", "regression"})
    @Feature("Add To Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that items can be added to cart via item page")
    public void addToCartTest() {
        productsPage.clickOnItem("Sauce Labs Fleece Jacket").addItemToCart();
        Assert.assertTrue(itemPage.isItemAdded());
    }

    @Test(description = "Removing item from cart via item page", groups = {"sanity", "regression"})
    @Feature("Remove From Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that items can be removed from cart via item page")
    public void removeFromCartTest() {
        productsPage.clickOnItem("Sauce Labs Fleece Jacket").addItemToCart().removeItemFromCart();
        Assert.assertFalse(itemPage.isItemAdded());
    }

    @Test(description = "Go back to products page", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that user can go back to products page via item page")
    public void navigateBackToProductsPageTest() {
        productsPage.clickOnItem("Sauce Labs Fleece Jacket").goBackToProductsPage();
        Assert.assertTrue(productsPage.isOnProductsPage());
    }
}
