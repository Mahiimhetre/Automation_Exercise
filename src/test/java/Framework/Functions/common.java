package Framework.Functions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class common {
    public static WebDriver driver;

    /**
     * This method initializes the WebDriver for Chrome and opens the specified URL.
     * @author : Mr.MAHESH
     * @param url : URL to be opened in the browser
     * @return : WebDriver instance
     * @throws Exception
     */
    public static WebDriver openWeb(String url) throws Exception {
        WebDriverManager.chromedriver().setup(); // Automatically sets up the ChromeDriver executable
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new"); // Run Chrome in headless mode
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize(); // Maximize the browser window
        log().info("Launching browser and navigating to: " + url);
        driver.get(url);
        removeAds();
        driver.get(url);
        return driver; // Return the WebDriver instance
    }

    public static void removeAds() throws Exception {
        // This method is to remove ads from the webpage using JavaScript
        try {
            // Find all ad elements
            List<WebElement> ads = driver.findElements(By.xpath("//iframe[@title='Advertisement']"));
            if( !ads.isEmpty() && ads.size() > 0) {
                log().info("Advertisement iframes removed from the current page...");

                // Remove each ad using JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                for (WebElement ad : ads) {
                    js.executeScript("arguments[0].remove();", ad);
                }
            }
        }
        catch (Exception e) {log().info("");}
    }

    /**
     * Initializes and configures the Log4j logger using the specified properties file.
     * This logger should be used throughout the framework instead of System.out.println()
     * to provide structured logging with timestamps, log levels, and source location.
     *
     * @return Logger instance configured for the framework
     * @throws Exception if the properties file cannot be loaded
     * @author Mr.MAHESH
     */
    public static Logger log() throws Exception{
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/log4j.properties"));         // path of your log4j.properties file
        PropertyConfigurator.configure(prop);
        PropertyConfigurator.configure("src/test/resources/log4j.properties");  // path of your log4j.properties file
        BasicConfigurator.configure();
        return Logger.getLogger(common.class.getName());
    }

    /**
     * This method reads the properties file and returns the value of the specified key.
     * @param key
     * @return : String value of the key from the properties file
     * @throws Exception
     * @author : Mr.MAHESH
     */
    public static String readProp(String key) throws Exception{
        File file = new File(System.getProperty("user.dir") + "/src/test/java/Framework/testData/AutomationExcercise.properties");
        FileInputStream fis = new FileInputStream(file);
        Properties value = new Properties();
        value.load(fis);
        return value.getProperty(key);
    }

    /**
     * This method checks if the WebElement is displayed on the page.
     * @param element : WebElement to be checked
     * @return : boolean value indicating whether the element is displayed or not
     * @throws Exception
     * @author : Mr.MAHESH
     */
    public static boolean isRequired(WebElement element) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean result = (Boolean) js.executeScript("return arguments[0].hasAttribute('required');", element);
        return result; // Return the actual attribute check result
    }

    public static boolean isEmpty(WebElement element) throws Exception {
        // This method checks if the WebElement is empty
        boolean isEmpty = (element.getAttribute("value") == null) || element.getAttribute("value").isEmpty();
        String value = element.getAttribute("value");
        return value == null || value.isEmpty(); // Return true if the value is null or empty
    }

    // This method checks the validity of a form field using JavaScript and logs the validation message if it fails.
    public static boolean checkFieldValidity(WebDriver driver, WebElement element) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean isValid = (Boolean) js.executeScript(
                "return arguments[0].reportValidity();", element
        );

        if (!isValid) {
            String validationMsg = (String) js.executeScript(
                    "return arguments[0].validationMessage;", element);
            log().info("Validation Message: " + validationMsg);
            return true; // Return true if validation fails
        }
        return false; // Return false if validation passes
    }

    // Implicit Wait (still applies globally)
    public static void impWait(WebDriver driver, int sec) throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
        log().info("Setting implicit wait to " + sec + " seconds.");
    }

    //Explicit Wait - Element to be present
    public static WebElement waitForPresence(WebDriver driver, By locator, int sec) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        log().info("Waiting up to " + sec + " seconds for the element to be present: " + locator.toString());
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Explicit Wait - Element to be visible
    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int sec) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        log().info("Waiting up to " + sec + " seconds for the element to be visible: " + element.toString());
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Explicit Wait - Element to be clickable
    public static WebElement waitForClickability(WebDriver driver, WebElement element, int sec) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        log().info("Waiting up to " + sec + " seconds for the element to be clickable: " + element.toString());
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Explicit Wait - Title contains specific text
    public static boolean waitForTitleContains(WebDriver driver, String partialTitle, int sec) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        log().info("Waiting up to " + sec + " seconds for the title to contain: " + partialTitle);
return wait.until(ExpectedConditions.titleContains(partialTitle));
    }

    // Explicit Wait - Alert is present
    public static Alert waitForAlert(WebDriver driver, int sec) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        log().info("Waiting up to " + sec + " seconds for an alert to appear...");
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // Fluent Wait - Waits for an element to become visible with a specified timeout and polling interval.
    public static WebElement fluentWait(WebDriver driver, WebElement element, int timeoutSec, int pollingSec) throws Exception {
        log().info("Waiting for element to become visible using fluent wait...");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofSeconds(pollingSec))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver1 -> {
            if (element.isDisplayed()) {
                try {
                    log().info("Element became visible: " + element.toString());
                } catch (Exception e) {
                    System.out.print("");
                }
                return element;
            } else {
                throw new NoSuchElementException("Element not visible yet.");
            }
        });


    }

    // This method closes the WebDriver instance and quits the browser.
    public static void closeWeb(WebDriver driver) throws Exception {
        log().info("Quitting the browser...");
        driver.quit();
    }
}
