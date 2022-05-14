package pages;

import io.qameta.allure.Step;
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

    @Step("Checkout info page: Set first name as: {firstName}")
    public CheckoutInfoPage setFirstName(String firstName) {
        type(firstNameInput, firstName);
        return this;
    }

    @Step("Checkout info page: Set last name as: {lastName}")
    public CheckoutInfoPage setLastName(String lastName) {
        type(lastNameInput, lastName);
        return this;
    }

    @Step("Checkout info page: Set zipcode as: {zipCode}")
    public CheckoutInfoPage setZipCode(String zipCode) {
        type(zipCodeInput, zipCode);
        return this;
    }

    @Step("Checkout info page: Click on \"CANCEL\" button")
    public CartPage cancelCheckout() {
        click(cancelBtn);
        return new CartPage(driver);
    }

    @Step("Checkout info page: Click on \"CONTINUE\" button")
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
