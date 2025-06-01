package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {
    WebDriver driver;
    public ProductPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "search_product")
    public WebElement SearchProd;

    @FindBy(id = "submit_search")
    public WebElement searchBtn;

    @FindBy(xpath = "//h2[@class]")
    public WebElement SearchTitle;

    @FindBy(xpath = "//a[contains(@href,'product_details')]")
    public List<WebElement> products;

    @FindBy(xpath = "(//*[@class='product-image-wrapper'])[1]//a[@href]")
    public WebElement firstProdView;

    @FindBy(xpath = "((//*[@class='product-image-wrapper'])[1]//h2)[1]")
    public WebElement firstProdPrice;

    @FindBy(xpath = "((//*[@class='product-image-wrapper'])[1]//p)[1]")
    public WebElement firstProdName;

    @FindBy(xpath = "((//*[@class='product-image-wrapper'])[1]//a)[1]")
    public WebElement addFirstProdtoCart;


    // Product details Page elements

    @FindBy(xpath = "//div[@class='product-information']//h2")
    public WebElement ProductName;

    @FindBy(xpath = "(//div[@class='product-information']//following::span/span)[1]")
    public WebElement ProductPrice;

    @FindBy(id = "quantity")
    public WebElement productQuantity;

    @FindBy(xpath = "//button[contains(@class,'cart')]")
    public WebElement addToCartBtn;


    @FindBy(xpath = "(//*[@id='cartModal']//p)[1]")
    public WebElement addCardConfirmation;


}
