package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import utilities.ConfigLoader;
import com.typesafe.config.Config;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;


import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class VendorRegistration extends PageObject {
    @FindBy(xpath = "//input[@id='inboxfield']")
    public WebElementFacade emailSearch;
    @FindBy(id = "go_inbox1")
    public WebElementFacade goButton;
    @FindBy(xpath = "//tr[starts-with(@class,'even pointer')]")
    public WebElementFacade mailSubject;
    @FindBy(id = "msg_body")
    public WebElementFacade mailFrame;
    @FindBy(xpath = "//a[contains(text(),'Create Password')]")
    public WebElementFacade createPassword;
    @FindBy(id = "password")
    public WebElementFacade setPassword;
    @FindBy(id = "confirmPassword")
    public WebElementFacade confirmPassword;
    @FindBy(xpath = "//span[@class='Polaris-Checkbox']")
    public WebElementFacade checkbox;
    @FindBy(id = "registerButton")
    public WebElementFacade registerButton;


    InviteVendorPage inviteVendorPage;
    private static Config conf = ConfigLoader.load();

    public static void setEnvironMent(String environment) {
        System.setProperty("env", environment);
        conf = ConfigLoader.load();
    }

    public void mailinator() {
        getDriver().get(conf.getString("mail_url"));

        emailSearch.sendKeys(inviteVendorPage.emailId);
        goButton.click();
        waitABit(5000);
        mailSubject.click();
    }


    public void openMail() {
        waitFor(mailFrame).withTimeoutOf(20, TimeUnit.SECONDS);
        getDriver().switchTo().frame(waitFor(mailFrame));
        waitFor(createPassword).click();

    }

    public void registration(){
        waitABit(1000);
        Set<String> sessions=getDriver().getWindowHandles();
        Iterator<String> itr=sessions.iterator();
        String parentID=itr.next();
        System.out.println(parentID);
        String childID=itr.next();
        System.out.println(childID);
        getDriver().switchTo().window(childID);

        waitABit(8000);
        waitFor(setPassword).sendKeys("abcdefgh");
        waitFor(confirmPassword).sendKeys("abcdefgh");
        if(!waitFor(checkbox).isSelected());
        {
            checkbox.click();
        }
        waitFor(registerButton).click();
        waitABit(5000);
    }


}
