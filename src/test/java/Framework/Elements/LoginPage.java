package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;

public class LoginPage {
    WebDriver driver;
    /**
     * Method to open(start) the browser in a test case
     * @author : mhetrem15@gmail.com
     * @param : No Params
     * @return : No Return Value
     */
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@data-qa='login-email']")
    public WebElement loginEmail;

    @FindBy(name = "password")
    public WebElement loginPass;

    @FindBy(xpath = "//*[@data-qa='login-button']")
    public WebElement loginBtn;

    @FindBy(xpath = "//*[@action='/login']//*[@required]")
    public LinkedList<WebElement> LoginReqFields;

}
