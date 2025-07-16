package Framework;

import org.openqa.selenium.WebDriver;

import static Framework.Functions.common.log;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void quitDriver() throws Exception {
        if (driver.get() != null) {
            log().info("Quitting the browser...");
            driver.get().quit();
            driver.remove();
        }
    }
}