package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
    public WebElement cartPage;

    @FindBy(partialLinkText = "Login")
    public WebElement loginSignupPage;

    @FindBy(linkText = "Contact us")
    public WebElement ContactPage;

    @FindBy(xpath = "//div[contains(@class,'single')]//a[@data-product-id]")
    public List<WebElement> homeProducts;

    @FindBy(xpath = "//button[contains(@class,'close-modal')]")
    public WebElement closeModal;

    @FindBy(id = "susbscribe_email")
    public WebElement subscriberEmail;

    @FindBy(xpath = "//button[@id='subscribe']")
    public WebElement subscribeBtn;

}
