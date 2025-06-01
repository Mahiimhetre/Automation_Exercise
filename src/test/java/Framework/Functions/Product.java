package Framework.Functions;

import Framework.Elements.HomePage;
import Framework.Elements.ProductPage;
import org.testng.Assert;

import static Framework.Functions.common.driver;
import static Framework.Functions.common.log;

public class Product {
    private final HomePage homePage = new HomePage(driver);
    private final ProductPage productPage = new ProductPage(driver);

    public void searchProduct(String productName) throws Exception {
        homePage.prodPage.click();
        log().info("Navigating to Product Page...");
        productPage.SearchProd.clear();
        productPage.SearchProd.sendKeys(productName);
        productPage.searchBtn.click();
        log().info("Searching for product: '" + productName + "'...");
        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Search URL validation failed.");
        Assert.assertTrue(productPage.SearchTitle.getText().contains(common.readProp("afterSearchTitle")), "Search results title validation failed.");
        Assert.assertTrue(productPage.products.size() > 0, "No products found for: '" + productName + "'");
    }

    public void openSearchedProductDetails(int productIndex) throws Exception {
        searchProduct(common.readProp("search_product"));
        Assert.assertTrue(productIndex >= 0 && productIndex < productPage.products.size(), "Invalid product index.");
        productPage.products.get(productIndex).click();
        log().info("Opening product details for index: " + productIndex);
        Assert.assertTrue(driver.getTitle().contains("Product Details"), "Product details URL validation failed.");
    }

    public void verifyProductDetails(String expectedName, String expectedPrice) throws Exception {
        log().info("Verifying product details...");
        Assert.assertEquals(productPage.ProductName.getText(), expectedName, "Product name mismatch.");
        Assert.assertEquals(productPage.ProductPrice.getText(), expectedPrice, "Product price mismatch.");
        log().info("Product details verified successfully...");
    }

    public void addProductToCart(int quantity) throws Exception {
        openSearchedProductDetails(0); // Assuming we want to add the first product in the search results
        log().info("Adding product to cart...");
        productPage.productQuantity.clear();
        productPage.productQuantity.sendKeys(String.valueOf(quantity));
        productPage.addToCartBtn.click();
        Assert.assertTrue(productPage.addCardConfirmation.isDisplayed(), "Add to cart confirmation not displayed.");
        log().info("Product added to cart successfully...");
    }

    public void verifyProductPriceAccordingToQuantity(String expectedPrice) throws Exception {
        log().info("Verifying product price...");
        Assert.assertEquals(productPage.ProductPrice.getText(), expectedPrice, "Product price mismatch.");
        log().info("Product price verified successfully.");
    }
}