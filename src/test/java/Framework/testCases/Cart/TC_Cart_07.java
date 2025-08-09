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

    @Test(description = "TC_Cart_07 - Add multiple products to cart")
    public void addMultipleProductsToCart() throws Exception {
        log().info("TC_Cart_07: Verifying addition of multiple products to cart");
        login.loginWithValidCreds();
        removeAds();
        cart.addProducts(3); // Add 3 products to the cart
        log().info("TC_Cart_07: Successfully verified addition of multiple products to cart");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
