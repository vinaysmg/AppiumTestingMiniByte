import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Gestures extends BaseTest{

    AndroidDriver<AndroidElement> androidDriver = (AndroidDriver<AndroidElement>) driver;
    @Test
    public void tapOnWebElement(){
        AndroidElement views = androidDriver.findElementByAccessibilityId("Views");
        new TouchAction<>(androidDriver)
                .tap(TapOptions.tapOptions().withElement(ElementOption.element(views)))
                .perform();
    }

    @Test
    public void tapOnCoOrdinate(){
        AndroidElement views = androidDriver.findElementByAccessibilityId("Views");
        new TouchAction<>(androidDriver)
                .tap(PointOption.point(views.getCenter().getX(),
                        views.getCenter().getY())).perform();
    }

    @Test
    public void longPress(){
        AndroidElement views = androidDriver.findElementByAccessibilityId("Views");
        views.click();
        AndroidElement expandableList = androidDriver.findElementByAccessibilityId("Expandable Lists");
        expandableList.click();
        AndroidElement customerAdapter = androidDriver.findElementByAccessibilityId("1. Custom Adapter");
        customerAdapter.click();
        AndroidElement peopleNames = androidDriver.findElementByXPath("//android.widget.TextView[@text='People Names']");

        new TouchAction<>(androidDriver).longPress(LongPressOptions.longPressOptions()
                .withElement(ElementOption.element(peopleNames)).withDuration(Duration.ofSeconds(2))).perform();

        androidDriver.findElementByXPath("//*[@text='Sample action']").click();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        new TouchAction<>(androidDriver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(peopleNames)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .perform();
    }

    @Test
    public void swipe(){
         AndroidElement views = androidDriver.findElementByAccessibilityId("Views");
        views.click();
        AndroidElement dateWidgets = androidDriver.findElementByAccessibilityId("Date Widgets");
        dateWidgets.click();
        AndroidElement inline = androidDriver.findElementByAccessibilityId("2. Inline");
        inline.click();

        androidDriver.findElementByAccessibilityId("3").click();

        AndroidElement source = androidDriver.findElementByAccessibilityId("15");
        AndroidElement destination = androidDriver.findElementByAccessibilityId("45");

        new TouchAction<>(androidDriver)
                .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)))
                .moveTo(ElementOption.element(destination))
                .release()
                .perform();

    }

    @Test
    public void dragDrop(){
        AndroidElement views = androidDriver.findElementByAccessibilityId("Views");
        views.click();
        AndroidElement dragDrop = androidDriver.findElementByAccessibilityId("Drag and Drop");
        dragDrop.click();

        AndroidElement source = androidDriver.findElementById("io.appium.android.apis:id/drag_dot_1");
        AndroidElement destination = androidDriver.findElementById("io.appium.android.apis:id/drag_dot_2");

        new TouchAction<>(androidDriver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(destination))
                .release().perform();

        AndroidElement dropped = androidDriver.findElementById("io.appium.android.apis:id/drag_result_text");
        Assert.assertTrue(dropped.isDisplayed());
    }

    @Test
    public void scrollTillVisibilityOfElement(){
        AndroidElement views = androidDriver.findElementByAccessibilityId("Views");
        views.click();
        //AndroidElement dragDrop = driver.findElementByAccessibilityId("TextSwitcher");

        while (androidDriver.findElementsByAccessibilityId("TextSwitcher").isEmpty()){
            int hieghtCentre = driver.manage().window().getSize().height/2;
            int widthCentre = driver.manage().window().getSize().width/2;

            //Swiping down
            Point source = new Point(widthCentre, hieghtCentre);
            Point destination = new Point(widthCentre, hieghtCentre/2);

            new TouchAction<>(androidDriver).press(PointOption.point(source))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(destination))
                    .release().perform();

        }

        Assert.assertTrue(!androidDriver.findElementsByAccessibilityId("TextSwitcher").isEmpty());
    }
}
