package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
    WebDriver driver;
    /**
     * Method to open(start) the browser in a test case
     * @author : mhetrem15@gmail.com
     * @param : No Params
     * @return : No Return Value
     */
    public void LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@data-qa='login-email']")
    public WebElement loginEmail;

    @FindBy(name = "password")
    public WebElement loginPass;

    @FindBy(xpath = "//*[@data-qa='login-button']")
    public WebElement loginBtn;

    @FindBy(xpath = "//*[@data-qa='signup-name']")
    public WebElement signupName;

    @FindBy(xpath = "//*[@data-qa='signup-email']")
    public WebElement SignupEmail;

    @FindBy(id = "id_gender1")
    public WebElement gender;

    @FindBy(id = "name")
    public WebElement name;

    @FindBy(name = "password")
    public WebElement pwd;

    @FindBy(id = "days")public WebElement day;//select
    @FindBy(id = "months")public WebElement month;//select
    @FindBy(id = "years")public WebElement year;//select

    @FindBy(id = "newsletter")
    public WebElement newsletter;

    @FindBy(id = "optin")
    public WebElement offers;


}
