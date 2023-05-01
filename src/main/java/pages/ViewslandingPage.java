package pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import util.driver.DriverUtils;

public class ViewslandingPage extends BasePage{
    public ViewslandingPage(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverUtils.getDriver()), this);
    }

    @AndroidFindBy(accessibility = "Drag and Drop")
    @iOSXCUITFindBy(accessibility = "Drag and Drop")
    private MobileElement dragDrop;

    public DragAndDropPage clickOnDragDrop(){
        click(dragDrop, "Drag and Drop");
        return new DragAndDropPage();
    }

    public void clickOn(String menu){
        WebElement element = DriverUtils.getDriver().findElement(MobileBy.AccessibilityId(menu));
        click(element, menu);
    }
}
