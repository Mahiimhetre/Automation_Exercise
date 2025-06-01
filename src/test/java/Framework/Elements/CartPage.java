package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(@class,'check_out')]")
    public WebElement checkoutBtn;

     @FindBy(xpath = "//td[@class='cart_product']")
    public List<WebElement> cartProdCount;

     @FindBy(xpath = "//tr[contains(@id,'product-1')]/td[@class='cart_price']/p")
    public WebElement firstProductPrice;

    @FindBy(xpath = "//tr[contains(@id,'product-1')]/td[@class='cart_quantity']/button")
    public WebElement firstProductQuantity;

    @FindBy(xpath = "//tr[contains(@id,'product-1')]/td/p[contains(@class,'total_price')]")
    public WebElement firstProductTotalPrice;

    @FindBy(xpath = "//tr[contains(@id,'product-2')]/td[@class='cart_price']/p")
    public WebElement secondProductPrice;

    @FindBy(xpath = "//tr[contains(@id,'product-2')]/td[@class='cart_quantity']/button")
    public WebElement secondProductQuantity;

    @FindBy(xpath = "//tr[contains(@id,'product-2')]/td/p[contains(@class,'total_price')]")
    public WebElement secondProductTotalPrice;

    @FindBy(xpath = "(//*[@data-product-id])[1]")
    public WebElement rmFirstProduct;

    @FindBy(xpath = "(//*[@data-product-id])[2]")
    public WebElement rmSecondProduct;

}
