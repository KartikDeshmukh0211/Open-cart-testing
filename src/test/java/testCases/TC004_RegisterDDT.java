package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC004_RegisterDDT extends BaseClass {
    @Test(dataProvider = "RegisterData", dataProviderClass = DataProviders.class, groups = {"DataDriven"})
    void testRegisterDDT(String email, String password){
        logger.info("***** Starting TC004_RegisterDDT *****");

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
            rp.setEmail(email);
            rp.setTelephone(randomNumber());
            rp.setPassword(password);
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

        logger.info("***** Finished TC004_RegisterDDT *****\n");
        
    }
}
