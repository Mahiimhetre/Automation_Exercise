package Framework.testCases.Product;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Prod_05 {
    private WebDriver driver;
    private Product prod;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        prod = new Product(driver);
    }

    @Test
    public void prodDetailVerification() throws Exception {
        common.log().info("Executing TC_Prod_05: Verify product details...");
        prod.searchProduct(common.readProp("productName")); // Re-searching to ensure the product is available
        prod.openProductDetails(0); // Open the first product in the search results

        prod.verifyProductDetails(common.readProp("expectedProduct"), common.readProp("expectedProductPrice"));
        common.log().info("TC_Prod_05 Verify product details executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
