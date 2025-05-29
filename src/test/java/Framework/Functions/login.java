package Framework.Functions;

import Framework.Elements.HomePage;
import Framework.Elements.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static Framework.Functions.common.driver;
import static Framework.Functions.common.log;

public class login {
    HomePage hp = new HomePage(driver);
    LoginPage lp = new LoginPage(driver);

    /**
     * This method performs a login operation with valid credentials.
     * @throws Exception if an error occurs during the login process
     * @author Mr.MAHESH
     */
    public void validCreds() throws Exception{
        hp.loginpage.click();

        lp.loginEmail.sendKeys(common.readProp("email"));
        lp.loginPass.sendKeys(common.readProp("password"));

        lp.loginBtn.click();
        Assert.assertTrue(common.waitForVisibility(driver, lp.loggedInUser,10).isDisplayed(), "Login failed or user is not logged in...");
    }

    /**
     * This method performs a login operation with invalid email.
     * @throws Exception if an error occurs during the login process
     * @author Mr.MAHESH
     */
    public void inValidEmail() throws Exception{
        hp.loginpage.click();

        lp.loginEmail.sendKeys(common.readProp("invEmail"));
        lp.loginPass.sendKeys(common.readProp("password"));

        lp.loginBtn.click();
        log().info("Checking for Credentials Validity...");

        if (common.checkFieldValidity(driver, lp.loginEmail)) {
            Assert.assertTrue(true, "Validation correctly triggered for required field: " + lp.loginEmail.getAttribute("data-qa"));
            return; // Stop further execution if validation fails
        }else {
            Assert.fail("Error message not displayed for invalid email.");
        }
    }

    /**
     * This method performs a login operation with invalid password.
     * @throws Exception if an error occurs during the login process
     * @author Mr.MAHESH
     */
    public void inValidPass() throws Exception{
        hp.loginpage.click();

        lp.loginEmail.sendKeys(common.readProp("email"));
        lp.loginPass.sendKeys(common.readProp("invPassword"));

        lp.loginBtn.click();
        log().info("Checking for Credentials Validity...");
        if(common.waitForVisibility(driver, lp.errorMsg, 10).isDisplayed()) {
            Assert.assertTrue(true, "Validation correctly triggered for invalid email/password: " + lp.errorMsg.getText());
        }
        else {
            Assert.fail("Error message not displayed for invalid credentials.");
        }
    }

    /**
     * This method performs a login operation with empty fields.
     * @throws Exception if an error occurs during the login process
     * @author Mr.MAHESH
     */
    public void emptyFields() throws Exception{
        hp.loginpage.click();

        lp.loginEmail.clear();
        lp.loginPass.clear();

        lp.loginBtn.click();

        for (WebElement element : lp.loginReqFields) {
            boolean isEmpty = element.getAttribute("value") == null || element.getAttribute("value").isEmpty();

            if (common.isRequired(element)) {
                if (isEmpty) {
                    log().info("Validation correctly triggered for required field: " + element.getAttribute("data-qa"));
                    /* Pass the test and stop execution immediately */
                    Assert.assertTrue(true, "Validation correctly triggered for required field: " + element.getAttribute("data-qa"));
                    return; // Stop further execution
                }
            } else {
                Assert.fail("Validation not correctly triggered for required field: " + element.getAttribute("data-qa"));
            }
        }
    }
}

