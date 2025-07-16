package Framework.testCases.Product;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Prod_03 {
    private WebDriver driver;
    private Product prod;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        prod = new Product(driver);
    }

    // this priority is set to 1 so that it runs After TC_Prod_01/TC_Prod_02  because the other test depends on the product search.
    @Test
    public void SearchProd() throws Exception {
        common.log().info("Executing TC_Prod_03: Search Available Product name...");
        prod.searchProduct(common.readProp("productName"));
        common.log().info("TC_Prod_03 Search Available Product name executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
