package Framework.testCases.CheckOut;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Checkout_01 {
    private WebDriver driver;
    private CheckOut cout;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cout = new CheckOut(driver);
    }

    @Test
    public void checkout() throws Exception {
        log().info("Executing TC_Checkout_01: Checkout with user login...");
        cout.proceedToCheckout();
        log().info("TC_Checkout_01: Checkout with user login executed Successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
