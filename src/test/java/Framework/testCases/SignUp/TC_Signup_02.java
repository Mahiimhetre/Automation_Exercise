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

public class TC_Signup_02 {
    private WebDriver driver;
    private SignUp si;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        si = new SignUp(driver);
    }

    @Test(priority = 1, description = "Duplicate User Registration Test")
    @Description("This test case checks the scenario where a user tries to register with an email that is already registered.")
    @Epic("User Registration")
    @Feature("Feature1: Registration")
    @Story("Story1: Duplicate Registration")
    @Severity(SeverityLevel.BLOCKER)
    public void duplicateRegistration() throws Exception{
        log().info("Executing TC_Signup_02: Duplicate User Registration started...");
        si.DuplicateUser();
        log().info("TC_Signup_02: Duplicate User Registration executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
