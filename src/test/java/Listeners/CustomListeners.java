package Listeners;

import Base.TestBase;
import Utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import java.io.IOException;

public class CustomListeners extends TestBase implements ITestListener {

public void onTestFailure(ITestResult arg0){
    System.setProperty("org.uncommons.reportng.escape-output","false");
    try {
        TestUtil.captureScreenshot();
    } catch (IOException e) {
        e.printStackTrace();
    }
    test.log(LogStatus.FAIL,arg0.getName().toUpperCase()+" : FAILED WITH EXCEPTION: "+arg0.getThrowable());
    test.log(LogStatus.FAIL,test.addScreenCapture(TestUtil.screenshotName));


    Reporter.log("click to view screenshot");
    Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
    Reporter.log("<br>");
    Reporter.log("<br>");
    Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=400></img></a>");
    report.endTest(test);
    report.flush();

}

public void onTestSuccess(ITestResult arg0){
    test.log(LogStatus.PASS,arg0.getName().toUpperCase()+" : PASS");
    report.endTest(test);
    report.flush();

}

public void onTestStart(ITestResult arg0){

    test=report.startTest(arg0.getName().toUpperCase());

    }
  
public void onTestSkipped(ITestResult arg0){
    test.log(LogStatus.SKIP,arg0.getName().toUpperCase()+" Skipped the test as Run mode is N");
    report.endTest(test);
    report.flush();

}
}
