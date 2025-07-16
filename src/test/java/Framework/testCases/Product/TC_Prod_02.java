package Framework.testCases.Product;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Prod_02 {
    private WebDriver driver;
    private Product prod;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        prod = new Product(driver);
    }

    // Search product with invalid name
    @Test(priority = 0)
    public void invProdSearch() throws Exception{
        common.log().info("Executing TC_Prod_02: Search Product by unavailable Product name...");
        prod.searchNonAvailableProduct(common.readProp("invalidProductName"));
        common.log().info("TC_Prod_02 Search Product by unavailable Product name executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
