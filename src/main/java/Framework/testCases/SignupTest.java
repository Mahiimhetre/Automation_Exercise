package Framework.testCases;

//SignupPage	- Successful registration - Duplicate email error - Mandatory field validation

import Framework.Functions.common;
import Framework.Functions.signUp;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static Framework.Functions.common.log;

public class SignupTest {
    ChromeDriver driver;

    @BeforeMethod
    public  void preCondition() throws Exception{
        driver = common.openWeb(common.readProp("url"));
    }

    @Test
    public void register() throws Exception {
        signUp si= new signUp();
        log().info("====TC01 User Registration started====");
        si.newUser();
    }

    public void postCondition() throws Exception{
        common.closeWeb();
    }

}

