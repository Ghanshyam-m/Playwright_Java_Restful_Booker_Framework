package ui.inventory;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryValidationTest extends BaseTest {

    @Test(
            description = "Verify Inventory page loads successfully",
            groups = {"Smoke", "Regression"}
    )
    public void verifyInventoryPage() {

        // Arrange
        LoginPage loginPage = new LoginPage();

        // Act
        InventoryPage inventoryPage = loginPage
                .open()
                .loginAsStandardUser();

        // Assert - Inventory page displayed
        Assert.assertTrue(
                inventoryPage.isDisplayed(),
                "Inventory page is not displayed."
        );

        // Assert - Page title
        Assert.assertEquals(
                inventoryPage.getTitle(),
                "Products",
                "Inventory page title mismatch."
        );

        // Assert - Product count
        Assert.assertEquals(
                inventoryPage.getProductCount(),
                6,
                "Unexpected number of products displayed."
        );

        // Assert - Product names loaded
        Assert.assertFalse(
                inventoryPage.getProductNames().isEmpty(),
                "Product names are not displayed."
        );
    }
}