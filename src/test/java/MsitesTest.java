import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Automating browsers in mobile device.
 * We need to use RemoteWebDriver for session generation,
 * In capabilities we need to mention capability browserName as chrome, chromeDriver executable is not required.
 */
public class MsitesTest {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities cap = new DesiredCapabilities();
        /*
        cap.setCapability("browserstack.user", "vinaykumark_cCTaUV");
        cap.setCapability("browserstack.key", "kmJAzyPsyaxJ5b8m18BV");
        cap.setCapability("project", "Appium Testing MiniByte");
        cap.setCapability("build", "Browser test");
        cap.setCapability("name", "Msite test");

         */


        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel 7 Pro");
        cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "C:/Users/VK37/Downloads/chromedriver.exe");

//        WebDriver driver = new RemoteWebDriver(new URL("http://hub.browserstack.com/wd/hub"), cap);
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        System.out.println("Browser opened");

        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("Vinay Kumar K");

        Thread.sleep(10000);

        System.out.println("Closing session");
        driver.quit();
    }
}
