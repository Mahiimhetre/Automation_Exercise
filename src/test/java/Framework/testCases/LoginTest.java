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

    @Test
    public void TC_Login_01() throws Exception {
        common.log().info("Executing TC_Login_01: Valid Login started...");
        lin.loginWithValidCreds();
        common.log().info("TC_Login_01 Valid Login executed successfully...");
    }

    @Test
    public void TC_Login_02() throws Exception {
        common.log().info("Executing TC_Login_02: Invalid Email login started...");
        lin.invalidEmailLogin();
        common.log().info("TC_Login_02 Invalid Email login executed successfully...");
    }

    @Test
    public void TC_Login_03() throws Exception {
        common.log().info("Executing TC_Login_03: Invalid Password login started...");
        lin.invalidPassLogin();
        common.log().info("TC_Login_03 Invalid Password login executed successfully...");
    }

    @Test
    public void TC_Login_04() throws Exception {
        common.log().info("Executing TC_Login_04: Blank Fields login started...");
        lin.blankFieldsLogin();
        common.log().info("TC_Login_04 Blank Fields login executed successfully...");
    }

    @Test
    public void TC_Login_05() throws Exception {
        common.log().info("Executing TC_Login_05: Logout from Application started...");
        lin.loginWithValidCreds();
        lin.logoutFromApplication();
        common.log().info("TC_Login_05 Logout from Application executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception{
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
