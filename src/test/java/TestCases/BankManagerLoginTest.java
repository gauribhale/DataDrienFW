package TestCases;

import Base.TestBase;
import Utilities.TestUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;

public class BankManagerLoginTest extends TestBase {
    @Test(priority=1)
    public void BankManagerLoginTest() throws IOException {

        //run modes
        if(!TestUtil.isTestRunnable("BankManagerLoginTest",excel)) {
            throw new SkipException("Skipping the test : " + "BankManagerLoginTest".toUpperCase());
        }

        //   verifYEquals("abc","xyz");
        click("bankMgrLoginBtn");
        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login is not successfull");
        log.debug("Login as bank manager test executed!");
        Reporter.log("Login as bank manager test executed");
        Reporter.log("<br>");

       // Assert.fail("login test failed");
    }
}
