package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class Listener extends TestListenerAdapter {

    public WebDriver driver;
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
        if (webDriverAttribute instanceof WebDriver) {
            AllureAttachment.attachScreenshot((WebDriver) webDriverAttribute);
            AllureAttachment.attachConsoleLogs((WebDriver) webDriverAttribute);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }


}
