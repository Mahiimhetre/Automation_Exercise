package Framework.testCases.Cart;

import Framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;

import static Framework.Functions.common.*;

public class TC_Cart_01 {
    private WebDriver driver;
    private Cart cart;

    @BeforeMethod
    public void preCondition() throws Exception    {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cart = new Cart(driver);
    }

    @Test(description = "TC_Cart_01 - View cart contents")
    public void viewCartContents() throws Exception {
        log().info("TC_Cart_01: Verifying the cart page displays correctly");
        cart.viewCart();
        log().info("TC_Cart_01: Successfully verified the cart page displays correctly");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
