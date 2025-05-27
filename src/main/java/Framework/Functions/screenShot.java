package Framework.Functions;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Framework.Functions.common.log;

public class screenShot {

    // This method captures a screenshot and saves it to the specified path with a unique name based on the test name and timestamp.
    public static void capture(WebDriver driver, String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // Generate timestamp for unique file names
        File path = new File("screenshots/" + testName + "_" + timestamp + ".png"); // Save screenshot with test name and timestamp
        try {
            FileUtils.copyFile(src, path); // Copy the screenshot to the specified path
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method captures a screenshot when a test fails and attaches it to the Allure report.
    public static void captureOnFailure(WebDriver driver, ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            Thread.sleep(1000); // Wait for a second to ensure the page is fully loaded before capturing the screenshot.
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);  // Capture screenshot on failure
            try {
                FileUtils.copyFile(screenshot, new File("screenshots/" + result.getName() + ".png")); // Save the screenshot with the test name
                log().info("Screenshot saved for failed test: " + result.getName());
                Allure.addAttachment("Screenshot on Failure", FileUtils.openInputStream(screenshot)); // Attach the screenshot to Allure report
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
