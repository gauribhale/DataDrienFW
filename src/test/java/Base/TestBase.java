package Base;

import Utilities.ExcelReader;
import Utilities.ExtentManager;
import Utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
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
    public ExtentReports report= ExtentManager.getInstance();
    public static ExtentTest test;
    static WebElement dropdown;

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
    public void click(String locator){
        driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
        test.log(LogStatus.INFO,"clicking on : "+locator);
    }

    public void type(String locator,String text){
        driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(text);
        test.log(LogStatus.INFO,"typing in : "+locator+" with value : "+text);
    }

    public void select(String locator,String value){
        dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
        Select select=new Select(dropdown);
        select.selectByVisibleText(value);

        test.log(LogStatus.INFO,"Selecting from dropdwn "+locator+" with value : "+value);
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

    public static void verifYEquals(String actual,String expected) throws IOException {
        try{
            Assert.assertEquals(actual,expected);
        }
        catch(Throwable t){
            TestUtil.captureScreenshot();
            //Reportng
            Reporter.log("<br>"+"Verification Failure: "+t.getMessage()+"<br>");
            Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=400></img></a>");
            Reporter.log("<br>");

            //extent reports
            test.log(LogStatus.FAIL,"Verification failed : "+t.getMessage());
            test.log(LogStatus.FAIL,test.addScreenCapture(TestUtil.screenshotName));

        }
    }

    @AfterSuite
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
        log.debug("Test execution completed!");


    }
}
