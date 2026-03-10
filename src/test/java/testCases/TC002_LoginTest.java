package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
    @Test
    public void testLogin(){
        logger.info("***** Starting TC002_LoginTest *****");
        try{
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account");

            hp.clickLogin();
            logger.info("Clicked on Login");

            LoginPage lp = new LoginPage(driver);
            logger.info("Entering details for login...");

            lp.enterEmail("kartikdeshmukh58@gmail.com");
            lp.enterPassword("123456");

            lp.clickLogin();
            logger.info("Clicked on Login Button");

            logger.info("Validating Expected Message");
            String msg = lp.getConfirmationMessage();
            Assert.assertEquals(msg, "My Account");
        }catch(Throwable e){
            logger.error("Test Failed");
            Assert.fail();
        }

        logger.info("***** Finished TC002_LoginTest *****\n");
        
    }
}
