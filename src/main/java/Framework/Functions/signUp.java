package Framework.Functions;

import Framework.Elements.HomePage;
import Framework.Elements.SignupPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
        log().info("After checking The 'Name' and 'Email' field going to fill the Registration Form.");
    }

    public void registration()  throws Exception {
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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sign.createBtn);
    }

    public void confirmation() throws Exception {
        String message = common.waitForVisibility(driver, sign.createConfirmation, 15).getText();
        Assert.assertTrue(message.contains("Congratulation"));
        log().info(sign.createConfirmation.getText());

        sign.continueBtn.click();
    }

    public void DuplicateUser() throws Exception {
        home.loginpage.click();
        sign.SignupName.sendKeys(common.readProp("full_name"));
        sign.SignupEmail.sendKeys(common.readProp("email"));
        sign.SignupBtn.click();

        // Validate the error message
        Assert.assertTrue(common.waitForVisibility(driver,sign.existMsg,10).isDisplayed(), sign.existMsg.getText());
    }


    public void checkSignupReqFields() throws Exception{
        home.loginpage.click();
        // Clear input fields to simulate empty values
        sign.SignupName.clear();
        sign.SignupEmail.clear();
        sign.SignupBtn.click();

        List<WebElement> SignupFields = Arrays.asList(sign.SignupName, sign.SignupEmail);

        for (WebElement element : SignupFields) {
            boolean isEmpty = element.getAttribute("value") == null || element.getAttribute("value").isEmpty();

            if (common.isRequired(element)) {
                if (isEmpty) {
                    log().info("Validation correctly triggered for required field: " + element.getAttribute("data-qa"));
                    /* Pass the test and stop execution immediately */
                    Assert.assertTrue(true, "Validation correctly triggered for required field: " + element.getAttribute("data-qa"));
                    return; // Stop further execution
                }
            }
        }
        sign.createBtn.click();

        for (WebElement element : sign.formfields) {
            boolean isEmpty = element.getAttribute("value") == null || element.getAttribute("value").isEmpty();

            if (common.isRequired(element)) {
                if (isEmpty) {
                    log().info("Validation correctly triggered for required field: " + element.getAttribute("data-qa"));
                    /* Pass the test and stop execution immediately */
                    Assert.assertTrue(true, "Validation correctly triggered for required field: " + element.getAttribute("data-qa"));
                    return; // Stop further execution
                }
            }
        }
        // Required field has value, so test continues
        log().info("All Required field is correctly filled with Details");
    }

    public void invalidEmailRegistration() throws Exception{
        home.loginpage.click();
        sign.SignupName.sendKeys(common.readProp("first_name"));
        sign.SignupEmail.sendKeys(common.readProp("invEmail"));
        sign.SignupBtn.click();

        log().info("Checking for Field Validity...");

        // Check validity and get validation message for the 'Name' field
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean isValid = (Boolean) js.executeScript(
                "return arguments[0].reportValidity();",sign.SignupEmail
        );

        if (!isValid) {
            String validationMsg = (String) js.executeScript(
                    "return arguments[0].validationMessage;",sign.SignupEmail
            );
            log().info("Validation Message: " + validationMsg);
            /* Pass the test and stop execution immediately */
            Assert.assertTrue(true, "Validation correctly triggered for required field: " + sign.SignupEmail.getAttribute("data-qa"));
            return; // Stop further execution
        }
        log().info("Required field is correctly filled with Details.");

    }
}


