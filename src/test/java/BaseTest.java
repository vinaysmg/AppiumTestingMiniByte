import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.driver.DriverUtils;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    @BeforeMethod
    public void initSession() throws MalformedURLException {
        DriverUtils.initDriver();
        driver = DriverUtils.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(){
        DriverUtils.tearDownDriver();
    }
}
