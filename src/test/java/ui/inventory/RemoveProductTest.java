package ui.inventory;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

public class RemoveProductTest extends BaseTest {

    @Test(
            description = "Verify user can remove product from cart",
            groups = {"Smoke", "Regression"}
    )
    public void verifyRemoveProductFromCart() {

        // Arrange
        LoginPage loginPage = new LoginPage();

        // Act
        InventoryPage inventoryPage = loginPage
                .open()
                .loginAsStandardUser();

        String productName = "Sauce Labs Backpack";

        // Add Product
        inventoryPage.addProductToCart(productName);

        Assert.assertEquals(
                inventoryPage.getCartBadgeCount(),
                1,
                "Cart badge should display 1."
        );

        // Remove Product
        inventoryPage.removeProductFromCart(productName);

        // Verify Product Removed
        Assert.assertTrue(
                inventoryPage.isProductRemoved(productName),
                "Product was not removed from cart."
        );

        // Verify Cart Badge Removed
        Assert.assertEquals(
                inventoryPage.getCartBadgeCount(),
                0,
                "Cart badge should disappear after removing product."
        );
    }
}