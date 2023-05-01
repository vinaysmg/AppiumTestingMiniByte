import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.IResultMap;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import reports.ExtentReport;
import util.driver.DriverUtils;
import util.fileUtils.ScreenShotUtil;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    @BeforeSuite
    public void beforeSuite(){

//        ExtentReport.initExtentReport(); Extent report codes are moved to listeners
    }

    @AfterSuite
    public void afterSuite(){
//        ExtentReport.prepareReport();
    }

    @BeforeMethod
    public void initSession(Method method) throws MalformedURLException {
//        ExtentReport.createTest(method.getName());
        DriverUtils.initDriver();
        driver = DriverUtils.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
//        if(result.isSuccess()){
//            ExtentReport.getTest().pass(result.getName() + "has passed");
//        } else {
//            String screenShot = ScreenShotUtil.getSscreenShot();
//            ExtentReport.getTest().fail(result.getName() + " has failed", MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
//        }
        DriverUtils.tearDownDriver();
    }
}
