package ui.inventory;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

public class AddProductTest extends BaseTest {

    @Test(
            description = "Verify user can add a product to cart",
            groups = {"Smoke", "Regression"}
    )
    public void verifyAddProductToCart() {

        // Arrange
        LoginPage loginPage = new LoginPage();

        // Act
        InventoryPage inventoryPage = loginPage
                .open()
                .loginAsStandardUser();

        String productName = "Sauce Labs Backpack";

        inventoryPage.addProductToCart(productName);

        // Assert - Button changed to Remove
        Assert.assertTrue(
                inventoryPage.isProductAdded(productName),
                "Product was not added to cart."
        );

        // Assert - Cart badge count
        Assert.assertEquals(
                inventoryPage.getCartBadgeCount(),
                1,
                "Cart badge count is incorrect."
        );
    }
}