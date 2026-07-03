package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.microsoft.playwright.Locator;

public class InventoryPage extends BasePage {

    // ==========================================================
    // Locators
    // ==========================================================

    private final Locator inventoryTitle;
    private final Locator productSortDropdown;

    private final Locator inventoryItems;
    private final Locator productNames;
    private final Locator productPrices;

    private final Locator addToCartButtons;
    private final Locator removeButtons;

    private final Locator cartBadge;
    private final Locator cartLink;

    private final Locator menuButton;
    private final Locator logoutLink;

    // ==========================================================
    // Constructor
    // ==========================================================

    public InventoryPage() {

        super();

        inventoryTitle =
                page.locator(".title");

        productSortDropdown =
                page.locator("[data-test='product-sort-container']");

        inventoryItems =
                page.locator(".inventory_item");

        productNames =
                page.locator(".inventory_item_name");

        productPrices =
                page.locator(".inventory_item_price");

        addToCartButtons =
                page.locator("button:has-text('Add to cart')");

        removeButtons =
                page.locator("button:has-text('Remove')");

        cartBadge =
                page.locator(".shopping_cart_badge");

        cartLink =
                page.locator(".shopping_cart_link");

        menuButton =
                page.locator("#react-burger-menu-btn");

        logoutLink =
                page.locator("#logout_sidebar_link");
    }

    // ==========================================================
    // Page Validation
    // ==========================================================

    /**
     * Returns true if Inventory page is displayed.
     */
    public boolean isDisplayed() {

        return inventoryTitle.isVisible();
    }

    /**
     * Compatibility method.
     */
    public boolean isLoaded() {

        return isDisplayed();
    }

    /**
     * Compatibility method.
     */
    public boolean isInventoryPageLoaded() {

        return isDisplayed();
    }

    /**
     * Returns Inventory page title.
     */
    public String getTitle() {

        return getText(inventoryTitle);
    }

    /**
     * Returns browser page title.
     */
    public String getCurrentPageTitle() {

        return page.title();
    }

    /**
     * Compatibility method.
     */
    public String getPageTitle() {

        return getCurrentPageTitle();
    }

    /**
     * Returns current URL.
     */
    public String getCurrentPageUrl() {

        return page.url();
    }

    // ==========================================================
    // Product Information
    // ==========================================================

    /**
     * Returns total number of products.
     */
    public int getProductCount() {

        return inventoryItems.count();
    }

    /**
     * Returns true if products are displayed.
     */
    public boolean isProductDisplayed() {

        return inventoryItems.count() > 0;
    }

    /**
     * Returns all product names.
     */
    public List<String> getProductNames() {

        return productNames
                .allTextContents()
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Returns all product prices as String.
     */
    public List<String> getProductPrices() {

        return productPrices
                .allTextContents()
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Returns all product prices as Double.
     */
    public List<Double> getProductPricesAsDouble() {

        List<Double> prices = new ArrayList<>();

        for (String price : getProductPrices()) {

            prices.add(
                    Double.parseDouble(
                            price.replace("$", "")
                    )
            );
        }

        return prices;
    }
    // ==========================================================
    // Sorting
    // ==========================================================

    /**
     * Sort products by Name (A to Z).
     */
    public InventoryPage sortByNameAscending() {

        selectByValue(productSortDropdown, "az");

        waitForPageLoad();

        return this;
    }

    /**
     * Sort products by Name (Z to A).
     */
    public InventoryPage sortByNameDescending() {

        selectByValue(productSortDropdown, "za");

        waitForPageLoad();

        return this;
    }

    /**
     * Sort products by Price (Low to High).
     */
    public InventoryPage sortByPriceLowToHigh() {

        selectByValue(productSortDropdown, "lohi");

        waitForPageLoad();

        return this;
    }

    /**
     * Sort products by Price (High to Low).
     */
    public InventoryPage sortByPriceHighToLow() {

        selectByValue(productSortDropdown, "hilo");

        waitForPageLoad();

        return this;
    }

    // ==========================================================
    // Sorting Validation
    // ==========================================================

    /**
     * Returns true if products are sorted A → Z.
     */
    public boolean areProductsSortedAscending() {

        List<String> actual = getProductNames();

        List<String> expected = new ArrayList<>(actual);

        Collections.sort(expected);

        return actual.equals(expected);
    }

    /**
     * Returns true if products are sorted Z → A.
     */
    public boolean areProductsSortedDescending() {

        List<String> actual = getProductNames();

        List<String> expected = new ArrayList<>(actual);

        expected.sort(Collections.reverseOrder());

        return actual.equals(expected);
    }

    /**
     * Returns true if prices are sorted Low → High.
     */
    public boolean arePricesSortedLowToHigh() {

        List<Double> actual = getProductPricesAsDouble();

        List<Double> expected = new ArrayList<>(actual);

        Collections.sort(expected);

        return actual.equals(expected);
    }

    /**
     * Returns true if prices are sorted High → Low.
     */
    public boolean arePricesSortedHighToLow() {

        List<Double> actual = getProductPricesAsDouble();

        List<Double> expected = new ArrayList<>(actual);

        expected.sort(Collections.reverseOrder());

        return actual.equals(expected);
    }

    // ==========================================================
    // Cart Operations
    // ==========================================================

    /**
     * Add a product to the cart.
     */
    public InventoryPage addProductToCart(String productName) {

        page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName))
                .locator("button")
                .click();

        return this;
    }
    /**
     * Removes a specific product from the cart.
     */
    public InventoryPage removeProductFromCart(String productName) {

        page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName))
                .locator("button")
                .click();

        return this;
    }
    /**
     * Returns true if the specified product has been added to the cart.
     */
    public boolean isProductAdded(String productName) {

        return page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName))
                .locator("button:has-text('Remove')")
                .isVisible();
    }

    /**
    /**
     * Add all available products to the cart.
     */
    public InventoryPage addAllProducts() {

        while (addToCartButtons.count() > 0) {
            addToCartButtons.nth(0).click();
        }

        return this;
    }

    /**
     * Remove all products from the cart.
     */
    public InventoryPage removeAllProducts() {

        while (removeButtons.count() > 0) {
            removeButtons.nth(0).click();
        }

        return this;
    }

    /**
     * Returns true if the specified product has been removed.
     */
    public boolean isProductRemoved(String productName) {

        return page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName))
                .locator("button:has-text('Add to cart')")
                .isVisible();
    }

    /**
     * Returns the number displayed on the cart badge.
     */
    public int getCartBadgeCount() {

        if (cartBadge.count() == 0) {
            return 0;
        }

        return Integer.parseInt(cartBadge.textContent().trim());
    }

    /**
     * Returns true if the cart badge is displayed.
     */
    public boolean isCartBadgeDisplayed() {

        return cartBadge.count() > 0
                && cartBadge.isVisible();
    }

    /**
     * Returns true if the cart icon is visible.
     */
    public boolean isCartVisible() {

        return cartLink.isVisible();
    }

    // ==========================================================
    // Navigation
    // ==========================================================

    /**
     * Opens the shopping cart.
     */
    public CartPage openCart() {

        click(cartLink);

        return new CartPage();
    }

    /**
     * Compatibility method.
     */
    public CartPage goToCart() {

        return openCart();
    }

    /**
     * Logout from the application.
     */
    public LoginPage logout() {

        click(menuButton);

        logoutLink.waitFor();

        click(logoutLink);

        return new LoginPage();
    }

    // ==========================================================
    // Utility Methods
    // ==========================================================

    /**
     * Returns true if products are available.
     */
    public boolean hasProducts() {

        return inventoryItems.count() > 0;
    }

    /**
     * Returns the lowest product price.
     */
    public double getLowestPrice() {

        return Collections.min(getProductPricesAsDouble());
    }

    /**
     * Returns the highest product price.
     */
    public double getHighestPrice() {

        return Collections.max(getProductPricesAsDouble());
    }

    /**
     * Returns the average product price.
     */
    public double getAveragePrice() {

        List<Double> prices = getProductPricesAsDouble();

        if (prices.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Double price : prices) {
            total += price;
        }

        return total / prices.size();
    }

}