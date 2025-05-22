package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

public class SignupPage {
    WebDriver driver;

    public SignupPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@data-qa='signup-name']")
    public WebElement signupName;

    @FindBy(xpath = "//*[@data-qa='signup-email']")
    public WebElement SignupEmail;

    @FindBy(xpath = "//*[@data-qa='signup-button']")
    public WebElement SignupBtn;

    @FindBy(id = "id_gender1")
    public WebElement title;

    @FindBy(name = "password")
    public WebElement pass;

    @FindBy(id = "days")public WebElement day;//select
    @FindBy(id = "months")public WebElement month;//select
    @FindBy(id = "years")public WebElement year;//select

    @FindBy(id = "aswift_1")
    public WebElement iframe;

    @FindBy(xpath = "//input[@id='newsletter']")
    public WebElement newsletter;

    @FindBy(id = "optin")
    public WebElement offers;

    @FindBy(id = "name")
    public WebElement name;

    @FindBy(id = "first_name")
    public WebElement fname;

    @FindBy(id = "last_name")
    public WebElement lname;

    @FindBy(id = "company")
    public WebElement company;

    @FindBy(id = "address1")
    public WebElement address1;

    @FindBy(id = "address2")
    public WebElement address2;

    @FindBy(id = "country")
    public WebElement country;//select

    @FindBy(id = "state")
    public WebElement state;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "zipcode")
    public WebElement zipcode;

    @FindBy(id = "mobile_number")
    public WebElement mobile;

    @FindBy(xpath = "//*[@data-qa='create-account']")
    public WebElement createBtn;

}
