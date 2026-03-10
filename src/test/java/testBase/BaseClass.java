package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/*
    This base class contains the reusable methods
    all the common methods which our test classes use will
    be present in this base class....

    This will be the parent class for all the test classes.......
*/

public class BaseClass {
    public WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
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
