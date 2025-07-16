package Framework.Functions;

import Framework.DriverManager;
import Framework.Elements.HomePage;
import Framework.Elements.ProductPage;
import Framework.Elements.SignupPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import static Framework.Functions.common.*;

public class Product {
    private WebDriver driver;
    private HomePage hp;
    private ProductPage pp;

    public Product(WebDriver driver){
        this.driver = driver;
        hp = new HomePage(driver);
        pp = new ProductPage(driver);
    }

    public void searchProduct(String productName) throws Exception {
        hp.prodPage.click();
        log().info("Navigating to Product Page...");
        pp.SearchProd.clear();
        pp.SearchProd.sendKeys(productName);
        pp.searchBtn.click();
        log().info("Searching for product: '" + productName + "'...");
        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Search URL validation failed.");

        Assert.assertTrue(pp.SearchTitle.getText().contains(common.readProp("afterSearchTitle")), "Search results title validation failed.");

        Assert.assertFalse(pp.products.isEmpty(), "No products found for: '" + productName + "'");
    }

    public void searchProductByEmptyName() throws Exception {
        log().info("Navigating to Product Page...");
        hp.prodPage.click();
        log().info("Searching for product by empty name...");
        pp.searchBtn.click();

        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Search URL validation failed.");
        Assert.assertTrue(pp.SearchTitle.getText().contains(common.readProp("beforeSearchTitle")),
                "Search results title validation failed.");
        log().info("Search results title validated successfully...");
    }

    public void searchNonAvailableProduct(String productName) throws Exception {
        log().info("Navigating to Product Page...");
        hp.prodPage.click();

        pp.SearchProd.clear();
        pp.SearchProd.sendKeys(productName);
        pp.searchBtn.click();
        log().info("Searching for invalid product: '" + productName + "'...");

        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Search URL validation failed.");
        Assert.assertTrue(pp.SearchTitle.getText().contains(common.readProp("afterSearchTitle")), "Search results title validation failed.");

        Assert.assertTrue(pp.products.isEmpty(), "Products found for invalid search: '" + productName + "'");
    }

    public void openProductDetails(int productIndex) throws Exception {
        removeAds();
        Assert.assertTrue(productIndex >= 0 && productIndex < pp.products.size(), "Invalid product index.");

        // Scroll to the product and click on it
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", pp.products.get(productIndex));
        pp.products.get(productIndex).click();

        log().info("Opening product details for index: " + (productIndex + 1) );
        Assert.assertTrue(driver.getTitle().contains("Product Details"), "Product details URL validation failed.");
    }

    public void verifyProductDetails(String expectedName, String expectedPrice) throws Exception {
        log().info("Verifying product details...");

        //converting into lower case for case-insensitive comparison
        Assert.assertTrue(common.waitForVisibility(driver,pp.ProductName,2).getText().toLowerCase().contains(expectedName.toLowerCase()), "Product name mismatch.");
        Assert.assertTrue(pp.ProductPrice.getText().contains(expectedPrice), "Product price mismatch.");

        log().info("Product details verified successfully...");
    }

    public void addToCart(String quantity) throws Exception {
        log().info("Adding product to cart...");
        pp.productQuantity.clear();
        pp.productQuantity.sendKeys(String.valueOf(quantity));
        pp.addToCartBtn.click();

        Assert.assertTrue(common.waitForVisibility(driver, hp.closeModal, 5).isDisplayed(),
                "Close modal button is not displayed after adding product to cart.");
        log().info("Product added to cart.");
        hp.closeModal.click();
        log().info("Product added to cart successfully...");
    }

    public void addToCartWithInvalidQuantity(String quantity) throws Exception {
        log().info("Adding product to cart with invalid quantity...");

        pp.productQuantity.clear();
        pp.productQuantity.sendKeys(String.valueOf(quantity));
        pp.addToCartBtn.click();

        Assert.assertFalse(pp.addCardConfirmation.isDisplayed(),
                "Invalid quantity unexpectedly added to cart.");
        log().info("Invalid quantity error displayed successfully...");
    }
}