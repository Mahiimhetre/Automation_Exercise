package Framework.Functions;

//CheckoutPage	- Proceed to checkout - Place an order with valid details - Verify order confirmation

import Framework.Elements.CheckoutPage;
import Framework.Elements.HomePage;

import static Framework.Functions.common.driver;

public class CheckOut {
    HomePage hp = new HomePage(driver);
    CheckoutPage cop = new CheckoutPage(driver);
}
