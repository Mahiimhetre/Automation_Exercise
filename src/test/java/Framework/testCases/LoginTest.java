package Framework.testCases;

import Framework.Functions.common;
import Framework.Functions.Login;
import Framework.Functions.ScreenShot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//LoginPage	- Valid login - Invalid login - Blank email/password validation.
public class LoginTest {
    WebDriver driver;
    Login lin;


    @BeforeMethod
    public void preCondition() throws Exception{
        driver = common.openWeb(common.readProp("url"));
        lin = new Login();
    }

    @Test(priority = 1, description = "Valid Login Test")
    public void TC001_LoginWithValidCreds() throws Exception {
        common.log().info("====TC001 Valid Login started====");
        lin.validCreds();
        common.log().info("Positive test case for valid login executed successfully...");
    }

    @Test(priority = 2, description = "Invalid Email Login Test")
    public void TC002_LoginWithInvalidEmail() throws Exception {
        common.log().info("====TC002 Invalid Email Login started====");
        lin.inValidEmail();
        common.log().info("Negative test case for invalid email login executed successfully...");
    }

    @Test(priority = 3, description = "Invalid Password Login Test")
    public void TC003_LoginWithInvalidPassword() throws Exception {
        common.log().info("====TC003 Invalid Password Login started====");
        lin.inValidPass();
        common.log().info("Negative test case for invalid password login executed successfully...");
    }

    @Test(priority = 4, description = "Empty Fields Login Test")
    public void TC004_LoginWithEmptyFields() throws Exception {
        common.log().info("====TC004 Empty Fields Login started====");
        lin.emptyFields();
        common.log().info("Negative test case for empty fields login executed successfully...");
    }
    @AfterMethod
    public void postCondition(ITestResult result) throws Exception{
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
