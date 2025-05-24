package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    /**
     * Method to open(start) the browser in a test case
     * @author : mhetrem15@gmail.com
     * @param : No Params
     * @return : No Return Value
     */
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@href='/products']")
    public WebElement  prodPage;

    @FindBy(css = "a[href='/view_cart']")
    public WebElement cartpage;

    @FindBy(xpath = "//a[@href='/login']")
    public WebElement loginpage;

    @FindBy(linkText = "Contact us")
    public WebElement Contactpage;

    @FindBy(id = "subscribe_email")
    public WebElement subscriberEmail;

    @FindBy(xpath = "//button[@id='subscribe']")
    public WebElement subscribeBtn;

    @FindBy(xpath = "//*[@id='susbscribe_email' and @required]")
    public WebElement SubscribeReqFields;


}
