package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends Navbar {


    @FindBy(className = "title")
    private WebElement pageHeader;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement zipCodeInput;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    @FindBy(xpath = "//h3[contains(text(),'First Name')]")
    private WebElement firstNameErrorMsg;

    @FindBy(xpath = "//h3[contains(text(),'Last Name')]")
    private WebElement lastNameErrorMsg;

    @FindBy(xpath = "//h3[contains(text(),'Postal')]")
    private WebElement zipCodeErrorMsg;

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return getText(pageHeader);
    }

    public CheckoutInfoPage setFirstName(String firstName) {
        type(firstNameInput, firstName);
        return this;
    }

    public CheckoutInfoPage setLastName(String lastName) {
        type(lastNameInput, lastName);
        return this;
    }

    public CheckoutInfoPage setZipCode(String zipCode) {
        type(zipCodeInput, zipCode);
        return this;
    }

    public CartPage cancelCheckout() {
        click(cancelBtn);
        return new CartPage(driver);
    }

    public CheckoutOverviewPage proceedToOverviewPage() {
        click(continueBtn);
        return new CheckoutOverviewPage(driver);
    }

    public String getFirstNameErrorMsg() {
        return getText(firstNameErrorMsg);
    }

    public String getLastNameErrorMsg() {
        return getText(lastNameErrorMsg);
    }

    public String getZipCodeErrorMsg() {
        return getText(zipCodeErrorMsg);
    }


}
