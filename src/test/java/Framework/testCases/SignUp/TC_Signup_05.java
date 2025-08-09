package Framework.testCases.SignUp;

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

public class TC_Signup_05 {
    private WebDriver driver;
    private SignUp si;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = openWeb(readProp("url"));
        si = new SignUp(driver);
    }

    @Test(priority = 4, description = "TC_Signup_05 - Verify registration with special characters in name field")
    public void spCharSignup() throws Exception{
        log().info("TC_Signup_05: Verifying registration with special characters in name field");
        si.specialCharInName();
        log().info("TC_Signup_05: Successfully verified registration with special characters in name field");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
