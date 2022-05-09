package pages;

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

    public CartPage goToCart() {
        click(shoppingCartLink);
        return new CartPage(driver);
    }

    public boolean isCartEmpty() {
        return !driver.getPageSource().contains("shopping_cart_badge");
    }

    private Navbar openMenu() {
        waitAndClick(menuButton);
        return new Navbar(driver);
    }

    public void clickOnMenuOption(SiteConst menuLink) {
        switch (menuLink) {
            case ALL_ITEMS:
                openMenu();
                waitAndClick(menuLinks.get(0));
                break;
            case ABOUT:
                openMenu();
                waitAndClick(menuLinks.get(1));
                break;
            case LOGOUT:
                openMenu();
                waitAndClick(menuLinks.get(2));
                break;
            case RESET_APP_STATE:
                openMenu();
                waitAndClick(menuLinks.get(3));
                break;
        }
    }


}
