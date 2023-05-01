package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import util.driver.DriverUtils;

import java.util.List;

/**
 * Implementing Page Object Model using Page factory.
 *
 * Disadvantages of PageFactory are
 *      - We cannot use dynamic xpaths
 *      - In Web automation we may get StaleElement Exception from Ajax elements
 *
 */
public class HomePage extends BasePage{
    AppiumDriver driver = null;

    public HomePage(){
        driver =(AppiumDriver) DriverUtils.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator((AndroidDriver)DriverUtils.getDriver()), this);
    }

    /**
     * @FindBy annotation will only give limited locators.
     * In order to use Android locators we need to use @AndroidFindBy
     *
     *   Disadvantage of saving xpath as string is we need to keep seperate locators for iOS and Android.
     *   So We can avoid is by using page factory. If we mention @AndroidFinfBy @iOSXCUITFindBy to a element
     *   it is available for both the platform.
     */

    @AndroidFindBy(accessibility = "Views")
    @iOSXCUITFindBy(accessibility = "Views")
    private WebElement views;

    String dynamicXpathForMenu = "//android.widget.TextView[@text='%s']";

    @iOSXCUITFindBy(xpath = "//android.widget.TextView")
    @AndroidFindBy(xpath = "//android.widget.TextView")
    private List<WebElement> menuOptions;

    public ViewslandingPage clickOnViews(){
        views.click();
        System.out.println("Clicked on views");
        return new ViewslandingPage();
    }

    public void clickOnMenu(String menu){
        String xpathMenu = String.format(dynamicXpathForMenu, menu);
        driver.findElementByXPath(xpathMenu).click();
        System.out.println("Clicked on " + menu);
    }

    public void clickOnMenuDynamicallyUsingPageFactoryElement(String menu){
//        for(MobileElement element : menuOptions){
//            if(element.getText().equalsIgnoreCase(menu)){
//                element.click();
//                break;
//            }
//        }

        menuOptions.parallelStream().filter(element -> element.getText().equals(menu))
                .findFirst().ifPresent(WebElement::click);
    }
}
