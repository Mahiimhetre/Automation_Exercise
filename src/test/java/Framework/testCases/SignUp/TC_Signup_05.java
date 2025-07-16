package Framework.testCases.SignUp;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Signup_05 {
    private WebDriver driver;
    private SignUp si;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        si = new SignUp(driver);
    }

    @Test(priority = 4, description = "Signup with Special Character in Name Test")
    public void spCharSignup() throws Exception{
        log().info("Executing TC_Signup_05: Signup with Special Character in Name started...");
        si.specialCharInName();
        log().info("TC_Signup_05: Signup with Special Character in Name executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
