package Framework.Functions;

import Framework.Elements.HomePage;
import Framework.Elements.SignupPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static Framework.Functions.common.driver;
import static Framework.Functions.common.log;

public class signUp {
    HomePage home = new HomePage(driver);
    SignupPage sign = new SignupPage(driver);

    public void gotoSign() throws Exception {

        home.loginpage.click();

        sign.SignupName.sendKeys(common.readProp("full_name"));
        sign.SignupEmail.sendKeys(common.readProp("email"));
        sign.SignupBtn.click();
    }

    public void registration()  throws Exception {
        Actions action = new Actions(driver);
        sign.title.click();
        sign.pass.sendKeys(common.readProp("password"));

        Select day = new Select(sign.day);
        day.selectByValue(common.readProp("day"));

        Select month = new Select(sign.month);
        month.selectByValue(common.readProp("month"));

        Select year = new Select(sign.year);
        year.selectByVisibleText(common.readProp("year"));

//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sign.newsletter);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sign.offers);
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", sign.newsletter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", sign.offers);


        action.moveToElement(sign.fname).click().sendKeys(common.readProp("first_name")).perform();

//        sign.fname.sendKeys(common.readProp("first_name"));
        sign.lname.sendKeys(common.readProp("last_name"));
        sign.company.sendKeys(common.readProp("company"));
        sign.address1.sendKeys(common.readProp("street"));
        sign.address2.sendKeys(common.readProp("region"));

        Select country = new Select(sign.country);
        country.selectByVisibleText(common.readProp("country"));

        action.moveToElement(sign.state).click().sendKeys(common.readProp("state")).perform();
        sign.city.sendKeys(common.readProp("city"));
        sign.zipcode.sendKeys(common.readProp("postal"));
        sign.mobile.sendKeys(common.readProp("mobile"));

        action.moveToElement(sign.createBtn).click().click().perform();
    }
    public void confirmation() throws Exception {

        Assert.assertTrue(common.waitForVisibility(driver, sign.confirmation, 15).contains("Congratulation"));
        log().info(sign.confirmation.getText());

        sign.continueBtn.click();
    }

}

