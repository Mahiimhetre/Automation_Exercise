package Framework.testCases.CheckOut;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Checkout_03 {
    private WebDriver driver;
    private CheckOut cout;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cout = new CheckOut(driver);
    }

    @Test
    public void invalidPayment() throws Exception {
        log().info("Executing TC_Checkout_03: PLace Order with invalid payment details...");
        cout.proceedToCheckout();
        cout.placeOrder();
        cout.invalidPaymentDetails();
        log().info("TC_Checkout_03: Place Order with invalid payment details executed Successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
