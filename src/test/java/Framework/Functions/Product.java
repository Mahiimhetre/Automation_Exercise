package Framework.Functions;

import Framework.Elements.HomePage;
import Framework.Elements.ProductPage;
import org.openqa.selenium.JavascriptExecutor;
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

        Assert.assertTrue( !productPage.products.isEmpty(), "No products found for: '" + productName + "'");
    }

    public void openSearchedProductDetails(int productIndex) throws Exception {
        Assert.assertTrue(productIndex >= 0 && productIndex < productPage.products.size(), "Invalid product index.");

        // Scroll to the product and click on it
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", productPage.products.get(productIndex));
        productPage.products.get(productIndex).click();

        log().info("Opening product details for index: " + productIndex);
        Assert.assertTrue(driver.getTitle().contains("Product Details"), "Product details URL validation failed.");
    }

    public void verifyProductDetails(String expectedName, String expectedPrice) throws Exception {
        log().info("Verifying product details...");

        //converting into lower case for case-insensitive comparison
        Assert.assertTrue(productPage.ProductName.getText().toLowerCase().contains(expectedName.toLowerCase()), "Product name mismatch.");
        Assert.assertTrue(productPage.ProductPrice.getText().contains(expectedPrice), "Product price mismatch.");

        log().info("Product details verified successfully...");
    }

    public void addToCart(String quantity) throws Exception {
        log().info("Adding product to cart...");
        productPage.productQuantity.clear();
        productPage.productQuantity.sendKeys(String.valueOf(quantity));
        productPage.addToCartBtn.click();

        Assert.assertTrue(productPage.addCardConfirmation.isDisplayed(), "Add to cart confirmation not displayed.");
        log().info("Product added to cart successfully...");
    }

    public void addToCartWithInvalidQuantity(String quantity) throws Exception {
        log().info("Adding product to cart with invalid quantity...");

        productPage.productQuantity.clear();
        productPage.productQuantity.sendKeys(String.valueOf(quantity));
        productPage.addToCartBtn.click();

        Assert.assertFalse(productPage.addCardConfirmation.isDisplayed(), "Invalid quantity unexpectedly added to cart.");
        log().info("Invalid quantity error displayed successfully...");
    }
}