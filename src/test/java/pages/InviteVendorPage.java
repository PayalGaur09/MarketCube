
package pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigLoader;
import com.typesafe.config.Config;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import utilities.RandomGenerator;



public class InviteVendorPage extends PageObject {


    @FindBy(id ="email")
    public WebElementFacade email;
    @FindBy(id ="password")
    public WebElementFacade password;
    @FindBy(id="submitButton")
    public WebElementFacade loginButton;

    @FindBy(xpath = "//button[@class='Polaris-Header-Action']")
    public WebElementFacade menu;

    @FindBy(xpath = "//div[contains(text(),'Vendors')]")
    public WebElementFacade vendorOption;
    @FindBy(xpath = "//li[6]//button[1]")
    public WebElementFacade activityLogs;
    @FindBy(xpath = "//body/div[@id='wrapper']/div/div/div/div/div/div/div[@class='Polaris-Page']/div[@class='Polaris-Page-Header Polaris-Page-Header__Header--hasRollup Polaris-Page-Header__Header--hasSecondaryActions']/div[@class='Polaris-Page-Header__MainContent']/div[@class='Polaris-Page-Header__PrimaryAction']/button[@class='Polaris-Button Polaris-Button--primary']/span[@class='Polaris-Button__Content']/span[1]")
    public WebElementFacade addVendor;
    @FindBy(id = "createVendorType")
    public WebElementFacade howToInvite;
    @FindBy(xpath = "//input[@id='email']")
    public WebElementFacade emailField;
    @FindBy(xpath = "//span[text()='Submit']")
    public WebElementFacade submitButton;

    @FindBy(xpath = "//span[@class='Polaris-Avatar__Initials']")
    public WebElementFacade sideMenu;
    @FindBy(xpath = "//button/div[text()='Log Out']")
    public WebElementFacade logout;


    @FindBy(xpath = "//button[text()='payaltask@mailinator.com']/../ancestor::div[@class='Polaris-Stack__Item Polaris-Stack__Item--fill']//following-sibling::div//span[text()='Already Registered']")
    public WebElementFacade searchMail;
    public static String emailId;
    public static String vendorId;


    private static Config conf = ConfigLoader.load();

    public static void setEnvironMent(String environment){
        System.setProperty("env",environment);
        conf=ConfigLoader.load();
    }
    public void homePage(){
//        setEnvironMent("test");
        getDriver().get(conf.getString("base_url"));
    }


    public void login(String userId,String userPassword) {
        waitABit(5000);
        email.sendKeys(userId);
       password.sendKeys(userPassword);
        loginButton.click();

    }
    public void addVendorPage(){
        waitFor(menu).click();
        waitFor(vendorOption).click();
        waitFor(addVendor).click();
    }


    public void inviteVendor() {
        waitFor(howToInvite).click();
        Select ways = new Select(howToInvite);
        ways.selectByVisibleText("Invite vendors to self register using our form");
        waitABit(2000);
        Serenity.setSessionVariable("email").to("payal" +  RandomGenerator.randomInteger(3) + "@mailinator.com");
        emailField.type(Serenity.sessionVariableCalled("email"));
        vendorId = emailField.getAttribute("value");
        emailId = emailField.getAttribute("value");
        System.out.println(vendorId);
        System.out.println(emailId);
        submitButton.click();

    }
     public void inviteDropdown(){
        waitFor(howToInvite).click();
        Select ways = new Select(howToInvite);
        ways.selectByVisibleText("Invite vendors to self register using our form");
    }
     public void registeredMail(){
         emailField.sendKeys("payaltask@mailinator.com");
         submitButton.click();
         waitABit(5000);

     }

     public void assertRegistered(){
        waitFor(menu).click();
        waitABit(5000);
        waitFor(activityLogs).click();
        waitABit(5000);
        Assert.assertTrue("Already Registered",searchMail.isVisible());

    }

    public String getEmail(){
        return emailId;
    }

    public void logout() {
        waitFor(sideMenu).click();
        waitFor(logout).click();
    }



}
