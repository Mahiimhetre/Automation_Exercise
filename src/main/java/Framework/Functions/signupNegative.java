package Framework.Functions;

import Framework.Elements.SignupPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static Framework.Functions.common.driver;
import static Framework.Functions.common.log;

public class signupNegative {
    SignupPage sign = new SignupPage(driver);

    public void DuplicateUser() throws Exception {
        sign.SignupName.sendKeys(common.readProp("full_name"));
        sign.SignupEmail.sendKeys(common.readProp("email"));
        sign.SignupBtn.click();

        // Validate the error message
        Assert.assertTrue(sign.existMsg.isDisplayed(), sign.existMsg.getText());

        log().info("Negative test case executed successfully.");
    }

    public void checkSignupReqFields() throws Exception {

        sign.SignupName.clear();
        sign.SignupEmail.clear();
        sign.SignupBtn.click();

        List<WebElement> reqElements = Arrays.asList(sign.SignupEmail, sign.SignupName);

        for (WebElement element : reqElements) {
            Assert.assertFalse(common.isRequired(element), "***** Assertion Failed ****");
        }

        List<WebElement> reqFields = sign.RequiredFields;

        for (WebElement element : reqFields) {
            Assert.assertFalse(common.isRequired(element), "***** Assertion Failed ****");
        }


    }
}
