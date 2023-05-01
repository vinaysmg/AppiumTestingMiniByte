package facade;

import pages.DragAndDropPage;
import pages.HomePage;
import pages.ViewslandingPage;

public class DragDropFacade {

    public DragAndDropPage moveToDragDropPage(){
        new HomePage().clickOnViews();
        new ViewslandingPage().clickOnDragDrop();
        return new DragAndDropPage();
    }
}
