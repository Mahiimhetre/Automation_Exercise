package Framework.testCases;

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

    @BeforeClass
    public void preConditions() throws Exception {
        // Initialize WebDriver, navigate to the product page, etc.
        driver = common.openWeb(common.readProp("url")); // Assuming common.getDriver() initializes and returns the WebDriver instance
        prod = new Product();
    }

    @Test
    public void TC001_SearchProduct() throws Exception {
        common.log().info("====TC001 Search Product started====");
        prod.searchProduct(common.readProp("search_product"));
        common.log().info("Search product test executed successfully...");
    }

    @Test
    public void TC002_OpenProductDetails() throws Exception {
        common.log().info("====TC002 Open Product Details started====");
        prod.openSearchedProductDetails(0); // Assuming we want to open the first product in the search results
        common.log().info("Open product details test executed successfully...");
    }

    @Test
    public void TC003_VerifyProductDetails() throws Exception {
        common.log().info("====TC003 Verify Product Details started====");
        prod.verifyProductDetails(common.readProp("expectedProduct"), common.readProp("expectedProductPrice"));
        common.log().info("Verify product details test executed successfully...");
    }


    //add Product to cart with invalid quantity
    @Test
    public void TC004_AddProductToCartWithInvalidQuantity() throws Exception {
        common.log().info("====TC004 Add Product to Cart with Invalid Quantity started====");
        prod.addToCartWithInvalidQuantity(common.readProp("invalidProdQuantity")); // Assuming the product quantity is invalid
        common.log().info("TC004 Test executed successfully...");
    }

    //add Product to cart with valid quantity
    @Test
    public void TC005_AddProductToCart() throws Exception {
        common.log().info("====TC005 Add Product to Cart started====");
        prod.addToCart(common.readProp("productQuantity"));
        common.log().info("TC005 test executed successfully...");
    }



    @AfterMethod
    public void postConditions(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver,result); //capturing ScreenShot if Test Failure.

    }

//    @AfterClass
    public void tearDown() throws Exception {
        common.log().info("====Product Test Completed====");
        // Close the WebDriver or perform any necessary cleanup
        common.closeWeb(driver);
    }
}
