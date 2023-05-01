package util.fileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.driver.DriverUtils;

public class ScreenShotUtil {
    private ScreenShotUtil(){}

    public static String getSscreenShot(){
        String screenshotAs = ((TakesScreenshot) DriverUtils.getDriver()).getScreenshotAs(OutputType.BASE64);
        return screenshotAs;
    }
}
