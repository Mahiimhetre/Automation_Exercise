package Framework.testCases.CheckOut;

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

public class TC_Checkout_04 {
    private WebDriver driver;
    private CheckOut cout;

    @BeforeMethod
    public void preCondition() throws Exception {
        driver = DriverManager.getDriver();
        driver = openWeb(readProp("url"));
        cout = new CheckOut(driver);
    }

    @Test(description = "TC_Checkout_04 - Verify order placement with empty payment details")
    public void placeOrderWithEmptyPaymentDetails() throws Exception {
        log().info("TC_Checkout_04: Verifying order placement with empty payment details");
        cout.proceedToCheckout();
        cout.placeOrder();
        cout.emptyPaymentDetails();
        log().info("TC_Checkout_04: Successfully verified order placement with empty payment details");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
