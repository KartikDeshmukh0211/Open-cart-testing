package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
    Data is VALID ----> 1. Login successfull -- test pass -- Logout
                        2. Login unsuccessfull -- test fail

    Data is INVALID ----> 1. Login successfull -- test fail -- Logout
                          2. Login unsuccessfull -- test pass
*/

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void testLoginDDT(String email, String password, String result){
        logger.info("***** Starting TC003_LoginDDT *****");
        try{
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account");

            hp.clickLogin();
            logger.info("Clicked on Login");

            LoginPage lp = new LoginPage(driver);
            logger.info("Entering details for login...");

            lp.enterEmail(email);
            lp.enterPassword(password);

            lp.clickLogin();
            logger.info("Clicked on Login Button");

            logger.info("Validating Expected Message");
            MyAccountPage mcp = new MyAccountPage(driver);
            boolean target = mcp.getConfirmationMessage();

            if(result.equalsIgnoreCase("Valid")){
                if(target){
                    // login is success
                    mcp.clickLogout();
                    Assert.assertTrue(true);
                }else{
                    Assert.fail();
                }
            }else{
                if(target){
                    mcp.clickLogout();
                    Assert.fail();
                }else{
                    Assert.assertTrue(true);
                }
            }        
        }catch(Throwable e){
            logger.error("Test Failed");
            Assert.fail();
        }

        logger.info("***** Finished TC003_LoginDDT *****\n");
    }
    
}
