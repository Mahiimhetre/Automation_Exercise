package Framework.Functions;

//Chartpage = Check product count in cart - Remove products from cart - Verify cart is reduces/empty after removal
                // - verify the total price is updated according to the product quantity in the cart

import Framework.DriverManager;

import Framework.Elements.CartPage;
import Framework.Elements.HomePage;
import Framework.Elements.SignupPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import static Framework.Functions.common.log;

public class Cart {
    private static final Log log = LogFactory.getLog(SignUp.class);
    private WebDriver driver;
    private HomePage hp;
    private CartPage cp;
    private Login login;

    public Cart(WebDriver driver){
        this.driver = driver;
        hp = new HomePage(driver);
        cp = new CartPage(driver);
        login = new Login(driver);
    }
    public void viewCart() throws Exception {
        log().info("Opening cart page...");
        hp.cartPage.click();
        Assert.assertNotNull(driver.getTitle(), "Cart page title is null...");
        Assert.assertTrue(driver.getTitle().contains("Checkout"), "Unable to navigate to cart page, title verification failed...");
        log().info("Cart page is displayed successfully...");
    }

    public void checkProdCount() throws Exception {
        log().info("Navigating to cart page to check product count...");
        hp.cartPage.click();
        log().info("counting products in cart...");
        if (prodCount() == 0) {
            log().info("No products found in cart...");
        } else {
            log().info("Product count in cart: " + prodCount());
        }
    }

    public int prodCount() throws Exception {
        hp.cartPage.click();
        int count = cp.cartProducts.size();
        return count;
    }

    public void checkEmptyCart() throws Exception {
        hp.cartPage.click();
        Assert.assertTrue(cp.cartProducts.isEmpty(), "Cart is not empty, products found.");
        log().info("Cart is empty as expected.");
    }

    public void RemoveFromCart() throws Exception {
        hp.cartPage.click();
        log().info("Total products in cart :" + cp.cartProducts.size());
        for (int i = cp.cartProducts.size() - 1; i >= 0; i--) {
            log().info("Removing product " + (i + 1) + " from cart");
            cp.cartProducts.get(i).click();
            log().info("Clicked on product " + (i + 1) + " in cart");
            if (i == 0) {
                cp.checkoutBtn.click();
                log().info("Clicked on checkout button after removing last product");
            } else {
                log().info("Continuing to remove next product...");
            }
        }
        Assert.assertSame(cp.cartProducts.size(), 0, "Cart is not empty after removing all products.");
        log().info("All products removed from cart...");
    }

    public void checkEmptyCartModal() throws Exception {
        log().info("clicking on checkout button to open modal...");
        try {
            Assert.assertFalse(common.waitForVisibility(driver, cp.checkoutBtn, 5).isDisplayed(),
                    "Checkout button is displayed when it should not be.");
        } catch (Exception e) {
            log().info("Checkout button is not displayed, as expected...");
        }
    }

    public void checkTotalPrice() throws Exception {
        log().info("Checking total price in cart...");
        hp.cartPage.click();
        System.out.println("Total products in cart: " + cp.cartProducts.size());
        for (int i = 0; i < cp.cartProducts.size(); i++) {
            WebElement price = driver.findElement(By.xpath(
                    "(//tr[contains(@id,'product')]/td[@class='cart_price']/p)[" + (i + 1) + "]"));
            WebElement quantity = driver.findElement(By.xpath(
                    "(//tr[contains(@id,'product')]/td[@class='cart_quantity']/button)[" + (i + 1) + "]"));
            WebElement totalPrice = driver.findElement(By.xpath(
                    "(//tr[contains(@id,'product')]/td/p[contains(@class,'total_price')])[" + (i + 1) + "]"));
            int unitPrice = Integer.parseInt(price.getText().replaceAll("[^0-9]", ""));
            int qty = Integer.parseInt(quantity.getText());
            int FinalPrice = Integer.parseInt(totalPrice.getText().replaceAll("[^0-9]", ""));
            Assert.assertTrue(FinalPrice == unitPrice * qty,
                    "Total price for product " + (i + 1) + " is incorrect. Expected: " + (unitPrice * qty) +
                            ", Actual: " + FinalPrice);
            log().info("Product " + (i + 1) + ": Unit Price = Rs. " + unitPrice + ", Quantity = " + qty +
                    ", Total Price = Rs. " + FinalPrice);
        }
        log().info("Total price of all Product/unit is displayed correctly in the cart...");
    }


    public void addProducts(int count) throws Exception {

        List<WebElement> visibleProducts = new ArrayList<>();
        for (int i = 1; i < hp.homeProducts.size(); i++) {
            if (i % 2 != 0) { // 1-based even index
                visibleProducts.add(driver.findElement(By.xpath("(//div[contains(@class,'single')]//a[@data-product-id])[" + i + "]")));
            }
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        common.removeAds();
        log().info("Adding " + count + " products to cart...");
        int added = 0;
        for (int i = 0; i < visibleProducts.size() && added < count; i++) {
            if ((i + 1) % 2 != 0) { // 1-based even index
                WebElement product = common.waitForVisibility(driver, visibleProducts.get(i), 5);
                js.executeScript("arguments[0].scrollIntoView(true);", product);
                product.click();
                Assert.assertTrue(common.waitForVisibility(driver, hp.closeModal, 5).isDisplayed(),
                        "Close modal button is not displayed after adding product to cart.");
                log().info("Product " + (added + 1) + " added to cart.");
                hp.closeModal.click();
                added++;
            }
        }
    }

    public void cartPercistence() throws Exception {

        login.loginWithValidCreds();
        checkProdCount();
        int initialCount = prodCount();
        login.logoutFromApplication();

        //reLogin to verify cart persistence
        log().info("Re-logging in to verify cart persistence...");

        login.loginWithValidCreds();
        checkProdCount();
        int finalCount = prodCount();
        Assert.assertTrue(initialCount == finalCount,
                "Cart persistence failed, product count mismatch after logout and re-login.");
    }
}
