package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SiteConst;
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
    public void clickOnMenuOption(SiteConst menuLink) {
        switch (menuLink) {
            case ALL_ITEMS:
                openMenu();
                click(menuLinks.get(0));
                break;
            case ABOUT:
                openMenu();
                click(menuLinks.get(1));
                break;
            case LOGOUT:
                openMenu();
                click(menuLinks.get(2));
                break;
            case RESET_APP_STATE:
                openMenu();
                click(menuLinks.get(3));
                break;
        }
    }


}
