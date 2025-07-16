package Framework.testCases.CheckOut;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Checkout_02 {
    private WebDriver driver;
    private CheckOut cout;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cout = new CheckOut(driver);
    }

    @Test
    public void validPayment() throws Exception {
        log().info("Executing TC_Checkout_02: Place Order with valid payment details...");
        cout.proceedToCheckout();
        cout.placeOrder();
        cout.paymentDetails();
        // Additional assertions can be added here to verify order confirmation
        log().info("TC_Checkout_02: Place Order with valid payment details executed Successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
