package stepDef;

import config.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObj.AbstractPage;
import pageObj.AdminDashboardPage;
import pageObj.LoginPage;


public class LoginStepDef extends AbstractPage {
    private WebDriver driver;
    private LoginPage loginPage;
    private AdminDashboardPage adminDashboardPage;

    public LoginStepDef() {
        this.driver = super.driver;
    }

    @When("user has entered Instant Payment System URL")
    public void user_has_entered_Shared_Wallet_system_URL() {
        driver.navigate().to(Config.baseURL);
        loginPage = new LoginPage(driver);
    }

    @And("user enters {string} into username textfield")
    public void userEntersIntoUsernameTextfield(String username) {
        loginPage.setTxtUsername(username);
    }

    @And("user enters {string} into password textfield")
    public void userEntersIntoPasswordTextfield(String password) {
        loginPage.setTxtPassword(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        adminDashboardPage=loginPage.clickBtnSignIn();
    }

    @Then("Instant Payment System displays Admin dashboard page")
    public void instantPaymentSystemDisplaysAdminDashboardPage() {
        Assert.assertEquals(adminDashboardPage.getURL(),Config.adminDashboardPageURL);
    }

    @And("on Admin dashboard page user clicks on logged in user profile link")
    public void onAdminDashboardPageUserClicksOnLoggedInUserProfileLink() {
        adminDashboardPage.clickLinkUserProfile();
    }

    @And("on Admin dashboard page user clicks on Logout link")
    public void onAdminDashboardPageUserClicksOnLogoutLink() {
        adminDashboardPage.clickLinkLogout();
    }
    @And("user clicks on Yes button on popup alert to confirm the logout")
    public void userClicksOnYesButtonOnPopupAlertToConfirmTheLogout() {
        loginPage=adminDashboardPage.clickConfirmLogoutButton();
    }

    @Then("Instant Payment system logs out and displays login page")
    public void instantPaymentSystemLogsOutAndDisplaysLoginPage() {
        Assert.assertEquals(loginPage.getURL(),Config.baseURL);
    }

    @Then("login page displays username validation message {string}")
    public void instantPaymentSystemLoginPageDisplaysUsernameValidationMessage(String usernameRequiredMessage) {
        Assert.assertEquals(loginPage.getUserNameValidation(),usernameRequiredMessage);
    }

    @Then("login page displays password validation message {string}")
    public void instantPaymentSystemLoginPageDisplaysPasswordValidationMessage(String passwordRequiredMessage) {
        Assert.assertEquals(loginPage.getPasswordValidation(),passwordRequiredMessage);
    }
}