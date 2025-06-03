package Framework.Functions;

import Framework.Elements.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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
        log().info("Navigating back to the home page...");
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
        log().info("Navigating back to the home page...");
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
        log().info("Navigating back to the home page...");
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
        log().info("Navigating back to the home page...");
        driver.navigate().back();
    }

    public void checkSubscribe() throws Exception {
        log().info("Subscribing to the newsletter with email: " + common.readProp("email"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", hp.subscriberEmail);

        hp.subscriberEmail.clear();
        hp.subscriberEmail.sendKeys(common.readProp("email"));
        hp.subscribeBtn.click();

        By successMsgLocator = By.xpath("//div[contains(text(), 'You have been successfully subscribed')]");
        WebElement successMsg = common.waitForPresence(driver, successMsgLocator, 10);

        Assert.assertTrue(successMsg.isDisplayed(), "Subscription success message is not displayed.");
        log().info("Subscription successful with email: " + common.readProp("email..."));
    }


    public void checkSubscribeInvalidEmail() throws Exception{
        log().info("Checking for Credentials Validity...");

        hp.subscriberEmail.clear();
        hp.subscriberEmail.sendKeys(common.readProp("invEmail"));
        hp.subscribeBtn.click();
        // Check if the error message is displayed for invalid email
        if (common.checkFieldValidity(driver, hp.subscriberEmail)) {
            Assert.assertTrue(true, "Validation correctly triggered for required field: " + hp.subscriberEmail.getAttribute("data-qa"));
            return; // Stop further execution if validation fails
        }else {
            Assert.fail("Error message not displayed for invalid email.");
        }
    }

    public void checkEmptySubscribeEmail() throws Exception {
        // Check required fields for subscription
        hp.subscriberEmail.clear();
        log().info("Checking required fields for subscription with empty email...");

        hp.subscribeBtn.click();

        // Verify that the required field error is displayed
        log().info("Checking required fields for subscription...");
        Assert.assertTrue(common.isRequired(hp.subscriberEmail) && common.isEmpty(hp.subscriberEmail),
                "Required field validation failed for subscriber email.");
        log().info("Required field Validation passed for subscriber email...");
    }

}
