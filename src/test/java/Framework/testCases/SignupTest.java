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

    @Test (priority = 0, description = "New User Registration Test")
    public void TC_Signup_01() throws Exception {
        log().info("Executing TC_Signup_01: New User Registration started...");
        si.gotoSign();
        si.fillDetails();
        si.confirmation();
        log().info("TC_Signup_01: New User Registration executed successfully...");

    }

    @Test(priority = 1, description = "Duplicate User Registration Test")
    @Description("This test case checks the scenario where a user tries to register with an email that is already registered.")
    @Epic("User Registration")
    @Feature("Feature1: Registration")
    @Story("Story1: Duplicate Registration")
    @Severity(SeverityLevel.BLOCKER)
    public void TC_Signup_02() throws Exception{
        log().info("Executing TC_Signup_02: Duplicate User Registration started...");
        si.DuplicateUser();
        log().info("TC_Signup_02: Duplicate User Registration executed successfully...");
    }

    @Test(priority = 2, description = "Registration with Empty Required Fields Test")
    @Description("This test case checks the scenario where a user tries to register without filling in the required fields.")
    @Epic("User SignUp")
    @Feature("Feature1: SignUp")
    @Story("Story2: Empty Required Fields")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_Signup_03() throws Exception{
        log().info("Executing TC_Signup_03: Registration with Empty Required Fields started...");
        si.checkSignupReqFields();
        log().info("TC_Signup_03: Registration with Empty Required Fields executed successfully...");

    }

    @Test(priority = 3, description = "Signup with Invalid Email Test")
    @Description("This test case checks the scenario where a user tries to Signup with an invalid email format.")
    @Epic("User Registration")
    @Feature("Feature1: Registration")
    @Story("Story3: Invalid Email Registration")
    @Severity(SeverityLevel.NORMAL)
    public void TC_Signup_04() throws Exception{
        log().info("Executing TC_Signup_04: Signup with Invalid Email started...");
        si.invalidEmailRegistration();
        log().info("TC_Signup_04: Signup with Invalid Email executed successfully...");
    }

    @Test(priority = 4, description = "Signup with Special Character in Name Test")
    public void TC_Signup_05() throws Exception{
        log().info("Executing TC_Signup_05: Signup with Special Character in Name started...");
        si.specialCharInName();
        log().info("TC_Signup_05: Signup with Special Character in Name executed successfully...");
    }

    @Test(priority = 5, description = "Signup with Invalid Password Test")
    public void TC_Signup_06() throws Exception {
        log().info("Executing TC_Signup_06: Signup with Invalid Password started...");
        si.caseSensitiveEmail();
        log().info("TC_Signup_06: Signup with Invalid Password executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver,result);
        common.closeWeb(driver);
    }

}

