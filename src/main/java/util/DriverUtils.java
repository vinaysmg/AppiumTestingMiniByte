package util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class DriverUtils {
    private static AndroidDriver<AndroidElement> driver = null;

    public static void initDriver(){
        if(Objects.nonNull(driver))
            return;

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel 7 Pro");
        cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/ApiDemos-debug.apk");

        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("App launched");
    }

    public static void tearDownDriver(){
        System.out.println("Closing session");
        driver.quit();
        driver = null;
    }

    public static AndroidDriver getDriver(){
        if(Objects.isNull(driver))
            initDriver();
        return driver;
    }
}
