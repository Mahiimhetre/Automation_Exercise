package Framework.testCases;

import Framework.Functions.Home;
import Framework.Functions.ScreenShot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import Framework.Functions.common;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Framework.Functions.common.log;

public class HomeTest {
    WebDriver driver;
    Home home;

    @BeforeMethod
    public void preCondition() throws Exception {
        // Code to set up the test environment, e.g., initializing WebDriver, loading properties, etc.
        log().info("Setting up the test environment...");
        driver = common.openWeb(common.readProp("url"));
        home = new Home();
    }

    @Test
    public void TC_Home_01() throws Exception{
        // Test case to verify the home page title
        log().info("Executing TC_Home_001: Verify Home Page Title");
        home.checkProductPage();
        home.checkCartPage();
        home.checkLoginSignupPage();
        home.checkContactPage();
    }

    @Test
    public void TC_Home_02() throws Exception {
        log().info("Executing TC_Home_002: Verify Subscribe Functionality");
        home.checkSubscribe();
        log().info("TC_Home_002 Verify Subscribe Functionality executed successfully...");
    }

    @Test
    public void TC_Home_03() throws Exception {
//        negative
        log().info("Executing TC_Home_003: Verify Subscribe Functionality with Invalid Email");
        home.checkSubscribeInvalidEmail();
        log().info("TC_Home_003 Verify Subscribe Functionality with Invalid Email executed successfully...");
    }

    @Test
    public void TC_Home_04() throws Exception {
        log().info("Executing TC_Home_004: Verify Login with Valid Credentials");
        home.checkEmptySubscribeEmail();
        log().info("TC_Home_004 Verify Login with Valid Credentials executed successfully...");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        // Code to handle the result of the test, e.g., logging, taking screenshots, etc.
        ScreenShot.captureOnFailure(driver, result);
        common.closeWeb(driver);
    }

}
