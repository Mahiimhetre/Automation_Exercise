package Framework.Functions;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class screenShot {

    public static void capture(WebDriver driver, String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File path = new File("screenshots/" + testName + "_" + timestamp + ".png");
        try {
            FileUtils.copyFile(src, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void captureOnFailure(WebDriver driver, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File("screenshots/" + result.getName() + ".png"));
                System.out.println("Screenshot saved for failed test: " + result.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
