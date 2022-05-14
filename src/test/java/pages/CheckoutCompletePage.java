package pages;

import io.qameta.allure.Step;
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

    @Step("Checkout complete page: Click on \"BACK HOME\" button")
    public ProductsPage clickOnBackHomeBtn() {
        click(backHomeBtn);
        return new ProductsPage(driver);
    }

    @Step("Checkout complete page: Check if order completion succeeded")
    public boolean isOrderCompleted() {
        return backHomeBtn.isDisplayed() && isCartEmpty();
    }

}
