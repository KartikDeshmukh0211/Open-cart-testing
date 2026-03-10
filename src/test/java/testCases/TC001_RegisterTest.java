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
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        RegisterPage rp = new RegisterPage(driver);
        rp.setFirstName(randomString().toUpperCase());
        rp.setLastName(randomString().toUpperCase());
        rp.setEmail(randomString() + "@gmail.com");
        rp.setTelephone(randomNumber());
        rp.setPassword(randomAlphaNumeric());
        rp.clickAgreement();
        rp.clickContinue();

        String message = rp.getConfirmationMessage();

        Assert.assertEquals(message, "Your Account Has Been Created!");
    }
}
