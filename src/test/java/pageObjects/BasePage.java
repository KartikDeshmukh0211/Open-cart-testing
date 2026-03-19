package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import testBase.BaseClass;

public class BasePage {
    WebDriver driver;

    // since we are using threadlocal, we dont need to pass dirver when we calling parent class constructor....
    public BasePage(){
        this.driver = BaseClass.getDriver();
        PageFactory.initElements(driver, this);
    }

    
    // public BasePage(WebDriver driver){
    //     this.driver = driver;
    //     PageFactory.initElements(driver, this);
    // }
}

/*
    This base class will only contain the contstructor as this constructor is 
    common in every page object class
*/