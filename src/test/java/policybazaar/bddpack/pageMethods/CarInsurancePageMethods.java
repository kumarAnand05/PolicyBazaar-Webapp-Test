package policybazaar.bddpack.pageMethods;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import policybazaar.pageactions.PageActions;
import policybazaar.utility.ExtentReportManager;
import policybazaar.utility.Utility;

public class CarInsurancePageMethods {

    WebDriver driver;
    Utility utility = new Utility();
    public CarInsurancePageMethods(WebDriver driver){
        this.driver = driver;
    }

    public void submitCarNumber(String carNumber){
        WebElement searchCarNumber = driver.findElement(By.xpath("//*[contains(@class,'input_box')]//input"));
        searchCarNumber.clear();
        searchCarNumber.sendKeys(carNumber);
    }

    public boolean getErrorMessage(String methodName){
        Actions action = new Actions(driver);
        action.keyDown(Keys.ENTER).perform();
        try {
            WebElement errorMessage  = driver.findElement(By.xpath("//div[@class='err' and contains(text(),'Please enter')]"));
            if(errorMessage.isDisplayed()){
                utility.getScreenshot(driver,methodName);
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
        }
        return false;
    }

    public String getData(String filedValue){
        String dataFile = "carInsuranceData";
        String[] testData = utility.parseTestData(dataFile, filedValue);
        return utility.getRandomData(testData);
    }

    public String[] getAllData(String filedValue){
        String dataFile = "carInsuranceData";
        return utility.parseTestData(dataFile, filedValue);
    }


    public void fillApplicationDetails(String name, String email, String number){

        // Filling username
        WebElement inputUsername = driver.findElement(By.xpath("//input[@id='txtName']"));
        inputUsername.clear();
        inputUsername.sendKeys(name);

        // Filling email
        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='txtEmail']"));
        inputEmail.clear();
        inputEmail.sendKeys(email);

        // Filling phone number
        WebElement inputPhNumber = driver.findElement(By.xpath("//input[@id='mobNumber']"));
        inputPhNumber.clear();
        inputPhNumber.sendKeys(number);

        //Submitting the details
        WebElement viewPriceBtn = driver.findElement(By.xpath("//*[@id='btnSubmit']"));
        try {
            viewPriceBtn.click();
        }
        catch (ElementClickInterceptedException e){
            new PageActions(driver).clickUsingJS(viewPriceBtn);
        }
    }
}
