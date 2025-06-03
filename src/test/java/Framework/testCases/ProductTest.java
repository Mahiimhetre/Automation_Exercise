package Framework.testCases;

import Framework.Functions.Login;
import Framework.Functions.Product;
import Framework.Functions.common;
import Framework.Functions.ScreenShot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class ProductTest {
    // ProductPage - Search product - View product details - Add to cart - Verify cart confirmation
    WebDriver driver;
    Product prod;

    @BeforeMethod
    public void preConditions() throws Exception {
        // Initialize WebDriver, navigate to the product page, etc.
        driver = common.openWeb(common.readProp("url")); // Assuming common.getDriver() initializes and returns the WebDriver instance
        prod = new Product();
    }
    @Test(priority = 0)
    public void TC_Prod_01() throws Exception {
        common.log().info("====TC_Prod_01 Search Product by Empty Name started====");
        prod.searchProductByEmptyName();
        common.log().info("Test case for Search product with Empty Name executed successfully...");
    }

    // Search product with invalid name
    @Test(priority = 0)
    public void TC_Prod_02() throws Exception{
        common.log().info("====TC_Prod_02 Search Product by non Available Product name started====");
        prod.searchNonAvailableProduct(common.readProp("invalidProductName"));
        common.log().info("Test case for Search Product by non Available Product name executed successfully...");
    }

    // this priority is set to 1 so that it runs After TC_Prod_01/TC_Prod_02  because the other test depends on the product search.
    @Test(priority = 1)
    public void TC_Prod_03() throws Exception {
        common.log().info("====TC_Prod_03 Search Product by Valid Name started====");
        prod.searchProduct(common.readProp("productName"));
        common.log().info("Test case for Search product by valid name executed successfully...");
    }

    @Test(priority = 2)
    public void TC_Prod_04() throws Exception {
        common.log().info("====TC_Prod_04 View Product Details started====");
        prod.openProductDetails(0); // Assuming we want to open the first product in the search results
        common.log().info("Test case to View product details executed successfully...");
    }

    @Test(priority = 3)
    public void TC_Prod_05() throws Exception {
        common.log().info("====TC_Prod_05 Verify Product Details started====");
        prod.searchProduct(common.readProp("productName")); // Re-searching to ensure the product is available
        prod.openProductDetails(0); // Open the first product in the search results

        prod.verifyProductDetails(common.readProp("expectedProduct"), common.readProp("expectedProductPrice"));
        common.log().info("Test case to Verify product details executed successfully...");
    }

    //add Product to cart with invalid quantity
    @Test(priority = 4)
    public void TC_Prod_06() throws Exception {
        common.log().info("====TC_Prod_06 Add Product to Cart with Invalid Quantity started====");
        prod.searchProduct(common.readProp("productName")); // Re-searching to ensure the product is available
        prod.openProductDetails(0); // Open the first product in the search results

        prod.addToCartWithInvalidQuantity(common.readProp("invalidProdQuantity")); // Assuming the product quantity is invalid
        common.log().info("Test to add Product to cart with Invalid Quantity executed successfully...");
    }

    //add Product to cart with valid quantity
    @Test(priority = 5)
    public void TC_Prod_07() throws Exception {
        common.log().info("====TC_Prod_07 Add Product to Cart started====");
        prod.searchProduct(common.readProp("productName")); // Re-searching to ensure the product is available
        prod.openProductDetails(0); // Open the first product in the search results
        prod.addToCart(common.readProp("productQuantity"));
        common.log().info("Test case to add Product to cart executed successfully...");
    }

    @AfterMethod
    public void postConditions(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver,result); //capturing ScreenShot if Test Failure.
        common.closeWeb(driver); // Close the WebDriver instance
    }

}
