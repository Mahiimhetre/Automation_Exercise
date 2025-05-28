package Framework.Functions;

import Framework.Elements.HomePage;
import Framework.Elements.LoginPage;

import static Framework.Functions.common.driver;

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

        lp.loginBtn.sendKeys("email");
        lp.loginPass.sendKeys("password");

        lp.loginBtn.click();
    }

    /**
     * This method performs a login operation with invalid email.
     * @throws Exception if an error occurs during the login process
     * @author Mr.MAHESH
     */
    public void inValidEmail() throws Exception{
        hp.loginpage.click();

        lp.loginEmail.sendKeys("invEmail");
        lp.loginPass.sendKeys("password");

        lp.loginBtn.click();
    }

    /**
     * This method performs a login operation with invalid password.
     * @throws Exception if an error occurs during the login process
     * @author Mr.MAHESH
     */
    public void inValidPass() throws Exception{
        hp.loginpage.click();

        lp.loginEmail.sendKeys("email");
        lp.loginPass.sendKeys("invPassword");

        lp.loginBtn.click();
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
    }
}

