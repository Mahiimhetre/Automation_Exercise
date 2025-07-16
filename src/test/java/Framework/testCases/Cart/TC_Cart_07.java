package Framework.testCases.Cart;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Cart_07 {
    private WebDriver driver;
    private Cart cart;
    private Login login;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        cart = new Cart(driver);
        login = new Login(driver);
    }

@Test
    public void TC_Cart_07() throws Exception{
        log().info("Executing TC_Cart_07: Add multiple products to the cart...");
        login.loginWithValidCreds();
        removeAds();
        cart.addProducts(3); // Assuming 3 products to be added
        log().info("TC_Cart_07 Add multiple products to the cart executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
