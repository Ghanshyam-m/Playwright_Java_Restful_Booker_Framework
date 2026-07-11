package pages;

import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.WaitForSelectorState;

import factory.BrowserFactory;

public abstract class BasePage {

    protected final Page page;

    protected BasePage() {

        this.page = BrowserFactory.getPage();

        if (this.page == null) {
            throw new IllegalStateException(
                    "Playwright Page is null. Browser is not initialized.");
        }
    }

    // ==========================================================
    // Navigation
    // ==========================================================

    protected void navigate(String url) {

        page.navigate(url);
        waitForPageLoad();
    }

    protected void refreshPage() {

        page.reload();
        waitForPageLoad();
    }

    protected void goBack() {

        page.goBack();
        waitForPageLoad();
    }

    protected void goForward() {

        page.goForward();
        waitForPageLoad();
    }

    // ==========================================================
    // Wait Methods
    // ==========================================================

    protected void waitForPageLoad() {

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    protected void waitForURL(String urlPattern) {

        page.waitForURL(urlPattern);
    }

    protected void waitForElement(Locator locator) {

        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));

        locator.scrollIntoViewIfNeeded();
    }

    protected void waitForHidden(Locator locator) {

        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.HIDDEN));
    }

    // ==========================================================
    // Click Actions
    // ==========================================================

    protected void click(Locator locator) {

        waitForElement(locator);

        locator.click(
                new Locator.ClickOptions()
                        .setTimeout(10000));
    }

    protected void doubleClick(Locator locator) {

        waitForElement(locator);

        locator.dblclick();
    }

    protected void rightClick(Locator locator) {

        waitForElement(locator);

        locator.click(
                new Locator.ClickOptions()
                        .setButton(MouseButton.RIGHT));
    }

    // ==========================================================
    // Text Actions
    // ==========================================================

    protected void type(Locator locator, String text) {

        waitForElement(locator);

        locator.clear();
        locator.fill(text);
    }

    protected void appendText(Locator locator, String text) {

        waitForElement(locator);

        locator.pressSequentially(text);
    }

    protected void clear(Locator locator) {

        waitForElement(locator);

        locator.clear();
    }

    // ==========================================================
    // Dropdown
    // ==========================================================

    protected void selectByValue(Locator locator, String value) {

        waitForElement(locator);

        locator.selectOption(value);
    }

    protected void selectByLabel(Locator locator, String label) {

        waitForElement(locator);

        locator.selectOption(label);
    }

    // ==========================================================
    // Mouse Actions
    // ==========================================================

    protected void hover(Locator locator) {

        waitForElement(locator);

        locator.hover();
    }

    // ==========================================================
    // Keyboard
    // ==========================================================

    protected void pressKey(Locator locator, String key) {

        waitForElement(locator);

        locator.press(key);
    }

    // ==========================================================
    // Getter Methods
    // ==========================================================

    protected String getText(Locator locator) {

        waitForElement(locator);

        String text = locator.textContent();

        return text == null ? "" : text.trim();
    }

    protected String getInputValue(Locator locator) {

        waitForElement(locator);

        return locator.inputValue();
    }

    protected String getAttribute(Locator locator, String attribute) {

        waitForElement(locator);

        return locator.getAttribute(attribute);
    }

    // ==========================================================
    // Validations
    // ==========================================================

    protected boolean isVisible(Locator locator) {

        waitForElement(locator);

        return locator.isVisible();
    }

    protected boolean isHidden(Locator locator) {

        return locator.isHidden();
    }

    protected boolean isEnabled(Locator locator) {

        waitForElement(locator);

        return locator.isEnabled();
    }

    protected boolean isDisabled(Locator locator) {

        waitForElement(locator);

        return locator.isDisabled();
    }

    protected boolean isChecked(Locator locator) {

        waitForElement(locator);

        return locator.isChecked();
    }

    // ==========================================================
    // Browser Information
    // ==========================================================

    public String getPageTitle() {

        return page.title();
    }

    public String getCurrentUrl() {

        return page.url();
    }

    public Page getPage() {

        return page;
    }

    // ==========================================================
    // Screenshot
    // ==========================================================

    protected void takeScreenshot(String fileName) {

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get("screenshots", fileName + ".png")));
    }

    // ==========================================================
    // Utility
    // ==========================================================

    /**
     * Debug only. Avoid using in production tests.
     */
    protected void pause(int milliseconds) {

        page.waitForTimeout(milliseconds);
    }

    protected int getElementCount(Locator locator) {

        return locator.count();
    }

    protected boolean hasElements(Locator locator) {

        return locator.count() > 0;
    }
}