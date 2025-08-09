package Framework.testCases.Cart;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Cart_06 {
    private WebDriver driver;
    private Cart cart;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cart = new Cart(driver);
    }

    @Test(description = "TC_Cart_06 - Verify checkout without login")
    public void verifyCheckoutWithoutLogin() throws Exception {
        log().info("TC_Cart_06: Verifying checkout without login");
        cart.viewCart();
        cart.checkEmptyCartModal();
        log().info("TC_Cart_06: Successfully verified checkout without login");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
