package Framework.testCases;

import Framework.Functions.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Framework.Functions.common.log;

public class CheckoutTest {
    WebDriver driver;
    CheckOut cout;
    Cart cart;

    @BeforeMethod
    public void preCondition() throws Exception {
        // Code to set up the test environment, e.g., initializing WebDriver, loading properties, etc.
        System.out.println("Setting up the test environment for CheckoutTest...");
        // Initialize WebDriver and other necessary components here
        driver = common.openWeb(common.readProp("url"));
        cout = new CheckOut();
    }

    @Test
    public void TC_Checkout_01() throws Exception {
        // Test case to proceed to checkout and verify order confirmation
        log().info("Executing TC_Checkout_01: Checkout iwth user login...");
        // Navigate to the cart page
        cout.proceedToCheckout();
        log().info("Test case TC_Checkout_01: Checkout with user login executed Successfully...");
    }

    @Test
    public void TC_Checkout_02() throws Exception {
        // Test case to place an order with valid details
        log().info("Executing TC_Checkout_02: Place Order with valid details...");
        // Assuming proceedToCheckout has already been called in the previous test
        cout.placeOrder();
        cout.paymentDetails();
        // Additional assertions can be added here to verify order confirmation
        log().info("Test case TC_Checkout_02: Place Order with valid details executed Successfully...");
    }

    @Test
    public void TC_Checkout_03() throws Exception {
        // Test case to verify order confirmation
        log().info("Executing TC_Checkout_03: PLace Order with invalid payment details...");
        // Assuming placeOrder has already been called in the previous test
        cout.placeOrder();
        cout.invalidPaymentDetails();
        log().info("Test case TC_Checkout_03: Place Order with invalid payment details executed Successfully...");
    }

    @Test
    public void TC_Checkout_04() throws Exception {
        // Test case to verify order confirmation
        log().info("Executing TC_Checkout_04: Place order with Empty payment details...");
        cout.placeOrder();
        cout.emptyPaymentDetails();
        log().info("Test case TC_Checkout_04: Place order with Empty payment details executed Successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}

// have to add product to execute the test cases correctly ...