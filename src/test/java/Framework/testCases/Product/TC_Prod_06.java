package Framework.testCases.Product;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

public class TC_Prod_06 {
    private WebDriver driver;
    private Product prod;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        prod = new Product(driver);
    }

    //add Product to cart with valid quantity
    @Test
    public void addValidQuant() throws Exception {
        common.log().info("Executing TC_Prod_07: Add Product to Cart with Valid Quantity...");
        prod.searchProduct(common.readProp("productName")); // Re-searching to ensure the product is available
        prod.openProductDetails(0); // Open the first product in the search results
        prod.addToCart(common.readProp("productQuantity"));
        common.log().info("TC_Prod_07 Add Product to Cart with Valid Quantity executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
