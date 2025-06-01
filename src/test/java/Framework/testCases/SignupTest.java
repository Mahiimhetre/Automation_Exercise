package Framework.testCases;

import Framework.Functions.common;
import Framework.Functions.ScreenShot;
import Framework.Functions.SignUp;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Framework.Functions.common.log;

public class SignupTest {
    SignUp si;
    WebDriver driver;

    @BeforeMethod
    public  void preCondition() throws Exception{
        driver = common.openWeb(common.readProp("url"));
        si= new SignUp();
    }

    @Test
    public void TC001_SignupWithValidData() throws Exception {
        log().info("====TC001 New User Registration started====");
        si.gotoSign();
        si.fillDetails();
        si.confirmation();
        log().info("Positive test case for New User Registration executed successfully...");

    }

    @Test(priority = 1, description = "Duplicate User Registration Test")
    @Description("This test case checks the scenario where a user tries to register with an email that is already registered.")
    @Epic("User Registration")
    @Feature("Feature1: Registration")
    @Story("Story1: Duplicate Registration")
    @Severity(SeverityLevel.BLOCKER)
    public void TC002_SignupDuplicateUser() throws Exception{
        log().info("====TC002 Duplicate User Registration started====");
        si.DuplicateUser();
        log().info("Negative test case for Duplicate user Registration executed successfully...");

    }

    @Test(priority = 2, description = "Registration with Empty Required Fields Test")
    @Description("This test case checks the scenario where a user tries to register without filling in the required fields.")
    @Epic("User SignUp")
    @Feature("Feature1: SignUp")
    @Story("Story2: Empty Required Fields")
    @Severity(SeverityLevel.CRITICAL)
    public void TC003_SignupWithEmptyRequiredFields() throws Exception{
        log().info("====TC003 Registration with Empty Required Fields started====");
        si.checkSignupReqFields();
        log().info("Negative test case for required fields executed successfully...");

    }

    @Test(priority = 3, description = "Registration with Invalid Email Test")
    @Description("This test case checks the scenario where a user tries to register with an invalid email format.")
    @Epic("User Registration")
    @Feature("Feature1: Registration")
    @Story("Story3: Invalid Email Registration")
    @Severity(SeverityLevel.NORMAL)
    public void TC004_SignupWithInvalidEmail() throws Exception{
        log().info("====TC004 Registration with Invalid Email Fields started====");
        si.invalidEmailRegistration();
        log().info("Negative test case for Invalid Email Registration executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver,result);
        common.closeWeb(driver);
    }

}

