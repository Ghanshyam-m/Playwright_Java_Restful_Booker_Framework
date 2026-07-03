package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import factory.BrowserFactory;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        BrowserFactory.initializeBrowser();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        BrowserFactory.closeBrowser();
    }
}