package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import util.driver.DriverUtils;

/**
 * Implementing Page Object Model using Page factory.
 *
 * Disadvantages of PageFactory are
 *      - We cannot use dynamic xpaths
 *      - In Web automation we may get StaleElement Exception from Ajax elements
 */
public class HomePage {
    AppiumDriver driver = null;

    public HomePage(){
        driver =(AppiumDriver) DriverUtils.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator((AndroidDriver)DriverUtils.getDriver()), this);
    }

    /**
     * @FindBy annotation will only give limited locators.
     * In order to use Android locators we need to use @AndroidFindBy
     */

    @AndroidFindBy(accessibility = "Views")
    private WebElement views;

    String dynamicXpathForMenu = "//android.widget.TextView[@text='%s']";

    public void clickOnViews(){
        views.click();
        System.out.println("Clicked on views");
    }

    public void clickOnMenu(String menu){
        String xpathMenu = String.format(dynamicXpathForMenu, menu);
        driver.findElementByXPath(xpathMenu).click();
        System.out.println("Clicked on " + menu);
    }
}
