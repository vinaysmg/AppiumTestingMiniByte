package pages;

import enums.Locators;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.driver.DriverUtils;

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
}
