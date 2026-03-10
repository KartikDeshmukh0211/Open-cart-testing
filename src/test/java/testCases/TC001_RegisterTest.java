package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

/*
    Once we extent the base class, then our test class mostly contains the @Test methods only......
    Normally for 1 class we will maintain 1 test.........
*/
public class TC001_RegisterTest extends BaseClass {

    @Test
    void testRegister(){
        logger.info("***** Starting TC001_RegisterTest *****");

        try{
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account");

            hp.clickRegister();
            logger.info("Clicked on Register");

            RegisterPage rp = new RegisterPage(driver);
            logger.info("Entering details for registration...");

            rp.setFirstName(randomString().toUpperCase());
            rp.setLastName(randomString().toUpperCase());
            rp.setEmail(randomString() + "@gmail.com");
            rp.setTelephone(randomNumber());
            rp.setPassword(randomAlphaNumeric());
            rp.clickAgreement();
            rp.clickContinue();
            logger.info("Clicked on Continue(register)");

            logger.info("Validating Expected Message");
            String message = rp.getConfirmationMessage();

            Assert.assertEquals(message, "Your Account Has Been Created!");
        } catch(Throwable e){
            logger.error("Test Failed");
            logger.debug("Debug logs...");
            Assert.fail();
        }

        logger.info("***** Finished TC001_RegisterTest *****\n");
        
    }
}
