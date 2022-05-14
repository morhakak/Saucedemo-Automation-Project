package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends Navbar {

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingBtn;

    @FindBy(className = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "button[id*='remove']")
    private WebElement removeItemBtn;

    @FindBy(css = "#header_container .title")
    private WebElement cartPageHeader;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartPageHeader() {
        return getText(cartPageHeader);
    }

    @Step("Cart page: Click on \"CONTINUE SHOPPING\" button")
    public ProductsPage continueShopping() {
        click(continueShoppingBtn);
        return new ProductsPage(driver);
    }

    @Step("Cart page: Click on \"CHECKOUT\" button")
    public CheckoutInfoPage proceedToCheckoutInfoPage() {
        click(checkoutBtn);
        return new CheckoutInfoPage(driver);
    }

    @Step("Cart page: Removing {itemName} item from cart")
    public CartPage removeItemFromCart(String itemName) {
        for (WebElement item : cartItems) {
            WebElement itemTitle = item.findElement(By.className(".inventory_item_name"));
            String title = getText(itemTitle);
            if (title.equals(itemName)) {
                click(item.findElement(By.cssSelector("button[id*='remove']")));
                break;
            }
        }
        return this;
    }

    public boolean isItemRemoved(String itemName) {
        for (WebElement item : cartItems) {
            WebElement itemTitle = item.findElement(By.className(".inventory_item_name"));
            String title = getText(itemTitle);
            if (title.equals(itemName)) {
                return false;
            }
        }
        return true;
    }


}
