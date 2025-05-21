package Framework.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;
    /**
     * Method to open(start) the browser in a test case
     * @author : mhetrem15@gmail.com
     * @param : No Params
     * @return : No Return Value
     */
    public void CheckoutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


}
