package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public final class WaitUtils {

    private WaitUtils() {
    }

    public static void waitForVisible(Locator locator) {
        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );
    }

    public static void waitForHidden(Locator locator) {
        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.HIDDEN)
        );
    }
}