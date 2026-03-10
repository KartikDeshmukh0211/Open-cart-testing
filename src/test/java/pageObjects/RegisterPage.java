package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    // Constructor
    public RegisterPage(WebDriver driver){

        super(driver);
    }

    // Locators
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement aggrement;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfrimation;

    //Action Methods
    public void setFirstName(String name){
        firstName.sendKeys(name);
    }

    public void setLastName(String name){
        lastName.sendKeys(name);
    }

    public void setEmail(String val){
        email.sendKeys(val);
    }

    public void setTelephone(String number){
        telephone.sendKeys(number);
    }

    public void setPassword(String pass){
        password.sendKeys(pass);
        confirmPassword.sendKeys(pass);
    }

    // public void setConfirmPassword(String pass){
    //     password.sendKeys(pass);
    // }

    public void clickAgreement(){
        aggrement.click();
    }

    public void clickContinue(){
        btnContinue.click();
    }

    public String getConfirmationMessage(){
        // we will not do any validation in page object class......
        try{
            return msgConfrimation.getText();
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
