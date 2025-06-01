package Framework.Functions;

//Chartpage = Check product count in cart - Remove products from cart - Verify cart is reduces/empty after removal
                // - verify the total price is updated according to the product quantity in the cart

import Framework.Elements.CartPage;
import Framework.Elements.HomePage;
import org.testng.Assert;

import static Framework.Functions.common.driver;
import static Framework.Functions.common.log;

public class Cart {
    HomePage hp = new HomePage(driver);
    CartPage cp = new CartPage(driver);

    public void CheckProdCount() throws Exception {
        cp.cartProdCount.size();
        if (cp.cartProdCount.size() == 0) {
                log().info("Cart is empty");
                cp.checkoutBtn.click();
                log().info("Clicked on checkout button");
        } else if (cp.cartProdCount.size() == 1) {
                log().info("Cart has 1 product");
                cp.rmFirstProduct.click();
                log().info("Removed first product from cart");
        } else if (cp.cartProdCount.size() == 2) {
                log().info("Cart has 2 products");
                cp.rmSecondProduct.click();
                log().info("Removed second product from cart");
        } else {
                log().info("Cart has " + cp.cartProdCount.size() + " products");
        }
    }

    public void RemoveFromCart() throws Exception {
        CheckProdCount();
        if (cp.cartProdCount.size() == 0) {
            log().info("Cart is empty after removal");
        } else {
            log().info("Cart is not empty after removal");
            throw new Exception("Cart is not empty after removal");
        }
    }
}
