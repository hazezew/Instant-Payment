package pageObj;

import org.openqa.selenium.WebDriver;

public class AbstractPage {
    public static String parentWindow;
    public static WebDriver driver;
    public static boolean folderCreated=false;
    public static boolean userLoginStatus=false;
    public static LoginPage loginPage;


    public static void setDriver(WebDriver driverObj){
        driver=driverObj;
    }
    public static void setParentWindow(String windowID){
        parentWindow=windowID;
    }
    public static String getParentWindow(){
        return parentWindow;
    }
}