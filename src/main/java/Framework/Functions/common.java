package Framework.Functions;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

public class common {
    public static WebDriver driver;

    public static WebDriver openWeb(String url) throws Exception {

        //--------------------------------For ChromeDriver Only---------------------------------------------------------
        String path = System.getProperty("user.dir") +"/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        log().info("==============ChromeDriver Initialized Successfully.==============");
        driver.manage().window().maximize();
        log().info("==============Accessing '" + url + "' webpage using ChromeDriver.==============");
        driver.get(url);


        //-------------------------------*********************----------------------------------------------------------

        //----------------------------------For Any Browser ------------------------------------------------------------
//        String hubUrl = "http://localhost:4444/wd/hub";  //instead of this you can use "http://192.168.13.1:4444/wd/hub";
//        DesiredCapabilities cap = new DesiredCapabilities();
//
//        cap.setPlatform(Platform.WIN11);
//        cap.setBrowserName("chrome");
//
//        WebDriver driver = new RemoteWebDriver(new URL(hubUrl), cap);
//        driver.get(path);

        //-------------------------------*********************----------------------------------------------------------

        return driver;
    }


    public static Logger log() throws Exception{
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/log4j.properties"));
        PropertyConfigurator.configure(prop);
        PropertyConfigurator.configure("src/log4j.properties");
        BasicConfigurator.configure();
        return Logger.getLogger(common.class.getName());
    }

    public static String readProp(String key) throws Exception{
        File file = new File(System.getProperty("user.dir") + "/src/main/java/Framework/testData/AutomationExcercise.properties");
        FileInputStream fis = new FileInputStream(file);
        Properties value = new Properties();
        value.load(fis);
        return value.getProperty(key);
    }

    public static boolean isRequired(WebElement element) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean result = (Boolean) js.executeScript(
                "return arguments[0].hasAttribute('required');", element);
        return result; // Return the actual attribute check result
    }

    // Implicit Wait (still applies globally)
    public static void impWait(WebDriver driver, int sec) throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
        log().info("Implicit wait set to '" + sec + "' seconds.");
    }

    // Explicit Wait - Element to be visible
    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int sec) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        log().info("Explicit wait for Element Visibility: '" + element.getText() + "' set to " + sec + " seconds.");
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Explicit Wait - Element to be clickable
    public static WebElement waitForClickability(WebDriver driver, WebElement element, int sec) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        log().info("Explicit wait for Element Clickability: '" + element.getText() + "' set to " + sec + " seconds.");
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Explicit Wait - Title contains specific text
    public static boolean waitForTitleContains(WebDriver driver, String partialTitle, int sec) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        log().info("Explicit wait for WebDriver Title: '" + driver.getTitle() + "' set to " + sec + " seconds.");
        return wait.until(ExpectedConditions.titleContains(partialTitle));
    }

    // Explicit Wait - Alert is present
    public static Alert waitForAlert(WebDriver driver, int sec) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        log().info("Explicit wait for Alert Presence set to " + sec + " seconds.");
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public static WebElement fluentWait(WebDriver driver, WebElement element, int timeoutSec, int pollingSec) throws Exception{
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofSeconds(pollingSec))
                .ignoring(NoSuchElementException.class)
                .withMessage("Waiting for element to become visible...");

        return wait.until(driver1 -> {
            if (element.isDisplayed()) {
                System.out.println("Element is now visible: " + element);
                return element;
            } else {
                System.out.println("Still waiting for element...");
                throw new NoSuchElementException("Element not visible yet.");
            }
        });
    }

    public static void closeWeb() throws Exception {
        log().info("==============WebDriver signing off==============");
        driver.quit();
    }
}
