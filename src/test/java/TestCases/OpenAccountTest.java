package TestCases;

import Base.TestBase;
import Utilities.TestUtil;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OpenAccountTest extends TestBase {

    @Test(priority = 3,dataProviderClass= TestUtil.class,dataProvider = "dp")
    public void openAccountTest(String customer, String currency){

        //run modes
        if(!TestUtil.isTestRunnable("OpenAccountTest",excel)) {
            throw new SkipException("Skipping the test : " + "OpenAccountTest".toUpperCase());
        }
        click("openAccBtn");
        select("customer",customer);
        select("currency",currency);
        click("processBtn");
        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains("Account created successfully"));
        alert.accept();
        Reporter.log("Open account test executed");
        Reporter.log("<br>");

    }

}
