package Framework.testCases;

import Framework.Functions.common;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

//LoginPage	- Valid login - Invalid login - Blank email/password validation.
public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void preCondition() throws Exception{
        driver = common.openWeb(common.readProp("url"));

    }
}
