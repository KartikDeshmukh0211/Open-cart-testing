package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
// import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    // WebDriver driver;

    //constructor
    public HomePage(WebDriver driver){
        // this.driver = driver;
        // PageFactory.initElements(driver, this); // this is madatory

        super(driver);
    }

    // Locators
    @FindBy(xpath = "//ul[@class='list-inline']//li[@class='dropdown']")
    WebElement myAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement btnRegister;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement btnLogin;

    // Action Methods
    public void clickMyAccount(){
        myAccount.click();
    }

    public void clickRegister(){
        btnRegister.click();
    }

    public void clickLogin(){
        btnLogin.click();;
    }
}
