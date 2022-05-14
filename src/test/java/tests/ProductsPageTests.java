package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constants;

public class ProductsPageTests extends BaseTest {

    @Test(description = "Add to cart - Single item", groups = {"sanity", "regression"})
    @Feature("Add To Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Adding single item to cart")
    public void addSingleItemToCartTest() {
        productsPage.addItemToCart("Test.allTheThings() T-Shirt (Red)");
        Assert.assertTrue(productsPage.isItemAdded("Test.allTheThings() T-Shirt (Red)"));
    }

    @Test(description = "Remove from cart - Single item", groups = {"sanity", "regression"})
    @Feature("Remove From Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Removing single item from cart")
    public void removeSingleItemFromCartTest() {
        productsPage.addItemToCart("Sauce Labs Fleece Jacket")
                .removeItemFromCart("Sauce Labs Fleece Jacket");
        Assert.assertFalse(productsPage.isItemAdded("Sauce Labs Fleece Jacket"));
    }

    @Test(description = "Add to cart - Multiple items", groups = {"sanity", "regression"})
    @Feature("Add To Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Adding multiple items to cart")
    public void addMultipleItemsToCartTest() {
        productsPage.addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Onesie");
        Assert.assertTrue(productsPage.isItemAdded("Sauce Labs Fleece Jacket"));
        Assert.assertTrue(productsPage.isItemAdded("Sauce Labs Bolt T-Shirt"));
        Assert.assertTrue(productsPage.isItemAdded("Sauce Labs Onesie"));
    }

    @Test(description = "Remove from cart - Multiple items", groups = {"sanity", "regression"})
    @Feature("Remove From Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Removing multiple items from cart")
    public void removeMultipleItemsFromCartTest() {
        productsPage.addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Onesie")
                .removeItemFromCart("Sauce Labs Fleece Jacket")
                .removeItemFromCart("Sauce Labs Bolt T-Shirt")
                .removeItemFromCart("Sauce Labs Onesie");
        Assert.assertFalse(productsPage.isItemAdded("Sauce Labs Fleece Jacket"));
        Assert.assertFalse(productsPage.isItemAdded("Sauce Labs Bolt T-Shirt"));
        Assert.assertFalse(productsPage.isItemAdded("Sauce Labs Onesie"));
    }

    @Test(description = "Go back to products page", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Go back to products page")
    public void goToProductPageTest() {
        productsPage.clickOnItem("Sauce Labs Fleece Jacket");
        Assert.assertEquals(itemPage.getItemTitle(), "Sauce Labs Fleece Jacket");
    }

    @Test(description = "Sorting products by price - High to low", groups = {"regression"})
    @Feature("Sorting Products")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that products are sorted by price from highest to lowest")
    public void isSortedFromHighToLowTest() {
        productsPage.sortItems(Constants.PRICE_HIGH_TO_LOW);
        Assert.assertTrue(productsPage.isPricesSortedFromHighToLow());
    }

    @Test(description = "Sorting products by price - Low to high", groups = {"regression"})
    @Feature("Sorting Products")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the products are sorted by price from lowest to highest")
    public void isSortedFromLowToHighTest() {
        productsPage.sortItems(Constants.PRICE_LOW_TO_HIGH);
        Assert.assertFalse(productsPage.isPricesSortedFromHighToLow());
    }

    @Test(description = "Sorting products alphabetically - A to Z", groups = {"regression"})
    @Feature("Sorting Products")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that products are sorted alphabetically from A to Z")
    public void isSortedFromAToZTest() {
        productsPage.sortItems(Constants.NAME_A_TO_Z);
        Assert.assertTrue(productsPage.isSortedFromAToZ());
    }

    @Test(description = "Sorting products alphabetically - Z to A", groups = {"regression"})
    @Feature("Sorting Products")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that products are sorted alphabetically from Z to A")
    public void isSortedFromZToATest() {
        productsPage.sortItems(Constants.NAME_Z_TO_A);
        Assert.assertFalse(productsPage.isSortedFromZToA());
    }


}
