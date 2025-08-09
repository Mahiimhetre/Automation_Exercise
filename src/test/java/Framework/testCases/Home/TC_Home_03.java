package Framework.testCases.Home;

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

public class TC_Home_03 {
    private WebDriver driver;
    private Home home;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = openWeb(readProp("url"));
        home = new Home(driver);
    }

    @Test(description = "TC_Home_03 - Verify subscription with invalid email format")
    public void invEmailSubscribe() throws Exception {
        log().info("TC_Home_03: Verifying subscription using invalid email format");
        home.checkSubscribeInvalidEmail();
        log().info("TC_Home_03: Successfully verified subscription using invalid email format");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
