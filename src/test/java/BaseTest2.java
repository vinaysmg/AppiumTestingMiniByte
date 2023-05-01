import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.driver.DriverUtils;

import java.util.Map;

/**
 * This is just to demonstrate
 */
public class BaseTest2 {
    /*
    Whatever the data passed by dataProvider will also available in @Beforemethod as a
    Object[] in 0th position
     */
    @BeforeMethod
    public void intiDriver(Object[] data){
        Map<String, String> datum = (Map<String, String>) data[0];
        DriverUtils.intiDriver(datum);
    }

    @AfterMethod
    public void tearDown(){
        DriverUtils.tearDownDriver();
    }
}
