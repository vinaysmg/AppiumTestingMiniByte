package util.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStack implements IDriver{
    @Override
    public WebDriver initDriver() {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("browserstack.user", "vinaykumark_cCTaUV");
        cap.setCapability("browserstack.key", "kmJAzyPsyaxJ5b8m18BV");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel 7 Pro");

        // app path for browser stack we get it once we upload app to browser stack
        cap.setCapability(MobileCapabilityType.APP, "bs://ac35a1b5c9d1866bb6ec6a047bcc40cbe4581314");
        cap.setCapability("project", "Appium Testing MiniByte");
        cap.setCapability("build", "Api Demos");
        cap.setCapability("name", "Browser Stack Demo");

        try {
            return new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hub"), cap);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
