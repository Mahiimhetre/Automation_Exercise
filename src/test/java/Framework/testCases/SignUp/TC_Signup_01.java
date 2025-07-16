package Framework.testCases.SignUp;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import java.security.PrivateKey;

import static Framework.Functions.common.*;

public class TC_Signup_01 {
    private WebDriver driver;
    private SignUp si;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        si = new SignUp(driver);
    }

    @Test (priority = 0, description = "New User Registration Test")
    public void newRegistration() throws Exception {
        log().info("Executing TC_Signup_01: New User Registration with valid Data started...");
        si.gotoSign();
        si.fillDetails();
        si.confirmation();
        log().info("TC_Signup_01: New User Registration with valid Data executed successfully...");

    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
