package ui.inventory;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

public class ProductCountTest extends BaseTest {

    @Test(
            description = "Verify product count, names and prices",
            groups = {"Smoke", "Regression"}
    )
    public void verifyProductCount() {

        // Arrange
        LoginPage loginPage = new LoginPage();

        // Act
        InventoryPage inventoryPage = loginPage
                .open()
                .loginAsStandardUser();

        // Verify Inventory Page
        Assert.assertTrue(
                inventoryPage.isDisplayed(),
                "Inventory page is not displayed."
        );

        // Verify Product Count
        Assert.assertEquals(
                inventoryPage.getProductCount(),
                6,
                "Unexpected product count."
        );

        // Verify Product Names
        List<String> productNames = inventoryPage.getProductNames();

        Assert.assertEquals(
                productNames.size(),
                6,
                "Product names count mismatch."
        );

        for (String product : productNames) {

            Assert.assertFalse(
                    product.trim().isEmpty(),
                    "Product name should not be empty."
            );
        }

        // Verify Product Prices
        List<Double> prices = inventoryPage.getProductPricesAsDouble();

        Assert.assertEquals(
                prices.size(),
                6,
                "Product prices count mismatch."
        );

        for (Double price : prices) {

            Assert.assertTrue(
                    price > 0,
                    "Invalid product price: " + price
            );
        }
    }
}