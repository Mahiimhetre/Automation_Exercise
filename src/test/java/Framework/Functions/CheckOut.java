package Framework.Functions;

import Framework.Elements.CartPage;
import Framework.Elements.CheckoutPage;
import Framework.Elements.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import java.util.Objects;

import static Framework.Functions.common.*;

public class CheckOut {
    HomePage hp = new HomePage(driver);
    CartPage cp = new CartPage(driver);
    CheckoutPage cop = new CheckoutPage(driver);
    Cart cart = new Cart();

    Login login = new Login();
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void proceedToCheckout() throws Exception {
        login.loginWithValidCreds();
        cart.addProducts(1);
        hp.cartPage.click();
        Assert.assertFalse(cp.cartProducts.isEmpty(),
                "Cart is empty, please add products to the cart before proceeding to checkout.");
        log().info("Cart is not empty, proceeding to checkout...");
        cp.checkoutBtn.click();
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("checkout"),
                "URL does not contain 'checkout', checkout page not reached...");
        log().info("Checkout page reached successfully, URL contains 'checkout'...");
    }


    public void placeOrder() throws Exception {
        proceedToCheckout();
        Assert.assertFalse(cop.deliveryAddress.isEmpty(),
                "Delivery address is empty, please provide a valid delivery address before placing an order.");
        log().info("Delivery address is provided, proceeding to place the order...");
        int sum = 0;
        int n = cop.totalPrices.size();
        for (int i = 0; i < n - 1; i++) {
            sum += Integer.parseInt(cop.totalPrices.get(i).getText().replaceAll("[^0-9]", ""));
        }
        int lastValue = Integer.parseInt(cop.totalPrices.get(n - 1).getText().replaceAll("[^0-9]", ""));
        Assert.assertEquals(sum, lastValue, "Total price mismatch, expected: " + sum + ", but found: " + lastValue);
        log().info("Total price validation successful...");

        js.executeScript("arguments[0].scrollIntoView(true);", cop.placeOrderBtn);
        cop.placeOrderBtn.click();

        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("payment"),
                "URL does not contain 'payment', order placement page not reached...");
        log().info("User Navigated to Payment page successfully...");
    }

    public void paymentDetails() throws Exception{
        cop.cardName.sendKeys(common.readProp("ccName"));
        cop.cardNumber.sendKeys(common.readProp("ccNumber"));
        cop.cardCVC.sendKeys(common.readProp("ccCVV"));
        cop.cardExpiryMonth.sendKeys(common.readProp("ccExpiryMonth"));
        cop.cardExpiryYear.sendKeys(common.readProp("ccExpiryYear"));
        js.executeScript("arguments[0].scrollIntoView(true);", cop.payButton);
        cop.payButton.click();
        Assert.assertTrue(common.waitForVisibility(driver,cop.orderConfirmationTitle,5).getText().contains("PLACED!"),
                "Order confirmation title does not contain 'PlACED!', order placement failed.");
        log().info("Order placed successfully...");
    }

    public void invalidPaymentDetails() throws Exception {
        cop.cardName.sendKeys(common.readProp("ccInvName"));
        cop.cardNumber.sendKeys(common.readProp("ccInvNumber"));
        cop.cardCVC.sendKeys(common.readProp("ccInvCVV"));
        cop.cardExpiryMonth.sendKeys(common.readProp("ccExpiryMonth"));
        cop.cardExpiryYear.sendKeys(common.readProp("ccExpiryYear"));

        cop.payButton.click();

        try {
            Assert.assertTrue(common.checkFieldValidity(driver, cop.cardName),
                    "Validation correctly triggered for required field: " + cop.cardName.getAttribute("data-qa"));
            return; // Stop further execution if validation fails
        } catch (Exception e) {
            Assert.fail("Validation did not trigger for invalid payment details: " + e.getMessage());
        }
    }

    public void emptyPaymentDetails() throws Exception {
        removeAds();
        cop.cardName.clear();
        cop.cardNumber.clear();
        cop.cardCVC.clear();
        cop.cardExpiryMonth.clear();
        cop.cardExpiryYear.clear();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", cop.payButton);
        cop.payButton.click();
        if (common.checkFieldValidity(driver, cop.cardName)) {
            Assert.assertTrue(true, "Validation correctly triggered for required field: " + cop.cardName.getAttribute("data-qa"));
            return; // Stop further execution if validation fails
        }
        else {
            Assert.fail("Error message not displayed for invalid email.");
        }
        log().info("Order placement failed as expected with empty payment details...");
    }




}
