package Utilities;

import Base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

public class TestUtil extends TestBase {

    public static String screenshotPath;
    public static String screenshotName;
    public static void captureScreenshot() throws IOException {
       File scrFile=  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       Date d=new Date();
       screenshotName=d.toString().replace(":","_").replace(" ","_")+".jpg";
       screenshotPath=System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName;
       FileUtils.copyFile(scrFile,new File(screenshotPath));
    }

    @DataProvider(name="dp")
    public Object[][] getData(Method m){
        String sheetName=m.getName();
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

    public static boolean isTestRunnable(String testName,ExcelReader excel){
        String sheetName="Test_suite";
        int rows=excel.getRowCount(sheetName);
        for(int rNum=2;rNum<=rows;rNum++){
           String testCase= excel.getCellData(sheetName, "TCID", rNum);
            if(testCase.equalsIgnoreCase(testName)){
              String runmode=  excel.getCellData(sheetName,"RunMode",rNum);
                if(runmode.equalsIgnoreCase("y"))
                    return true;
                else
                    return false;
            }

        }
        return false;
    }
}
