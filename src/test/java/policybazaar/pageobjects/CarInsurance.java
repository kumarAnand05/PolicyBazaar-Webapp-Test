package policybazaar.pageobjects;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import policybazaar.baseclass.BaseClass;
import policybazaar.pageactions.PageActions;
import policybazaar.utility.Utility;
import policybazaar.utility.ExtentReportManager;


public class CarInsurance extends BaseClass {
	PageActions pageActions = new PageActions(driver);
	Utility utility = new Utility();

	/**
	 * Constructor to initialize the page object elements.
	 */
	public CarInsurance() {
		PageFactory.initElements(driver, this);
	}


	/**
	 * Insurance Product navigation tab element
	 */
	@FindBy(xpath="//a[contains(text(),'Insurance Products')]")
	public WebElement insuranceProducts;

	/**
	 * Car Insurance Menu element under Insurance product navigation tab.
	 */
	@FindBy(xpath="//a[@class='headlink' and contains(text(),'Car Insurance')]")
	public WebElement carInsuranceElement;

	/**
	 * Car Number input box element
	 */
	@FindBy(xpath="//*[contains(@class,'input_box')]//input")
	public WebElement searchCarNumber;

	/**
	 * View Prices button element on the Car Insurance page
	 */
	@FindBy(xpath="//*[@id='btnSubmit']")
	public WebElement viewPriceBtn;

	/**
	 * Error message element on the Car Insurance page.
	 */
	@FindBy(xpath="//div[@class='err' and contains(text(),'Please enter')]")
	public WebElement errorMessage;

	/**
	 * Username text box element in details page
	 */
	@FindBy(xpath="//input[@id='txtName']")
	public WebElement inputUsername;

	/**
	 * Email text box element in the details page
	 */
	@FindBy(xpath="//input[@id='txtEmail']")
	public WebElement inputEmail;

	/**
	 * Phone number text box element in the details page
	 */
	@FindBy(xpath="//input[@id='mobNumber']")
	public WebElement inputPhNumber;


	/**
	 * To navigate to Policy Bazaar home page then get the Car Insurance menu from Insurance products
	 * menu then navigates to the car insurance page.
	 */
	public void visitCarInsurance() {
		driver.get("https://www.policybazaar.com/");
		pageActions.hoverOnElement(insuranceProducts);
		carInsuranceElement.click();
	}

	/**
	 * To fill and submit the car number.
	 * @param carNumber String value of the car number input that needs to be sent in the text box
	 */
	public void submitCarNumber(String carNumber){
		searchCarNumber.clear();
		searchCarNumber.sendKeys(carNumber);
	}

	/**
	 * To check if any error message is being displayed on the WebPage and set the errorMessage variable value
	 * @return boolean value after checking if there is any error message element
	 */
	public boolean getErrorMessage(){
		Actions action = new Actions(driver);
		action.keyDown(Keys.ENTER).perform();
		try {
			if(errorMessage.isDisplayed()){
				ExtentReportManager.errorMessage=true;
				return true;
			}
			else{
				ExtentReportManager.errorMessage=false;
				return false;
			}
		}
		catch(Exception e){
		}
		ExtentReportManager.errorMessage=false;
		return false;
	}

	/**
	 * To parse the JSON Data file and get the specified value at the specified field.
	 * @param filedValue String value of the field whose data is to be read.
	 * @return String value at the specified field value.
	 */
	public String getData(String filedValue){
		String dataFile = "carInsuranceData";
		String[] testData = utility.parseTestData(dataFile, filedValue);
		return utility.getRandomData(testData);
	}

	/**
	 * To parse the JSON Data file and get all data at the specified field.
	 * @param filedValue String value of the field whose data is to be read.
	 * @return Array of String value at the specified field.
	 */
	public String[] getAllData(String filedValue){
		String dataFile = "carInsuranceData";
		return utility.parseTestData(dataFile, filedValue);
	}

	/**
	 * Fills details on the application page after submitting a valid car number
	 * @param name String value of the name input
	 * @param email String value of the email input
	 * @param number String value of the phone number input
	 */
	public void fillApplicationDetails(String name, String email, String number){

		// Filling username
		inputUsername.clear();
		inputUsername.sendKeys(name);

		// Filling email
		inputEmail.clear();
		inputEmail.sendKeys(email);

		// Filling phone number
		inputPhNumber.clear();
		inputPhNumber.sendKeys(number);

		//Submitting the details
		try {
			viewPriceBtn.click();
		}
		catch (ElementClickInterceptedException e){
			pageActions.clickUsingJS(viewPriceBtn);
		}
	}
}
