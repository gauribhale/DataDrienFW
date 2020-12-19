package TestCases;

import Base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider="getData")
    public void addCustomer(String firstName, String lastName,String postCode,String alertText){

        driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("firstName"))).sendKeys(firstName);
        driver.findElement(By.cssSelector(OR.getProperty("lastName"))).sendKeys(lastName);
        driver.findElement(By.cssSelector(OR.getProperty("postCode"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alertText));
        alert.accept();
        Reporter.log("Add customer test executed");
        Reporter.log("<br>");

    }

    @DataProvider
    public Object[][] getData(){
        String sheetName="AddCustomerTest";
        int rowNum = excel.getRowCount(sheetName);
        int colNum = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rowNum - 1][colNum];

        for (int rows = 2; rows <= rowNum; rows++) {
            for (int cols = 0; cols < colNum; cols++) {

                data[rows - 2][cols] = excel.getCellData(sheetName, cols, rows);

            }

        }

        return data;
    }
}
