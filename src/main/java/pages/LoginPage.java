package pages;

import com.microsoft.playwright.Locator;

public class LoginPage extends BasePage {

    // ==========================================================
    // Constants
    // ==========================================================

    private static final String LOGIN_URL =
            "https://www.saucedemo.com/";

    // ==========================================================
    // Locators
    // ==========================================================

    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator errorMessage;

    // ==========================================================
    // Constructor
    // ==========================================================

    public LoginPage() {

        super();

        usernameField = page.locator("#user-name");
        passwordField = page.locator("#password");
        loginButton = page.locator("#login-button");
        errorMessage = page.locator("[data-test='error']");
    }

    // ==========================================================
    // Navigation
    // ==========================================================

    /**
     * Opens SauceDemo login page.
     */
    public LoginPage open() {

        navigate(LOGIN_URL);

        waitForPageLoad();

        return this;
    }

    // ==========================================================
    // Login Actions
    // ==========================================================

    /**
     * Enter username.
     */
    public LoginPage enterUsername(String username) {

        type(usernameField, username);

        return this;
    }

    /**
     * Enter password.
     */
    public LoginPage enterPassword(String password) {

        type(passwordField, password);

        return this;
    }

    /**
     * Click Login.
     */
    public InventoryPage clickLogin() {

        click(loginButton);

        return new InventoryPage();
    }

    /**
     * Login with valid credentials.
     */
    public InventoryPage login(String username,
                               String password) {

        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    /**
     * Login expecting failure.
     * Returns LoginPage because the user stays
     * on the login screen.
     */
    public LoginPage loginExpectingFailure(String username,
                                           String password) {

        enterUsername(username);

        enterPassword(password);

        click(loginButton);

        return this;
    }
    // ==========================================================
    // Predefined Login Methods
    // ==========================================================

    /**
     * Login as Standard User
     */
    public InventoryPage loginAsStandardUser() {

        return login("standard_user", "secret_sauce");
    }

    /**
     * Login as Locked Out User
     */
    public LoginPage loginAsLockedOutUser() {

        return loginExpectingFailure(
                "locked_out_user",
                "secret_sauce");
    }

    /**
     * Login as Problem User
     */
    public InventoryPage loginAsProblemUser() {

        return login(
                "problem_user",
                "secret_sauce");
    }

    /**
     * Login as Performance Glitch User
     */
    public InventoryPage loginAsPerformanceGlitchUser() {

        return login(
                "performance_glitch_slow_user",
                "secret_sauce");
    }

    /**
     * Login as Error User
     */
    public InventoryPage loginAsErrorUser() {

        return login(
                "error_user",
                "secret_sauce");
    }

    /**
     * Login as Visual User
     */
    public InventoryPage loginAsVisualUser() {

        return login(
                "visual_user",
                "secret_sauce");
    }

    // ==========================================================
    // Validation Methods
    // ==========================================================

    /**
     * Returns true if Login page is displayed.
     */
    public boolean isDisplayed() {

        return usernameField.isVisible();
    }

    /**
     * Returns true if Username field is visible.
     */
    public boolean isUsernameFieldVisible() {

        return usernameField.isVisible();
    }

    /**
     * Returns true if Password field is visible.
     */
    public boolean isPasswordFieldVisible() {

        return passwordField.isVisible();
    }

    /**
     * Returns true if Login button is visible.
     */
    public boolean isLoginButtonVisible() {

        return loginButton.isVisible();
    }

    /**
     * Returns true if error message is displayed.
     */
    public boolean isErrorDisplayed() {

        return errorMessage.count() > 0
                && errorMessage.isVisible();
    }

    /**
     * Alias used by older test classes.
     */
    public boolean isErrorMessageDisplayed() {

        return isErrorDisplayed();
    }

    /**
     * Returns login error message.
     */
    public String getErrorMessage() {

        if (!isErrorDisplayed()) {
            return "";
        }

        return getText(errorMessage);
    }

    /**
     * Returns true if login page is fully loaded.
     */
    public boolean isLoginPageLoaded() {

        return isDisplayed()
                && isUsernameFieldVisible()
                && isPasswordFieldVisible()
                && isLoginButtonVisible();
    }
    // ==========================================================
    // Utility Methods
    // ==========================================================

    /**
     * Clears username field.
     */
    public LoginPage clearUsername() {

        clear(usernameField);

        return this;
    }

    /**
     * Clears password field.
     */
    public LoginPage clearPassword() {

        clear(passwordField);

        return this;
    }

    /**
     * Clears both username and password fields.
     */
    public LoginPage clearLoginForm() {

        clearUsername();
        clearPassword();

        return this;
    }

    /**
     * Returns entered username.
     */
    public String getEnteredUsername() {

        return getInputValue(usernameField);
    }

    /**
     * Returns entered password.
     */
    public String getEnteredPassword() {

        return getInputValue(passwordField);
    }

    /**
     * Returns page title.
     */
    public String getPageTitle() {

        return page.title();
    }

    /**
     * Returns current URL.
     */
    public String getCurrentPageUrl() {

        return page.url();
    }

    /**
     * Refreshes the Login page.
     */
    public LoginPage refresh() {

        refreshPage();

        return this;
    }

    /**
     * Returns true if username field is empty.
     */
    public boolean isUsernameEmpty() {

        return getInputValue(usernameField).isBlank();
    }

    /**
     * Returns true if password field is empty.
     */
    public boolean isPasswordEmpty() {

        return getInputValue(passwordField).isBlank();
    }

    /**
     * Returns true if both fields are empty.
     */
    public boolean isLoginFormEmpty() {

        return isUsernameEmpty()
                && isPasswordEmpty();
    }

    /**
     * Returns entered username value.
     * (Compatibility method for older tests)
     */
    public String getUsername() {

        return getEnteredUsername();
    }

    /**
     * Returns entered password value.
     * (Compatibility method for older tests)
     */
    public String getPassword() {

        return getEnteredPassword();
    }

    /**
     * Clears username and password fields.
     * (Compatibility method for older tests)
     */
    public LoginPage clearFields() {

        return clearLoginForm();
    }

}