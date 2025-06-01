package Framework.Functions;

import Framework.Elements.HomePage;
import org.testng.Assert;

import static Framework.Functions.common.driver;
import static Framework.Functions.common.log;

public class Home {
    HomePage hp = new HomePage(driver);


    public void checkProductPage() throws Exception {
        // Navigate to the product page
        log().info("Navigating to the product page...");
        common.waitForVisibility(driver,hp.prodPage,20).click();
        Assert.assertNotNull(driver.getTitle(), "Driver is not initialized or the page title is null.");
        Assert.assertTrue(driver.getTitle().contains("Products"),
                "Product page is not displayed correctly or URL is incorrect.");
        log().info("Product page is displayed correctly..");
        //Navigate back to the home page
        driver.navigate().back();
    }

    public void checkCartPage() throws Exception {
        // Navigate to the cart page
        log().info("Navigating to the cart page...");
        common.waitForVisibility(driver,hp.cartPage,20).click();
        Assert.assertNotNull(driver.getTitle(), "Driver is not initialized or the page title is null.");
        Assert.assertTrue(driver.getTitle().contains("Checkout"),
                "Cart page is not displayed correctly or URL is incorrect.");
        log().info("Cart page is displayed correctly..");
        //Navigate back to the home page
        driver.navigate().back();
    }

    public void checkLoginSignupPage() throws Exception {
        // Navigate to the login/signup page
        log().info("Navigating to the login/signup page...");
        common.waitForVisibility(driver,hp.loginSignupPage,20).click();
        Assert.assertNotNull(driver.getTitle(), "Driver is not initialized or the page title is null.");
        Assert.assertTrue(driver.getTitle().contains("Signup / Login"),
                "Login/Signup page is not displayed correctly or URL is incorrect.");
        log().info("Login/Signup page is displayed correctly..");
        //Navigate back to the home page
        driver.navigate().back();
    }

    public void checkContactPage() throws Exception {
        // Navigate to the contact page
        log().info("Navigating to the contact page...");
        common.waitForVisibility(driver,hp.ContactPage,20).click();
        Assert.assertNotNull(driver.getTitle(), "Driver is not initialized or the page title is null.");
        Assert.assertTrue(driver.getTitle().contains("Contact Us"),
                "Contact page is not displayed correctly or URL is incorrect.");

        log().info("Contact page is displayed correctly..");
        //Navigate back to the home page
        driver.navigate().back();
    }

}
