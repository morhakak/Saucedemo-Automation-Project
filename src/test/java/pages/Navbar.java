package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Constants;
import java.util.List;

public class Navbar extends Footer {

    @FindBy(css = "a.shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(css = "nav a")
    private List<WebElement> menuLinks;


    public Navbar(WebDriver driver) {
        super(driver);
    }

    @Step("Navbar: Click on cart button")
    public CartPage goToCart() {
        click(shoppingCartLink);
        return new CartPage(driver);
    }

    @Step("Navbar: Checking if cart is empty")
    public boolean isCartEmpty() {
        return !driver.getPageSource().contains("shopping_cart_badge");
    }

    @Step("Navbar: Open menu")
    private Navbar openMenu() {
        click(menuButton);
        return new Navbar(driver);
    }

    @Step("Navbar: Click on {menuLink} option")
    public void clickOnMenuOption(Constants menuLink) {
        openMenu();
        switch (menuLink) {
            case ALL_ITEMS -> click(menuLinks.get(0));
            case ABOUT -> click(menuLinks.get(1));
            case LOGOUT -> click(menuLinks.get(2));
            case RESET_APP_STATE -> click(menuLinks.get(3));
        }
    }


}
