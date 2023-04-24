import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ParallelExecution {
    /**
     * Parallel execution using multiple appium servers
     */
    @Test
    public void server1(){
        AndroidDriver<AndroidElement> driver;
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

        AndroidElement views =  driver.findElementByAccessibilityId("Views");
        views.click();
        //AndroidElement dragDrop = driver.findElementByAccessibilityId("TextSwitcher");

        while (driver.findElementsByAccessibilityId("TextSwitcher").isEmpty()){
            int hieghtCentre = driver.manage().window().getSize().height/2;
            int widthCentre = driver.manage().window().getSize().width/2;

            //Swiping down
            Point source = new Point(widthCentre, hieghtCentre);
            Point destination = new Point(widthCentre, hieghtCentre/2);

            new TouchAction<>(driver).press(PointOption.point(source))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(destination))
                    .release().perform();

        }

        Assert.assertTrue(!driver.findElementsByAccessibilityId("TextSwitcher").isEmpty());
        driver.quit();
    }

    @Test
    public void server2(){
        AndroidDriver<AndroidElement> driver;
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel 6 Pro");
        cap.setCapability(MobileCapabilityType.UDID, "emulator-5556");
        cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/ApiDemos-debug.apk");

        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4724/wd/hub"), cap);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("App launched");

        AndroidElement views =  driver.findElementByAccessibilityId("Views");
        views.click();
        //AndroidElement dragDrop = driver.findElementByAccessibilityId("TextSwitcher");

        while (driver.findElementsByAccessibilityId("TextSwitcher").isEmpty()){
            int hieghtCentre = driver.manage().window().getSize().height/2;
            int widthCentre = driver.manage().window().getSize().width/2;

            //Swiping down
            Point source = new Point(widthCentre, hieghtCentre);
            Point destination = new Point(widthCentre, hieghtCentre/2);

            new TouchAction<>(driver).press(PointOption.point(source))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(destination))
                    .release().perform();

        }

        Assert.assertTrue(!driver.findElementsByAccessibilityId("TextSwitcher").isEmpty());
        driver.quit();
    }
}
