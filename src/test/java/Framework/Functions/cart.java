package Framework.Functions;

//CartPage	- Add product to cart - Remove from cart - Update quantity

import Framework.Elements.CartPage;
import Framework.Elements.HomePage;

import static Framework.Functions.common.driver;

public class cart {
    HomePage hp = new HomePage(driver);
    CartPage cp = new CartPage(driver);
}
