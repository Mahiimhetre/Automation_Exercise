package Framework.testCases.Cart;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Cart_05 {
    private WebDriver driver;
    private Cart cart;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cart = new Cart(driver);
    }

    @Test(description = "TC_Cart_05 - Verify total price with multiple products")
    public void verifyTotalPriceWithMultipleProducts() throws Exception {
        log().info("TC_Cart_05: Verifying total price with multiple products");
        cart.addProducts(2); // Add 2 products to the cart
        cart.viewCart();
        cart.checkTotalPrice();
        log().info("TC_Cart_05: Successfully verified total price with multiple products");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
