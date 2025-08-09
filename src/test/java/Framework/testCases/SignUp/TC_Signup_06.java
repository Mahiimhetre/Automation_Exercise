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

public class TC_Signup_06 {
    private WebDriver driver;
    private SignUp si;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = openWeb(readProp("url"));
        si = new SignUp(driver);
    }

    @Test(description = "TC_Signup_06 - Verify case insensitivity in email during signup")
    public void caseInsensitivitySignup() throws Exception {
        log().info("TC_Signup_06: Verifying case insensitivity in email during signup");
        si.caseInsensitiveEmail();
        log().info("TC_Signup_06: Successfully verified case insensitivity in email during signup");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
