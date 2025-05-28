package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(@class,check_out)]")
    public WebElement checkout;

     @FindBy(xpath = "//td[@class='cart_product']")
    public WebElement cartProdCount;

    @FindBy(xpath = "//*[@data-product-id]")
    public WebElement oneProduct;

}
