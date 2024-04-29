package policybazaar.baseclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseClass {

    public static WebDriver driver;

    /**
     * Creates the WebDriver object and store it in static class variable.
     * @param browser Takes the String parameter from xml suite for specified browser WebDriver invocation
     */
    @Parameters("browser")
    @BeforeClass(groups = {"smoke", "regression"})
    public void createDriver(String browser){
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);

        if(browser.equals("Chrome")){
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
        }
        else if (browser.equals("Edge")) {
            EdgeOptions options = new EdgeOptions();
            options.setExperimentalOption("prefs", prefs);
            driver = new EdgeDriver(options);
        }
        else{
            System.out.println("Invalid Browser name provided.");
            System.exit(-1);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
    }
}
