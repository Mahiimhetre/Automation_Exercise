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

public class TC_Prod_02 {
    private WebDriver driver;
    private Product prod;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = openWeb(readProp("url"));
        prod = new Product(driver);
    }

    @Test(priority = 1, description = "TC_Prod_02 - Verify message when searching for unavailable product")
    public void invProdSearch() throws Exception {
        log().info("TC_Prod_02: Verifying message when searching for unavailable product");
        prod.searchNonAvailableProduct(readProp("invalidProductName"));
        log().info("TC_Prod_02: Successfully verified message when searching for unavailable product");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
