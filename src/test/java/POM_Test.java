import org.testng.annotations.Test;
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
}
