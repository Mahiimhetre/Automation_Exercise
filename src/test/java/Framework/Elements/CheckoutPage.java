package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    /**
     * Method to open(start) the browser in a test case
     * @author : mhetrem15@gmail.com
     * @param : No Params
     * @return : No Return Value
     */
    public CheckoutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //check each element is not empty put assertion.
    @FindBy(xpath = "//ul[@id='address_delivery']/li")
    public List<WebElement> deliveryAddress;

    //make addition till second last element and compare it with last one.
    @FindBy(xpath = "//p[@class='cart_total_price']")
    public List<WebElement> totalPrices;

    @FindBy(xpath = "//a[@href='/payment']")
    public WebElement placeOrderBtn;

    // payment page--------------------------------

    @FindBy(name = "name_on_card")
    public WebElement cardName;

    @FindBy(name = "card_number")
    public WebElement cardNumber;

    @FindBy(name = "cvc")
    public WebElement cardCVC;

    @FindBy(name = "expiry_month")
    public WebElement cardExpiryMonth;

    @FindBy(name = "expiry_year")
    public WebElement cardExpiryYear;

    @FindBy(xpath = "//button[@data-qa='pay-button']")
    public WebElement payButton;

    //make assertion on this element. using contains "placed!"
    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement orderConfirmationTitle;

}
