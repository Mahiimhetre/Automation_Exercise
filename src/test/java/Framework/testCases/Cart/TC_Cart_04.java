package Framework.testCases.Cart;

import Framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;

import static Framework.Functions.common.*;

public class TC_Cart_04 {
    private WebDriver driver;
    private Cart cart;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cart = new Cart(driver);
    }

    @Test(description = "TC_Cart_04 - Verify cart is empty after removal")
    public void verifyCartIsEmptyAfterRemoval() throws Exception {
        log().info("TC_Cart_04: Verifying cart is empty after removal");
        cart.checkEmptyCart();
        log().info("TC_Cart_04: Successfully verified cart is empty after removal");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
