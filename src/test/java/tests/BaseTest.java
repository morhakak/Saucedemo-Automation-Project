package tests;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.*;
import utils.ReadFromProperties;
import utils.SiteConst;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;

    public Navbar navbar;

    public ProductsPage productsPage;
    public ItemPage itemPage;
    public CartPage cartPage;
    public CheckoutInfoPage checkoutInfoPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    public CheckoutCompletePage checkoutCompletePage;
    public SauceLabsPage saucelabsPage;
    public Footer footer;
    public SauceLabsTwitterPage sauceLabsTwitterPage;
    public SauceLabsFacebookPage sauceLabsFacebookPage;
    public SauceLabsLinkedinPage sauceLabsLinkedinPage;
    WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void setup(ITestContext testContext) {

        if (driver == null) {
            String browser = ReadFromProperties.readProperty("browser");
            if ("firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("--headless");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
            }
        }
        testContext.setAttribute("WebDriver", this.driver);
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser.Version", ((RemoteWebDriver) driver).getCapabilities().getBrowserVersion())
                        .put("Browser", ((RemoteWebDriver) driver).getCapabilities().getBrowserName())
                        .put("URL", ReadFromProperties.readProperty("url"))
                        .build());
        driver.get(ReadFromProperties.readProperty("url"));
        driver.manage().window().maximize();
        initPages();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (!this.getClass().getCanonicalName().contains("LoginPageTests") && !driver.findElements(By.id("login-button")).isEmpty()) {
            initialLogin();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @BeforeMethod(alwaysRun = true)
    // If class name is LoginPageTests -> navigate to login page
    // else navigate to products page
    public void beforeEveryTestMethod() {
        if (!this.getClass().getCanonicalName().equals("LoginPageTests") && driver.findElements(By.id("login-button")).isEmpty()) {
            driver.get("https://www.saucedemo.com/inventory.html");
            wait.until(ExpectedConditions.visibilityOf(productsPage.getProductsPageTitleWebElement()));
        } else {
            goToBaseUrl();
        }
    }

    @AfterMethod(alwaysRun = true)
    //If class name is not LoginPageTests -> Reset app state
    public void reset() {
        if (!this.getClass().getCanonicalName().equals("LoginPageTests") && driver.findElements(By.id("login-button")).isEmpty()) {
            driver.get("https://www.saucedemo.com/inventory.html");
            wait.until(ExpectedConditions.visibilityOf(productsPage.getProductsPageTitleWebElement()));
            resetAppState();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshotAfterTestFailure(ITestResult result) {
        takeScreenshot(result);
    }

    private void initPages() {
        loginPage = new LoginPage(driver);
        navbar = new Navbar(driver);
        productsPage = new ProductsPage(driver);
        itemPage = new ItemPage(driver);
        cartPage = new CartPage(driver);
        checkoutInfoPage = new CheckoutInfoPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        saucelabsPage = new SauceLabsPage(driver);
        footer = new Footer(driver);
        sauceLabsTwitterPage = new SauceLabsTwitterPage(driver);
        sauceLabsFacebookPage = new SauceLabsFacebookPage(driver);
        sauceLabsLinkedinPage = new SauceLabsLinkedinPage(driver);
    }

    protected ProductsPage initialLogin() {
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginButton()));
        loginPage.login("standard_user", "secret_sauce");
        return new ProductsPage(driver);
    }

    private void resetAppState() {
        navbar.clickOnMenuOption(SiteConst.RESET_APP_STATE);
    }

    protected void goToBaseUrl() {
        driver.get(ReadFromProperties.readProperty("url"));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginButton()));
    }

    public void takeScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("./screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
