import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Gestures extends BaseTest{
    @Test
    public void tapOnWebElement(){
        System.out.println("is driver Null?, "+ Objects.isNull(driver));
        AndroidElement views = driver.findElementByAccessibilityId("Views");
        new TouchAction<>(driver)
                .tap(TapOptions.tapOptions().withElement(ElementOption.element(views)))
                .perform();
    }

    @Test
    public void tapOnCoOrdinate(){
        AndroidElement views = driver.findElementByAccessibilityId("Views");
        new TouchAction<>(driver)
                .tap(PointOption.point(views.getCenter().getX(),
                        views.getCenter().getY())).perform();
    }

    @Test
    public void longPress(){
        AndroidElement views = driver.findElementByAccessibilityId("Views");
        views.click();
        AndroidElement expandableList = driver.findElementByAccessibilityId("Expandable Lists");
        expandableList.click();
        AndroidElement customerAdapter = driver.findElementByAccessibilityId("1. Custom Adapter");
        customerAdapter.click();
        AndroidElement peopleNames = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");

        new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions()
                .withElement(ElementOption.element(peopleNames)).withDuration(Duration.ofSeconds(2))).perform();

        driver.findElementByXPath("//*[@text='Sample action']").click();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(peopleNames)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .perform();
    }

    @Test
    public void swipe(){
         AndroidElement views = driver.findElementByAccessibilityId("Views");
        views.click();
        AndroidElement dateWidgets = driver.findElementByAccessibilityId("Date Widgets");
        dateWidgets.click();
        AndroidElement inline = driver.findElementByAccessibilityId("2. Inline");
        inline.click();

        driver.findElementByAccessibilityId("3").click();

        AndroidElement source = driver.findElementByAccessibilityId("15");
        AndroidElement destination = driver.findElementByAccessibilityId("45");

        new TouchAction<>(driver)
                .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)))
                .moveTo(ElementOption.element(destination))
                .release()
                .perform();

    }

    @Test
    public void dragDrop(){
        AndroidElement views = driver.findElementByAccessibilityId("Views");
        views.click();
        AndroidElement dragDrop = driver.findElementByAccessibilityId("Drag and Drop");
        dragDrop.click();

        AndroidElement source = driver.findElementById("io.appium.android.apis:id/drag_dot_1");
        AndroidElement destination = driver.findElementById("io.appium.android.apis:id/drag_dot_2");

        new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(destination))
                .release().perform();

        AndroidElement dropped = driver.findElementById("io.appium.android.apis:id/drag_result_text");
        Assert.assertTrue(dropped.isDisplayed());
    }

    @Test
    public void scrollTillVisibilityOfElement(){
        AndroidElement views = driver.findElementByAccessibilityId("Views");
        views.click();
        //AndroidElement dragDrop = driver.findElementByAccessibilityId("TextSwitcher");

        while (driver.findElementsByAccessibilityId("TextSwitcher").isEmpty()){
            int hieghtCentre = driver.manage().window().getSize().height/2;
            int widthCentre = driver.manage().window().getSize().width/2;

            //Swiping down
            Point source = new Point(widthCentre, hieghtCentre);
            Point destination = new Point(widthCentre, hieghtCentre/2);

            new TouchAction<>(driver).press(PointOption.point(source))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(destination))
                    .release().perform();

        }

        Assert.assertTrue(!driver.findElementsByAccessibilityId("TextSwitcher").isEmpty());
    }
}
