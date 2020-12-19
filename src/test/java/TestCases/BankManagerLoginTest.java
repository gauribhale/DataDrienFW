package TestCases;

import Base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {
    @Test
    public void loginAsBankManager(){
            driver.findElement(By.cssSelector(OR.getProperty("bankMgrLoginBtn"))).click();
            Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login is not successfull");
            log.debug("Login as bank manager test executed!");
            Reporter.log("Login as bank manager test executed");
            Reporter.log("<br>");

    }
}
