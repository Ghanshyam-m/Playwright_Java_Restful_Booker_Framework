package factory;

import com.microsoft.playwright.*;

public final class PlaywrightFactory {

    private static final ThreadLocal<Playwright> PLAYWRIGHT =
            new ThreadLocal<>();

    private static final ThreadLocal<Browser> BROWSER =
            new ThreadLocal<>();

    private static final ThreadLocal<BrowserContext> CONTEXT =
            new ThreadLocal<>();

    private static final ThreadLocal<Page> PAGE =
            new ThreadLocal<>();

    private PlaywrightFactory() {
    }

    public static void initBrowser(Browser browser) {

        BROWSER.set(browser);

        CONTEXT.set(browser.newContext());

        PAGE.set(CONTEXT.get().newPage());

    }

    public static Playwright getPlaywright() {

        if (PLAYWRIGHT.get() == null) {

            PLAYWRIGHT.set(Playwright.create());

        }

        return PLAYWRIGHT.get();

    }

    public static Browser getBrowser() {

        return BROWSER.get();

    }

    public static BrowserContext getContext() {

        return CONTEXT.get();

    }

    public static Page getPage() {

        return PAGE.get();

    }

    public static void close() {

        if (PAGE.get() != null) {
            PAGE.get().close();
            PAGE.remove();
        }

        if (CONTEXT.get() != null) {
            CONTEXT.get().close();
            CONTEXT.remove();
        }

        if (BROWSER.get() != null) {
            BROWSER.get().close();
            BROWSER.remove();
        }

        if (PLAYWRIGHT.get() != null) {
            PLAYWRIGHT.get().close();
            PLAYWRIGHT.remove();
        }

    }

}