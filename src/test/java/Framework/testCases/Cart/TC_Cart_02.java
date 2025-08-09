package Framework.testCases.Cart;

import Framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;

import static Framework.Functions.common.*;

public class TC_Cart_02 {
    private WebDriver driver;
    private Cart cart;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cart = new Cart(driver);
    }

    @Test(description = "TC_Cart_02 - Verify product count in cart")
    public void verifyProductCountInCart() throws Exception {
        log().info("TC_Cart_02: Verifying product count in cart");
        cart.checkProdCount();
        log().info("TC_Cart_02: Successfully verified product count in cart");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
