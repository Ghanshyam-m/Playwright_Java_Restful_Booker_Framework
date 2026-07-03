package ui.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

public class LogoutTest extends BaseTest {

    @Test(
            description = "Verify user can logout successfully",
            groups = {"Smoke", "Regression"}
    )
    public void verifyLogout() {

        // Open Login Page
        LoginPage loginPage = new LoginPage();

        loginPage.open();

        // Login
        InventoryPage inventoryPage = loginPage.loginAsStandardUser();

        // Verify Inventory Page
        Assert.assertTrue(
                inventoryPage.isDisplayed(),
                "Inventory page should be displayed after successful login."
        );

        // Logout
        LoginPage returnedLoginPage = inventoryPage.logout();

        // Verify Login Page
        Assert.assertTrue(
                returnedLoginPage.isDisplayed(),
                "Login page should be displayed after logout."
        );

        Assert.assertTrue(
                returnedLoginPage.isUsernameFieldVisible(),
                "Username field should be visible."
        );

        Assert.assertTrue(
                returnedLoginPage.isPasswordFieldVisible(),
                "Password field should be visible."
        );

        Assert.assertTrue(
                returnedLoginPage.isLoginButtonVisible(),
                "Login button should be visible."
        );

        // Verify URL
        Assert.assertTrue(
                returnedLoginPage.getCurrentPageUrl().contains("saucedemo.com"),
                "Unexpected URL after logout."
        );

        // Verify Page Title
        Assert.assertTrue(
                returnedLoginPage.getPageTitle().contains("Swag"),
                "Unexpected page title."
        );
    }
}