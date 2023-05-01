package pages;

import enums.Locators;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.driver.DriverUtils;

import java.time.Duration;

/**
 * Here reusable methods like click, sendkeys, scroll, swipe can be developed
 */
public class BasePage {

    protected void click(WebElement element, String elementName){
        element.click();
        System.out.println("Click on " + elementName);
    }

    protected void click(MobileElement element, String elementName){
        click((WebElement) element, elementName);
    }

    protected void click(By by , String elementName){
        WebElement element = DriverUtils.getDriver().findElement(by);
        click(element, elementName);
    }

    protected void click(Locators locator, String value, String elementName) {
        WebElement element;
        switch (locator) {
            case ID:
                click(By.id(value), elementName);
                break;
            case XPATH:
                click(By.xpath(value), elementName);
                break;
            case ACCESSIBILITY_ID:
                element = ((AndroidDriver) DriverUtils.getDriver()).findElementByAccessibilityId(value);
                click(element, elementName);
                break;
            case CLASS_NAME:
                click(By.className(value), elementName);
                break;
            default:
                throw new RuntimeException("Invalid locator type " + locator);
        }
    }

    public void dragDrop(MobileElement source, MobileElement destination){
        new TouchAction<>((AppiumDriver<MobileElement>) DriverUtils.getDriver()).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(destination))
                .release().perform();
        System.out.println("Peformed drag and drop operation");
    }
}
