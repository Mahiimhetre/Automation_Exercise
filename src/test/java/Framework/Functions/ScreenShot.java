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

public class ScreenShot {
    /**
     * Captures a screenshot and saves it to the specified path with a unique name based on the test name and timestamp.
     * @author Mr.MAHESH
     * @param driver The WebDriver instance used for taking the screenshot.
     * @param testName The name of the test, used to create a unique filename for the screenshot.
     */
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

    /**
     * Captures a screenshot on test failure and attaches it to the Allure report.
     *
     * @param driver The WebDriver instance used for taking the screenshot.
     * @param result The ITestResult instance containing the test result details.
     * @throws Exception If an error occurs while capturing or saving the screenshot.
     * @author Mr.MAHESH
     */
    public static void captureOnFailure(WebDriver driver, ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            log().info("Test failed: " + result.getName() + ". Capturing screenshot...");
            Thread.sleep(1000); // Wait for a second to ensure the page is fully loaded before capturing the screenshot.
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);  // Capture screenshot on failure
            try {
                // Save the screenshot to the specified directory with the test name
//                FileUtils.copyFile(screenshot, new File("screenshots/" + result.getName() + ".png"));
                log().info("Screenshot saved for failed test: " + result.getName());
                Allure.addAttachment("Screenshot on Failure", FileUtils.openInputStream(screenshot)); // Attach the screenshot to Allure report
            } catch (IOException e) {
                log().info("Failed to capture screenshot for test: " + result.getName());
                e.printStackTrace();
            }
        }
    }
}
