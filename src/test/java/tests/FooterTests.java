package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constants;

@Feature("Social Media")
public class FooterTests extends BaseTest {

    @Test(description = "Go to Sauce Labs twitter page via footer test", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the user can navigate to Sauce Labs twitter page via footer")
    public void clickOnTwitterTest() {
        productsPage.chooseFooterLink(Constants.TWITTER);
        Assert.assertTrue(sauceLabsTwitterPage.getPageUrl().contains("twitter"));
    }

    @Test(description = "Go to Sauce Labs facebook page via footer test", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the user can navigate to Sauce Labs facebook page via footer")
    public void clickOnFacebookTest() {
        productsPage.chooseFooterLink(Constants.FACEBOOK);
        Assert.assertTrue(sauceLabsFacebookPage.getPageUrl().contains("facebook"));
    }

    @Test(description = "Go to Sauce Labs linkedin page via footer test", groups = {"sanity", "regression"})
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the user can navigate to Sauce Labs linkedin page via footer")
    public void clickOnLinkedinTest() {
        productsPage.chooseFooterLink(Constants.LINKEDIN);
        Assert.assertTrue(sauceLabsLinkedinPage.getPageUrl().contains("linkedin"));
    }
}
