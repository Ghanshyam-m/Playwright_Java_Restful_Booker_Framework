package ui.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

public class ValidLoginTest extends BaseTest {

    @Test(
            description = "Verify user can login with valid credentials",
            groups = {"Smoke", "Regression"}
    )
    public void verifyValidLogin() {

        LoginPage loginPage = new LoginPage();

        InventoryPage inventoryPage = loginPage
                .open()
                .login(
                        "standard_user",
                        "secret_sauce"
                );

        Assert.assertTrue(
                inventoryPage.isDisplayed(),
                "Inventory page is not displayed."
        );

        Assert.assertEquals(
                inventoryPage.getTitle(),
                "Products",
                "Inventory page title mismatch."
        );

        Assert.assertTrue(
                inventoryPage.getProductCount() > 0,
                "No products displayed."
        );
    }
}