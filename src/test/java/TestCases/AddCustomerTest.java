package TestCases;

import Base.TestBase;
import Utilities.TestUtil;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test(priority = 2,dataProviderClass= TestUtil.class,dataProvider = "dp")
    public void addCustomerTest(String firstName, String lastName,String postCode,String alertText,String runmode){

        //run modes
        if(!TestUtil.isTestRunnable("AddCustomerTest",excel)) {
            throw new SkipException("Skipping the test : " + "AddCustomerTest".toUpperCase());
        }

        if(!runmode.equalsIgnoreCase("Y")){
            throw new SkipException("Skipping the test with values : "+firstName+", "+lastName);
        }
        click("addCustBtn");
        type("firstName",firstName);
        type("lastName",lastName);
        type("postCode",postCode);
        click("addBtn");
        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alertText));
        alert.accept();
        Reporter.log("Add customer test executed");
        Reporter.log("<br>");

    }


}
