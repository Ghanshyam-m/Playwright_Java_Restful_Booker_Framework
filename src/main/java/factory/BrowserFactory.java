package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;

public final class BrowserFactory {

    private BrowserFactory() {
    }

    public static void initializeBrowser() {

        Browser browser =
                PlaywrightFactory
                        .getPlaywright()
                        .chromium()
                        .launch(
                                new BrowserType.LaunchOptions()
                                        .setHeadless(false)
                        );

        PlaywrightFactory.initBrowser(browser);
    }

    public static Page getPage() {

        return PlaywrightFactory.getPage();
    }

    public static void closeBrowser() {

        PlaywrightFactory.close();
    }
}