package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;
    protected Actions action;
    protected JavascriptExecutor javascriptExecutor;
    protected WebDriverWait wait;
    protected Select select;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void type(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
        pressOnTab(element);
    }

    public void click(WebElement element) {
        try {
            waitForClickableElement(element);
            element.click();
        } catch (ElementClickInterceptedException e) {
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }

    public String getText(WebElement element) {
        waitForElementToBeVisible(element);
        String displayedText = element.getText();
        String valueText = element.getAttribute("value");
        if (displayedText.isEmpty()) {
            return valueText.trim();
        } else {
            return displayedText.trim();
        }
    }

    public void waitForClickableElement(WebElement element) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void alertOk(String text) {
        driver.switchTo().alert().accept();
    }

    public void alertDismiss() {
        driver.switchTo().alert().dismiss();
    }

    public void setAlertText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

    public void pressOnTab(WebElement element) {
        element.sendKeys(Keys.TAB);
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public void switchWindows() {
        String currentWindow = getWindowHandle();
        Set<String> windowHandles = getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String newWindow = null;

        while (iterator.hasNext()) {
            newWindow = iterator.next();
            if (!currentWindow.equals(newWindow)) {
                driver.switchTo().window(newWindow);
            }
        }
    }

    public boolean waitForNewWindow(WebDriver driver, int timeout) {
        boolean flag = false;
        int counter = 0;
        while (!flag) {
            try {
                Set<String> winId = driver.getWindowHandles();
                if (winId.size() > 1) {
                    flag = true;
                    return flag;
                }
                Thread.sleep(1000);
                counter++;
                if (counter > timeout) {
                    return flag;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return flag;
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void goForward() {
        driver.navigate().forward();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void selectByIndex(WebElement element, int index) {
        select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectByValue(WebElement element, String value) {
        select = new Select(element);
        select.selectByValue(value);
    }

    public void selectByVisibleText(WebElement element, String text) {
        select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void dragAndDropByOffset(WebElement element, int x, int y) {
        action = new Actions(driver);
        action.dragAndDropBy(element, x, y).build().perform();
    }

    public void hover(WebElement element) {
        action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void scrollElementIntoView(WebElement element) {
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView()", element);
    }

    public void scrollPage(int x, int y) {
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    protected void highlightElement(WebElement element, String color) {
        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "border: 1px solid " + color + ";" + originalStyle;

        // Change the style
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);

        // Change the style back after few miliseconds
        javascriptExecutor.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);
    }

    public void takeScreenshot(String screenshotName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./ScreenShots/" + screenshotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeElementScreenshot(WebElement element, String elementName) {
        File sourceFile = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File("./ScreenShots/" + elementName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param frame The index of the frame to switch to (first frame has index 0)
     */
    public void switchFrames(int frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    public void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        driver.manage().addCookie(cookie);
    }

    public Cookie getCookie(String name) {
        return driver.manage().getCookieNamed(name);
    }

}
