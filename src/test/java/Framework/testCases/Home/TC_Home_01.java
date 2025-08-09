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

public class TC_Home_01 {
    private WebDriver driver;
    private Home home;

    @BeforeMethod
    public void preCondition() throws Exception     {
        driver = DriverManager.getDriver();
        driver = openWeb(readProp("url"));
        home = new Home(driver);
    }

    @Test(description = "TC_Home_01 - Verify navigation links to Product, Cart, Signup, Login, Contact pages")
    public void homeNavLinks() throws Exception {
        // Test case to verify the home page title
        log().info("TC_Home_01: Verifying navigation links to Product, Cart, Signup, Login, Contact pages");
        home.checkProductPage();
        home.checkCartPage();
        home.checkLoginSignupPage();
        home.checkContactPage();
        log().info("TC_Home_01: Successfully verified navigation links to Product, Cart, Signup, Login, Contact pages");
    }

    @AfterMethod
    public void postCondition(ITestResult result) throws Exception {
        ScreenShot.captureOnFailure(driver, result);
        closeWeb(driver);
    }
}
