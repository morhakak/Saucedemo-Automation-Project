package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends Navbar {

    @FindBy(css = "#inventory_item_container button[id^='add']")
    private WebElement addToCartButton;

    @FindBy(css = "#inventory_item_container button[id^='remove']")
    private WebElement removeFromCartButton;

    @FindBy(css = "#inventory_item_container button")
    private WebElement addRemoveButtonCaption;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    @FindBy(css = ".inventory_details_name")
    private WebElement itemTitle;


    public ItemPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on \"ADD TO CART\" button")
    public ItemPage addItemToCart() {
        if (!isItemAdded())
            click(addToCartButton);
        return this;
    }

    @Step("Item page: Click on \"REMOVE\" button")
    public void removeItemFromCart() {
        if (isItemAdded())
            click(removeFromCartButton);
    }

    public String getItemTitle() {
        return getText(itemTitle).trim();
    }

    public boolean isItemAdded() {
        String buttonCaption = addRemoveButtonCaption.getAttribute("id").trim();
        return buttonCaption.contains("remove");
    }

    @Step("Item page: Click on \"BACK TO PRODUCTS\" button")
    public ProductsPage goBackToProductsPage() {
        click(backToProductsButton);
        return new ProductsPage(driver);
    }
}
