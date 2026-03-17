package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
    public static WebDriver driver; // static to avoid conflict during captureScreen
    public Logger logger; // import this from log4j.......
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException, URISyntaxException{
        //Loading properties...
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);
        
        logger = LogManager.getLogger(this.getClass()); // for logger configurationm
        
        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
            // if its remote, decide which OS and browser you want to launch on the grid
            DesiredCapabilities capabilities  = new DesiredCapabilities();
            
            // deciding OS
            if(os.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WIN11);
            }else if(os.equalsIgnoreCase("mac")){
                capabilities.setPlatform(Platform.MAC);
            }else if(os.equalsIgnoreCase("linux")){
                capabilities.setPlatform(Platform.LINUX);
            }else{
                System.out.println("NO MATCHING OS..............");
                return;
            }

            // deciding BROWSER
            switch(br.toLowerCase()){
                case "chrome" : capabilities.setBrowserName("chrome"); break;
                case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
                case "firefox" : capabilities.setBrowserName("firefox"); break;
                default : System.out.println("Invalid Browser"); return;
            }

            driver = new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(), capabilities);
        }else{
            switch(br.toLowerCase()){
                case "chrome" : driver = new ChromeDriver(); break;
                case "edge" : driver = new EdgeDriver(); break;
                case "firefox" : driver = new FirefoxDriver(); break;
                default : System.out.println("Invalid Browser"); return;
            }
        }
        
        
        // driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appURL"));
        // driver.get("https://tutorialsninja.com/demo/");
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
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

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
