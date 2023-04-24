package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.DriverUtils;

public class HomePage {
    AndroidDriver<AndroidElement> driver;

    public HomePage(){
        driver = DriverUtils.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), HomePage.class);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Views']")
    public AndroidElement views;


}
