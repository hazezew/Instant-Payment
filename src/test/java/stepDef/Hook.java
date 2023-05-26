package stepDef;

import config.Config;
import config.CucumberExtentReporter;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageObj.AbstractPage;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class Hook {
    private String os;
    private WebDriver driver;
    private static CucumberExtentReporter cucumberExtentReporter;
    private static boolean isReporterRunning;

    @Before(order=1)
    public void initializeDriver(){
//        The following code resolved antivirus challenge we have
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        os=(System.getProperty("os.name")).toUpperCase();
        if (driver == null) {
            if (os.contains("WIN")) {
                if (Config.browser.equalsIgnoreCase("chrome")) {
                    System.setProperty("webdriver.chrome.driver", ".\\src\\test\\webDrivers\\WIN\\chromedriver.exe");

                    DesiredCapabilities capabilities=new DesiredCapabilities();
                    capabilities.acceptInsecureCerts();
                    capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
                    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);

                    ChromeOptions options = new ChromeOptions();
                    options.merge(capabilities);

//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--headless", "--window-size=1920,1200","--ignore-certificate-errors");
//                    options.addArguments("--headless");
//                    options.addArguments("--no-sandbox");

                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);

                } else if (Config.browser.equalsIgnoreCase("firefox")) {
                    System.setProperty("webdriver.gecko.driver", ".\\src\\test\\webDrivers\\WIN\\geckodriver.exe");
                    FirefoxOptions options=new FirefoxOptions();
                    FirefoxProfile profile=new FirefoxProfile();
                    profile.setAcceptUntrustedCertificates(true);
                    options.setProfile(profile);

                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(options);

//                    FirefoxBinary firefoxBinary = new FirefoxBinary();
//                    firefoxBinary.addCommandLineOptions("--headless");
//                    FirefoxOptions firefoxOptions = new FirefoxOptions();
//                    firefoxOptions.setBinary(firefoxBinary);
//                    driver = new FirefoxDriver(firefoxOptions);
                }
            } else {
                if (Config.browser.equalsIgnoreCase("firefox")) {
                    FirefoxBinary firefoxBinary = new FirefoxBinary();
                    firefoxBinary.addCommandLineOptions("--headless");
                    System.setProperty("webdriver.gecko.driver", "./src/test/webDrivers/Linux/geckodriver");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setBinary(firefoxBinary);
                    driver = new FirefoxDriver(firefoxOptions);
                } else if (Config.browser.equalsIgnoreCase("chrome")) {
                    System.setProperty("webdriver.chrome.driver", "./src/test/webDrivers/Linux/chromedriver");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless", "--window-size=1920,1200","--ignore-certificate-errors");
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    driver = new ChromeDriver(options);
                }
            }
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            AbstractPage.setDriver(driver);
        }
        if(!AbstractPage.folderCreated){
            try{
                String currentPath = new File(".").getCanonicalPath();
                File folder=new File(currentPath+"\\target\\Extent");

//                clearing all the files in the folder before creating new test result files
                try {
                    final File[] files = folder.listFiles();
                    for(File f:files)
                        f.delete();
                }
                catch (Exception e){}

//                creating the directory where we store all test result output files
                folder.mkdirs();
                AbstractPage.folderCreated=true;
            }
            catch (Exception e){}
        }
    }
    @Before(order = 2)
    public void beforeScenario(Scenario scenario){
//        The following code resolved antivirus challenge we have
        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        UnlockLockedUser.unlockAllUsers();
        if(!isReporterRunning){
            cucumberExtentReporter=new CucumberExtentReporter();
            isReporterRunning=true;
        }
        cucumberExtentReporter.startTest(scenario.getName());
        AbstractPage.userLoginStatus=false;
    }
    @After(order = 2)
    public void createJiraIssue(Scenario scenario){
        String file=null;
        if(scenario.isFailed()){
            try {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
            }
            catch (Exception e){
                System.out.println("Sorry! Cannot embed the screenshot to failed scenario.");
            }
            try{
                file= new File(".").getCanonicalPath()+"\\target\\Extent\\" + scenario.getName().replaceAll(" ", "_") + ".png";
            }
            catch (Exception e){}
//            jira.create(scenario.getName(),"Failed when running a scenario : "+scenario.getName(),file);

//            Logout user whenever a scenario fails but user is already logged in
            if(AbstractPage.userLoginStatus){
//                AbstractPage.unlockUserFromDB();
            }
        }
    }
    @After(order = 1)
    public void closeDriver(Scenario scenario){
        cucumberExtentReporter.createTest(scenario,driver);
        cucumberExtentReporter.writeTOReporter();
        driver.close();
    }
}