import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
        //mandatory capability if app is not installed in device, This will install given apk in device
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
        cap.setCapability(MobileCapabilityType.FULL_RESET, true);

        /*
        We can install app to connected device using adb command
        adb install path_to_apk

        We can create driver session for app which is already installed in device using app package and
        activity

        To view currently opened app package and activity
        adb shell
        dumpsys window windows | grep -E 'CurrentFocus|mFocusedApp'
        or
        dumpsys window displays | grep -E 'mCurrentFocus'
                mCurrentFocus will give full name, mFocusedApp will give relative name
         */
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
//        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        System.out.println("App launched");

        driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
        System.out.println("Clicking on Views ");

        /*
        Valid locator strategies for this request: xpath, id, class name, accessibility id,
        css selector, -android uiautomator
            * In Appium xpath won;t support text()
            * Attribute content-desc is the AccessibilityId: findElementByAccessibilityId()
            * Attribute resource-id is the Id: findElementById()
            * Attribute css-selector is not available for NATIVE_APP view
         */
        driver.findElementByAccessibilityId("Animation").click();
        driver.findElementById("android:id/text1").click();

        Thread.sleep(10000);
        System.out.println("Quitting driver");
        driver.quit();
    }
}
