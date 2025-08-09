package Framework.testCases.Login;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.log;
import static Framework.Functions.common.openWeb;
import static Framework.Functions.common.readProp;
import static Framework.Functions.common.closeWeb;

public class TC_Login_01 {
    private WebDriver driver;
    private Login login;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = openWeb(readProp("url"));
        login = new Login(driver);
    }

    @Test(priority = 0, description = "TC_Login_01 - Verify login as registered user")
    public void valCredLogin() throws Exception {
        log().info("TC_Login_01: Verifying login as registered user");
        login.loginWithValidCreds();
        log().info("TC_Login_01: Successfully verified login as registered user");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
