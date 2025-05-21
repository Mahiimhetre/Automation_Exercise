package Framework.Functions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Common {
    public static WebDriver driver;

    public static WebDriver openWeb(String url){
        String path = System.getProperty("user.dir") +"/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        return driver;
    }
    public static Properties readProp(String key) throws Exception{
        File file = new File(System.getProperty("user.dir") + "/src/main/java/Framework/testData/AutomationExcercise.properties");
        FileInputStream fis = new FileInputStream(file);
        Properties value = new Properties();
        value.load(fis);
        return value;
    }

    public static void closeWeb(){
        driver.quit();
    }
}
