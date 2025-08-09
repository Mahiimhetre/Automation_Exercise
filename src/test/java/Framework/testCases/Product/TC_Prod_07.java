package Framework.testCases.Product;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.log;
import static Framework.Functions.common.openWeb;
import static Framework.Functions.common.readProp;
import static Framework.Functions.common.closeWeb;

public class TC_Prod_07 {
    private WebDriver driver;
    private Product prod;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = openWeb(readProp("url"));
        prod = new Product(driver);
    }

    @Test(description = "TC_Prod_07 - Verify handling of invalid quantity when adding to cart")
    public void addInvQuant() throws Exception {
        log().info("TC_Prod_07: Verifying handling of invalid quantity when adding to cart");
        prod.searchProduct(readProp("productName")); // Re-searching to ensure the product is available
        prod.openProductDetails(0); // Open the first product in the search results

        prod.addToCartWithInvalidQuantity(readProp("invalidProdQuantity")); // Assuming the product quantity is invalid
        log().info("TC_Prod_07: Successfully verified handling of invalid quantity when adding to cart");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
