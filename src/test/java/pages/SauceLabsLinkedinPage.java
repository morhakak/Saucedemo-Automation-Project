package pages;

import org.openqa.selenium.WebDriver;

public class SauceLabsLinkedinPage extends Navbar {

    public SauceLabsLinkedinPage(WebDriver driver) {
        super(driver);
    }

    public String getPageUrl() {
        if (waitForNewWindow(driver, 5))
            switchWindows();
        return driver.getCurrentUrl();
    }
}
