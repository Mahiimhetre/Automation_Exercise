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

@Test
    public void TC_Cart_05() throws Exception {
        log().info("Executing TC_Cart_05: Verify total price is according to the Unit Price and Quantity...");
        cart.addProducts(2); // Assuming 2 products to be added
        cart.viewCart();
        cart.checkTotalPrice();
        log().info("TC_Cart_05 Verify total price is according to the Unit Price and Quantity executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
