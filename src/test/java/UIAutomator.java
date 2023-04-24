import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;
import util.DriverUtils;

/**
 * UIAutomator is similiar to JavaScriptExecutor in Selenium.
 * It allows click, scroll and many more
 * https://developer.android.com/training/testing/other-components/ui-automator
 *
 *  To click
 *      findElementByAndroidUIAutomator(new UISelector().methodName(String arg)).click
 *
 */
public class UIAutomator extends BaseTest{

    AndroidDriver<AndroidElement> driver = DriverUtils.getDriver();
    @Test
    public void clickUsingUIAutomator(){
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Views\")").click();

        // new UiSelector().text("Views") can also be written as text("Views")
        driver.findElementByAndroidUIAutomator("textContains(\"Date\")").click();
        System.out.println("Clicked on views button");
    }

    @Test
    public void scrollUsingUiAutomator(){
        driver.findElementByAndroidUIAutomator("textContains(\"View\")").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(text(\"Radio Group\"))").click();
    }

    @Test
    public void scrollToEndUsingUiAutomator(){
        driver.findElementByAndroidUIAutomator("textContains(\"View\")").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollToEnd(10)");
    }
}
