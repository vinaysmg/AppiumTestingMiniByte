package listensers;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentReport;
import util.fileUtils.ScreenShotUtil;

public class TestNGListener implements ITestListener, ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initExtentReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.prepareReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.getTest().pass( result.getName()+ "has passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String sscreenShot = ScreenShotUtil.getSscreenShot();
        ExtentReport.getTest().fail(result.getName() + " has failed"
                , MediaEntityBuilder.createScreenCaptureFromBase64String(sscreenShot).build());
    }
}
