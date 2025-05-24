package Framework.testCases;

//SignupPage	- Successful registration - Duplicate email error - Mandatory field validation

import Framework.Functions.FieldRequiredException;
import Framework.Functions.common;
import Framework.Functions.signUp;
import Framework.Functions.signupNegative;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Framework.Functions.common.log;

public class SignupTest {
    WebDriver driver;

    signUp si;
    signupNegative sn;

    @BeforeMethod
    public  void preCondition() throws Exception{
        driver = common.openWeb(common.readProp("url"));
        si= new signUp();
        sn = new signupNegative();
    }

//    @Test
    public void TC001_newUserRegister() throws Exception {
        log().info("====TC001 New User Registration started====");
        si.gotoSign();
        si.registration();
        si.confirmation();
    }

//    @Test
    public void TC002_DuplicateUserRegistration() throws Exception{
        log().info("====TC002 Duplicate User Registration started====");
        sn.DuplicateUser();
    }

    @Test
    public void TC003_RegistrationWithEmptyRequiredFields() throws FieldRequiredException, Exception{
        log().info("====TC002 Registration with Empty started====");
        sn.checkSignupReqFields();
    }

    @AfterMethod
    public void postCondition(){
        common.closeWeb();
    }

}

