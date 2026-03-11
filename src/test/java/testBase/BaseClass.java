package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // only import this log4j

/*
    This base class contains the reusable methods
    all the common methods which our test classes use will
    be present in this base class....

    This will be the parent class for all the test classes.......
*/

public class BaseClass {
    public WebDriver driver;
    public Logger logger; // import this from log4j.......

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String br){
        logger = LogManager.getLogger(this.getClass()); // for logger configurationm
        
        switch(br.toString()){
            case "chrome" : driver = new ChromeDriver(); break;
            case "edge" : driver = new EdgeDriver(); break;
            case "firefox" : driver = new FirefoxDriver(); break;
            default : System.out.println("Invalid Browser"); return;
        }
        
        // driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        return RandomStringUtils.secure().nextAlphabetic(5);
    }

    public String randomNumber(){
        return RandomStringUtils.secure().nextNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.secure().nextAlphanumeric(8);
    }
}
