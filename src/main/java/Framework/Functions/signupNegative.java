package Framework.Functions;

import Framework.Elements.HomePage;
import Framework.Elements.SignupPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static Framework.Functions.common.driver;
import static Framework.Functions.common.log;

public class signupNegative {
    SignupPage sign = new SignupPage(driver);
    HomePage home = new HomePage(driver);

    public void DuplicateUser() throws Exception {
        home.loginpage.click();
        sign.SignupName.sendKeys(common.readProp("full_name"));
        sign.SignupEmail.sendKeys(common.readProp("email"));
        sign.SignupBtn.click();

        // Validate the error message
        Assert.assertTrue(common.waitForVisibility(driver,sign.existMsg,10).isDisplayed(), sign.existMsg.getText());

        log().info("Negative test case executed successfully.");
    }

    public void checkSignupReqFields() throws Exception, FieldRequiredException {
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
                    // Stop execution if validation fails
                    throw new FieldRequiredException("Stopping execution: Required field is empty - " + element.getAttribute("data-qa"));
                }
            }
        }
        sign.createBtn.click();

        for (WebElement element : sign.formfields) {
            boolean isEmpty = element.getAttribute("value") == null || element.getAttribute("value").isEmpty();

            if (common.isRequired(element)) {
                if (isEmpty) {
                    log().info("Validation correctly triggered for required field: " + element.getAttribute("data-qa"));
                    // Stop execution if validation fails
                    Assert.fail("Stopping execution: Required field is empty - " + element.getAttribute("data-qa"));
                }
            }
        }
        // Required field has value, so test continues
        log().info("All Required field is correctly filled with Details");

        log().info("Negative test case for required fields executed successfully.");
    }
}