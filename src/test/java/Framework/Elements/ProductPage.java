package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedList;

public class ProductPage {
    WebDriver driver;
    public ProductPage(WebDriver Driver){
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "search_product")
    public WebElement SearchProd;

    @FindBy(id = "submit_search")
    public WebElement searchBtn;

    @FindBy(xpath = "//*[@class='product-image-wrapper']")
    public LinkedList<WebElement> SearchResults;

    @FindBy(xpath = "(//*[@class='product-image-wrapper'])[1]//a[@href]")
    public WebElement firstProdView;

    @FindBy(xpath = "((//*[@class='product-image-wrapper'])[1]//h2)[1]")
    public WebElement firstProdPrice;

    @FindBy(xpath = "((//*[@class='product-image-wrapper'])[1]//p)[1]")
    public WebElement firstProdName;

    @FindBy(xpath = "((//*[@class='product-image-wrapper'])[1]//a)[1]")
    public WebElement addFirstProdtoCart;

    @FindBy(xpath = "//*[@id='cartModal']//p")
    public WebElement addCardConfirmation;


}
