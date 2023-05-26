package config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.core.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class CucumberExtentReporter {
    private ExtentHtmlReporter extentHtmlReporter;
    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private ExtentSparkReporter spark;

    public CucumberExtentReporter(){
        extentHtmlReporter=new ExtentHtmlReporter("target//extent//ExtentReport.html");
        extentReports=new ExtentReports();

        extentReports.attachReporter(extentHtmlReporter);

        extentHtmlReporter.config().setDocumentTitle("Instant Payment System Test Automation");
        extentHtmlReporter.config().setReportName("Instant Payment System Test Automation Report");
        extentHtmlReporter.config().setTheme(Theme.DARK);

        extentReports.setSystemInfo("Developed By", "Habitamu Azezew");
        extentReports.setSystemInfo("Environment", "Ethswitch SIT Environment");
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("OS Name",System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
        extentReports.setSystemInfo("TestNG Version","7.1.0");
        extentReports.setSystemInfo("Cucumber Version","4.8.0");
        extentReports.setSystemInfo("Selenium Version","3.141.59");
        extentReports.setSystemInfo("Extent Report Version","4.1.3");
        extentReports.setSystemInfo("Automation Test Date", String.valueOf(java.time.LocalDate.now()));
    }

    public void createTest(Scenario scenario, WebDriver driver){
        if(scenario!=null){
            String filePath="target/extent/"+scenario.getName().replaceAll(" ","_")+".png";
            String testName=scenario.getName();
            extentTest=extentReports.createTest(testName);
            extentTest.assignAuthor("Test-Automation-Engineer - Habitamu-Azezew");
            switch (scenario.getStatus()){
                case PASSED:
                    extentTest.pass(MarkupHelper.createLabel("SCENARIO PASSED", ExtentColor.GREEN));
                    break;
                case FAILED:
//                    UnlockLockedUser.unlockAllUsers();
                    extentTest.fail(MarkupHelper.createLabel("SCENARIO FAILED",ExtentColor.RED));
                    captureScreenshot(driver,filePath);
                    try {
                        extentTest.info("Please refer below screenshot for more detail.", MediaEntityBuilder.createScreenCaptureFromPath(scenario.getName().replaceAll(" ","_")+".png").build());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    extentTest.skip(MarkupHelper.createLabel("SCENARIO SKIPPED",ExtentColor.BLUE));
            }
//            writeException(scenario.get);
        }
    }

    public void startTest(String scenarioName){
//        extentTest=extentReports.createTest(scenarioName);
//        extentTest.assignAuthor("EthSwitch-Automation-Test-Author:-Habitamu-Azezew");
    }

    public void writeException(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            extentTest.log(Status.INFO,result.getThrowable().getMessage());
        }
    }

    public static void captureScreenshot(WebDriver driver,String filePath) {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        File src=new File(filePath);
        FileOutputStream dest= null;

        try {
            dest = new FileOutputStream(src);
            dest.write(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                dest.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void writeTOReporter(){
        if(extentReports!=null){
            try {
                extentReports.flush();
//                Desktop.getDesktop().browse(URI.create("target//extent//ExtentReport.html"));
            }catch (Exception e){}
        }
    }
}
