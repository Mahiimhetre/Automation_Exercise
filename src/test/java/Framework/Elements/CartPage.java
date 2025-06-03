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

     @FindBy(xpath = "//a[@class = 'cart_quantity_delete']")
    public List<WebElement> cartProducts;


//-------------Checkout modal------------------------------------------
    @FindBy(xpath = "//div[@id='checkoutModal']//p[1]")
    public WebElement checkoutModalText;

    @FindBy(xpath = "//div[@id='checkoutModal']//p/a")
    public WebElement checkoutModalLink;

}
