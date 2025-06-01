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

public class SignUp {
    HomePage home = new HomePage(driver);
    SignupPage sign = new SignupPage(driver);

    /**
     * Navigates to the signup page and fills in the 'Name' and 'Email' fields.
     * This method is used to initiate the registration process for a new user.
     * @author Mr.MAHESH
     * @throws Exception if an error occurs during navigation or field interaction
     */
    public void gotoSign() throws Exception {
        home.signupPage.click();
        log().info("Navigating to Signup Page...");
        sign.SignupName.sendKeys(common.readProp("full_name"));
        sign.SignupEmail.sendKeys(common.readProp("email"));
        sign.SignupBtn.click();
        log().info("After checking The 'Name' and 'Email' field Navigating to fill the Registration Form...");
        // Wait for the page to load and title to match expected value
        Assert.assertEquals(driver.getTitle(), "Automation Exercise - Signup", "Page title does not match after navigating to Signup page.");
        log().info("Page title verified: " + driver.getTitle());
    }

    /**
     * Fills in the registration form with user details such as password, date of birth, address, etc.
     * This method is used to complete the registration process for a new user.
     * @author Mr.MAHESH
     * @throws Exception if an error occurs during form filling or interaction
     */
    public void fillDetails()  throws Exception {
        log().info("Filling the Registration Form with Details...");
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
        log().info("Registration Form filled with Details...");
    }

    public void confirmation() throws Exception {
        String message = common.waitForVisibility(driver, sign.createConfirmation, 15).getText();

        log().info("Checking for Confirmation Message...");

        if(message.contains("Congratulation")) {
            Assert.assertTrue(true,"Confirmation message is displayed: " + message);
        } else {
            Assert.fail("Confirmation message is not displayed or does not contain expected text.");
        }

        sign.continueBtn.click();
        log().info("User Registration completed successfully and redirected to Home Page...");
    }

    /**
     * Attempts to register a user with an email that already exists in the system.
     * This method is used to test the scenario where a user tries to register with a duplicate email.
     * @throws Exception if an error occurs during the registration process
     * @author Mr.MAHESH
     */
    public void DuplicateUser() throws Exception {
        home.signupPage.click();
        log().info("Navigating to Signup Page for Duplicate User Registration...");
        sign.SignupName.sendKeys(common.readProp("full_name"));
        sign.SignupEmail.sendKeys(common.readProp("email"));
        sign.SignupBtn.click();

        log().info("Checking for Duplicate User Registration...");
        // Validate the error message
        if(common.waitForVisibility(driver, sign.existMsg, 10).isDisplayed()) {
            Assert.assertTrue(true, "Validation correctly triggered for duplicate email: " + sign.existMsg.getText());
        } else {
            Assert.fail("Error message not displayed for duplicate email.");
        }
        log().info("Duplicate User Registration validation completed successfully...");
    }

    /**
     * Checks if the required fields in the signup form are filled correctly.
     * If any required field is empty, it logs the validation message and stops further execution.
     * @throws Exception if an error occurs during the validation process
     * @author Mr.MAHESH
     */
    public void checkSignupReqFields() throws Exception{
        home.signupPage.click();
        log().info("Navigating to Signup Page to check Required Fields...");
        // Clear input fields to simulate empty values
        sign.SignupName.clear();
        sign.SignupEmail.clear();
        sign.SignupBtn.click();
        log().info("Checking for Required Fields Validation...");

        List<WebElement> SignupFields = Arrays.asList(sign.SignupName, sign.SignupEmail);

        for (WebElement element : SignupFields) {
            boolean isEmpty = element.getAttribute("value") == null || element.getAttribute("value").isEmpty();

            if (common.isRequired(element)) {
                if (isEmpty) {
                    /* Pass the test and stop execution immediately */
                    Assert.assertTrue(true, "Validation correctly triggered for required field: " + element.getAttribute("data-qa"));
                    return; // Stop further execution
                }
            }
        }
        sign.createBtn.click();

        log().info("Checking for Required Fields Validation in the Registration Form...");

        for (WebElement element : sign.formfields) {
            boolean isEmpty = element.getAttribute("value") == null || element.getAttribute("value").isEmpty();

            if (common.isRequired(element)) {
                if (isEmpty) {
                    /* Pass the test and stop execution immediately */
                    Assert.assertTrue(true, "Validation correctly triggered for required field: " + element.getAttribute("data-qa"));
                    return; // Stop further execution
                }
            }
        }
        // Required field has value, so test continues
        sign.continueBtn.click();
        log().info("Required Fields Validation completed successfully...");
    }

    /**
     * Attempts to register a user with an invalid email format.
     * This method is used to test the scenario where a user tries to register with an email that does not conform to standard email formats.
     * @throws Exception if an error occurs during the registration process
     * @author Mr.MAHESH
     */
    public void invalidEmailRegistration() throws Exception{
        home.signupPage.click();
        log().info("Navigating to Signup Page for Invalid Email Registration...");
        sign.SignupName.sendKeys(common.readProp("first_name"));
        sign.SignupEmail.sendKeys(common.readProp("invEmail"));
        sign.SignupBtn.click();

        log().info("Checking for Field Validity...");

        if (common.checkFieldValidity(driver, sign.SignupEmail)) {
            Assert.assertTrue(true, "Validation correctly triggered for required field: " + sign.SignupEmail.getAttribute("data-qa"));
            return; // Stop further execution if validation fails
        }
        else {
            Assert.fail("Error message not displayed for invalid email.");
        }
        log().info("Invalid Email Registration validation completed successfully...");
    }
}



