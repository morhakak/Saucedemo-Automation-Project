package pages;

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

    public ItemPage addItemToCart() {
        if (!isItemAdded())
            click(addToCartButton);
        return this;
    }

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

    public ProductsPage goBackToProductsPage() {
        click(backToProductsButton);
        return new ProductsPage(driver);
    }
}
