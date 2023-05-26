package pageObj;

import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;

    @FindBy(id = "username")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(name = "login")
    private WebElement btnSignIn;

    @FindBy(id = "input-error")
    private WebElement userNameValidation;

    @FindBy(id = "password-input-error")
    private WebElement passwordValidation;


    public LoginPage(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver,this);
        action = new Actions(driver);

        handleAdvanceOption();
    }
    public void setTxtUsername(String username){
        wait.until(ExpectedConditions.visibilityOf(txtUsername)).sendKeys(username);
    }
    public void setTxtPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(txtPassword)).sendKeys(password);
    }
    public AdminDashboardPage clickBtnSignIn(){
        wait.until(ExpectedConditions.visibilityOf(btnSignIn)).click();

        handleAdvanceOption();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AdminDashboardPage(driver);
    }
    public String getUserNameValidation(){
        return wait.until(ExpectedConditions.visibilityOf(userNameValidation)).getText();
    }
    public String getPasswordValidation(){
//        When password mandatory field validation message is displayed, change the following line of code
        return wait.until(ExpectedConditions.visibilityOf(userNameValidation)).getText();
    }
    public String getURL(){
        return driver.getCurrentUrl();
    }
    public void handleAdvanceOption(){
        if(Config.browser=="chrome"){
            try{
                driver.findElement(By.id("details-button")).click();
                action=new Actions(driver);
                action.scrollToElement(driver.findElement(By.id("proceed-link")));
                driver.findElement(By.id("proceed-link")).click();
            }catch (Exception e){}
        }
//        else if(Config.browser=="firefox"){
//            try{
//                driver.findElement(By.id("details-button")).click();
//                action=new Actions(driver);
//                action.scrollToElement(driver.findElement(By.id("proceed-link")));
//                driver.findElement(By.id("proceed-link")).click();
//            }catch (Exception e){}
//        }
    }
}
