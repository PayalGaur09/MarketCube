package steps;

import com.typesafe.config.Config;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.DashboardPage;
import pages.InviteVendorPage;
import pages.VendorRegistration;
import utilities.ConfigLoader;

public class InviteVendorSteps {
    public static boolean isLogin=false;
    Config conf = ConfigLoader.load();
    InviteVendorPage inviteVendorPage;
    VendorRegistration register;
    DashboardPage dashboardPage;


    @Given("^User is on marketcube login page$")

    public void userIsOnMarketcubeLoginPage() {

        inviteVendorPage.homePage();
    }

    @When("^User login with a valid credentials$")
    public void userLoginWithAValidCredentials() throws InterruptedException {
        Thread.sleep(3000);
        if (!isLogin) {
            inviteVendorPage.login("marketcube.qa@gmail.com", "Password1234!");
            isLogin=true;
        }
    }

    @And("^User reaches to add vendor page$")
    public void userReachesToAddVendorPage() {
        inviteVendorPage.addVendorPage();
    }


    @When("^User invites vendor to register the form$")
    public void userInvitesVendorToRegisterTheForm() {
        inviteVendorPage.inviteVendor();
    }

    @And("^user logout marketcube profile$")
    public void userLogoutMarketcubeProfile() {
        inviteVendorPage.logout();
    }


    @When("^Vendor open his mailbox and reaches to the invitation mail$")
    public void vendorOpenHisMailboxAndReachesToTheInvitationMail() {
        register.mailinator();
    }


    @And("^Vendor creates password and clicks on the register button$")
    public void vendorCreatesPasswordAndClicksOnTheRegisterButton() {
        register.openMail();
        register.registration();
    }

    @And("^Vendor login the marketcube platform$")
    public void vendorLoginTheMarketcubePlatform() {
        inviteVendorPage.login(inviteVendorPage.emailId, "abcdefgh");
    }

    @Then("^Vendor is on the dashboard$")
    public void vendorIsOnTheDashboard() {
        dashboardPage.dashboardIsVisible();
//        inviteVendorPage.logout();

    }

    @When("^User selects \"([^\"]*)\" option$")
    public void userSelectsOption(String arg0) {
        inviteVendorPage.inviteDropdown();
    }

    @And("^User enters vendor email clicks Submit$")
    public void userEntersVendorEmailClicksSubmit() {
        inviteVendorPage.registeredMail();
    }

    @Then("^User finds out that vendor is already registered$")
    public void userFindsOutThatVendorIsAlreadyRegistered() {
        inviteVendorPage.assertRegistered();
    }
}
