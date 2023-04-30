package util.driver;

import enums.DriverEnvironments;
import enums.PropertyKeys;
import org.openqa.selenium.WebDriver;
import util.fileUtils.PropertyUtil;

import java.util.Objects;

public class DriverUtils {
    private DriverUtils(){}
    private static ThreadLocal<WebDriver> driverTL = new ThreadLocal<>();

    /*
    Class should be closed for modification but open for extension, So moving individual environment login
    to another class
     */
    public static void initDriver(){
        if(Objects.nonNull(driverTL.get()))
            return;

        DriverEnvironments mode = DriverEnvironments.valueOf(
                PropertyUtil.getProperty(PropertyKeys.DriverEnvironment).toUpperCase());

        switch (mode) {
            case LOCAL -> {
                driverTL.set(new LocalMachine().initDriver());
            }
            case BROWSER_STACK -> {
                driverTL.set(new BrowserStack().initDriver());
            }
            case SAUCE_LABS -> {
                driverTL.set(new SauceLabs().initDriver());
            }
            default -> {
                throw new RuntimeException("Invalid environemnt choosen");
            }
        }
        System.out.println("App launched");
    }

    public static void tearDownDriver(){
        System.out.println("Closing session");
        driverTL.get().quit();
        driverTL.remove();
    }

    public static WebDriver getDriver(){
        if(Objects.isNull(driverTL.get()))
            initDriver();
        return driverTL.get();
    }
}
