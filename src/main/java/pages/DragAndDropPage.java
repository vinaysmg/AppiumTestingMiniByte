package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import util.driver.DriverUtils;

public class DragAndDropPage extends BasePage{
    public DragAndDropPage(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverUtils.getDriver()), this);
    }

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_1")
    private MobileElement source;

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_2")
    private MobileElement destination;

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_result_text")
    private MobileElement droppedText;

    public DragAndDropPage performDragDrop(){
        dragDrop(source, destination);
        return this;
    }

    public boolean isDragDropSuccessful(){
        return !DriverUtils.getDriver()
                .findElements(By.id("io.appium.android.apis:id/drag_result_text")).isEmpty();
    }
}
