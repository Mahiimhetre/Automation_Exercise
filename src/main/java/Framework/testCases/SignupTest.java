package Framework.testCases;

import Framework.Functions.common;
import Framework.Functions.screenShot;
import Framework.Functions.signUp;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Framework.Functions.common.log;

public class SignupTest {
    signUp si;
    WebDriver driver;

    @BeforeMethod
    public  void preCondition() throws Exception{
        driver = common.openWeb(common.readProp("url"));
        si= new signUp();
    }

//    @Test
    public void TC001_NewUserRegister() throws Exception {
        log().info("====TC001 New User Registration started====");
        si.gotoSign();
        si.register();
        si.confirmation();
        log().info("Positive test case for New User Registration executed successfully.");

    }

    @Test
    public void TC002_DuplicateUserRegistration() throws Exception{
        log().info("====TC002 Duplicate User Registration started====");
        si.DuplicateUser();
        log().info("Negative test case for Duplicate user Registration executed successfully.");

    }

    @Test
    public void TC003_RegistrationWithEmptyRequiredFields() throws Exception{
        log().info("====TC003 Registration with Empty Required Fields started====");
        si.checkSignupReqFields();
        log().info("Negative test case for required fields executed successfully.");

    }

    @Test
    public void TC004_RegisterWithInvalidEmail() throws Exception{
        log().info("====TC004 Registration with Invalid Email Fields started====");
        si.invalidEmailRegistration();
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        screenShot.captureOnFailure(driver,result);
        common.closeWeb();
    }

}

