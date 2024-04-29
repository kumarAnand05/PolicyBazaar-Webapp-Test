package policybazaar.bddpack.baseclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseClass {

    WebDriver driver;
    public WebDriver createDriver(String browser){
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
        return driver;
    }
}
