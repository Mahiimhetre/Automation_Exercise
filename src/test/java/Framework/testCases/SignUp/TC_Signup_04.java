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

public class TC_Signup_04 {
    private WebDriver driver;
    private SignUp si;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        si = new SignUp(driver);
    }

    @Test(priority = 3, description = "Signup with Invalid Email Test")
    @Description("This test case checks the scenario where a user tries to Signup with an invalid email format.")
    @Epic("User Registration")
    @Feature("Feature1: Registration")
    @Story("Story3: Invalid Email Registration")
    @Severity(SeverityLevel.NORMAL)
    public void invEmailSignup() throws Exception{
        log().info("Executing TC_Signup_04: Signup with Invalid Email started...");
        si.invalidEmailRegistration();
        log().info("TC_Signup_04: Signup with Invalid Email executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
