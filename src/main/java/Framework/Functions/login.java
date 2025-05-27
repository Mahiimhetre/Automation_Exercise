package Framework.Functions;

import Framework.Elements.HomePage;
import Framework.Elements.LoginPage;
import sun.reflect.annotation.ExceptionProxy;

import static Framework.Functions.common.driver;

public class login {
    HomePage hp = new HomePage(driver);
    LoginPage lp = new LoginPage(driver);

    public void validCreds() throws Exception{
        hp.loginpage.click();

        lp.loginBtn.sendKeys("email");
        lp.loginPass.sendKeys("password");

        lp.loginBtn.click();
    }

    public void inValidEmail() throws Exception{
        hp.loginpage.click();

        lp.loginEmail.sendKeys("invEmail");
        lp.loginPass.sendKeys("password");

        lp.loginBtn.click();
    }
    public void inValidPass() throws Exception{
        hp.loginpage.click();

        lp.loginEmail.sendKeys("email");
        lp.loginPass.sendKeys("invPassword");

        lp.loginBtn.click();
    }

    public void emptyFields() throws Exception{
        hp.loginpage.click();

        lp.loginEmail.clear();
        lp.loginPass.clear();

        lp.loginBtn.click();
    }
}

