package Framework.testCases.Login;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.log;

public class TC_Login_05 {
    private WebDriver driver;
    private Login login;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        login = new Login(driver);
    }

    @Test
    public void logout() throws Exception {
        common.log().info("Executing TC_Login_05: Logout from Application...");
        login.loginWithValidCreds();
        login.logoutFromApplication();
        common.log().info("TC_Login_05 Logout from Application executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
