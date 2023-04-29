package util.driver;

import enums.DriverEnvironmens;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class DriverUtils {
    private static WebDriver driver = null;

    /*
    Class should be closed for modification but open for extension, So moving individual environment login
    to another class
     */
    public static void initDriver(){
        if(Objects.nonNull(driver))
            return;

        DriverEnvironmens mode = DriverEnvironmens.LOCAL;

        switch (mode) {
            case LOCAL -> {
                driver = new LocalMachine().initDriver();
            }
            case BROWSER_STACK -> {
                driver = new BrowserStack().initDriver();
            }
            case SAUCE_LABS -> {
                driver = new SauceLabs().initDriver();
            }
            default -> {
                throw new RuntimeException("Invalid environemnt choosen");
            }
        }
        System.out.println("App launched");
    }

    public static void tearDownDriver(){
        System.out.println("Closing session");
        driver.quit();
        driver = null;
    }

    public static WebDriver getDriver(){
        if(Objects.isNull(driver))
            initDriver();
        return driver;
    }
}
