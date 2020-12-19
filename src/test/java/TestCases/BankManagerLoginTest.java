package TestCases;

import Base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {
    @Test
    public void loginAsBankManager(){
        System.setProperty("org.uncommons.reportng.escape-output","false");
            driver.findElement(By.cssSelector(OR.getProperty("bankMgrLoginBtn"))).click();
            Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login is not successfull");
            log.debug("Login as bank manager test executed!");
            Reporter.log("Login as bank manager test executed!");
            Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=\"F:\\New folder\\6d42a2e4e190c75952924ab88a8c8f09.jpg\">Screenshot</a>");

    }
}
