package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import factory.BrowserFactory;

public abstract class BasePage {

    protected final Page page;

    /**
     * Constructor
     */
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

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    protected void refreshPage() {

        page.reload();

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    protected void goBack() {

        page.goBack();

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    protected void goForward() {

        page.goForward();

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    // ==========================================================
    // Waits
    // ==========================================================

    protected void waitForPageLoad() {

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    protected void waitForElement(Locator locator) {

        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
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

        locator.click();
    }

    protected void doubleClick(Locator locator) {

        waitForElement(locator);

        locator.dblclick();
    }

    protected void rightClick(Locator locator) {

        waitForElement(locator);

        locator.click(
                new Locator.ClickOptions()
                        .setButton(
                                com.microsoft.playwright.options.MouseButton.RIGHT));
    }

    // ==========================================================
    // Text Actions
    // ==========================================================

    protected void type(Locator locator, String text) {

        waitForElement(locator);

        locator.fill(text);
    }

    protected void clear(Locator locator) {

        waitForElement(locator);

        locator.clear();
    }

    protected void appendText(Locator locator, String text) {

        waitForElement(locator);

        locator.pressSequentially(text);
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
    // Mouse
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
    // Getters
    // ==========================================================

    protected String getText(Locator locator) {

        waitForElement(locator);

        return locator.textContent().trim();
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

        return locator.isVisible();
    }

    protected boolean isHidden(Locator locator) {

        return locator.isHidden();
    }

    protected boolean isEnabled(Locator locator) {

        return locator.isEnabled();
    }

    protected boolean isDisabled(Locator locator) {

        return locator.isDisabled();
    }

    protected boolean isChecked(Locator locator) {

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
    // Utility
    // ==========================================================

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