package ui.inventory;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

public class ProductSortTest extends BaseTest {

    @Test(
            description = "Verify all product sorting options",
            groups = {"Regression"}
    )
    public void verifyProductSorting() {

        LoginPage loginPage = new LoginPage();

        InventoryPage inventoryPage = loginPage
                .open()
                .loginAsStandardUser();

        // Name A-Z
        inventoryPage.sortByNameAscending();

        Assert.assertTrue(
                inventoryPage.areProductsSortedAscending(),
                "Products are not sorted A-Z."
        );

        // Name Z-A
        inventoryPage.sortByNameDescending();

        Assert.assertTrue(
                inventoryPage.areProductsSortedDescending(),
                "Products are not sorted Z-A."
        );

        // Price Low-High
        inventoryPage.sortByPriceLowToHigh();

        Assert.assertTrue(
                inventoryPage.arePricesSortedLowToHigh(),
                "Prices are not sorted Low-High."
        );

        // Price High-Low
        inventoryPage.sortByPriceHighToLow();

        Assert.assertTrue(
                inventoryPage.arePricesSortedHighToLow(),
                "Prices are not sorted High-Low."
        );
    }
}