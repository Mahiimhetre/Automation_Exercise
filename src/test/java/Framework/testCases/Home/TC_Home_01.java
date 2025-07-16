package Framework.testCases.Home;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import Framework.Functions.*;
import Framework.DriverManager;

import static Framework.Functions.common.*;

public class TC_Home_01 {
    private WebDriver driver;
    private Home home;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = common.openWeb(common.readProp("url"));
        home = new Home(driver);
    }

    @Test
    public void homeNavLinks() throws Exception{
        // Test case to verify the home page title
        log().info("Executing TC_Home_01: Verify Home Navigation Links...");
        home.checkProductPage();
        home.checkCartPage();
        home.checkLoginSignupPage();
        home.checkContactPage();
        log().info("TC_Home_001 Verify Home Navigation Links executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }
}
