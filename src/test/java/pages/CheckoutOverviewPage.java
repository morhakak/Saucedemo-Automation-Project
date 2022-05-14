package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CheckoutOverviewPage extends Navbar {

    @FindBy(css = "#header_container .title")
    private WebElement headerTitle;

    @FindBy(css = ".cart_item_label .inventory_item_name")
    private List<WebElement> orderedItemLabelsList;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPricesList;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeaderTitle() {
        return getText(headerTitle);
    }

    public boolean isOrderedItemExist(String OrderedItem) {
        for (WebElement itemName : orderedItemLabelsList) {
            if (getText(itemName).equals(OrderedItem))
                return true;
        }
        return false;
    }

    public String calculateTotalPrice() {
        double totalPriceDouble = 0;
        double itemPriceDouble = 0;
        String totalPriceString;
        String itemPriceString;
        for (WebElement itemPrice : itemPricesList) {
            itemPriceString = getText(itemPrice).replace("$", "");
            itemPriceDouble = Double.parseDouble(itemPriceString);
            totalPriceDouble += itemPriceDouble;
        }
        totalPriceDouble *= 1.08;
        BigDecimal bd = new BigDecimal(totalPriceDouble).setScale(2, RoundingMode.HALF_UP);
        totalPriceString = Double.toString(bd.doubleValue());
        return totalPriceString;
    }

    @Step("Checkout overview page: Click on \"FINISH\" button")
    public CheckoutCompletePage finishOverviewOrder() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }
}
