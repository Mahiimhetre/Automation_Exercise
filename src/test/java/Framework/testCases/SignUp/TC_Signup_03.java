package Framework.testCases.SignUp;

import io.qameta.allure.*;
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

public class TC_Signup_03 {
    private WebDriver driver;
    private SignUp si;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = openWeb(readProp("url"));
        si = new SignUp(driver);
    }

    @Test(priority = 2, description = "TC_Signup_03 - Verify error when signing up with empty required fields during registration")
    @Description("This test case checks the scenario where a user tries to register without filling in the required fields.")
    @Epic("User SignUp")
    @Feature("Feature1: SignUp")
    @Story("Story2: Empty Required Fields")
    @Severity(SeverityLevel.CRITICAL)
    public void EmptyReqFieldsSignup() throws Exception{
        log().info("TC_Signup_03: Verifying error when signing up with empty required fields");
        si.checkSignupReqFields();
        log().info("TC_Signup_03: Successfully verified error when signing up with empty required fields");

    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
