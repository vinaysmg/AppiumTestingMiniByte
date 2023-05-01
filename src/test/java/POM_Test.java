import facade.DragDropFacade;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DragAndDropPage;
import pages.HomePage;
import reports.ExtentReport;

public class POM_Test extends BaseTest{

    @Test
    public void pageFactoryElement(){
        new HomePage().clickOnViews();
        ExtentReport.getTest().pass("pageFactoryElement passed successfully");
    }

    @Test
    public void usingDynamicXpath(){
        new HomePage().clickOnMenu("Views");
        ExtentReport.getTest().pass("usingDynamicXpath passed successfully");
    }

    @Test
    public void usingDynamicPageFactory(){
        new HomePage().clickOnMenuDynamicallyUsingPageFactoryElement("Views");
        ExtentReport.getTest().pass("usingDynamicPageFactory passed successfully");
    }

    @Test
    public void usingFacadeUtils(){
        new DragDropFacade().moveToDragDropPage();
        DragAndDropPage dragAndDropPage = new DragAndDropPage();
        dragAndDropPage.performDragDrop();
        Assert.assertTrue(dragAndDropPage.isDragDropSuccessful());
        ExtentReport.getTest().pass("usingFacadeUtils passed successfully");
    }

}
