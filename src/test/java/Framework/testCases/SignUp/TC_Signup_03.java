package Framework.testCases.SignUp;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Signup_03 {
    private WebDriver driver;
    private SignUp si;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        si = new SignUp(driver);
    }

    @Test(priority = 2, description = "Registration with Empty Required Fields Test")
    @Description("This test case checks the scenario where a user tries to register without filling in the required fields.")
    @Epic("User SignUp")
    @Feature("Feature1: SignUp")
    @Story("Story2: Empty Required Fields")
    @Severity(SeverityLevel.CRITICAL)
    public void EmptyReqFieldsSignup() throws Exception{
        log().info("Executing TC_Signup_03: Registration with Empty Required Fields started...");
        si.checkSignupReqFields();
        log().info("TC_Signup_03: Registration with Empty Required Fields executed successfully...");

    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
