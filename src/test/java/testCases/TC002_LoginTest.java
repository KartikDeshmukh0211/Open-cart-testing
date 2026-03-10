package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
    @Test
    public void testLogin(){
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();

        LoginPage lp = new LoginPage(driver);
        lp.enterEmail("kartikdeshmukh58@gmail.com");
        lp.enterPassword("123456");
        lp.clickLogin();

        String msg = lp.getConfirmationMessage();
        Assert.assertEquals(msg, "My Account");
    }
}
