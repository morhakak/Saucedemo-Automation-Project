package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends Navbar {


    @FindBy(css = "div.header_container span.title")
    private WebElement productsPageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(css = "select.product_sort_container")
    private WebElement sortSelector;

    @FindBy(className = "active_option")
    private WebElement activeOptionSelected;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemsPrices;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemsNames;


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProductsPageTitleWebElement() {
        return productsPageTitle;
    }

    public boolean isOnProductsPage() {
        return productsPageTitle.isDisplayed();
    }

    @Step("Products page: Click on {itemName} item")
    public ItemPage clickOnItem(String itemName) {
        for (WebElement item : inventoryItems) {
            WebElement itemTitle = item.findElement(By.cssSelector(".inventory_item_name"));
            if (getText(itemTitle).equals(itemName)) {
                WebElement productLink = item.findElement(By.cssSelector("a[id*='title_link']"));
                click(productLink);
                break;
            }
        }
        return new ItemPage(driver);
    }

    @Step("Products page: Add {itemName} to cart")
    public ProductsPage addItemToCart(String itemName) {
        for (WebElement item : inventoryItems) {
            WebElement itemTitle = item.findElement(By.cssSelector(".inventory_item_name"));
            if (getText(itemTitle).equals(itemName)) {
                click(item.findElement(By.cssSelector("button[id*='add-to-cart']")));
                break;
            }
        }
        return new ProductsPage(driver);
    }

    @Step("Products page: Remove {itemName} from cart")
    public ProductsPage removeItemFromCart(String itemName) {
        if (isItemAdded(itemName)) {
            for (WebElement item : inventoryItems) {
                WebElement itemTitle = item.findElement(By.cssSelector(".inventory_item_name"));
                if (getText(itemTitle).equals(itemName)) {
                    click(item.findElement(By.cssSelector("button[id^='remove']")));
                    break;
                }
            }
            return new ProductsPage(driver);
        }
        throw new IllegalStateException(itemName + " item was not added to cart");
    }

    public boolean isItemAdded(String itemName) {
        for (WebElement item : inventoryItems) {
            WebElement itemTitle = item.findElement(By.cssSelector(".inventory_item_name"));
            if (getText(itemTitle).equals(itemName)) {
                String buttonCaption = item.findElement(By.tagName("button")).getAttribute("id").trim();
                if (buttonCaption.contains("remove"))
                    return true;
                break;
            }
        }
        return false;
    }

    @Step("Products page: Sort items using {sortOption} option")
    public void sortItems(Constants sortOption) {
        switch (sortOption) {
            case NAME_A_TO_Z:
                selectByValue(sortSelector, "az");
                break;
            case NAME_Z_TO_A:
                selectByValue(sortSelector, "za");
                break;
            case PRICE_LOW_TO_HIGH:
                selectByValue(sortSelector, "lohi");
                break;
            case PRICE_HIGH_TO_LOW:
                selectByValue(sortSelector, "hilo");
                break;
        }
    }

    private List<Double> convertPricesToDoubles() {
        List<Double> itemsPricesDouble = new ArrayList<>();
        if (!itemsPrices.isEmpty()) {
            for (WebElement itemsPrice : itemsPrices) {
                String priceString = getText(itemsPrice).replace("$", "");
                double priceDouble = Double.parseDouble(priceString);
                itemsPricesDouble.add(priceDouble);
            }
        }
        return itemsPricesDouble;
    }

    public boolean isPricesSortedFromHighToLow() {
        List<Double> list = convertPricesToDoubles();
        boolean isHigher = false;
        for (int i = 0; i < list.size() - 1; i++) {
            isHigher = list.get(i) >= list.get(i + 1);
        }
        return isHigher;
    }

    private List<String> convertWebElToString() {
        List<String> listOfStrings = new ArrayList<>();
        if (!itemsNames.isEmpty()) {
            for (WebElement el : itemsNames) {
                String name = getText(el);
                listOfStrings.add(name);
            }
        }
        return listOfStrings;
    }

    public boolean isSortedFromAToZ() {
        List<String> list = convertWebElToString();
        String previous = "";
        for (String current : list) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    public boolean isSortedFromZToA() {
        List<String> list = convertWebElToString();
        String previous = list.get(0);
        for (String current : list) {
            if (current.compareTo(previous) >= 0)
                return false;
            previous = current;
        }
        return true;
    }

}