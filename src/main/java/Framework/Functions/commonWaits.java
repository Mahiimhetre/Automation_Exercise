package Framework.Functions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * This method waits for 5 Seconds by default
 * @author : mhetrem15@gmail.com
 * @param : no params
 * @return : no return value
 */
public class commonWaits {

    public void defWait() throws InterruptedException{
        Thread.sleep(5000);
    }

    //Implicit Wait
    public static void impWait(WebDriver driver,int sec){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }


    //Explicit Wait - Element to be visible
    public static WebElement waitForVisibility(WebDriver driver, By locator, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Explicit Wait - Element to be clickable
    public static WebElement waitForClickability(WebDriver driver, By locator, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Explicit Wait - Presence of element in DOM
    public static WebElement waitForPresence(WebDriver driver, By locator, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    //Explicit Wait - Title contains specific text
    public static boolean waitForTitleContains(WebDriver driver, String partialTitle, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        return wait.until(ExpectedConditions.titleContains(partialTitle));
    }

    //Explicit Wait - Alert is present
    public static Alert waitForAlert(WebDriver driver, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    //Fluent Wait - Custom polling and exception handling
    public static WebElement fluentWait(WebDriver driver, final By locator, int sec, int pollingSec) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(sec))
                .pollingEvery(Duration.ofSeconds(pollingSec))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver1 -> driver1.findElement(locator));
    }

    }

