import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    AndroidDriver driver;

    @BeforeMethod
    public void initSession() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel 7 Pro");
        cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/ApiDemos-debug.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        System.out.println("App launched");
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Closing session");
        driver.quit();
    }
}
