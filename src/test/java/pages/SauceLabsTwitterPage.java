package pages;

import org.openqa.selenium.WebDriver;

public class SauceLabsTwitterPage extends Navbar{


    public SauceLabsTwitterPage(WebDriver driver) {
        super(driver);
    }

    public String getPageUrl() {
        if (waitForNewWindow(driver, 5))
            switchWindows();
        return driver.getCurrentUrl();
    }
}
