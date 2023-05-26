package pageObj;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminDashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;

    @FindBy(xpath = "//a[contains(@class,\"x-btn userButton\")]")
    private WebElement linkUserProfile;

    @FindBy(xpath = "//span[text()=\"Logout\"]")
//    @FindBy(xpath = "//span[contains(@id,\"menuitem\")]")
    private WebElement linkLogout;

    @FindBy(xpath = "//span[contains(@class,\"x-btn-inner-default-small\") and text()=\"Yes\"]")
    private WebElement confirmLogoutButton;

//    Main menu items

    @FindBy(xpath = "//div[text()=\"Business processes\"]")
    private WebElement menuBusinessProcesses;

    @FindBy(xpath = "//div[text()=\"Channels\"]")
    private WebElement menuChannels;

    @FindBy(xpath = "//div[contains(@id,\"ext-element\") and text()=\"Transactions\"]")
    private WebElement menuTransactions;

    @FindBy(xpath = "//div[text()=\"Batch processing\"]")
    private WebElement menuBatchProcessing;

    @FindBy(xpath = "//div[text()=\"User tasks\"]")
    private WebElement menuUserTasks;

//    @FindBy(xpath = "//div[text()=\"Attributes\"]")
    @FindBy(xpath = "//div[@class=\"x-treelist-item-text\" and text()=\"Attributes\"]")
    private WebElement menuAttributes;

    @FindBy(xpath = "//div[text()=\"ISO 8583\"]")
    private WebElement menuISO8583;

    @FindBy(xpath = "//div[text()=\"XML\"]")
    private WebElement menuXML;

    @FindBy(xpath = "//div[text()=\"SWIFT MT\"]")
    private WebElement menuSWIFTMT;

    @FindBy(xpath = "//div[text()=\"JSON\"]")
    private WebElement menuJSON;

    @FindBy(xpath = "//div[text()=\"Data files\"]")
    private WebElement menuDataFiles;

    @FindBy(xpath = "//div[text()=\"HSM\"]")
    private WebElement menuHSM;

    @FindBy(xpath = "//div[text()=\"Dictionaries\"]")
    private WebElement menuDictionaries;

    @FindBy(xpath = "//div[text()=\"Sequences\"]")
    private WebElement menuSequences;

    @FindBy(xpath = "//div[text()=\"Custom templates\"]")
    private WebElement menuCustomTemplates;

    @FindBy(xpath = "//div[text()=\"Audit\"]")
    private WebElement menuAudit;

    @FindBy(xpath = "//div[text()=\"About\"]")
    private WebElement menuAbout;

    @FindBy(xpath = "//div[contains(@class,\"x-tool x-box-item\")]")
    private WebElement closeAboutPage;

    public AdminDashboardPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }
    public void clickLinkUserProfile(){
        clickMenuAbout();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", linkUserProfile);
        wait.until(ExpectedConditions.visibilityOf(linkUserProfile)).click();
    }
    public void clickLinkLogout(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", linkLogout);
        wait.until(ExpectedConditions.visibilityOf(linkLogout)).click();
    }

//    Main menu click actions starts here
    public void clickMenuBusinessProcesses(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuBusinessProcesses);
        wait.until(ExpectedConditions.visibilityOf(menuBusinessProcesses)).click();
    }

    public void clickMenuChannels(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuChannels);
        wait.until(ExpectedConditions.visibilityOf(menuChannels)).click();
    }
    public void clickMenuTransactions(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuTransactions);
        wait.until(ExpectedConditions.visibilityOf(menuTransactions)).click();
    }

    public void clickMenuBatchProcessing(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuBatchProcessing);
        wait.until(ExpectedConditions.visibilityOf(menuBatchProcessing)).click();
    }

    public void clickMenuUserTasks(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuUserTasks);
        wait.until(ExpectedConditions.visibilityOf(menuUserTasks)).click();
    }

    public void clickMenuAttributes(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuAttributes);
        wait.until(ExpectedConditions.visibilityOf(menuAttributes)).click();
    }

    public void clickMenuISO8583(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuISO8583);
        wait.until(ExpectedConditions.visibilityOf(menuISO8583)).click();
    }

    public void clickMenuXML(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuXML);
        wait.until(ExpectedConditions.visibilityOf(menuXML)).click();
    }

    public void clickMenuSWIFTMT(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuSWIFTMT);
        wait.until(ExpectedConditions.visibilityOf(menuSWIFTMT)).click();
    }

    public void clickMenuJSON(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuJSON);
        wait.until(ExpectedConditions.visibilityOf(menuJSON)).click();
    }

    public void clickMenuDataFiles(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuDataFiles);
        wait.until(ExpectedConditions.visibilityOf(menuDataFiles)).click();
    }

    public void clickMenuHSM(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuHSM);
        wait.until(ExpectedConditions.visibilityOf(menuHSM)).click();
    }

    public void clickMenuDictionaries(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuDictionaries);
        wait.until(ExpectedConditions.visibilityOf(menuDictionaries)).click();
    }

    public void clickMenuSequences(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuSequences);
        wait.until(ExpectedConditions.visibilityOf(menuSequences)).click();
    }

    public void clickMenuCustomTemplates(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuCustomTemplates);
        wait.until(ExpectedConditions.visibilityOf(menuCustomTemplates)).click();
    }

    public void clickMenuAudit(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuAudit);
        wait.until(ExpectedConditions.visibilityOf(menuAudit)).click();
    }

    public void clickMenuAbout(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuAbout);
        wait.until(ExpectedConditions.visibilityOf(menuAbout)).click();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeAboutPage);
        wait.until(ExpectedConditions.visibilityOf(closeAboutPage)).click();
    }
//    Main menu click actions ends

    public LoginPage clickConfirmLogoutButton(){
        wait.until(ExpectedConditions.visibilityOf(confirmLogoutButton)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LoginPage(driver);
    }
    public String getURL(){
        return driver.getCurrentUrl();
    }
}
