package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends Navbar {

    @FindBy(css = "#header_container .title")
    private WebElement pageHeader;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(id = "back-to-products")
    private WebElement backHomeBtn;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return getText(pageHeader);
    }

    public String getCompleteHeader() {
        return getText(completeHeader);
    }

    public ProductsPage clickOnBackHomeBtn() {
        click(backHomeBtn);
        return new ProductsPage(driver);
    }

    public boolean isOrderCompleted() {
        return isCartEmpty();
    }

}
