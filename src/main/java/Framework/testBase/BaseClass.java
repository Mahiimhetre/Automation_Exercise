package Framework.testBase;

import Framework.Functions.common;
import com.beust.jcommander.Parameter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static Framework.Functions.common.driver;
import static Framework.Functions.common.log;

public class BaseClass {

    @Parameter(names = {"os", "browser"})
    public void setup(String os, String br) throws Exception{

        switch(br.toLowerCase()){
            case "chrome" :driver = new ChromeDriver(); break;
            case "edge" : driver = new EdgeDriver(); break;
            case "firefox" :driver = new FirefoxDriver(); break;
            default  : log().info("Invalid Browser Name...");
        }
        driver.manage().deleteAllCookies();
        common.impWait(driver,10);


    }
}
