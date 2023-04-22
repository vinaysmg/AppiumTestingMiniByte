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
 *
 *  To Work with realandroid device, Enable USB debugguing option in settings.
 */

public class DemoAppLaunch {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities cap = new DesiredCapabilities();
        //mandatory capability
        cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/ApiDemos-debug.apk");

        // Non-mandatory capabilities
        // uiautomator2 will be default for automationName, So setting AUTOMATION_NAME is not mandatory.
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        // if there multiple platform device are connected, So setting platform is mandarory.
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

        // if multiple versions of same platform are connected then we can use platform version capability
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");

        /*
        if Appium doest not receive any command for 60 sec, it will deletes that session. We can
        override using below capability
         */
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");

        /*
        UDID is unique id assigned to devices, emulators
        If UDID is given, Appium will pick that particular device. To find UDID, connect device or start emulator then execute
        adb devices
        Above command will list devices connected along with UDIDs
         */
        cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");

        /*
        To set orientations, we can use orientation capability
        LANDSCAPE, PORTRAIT
         */
        cap.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");

        /*
        By default while initiating session, Appium will stop app and clear app data and the creates session.

         if we want to install re-install app/apk before session and after test, then we can set fullReset to true. In case of iOS simulator
         it will destroy simulator also.

         If we want to start test without stopping running app without clearing app data, we can set noReset to true
         */

        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        System.out.println("App launched");

        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        Thread.sleep(10000);
        System.out.println("Quitting driver");
        driver.quit();
    }
}
