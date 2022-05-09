package pages;

import org.openqa.selenium.WebDriver;

public class SauceLabsFacebookPage extends Navbar {

    public SauceLabsFacebookPage(WebDriver driver) {
        super(driver);
    }

    public String getPageUrl() {
        if (waitForNewWindow(driver, 5))
            switchWindows();
        return driver.getCurrentUrl();
    }
}
