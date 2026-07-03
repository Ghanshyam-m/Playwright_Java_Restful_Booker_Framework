package utils;

import com.microsoft.playwright.Locator;

public final class PageActions {

    private PageActions() {
    }

    public static void click(Locator locator) {

        WaitUtils.waitForVisible(locator);

        locator.click();
    }

    public static void type(Locator locator,
                            String value) {

        WaitUtils.waitForVisible(locator);

        locator.fill(value);
    }

    public static String getText(Locator locator) {

        WaitUtils.waitForVisible(locator);

        return locator.textContent();
    }

    public static boolean isVisible(Locator locator) {

        return locator.isVisible();
    }

    public static void clear(Locator locator) {

        locator.clear();
    }
}