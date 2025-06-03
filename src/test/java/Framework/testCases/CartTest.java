package Framework.testCases;


import Framework.Elements.CartPage;
import Framework.Functions.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static Framework.Functions.common.log;
import static Framework.Functions.common.removeAds;

public class CartTest {
    WebDriver driver;
    Cart cart;
    Home home;
    Login login;

    @BeforeMethod
    public void preCondition() throws Exception{
        // Code to set up preconditions for the Cart tests
        log().info("Setting up preconditions for Cart tests");
        driver = common.openWeb(common.readProp("url"));
        cart = new Cart();
        home = new Home();
        login = new Login();
    }
    @Test//(dependsOnMethods = {"Framework.testCases.ProductTest.TC_Prod_07"})
    public void TC_Cart_01() throws Exception {
        // Test case to verify adding an item to the cart
        log().info("Executing TC_Cart_01: View cart...");
        cart.viewCart();
        log().info("TC_Cart_01 View cart executed successfully...");
    }

    @Test
    public void TC_Cart_02() throws Exception {

        // Test case to check product count in the cart
        log().info("Executing TC_Cart_02: Check product count in cart...");
        cart.checkProdCount();
        log().info("TC_Cart_02 Check product count in cart executed successfully...");
    }

    @Test
    public void TC_Cart_03() throws Exception {
        // Test case to remove products from the cart
        log().info("Executing TC_Cart_03: Remove products from cart...");
        cart.RemoveFromCart();
        log().info("TC_Cart_03 Remove products from cart executed successfully...");
    }

    @Test
    public void TC_Cart_04() throws Exception {
        // Test case to verify the cart is empty after removing all products
        log().info("Executing TC_Cart_04: Verify cart is empty after removal...");
        cart.checkEmptyCart();
        log().info("TC_Cart_04 Verify cart is empty after removal executed successfully...");
    }

    @Test
    public void TC_Cart_05() throws Exception {
        // Test case to verify the total price is updated according to the product quantity in the cart
        cart.addProductToCart(2); // Assuming 2 products to be added
        log().info("Executing TC_Cart_05: Verify total price is according to 1 unit Price and Quantity...");
        cart.viewCart();
        cart.checkTotalPrice();
        log().info("TC_Cart_05 Product Price Verification executed successfully...");
    }

    @Test
    public void TC_Cart_06() throws Exception{
        log().info("Executing TC_Cart_06: Checking out without Login...");
        cart.viewCart();
        cart.checkEmptyCartModal();
        log().info("TC_Cart_06 Checking out without Login executed successfully...");
    }

    @Test
    public void TC_Cart_07() throws Exception{
        log().info("Executing TC_Cart_07: Add Multiple Product To Cart...");
        login.loginWithValidCreds();
        removeAds();
        cart.addProductToCart(3); // Assuming 3 products to be added
        log().info("Test case to add multiple products to the cart Executed successfully...");
    }

    @Test
    public void TC_Cart_08() throws Exception{
        log().info("Executing TC_Cart_08: Cart persistence after logout...");
        cart.cartPercistence();
        log().info("Verifying cart persistence after logout Successful...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }

    @AfterClass
    public void tearDown() throws Exception {
        // Code to clean up after all tests
        log().info("Tearing down Cart tests");
            common.closeWeb(driver);
    }
}
