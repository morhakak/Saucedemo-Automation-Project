package pages;

import org.openqa.selenium.WebDriver;

public class SauceLabsPage extends Navbar {

    public SauceLabsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
}
