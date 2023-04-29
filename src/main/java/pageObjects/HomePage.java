package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.driver.DriverUtils;

public class HomePage {
    AndroidDriver<AndroidElement> driver;

    public HomePage(){
        driver = (AndroidDriver<AndroidElement>) DriverUtils.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), HomePage.class);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Views']")
    public AndroidElement views;


}
