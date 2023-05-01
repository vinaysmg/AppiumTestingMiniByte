package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.Objects;

public class ExtentReport {
    private static ExtentReports extentReport;

    private static ThreadLocal<ExtentTest> tlExtentTest = new ThreadLocal<>();
    public static void initExtentReport(){
        if(Objects.nonNull(extentReport))
            return;

        extentReport = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/index.html");
        spark.config().setReportName("Vinay");
        spark.config().setDocumentTitle("AppiumTestingMiniByte");
        spark.config().setTimeStampFormat("yyyyMMDDhhmmss");
        extentReport.attachReporter(spark);
    }

    public static void createTest(String name){
        ExtentTest test = extentReport.createTest(name);
        tlExtentTest.set(test);
    }

    public static ExtentTest getTest(){
        return tlExtentTest.get();
    }
    public static void prepareReport(){
        if(Objects.nonNull(extentReport))
            extentReport.flush();
    }
}
