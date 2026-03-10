package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class loginTest {
    WebDriver driver;

    @BeforeClass
    void setup() throws InterruptedException{
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
        // Thread.sleep(5000);
    }

    @Test
    void login(){
        System.out.println("Login Test");
        driver.findElement(By.xpath("//ul[@class='list-inline']//li[@class='dropdown']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
    }
}
