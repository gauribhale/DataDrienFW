package Base;

import Utilities.ExcelReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestBase {

    public static WebDriver driver;
    public static Properties config=new Properties();
    public static Properties OR=new Properties();
    public static FileInputStream fis;
    public static Logger log= Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Excel\\TestData.xlsx");
    public static WebDriverWait wait;

    @BeforeSuite
    public void setUp(){

        if(driver==null){
            try {
                fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.debug("Config file loaded!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.debug("OR file loaded!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String browser=config.getProperty("browser");

            switch(browser.toLowerCase()){
                case "chrome":
                    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\chromedriver.exe");
                    driver=new ChromeDriver();
                    log.debug("Chrome launched!");
                break;

                case "firefox":
                    System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\geckodriver.exe");
                    driver=new FirefoxDriver();
                    log.debug("Firefox launched!");
                    break;

                case "ie":
                    System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\IEDriverServer.exe");
                    driver=new InternetExplorerDriver();
                    log.debug("IE launched!");
                    break;
                default:
                    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\chromedriver.exe");
                    driver=new ChromeDriver();
                    log.debug("Default browser launched!");
                break;
            }
            driver.get(config.getProperty("testsite"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
            log.debug("Navigated to: "+config.getProperty("testsite"));
            wait=new WebDriverWait(driver,5);

        }
    }

    public boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
           // e.printStackTrace();
            return false;
        }
    }

   // @AfterSuite
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
        log.debug("Test execution completed!");


    }
}
