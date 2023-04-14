import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Setup info
 *
 * install Java and setup JAVA_HOME
 *      - downalod exe file from oracle website, after installing java will be present in C:\Program Files\Java\jdk-17
 * downaload maven and setup MAVEN_HOME
 *      - downalod zip file from maven website, extract to C driver and set MAVEN_HOME as C:\Program Files\apache-maven-3.9.1
 * Download Android studio, tools and platform tools will be availabe in
 *  C:\Users\{UserName}\AppData\Local\Android\Sdk
 *  here AppData will be hidden initially
 *  Set environment variable ANDROID_HOME till sdk folder, set below paths
 *      * C:\Users\VK37\AppData\Local\Android\Sdk\tools
 *      * C:\Users\VK37\AppData\Local\Android\Sdk\tools/bin
 *      * C:\Users\VK37\AppData\Local\Android\Sdk\platform-tools
 *  install Node
 *  install Appium desktop client, start server, use same host and port in code to init driver
 */

public class DemoAppLaunch {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/ApiDemos-debug.apk");

        // uiautomator2 will be default for automationName
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        System.out.println("App launched");
        Thread.sleep(10000);
        System.out.println("Quitting driver");
        driver.quit();
    }
}
