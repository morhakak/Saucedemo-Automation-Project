package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SiteConst;

import java.util.List;

public class Footer extends BasePage {

    @FindBy(css = ".social li")
    private List<WebElement> socialList;

    public Footer(WebDriver driver) {
        super(driver);
    }

    public void chooseFooterLink(SiteConst SocialLink) {
        switch (SocialLink) {
            case TWITTER:
                clickOnTwitter();
                break;
            case FACEBOOK:
                clickOnFacebook();
                break;
            case LINKEDIN:
                clickOnLinkedin();
                break;
        }
    }

    private SauceLabsTwitterPage clickOnTwitter() {
        click(socialList.get(0));
        return new SauceLabsTwitterPage(driver);
    }

    private SauceLabsFacebookPage clickOnFacebook() {
        click(socialList.get(1));
        return new SauceLabsFacebookPage(driver);
    }

    private SauceLabsLinkedinPage clickOnLinkedin() {
        click(socialList.get(2));
        return new SauceLabsLinkedinPage(driver);
    }


}
