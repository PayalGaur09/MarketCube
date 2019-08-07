package pages;


import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends PageObject{

    @FindBy(xpath ="//h1[text()='Dashboard']")
    public WebElementFacade dashboardText;


    public void dashboardIsVisible() {
        dashboardText.waitUntilClickable();
        //waitFor(dashboardText).isVisible();
        Assert.assertTrue(dashboardText.isVisible());
    }
}
