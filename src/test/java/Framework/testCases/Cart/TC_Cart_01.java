package Framework.testCases.Cart;

import Framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;

import static Framework.Functions.common.log;

public class TC_Cart_01 {
    private WebDriver driver;
    private Cart cart;

    @BeforeMethod
    public void preCondition() throws Exception    {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cart = new Cart(driver);
    }

@Test
    public void TC_Cart_01() throws Exception {

        log().info("Executing TC_Cart_01: View cart...");
        cart.viewCart();
        log().info("TC_Cart_01 View cart executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
