import facade.DragDropFacade;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DragAndDropPage;
import pages.HomePage;

public class POM_Test extends BaseTest{

    @Test
    public void pageFactoryElement(){
        new HomePage().clickOnViews();
    }

    @Test
    public void usingDynamicXpath(){
        new HomePage().clickOnMenu("Views");
    }

    @Test
    public void usingDynamicPageFactory(){
        new HomePage().clickOnMenuDynamicallyUsingPageFactoryElement("Views");
    }

    @Test
    public void usingFacadeUtils(){
        new DragDropFacade().moveToDragDropPage();
        DragAndDropPage dragAndDropPage = new DragAndDropPage();
        dragAndDropPage.performDragDrop();
        Assert.assertTrue(dragAndDropPage.isDragDropSuccessful());
    }

}
