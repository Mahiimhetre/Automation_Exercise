package Framework.Functions;

import Framework.Elements.HomePage;
import Framework.Elements.SignupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Scanner;

import static Framework.Functions.common.driver;

public class signUp {
    HomePage home= new HomePage(driver);
    SignupPage sign = new SignupPage(driver);

    public void newUser() throws Exception {


        home.loginpage.click();

        sign.signupName.sendKeys(common.readProp("full_name"));
        sign.SignupEmail.sendKeys(common.readProp("email"));
        sign.SignupBtn.click();


        sign.title.click();
        sign.pass.sendKeys(common.readProp("password"));

        Select day = new Select(sign.day);
        day.selectByValue(common.readProp("day"));

        Select month = new Select(sign.month);
        month.selectByValue(common.readProp("month"));

        Select year = new Select(sign.year);
        year.selectByVisibleText(common.readProp("year"));

//        driver.switchTo().frame(sign.iframe);
        common.waitForClickability(driver,sign.newsletter,15).click();
        common.waitForClickability(driver,sign.offers,15).click();
//        driver.switchTo().defaultContent();


//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sign.newsletter);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sign.offers);

//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", sign.newsletter);
//       ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", sign.offers);

        sign.fname.sendKeys(common.readProp("first_name"));
        sign.lname.sendKeys(common.readProp("last_name"));
        sign.company.sendKeys(common.readProp("company"));
        sign.address1.sendKeys(common.readProp("street"));
        sign.address2.sendKeys(common.readProp("region"));

        Select country = new Select(sign.country);
        country.selectByVisibleText(common.readProp("country"));

        sign.state.sendKeys(common.readProp("state"));
        sign.city.sendKeys(common.readProp("city"));
        sign.zipcode.sendKeys(common.readProp("postal"));
        sign.mobile.sendKeys(common.readProp("mobile"));


        sign.createBtn.click();
    }
}
