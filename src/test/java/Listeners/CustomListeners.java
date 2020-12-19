package Listeners;

import Utilities.TestUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class CustomListeners implements ITestListener {

public void onTestFailure(ITestResult arg0){
    System.setProperty("org.uncommons.reportng.escape-output","false");
    try {
        TestUtil.captureScreenshot();
    } catch (IOException e) {
        e.printStackTrace();
    }

    Reporter.log("click to view screenshot");
    Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
    Reporter.log("<br>");
    Reporter.log("<br>");
    Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=400></img></a>");

}


    
}
