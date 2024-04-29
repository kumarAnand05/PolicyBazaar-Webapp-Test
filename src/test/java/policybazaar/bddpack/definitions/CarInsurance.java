package policybazaar.bddpack.definitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import policybazaar.bddpack.baseclass.BaseClass;
import policybazaar.bddpack.pageMethods.CarInsurancePageMethods;
import policybazaar.pageactions.PageActions;


public class CarInsurance{

	CarInsurancePageMethods carInsurancePageMethods;

	/**
	 * Instance of driver
	 */
	WebDriver driver;

	/**
	 * Navigation to policy bazaar
	 */
	@Given("open policy bazaar website")
	public void user_opens_policy_bazaar() {
		driver = new BaseClass().createDriver("Chrome");
		carInsurancePageMethods = new CarInsurancePageMethods(driver);
		driver.get("https://www.policybazaar.com/");
	}

	/**
	 * Navigation to Car Insurance option
	 */
	@Given("user navigates to car insurance option")
	public void user_navigates_to_car_insurance() {
		new PageActions(driver).hoverOnElement(driver.findElement(By.xpath("//a[contains(text(),'Insurance Products')]")));
		driver.findElement(By.xpath("//a[@class='headlink' and contains(text(),'Car Insurance')]")).click();
	}

	/**
	 * Tests submission of application without providing car number
	 */
	@When("user search for car instance without car number")
	public void search_without_car_number(){
		carInsurancePageMethods.submitCarNumber("");
	}

	/**
	 * Checks if error message is displayed when searched without car number
	 */
	@Then("error message should be displayed")
	public void check_error_for_search_without_number(){
		boolean status = carInsurancePageMethods.getErrorMessage("search_without_car_number");
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with invalid car number
	 */
	@When("user search with invalid car number")
	public void search_with_invalid_car_number(){
		String carNumber = carInsurancePageMethods.getData("InvalidCarNumber");
		carInsurancePageMethods.submitCarNumber(carNumber);
	}

	@Then("search should not proceed and error message should be displayed")
	public void check_error_for_search_with_invalid_car_number(){
		boolean status = carInsurancePageMethods.getErrorMessage("search_with_invalid_car_number");
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with valid car number
	 */
	@When("user search with valid car number")
	public void search_with_valid_car_number(){
		String carNumber = carInsurancePageMethods.getData("ValidCarNumber");
		carInsurancePageMethods.submitCarNumber(carNumber);
	}

	@Then("search should proceed and no error message should be displayed")
	public void error_check_with_valid_car_number(){
		boolean status = carInsurancePageMethods.getErrorMessage("search_with_valid_car_number");
		Assert.assertFalse(status);
	}

	/**
	 * Tests submission of the application without providing any user details
	 */
	@When("user search without filling any details")
	public void search_without_any_detail(){
		carInsurancePageMethods.fillApplicationDetails("","","");

	}

	@Then("user should get get error message for all fields")
	public void check_error_message_for_no_detail_submission(){
		boolean status = carInsurancePageMethods.getErrorMessage("search_without_any_detail");
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with invalid name
	 */
	@When("user search with invalid name")
	public void search_with_invalid_name(){
		String[] formData = carInsurancePageMethods.getAllData("InvalidName");
		carInsurancePageMethods.fillApplicationDetails(formData[0],formData[1],formData[2]);
	}

	@Then("user should get error message for invalid name input")
	public void check_error_message_for_invalid_name(){
		boolean status = carInsurancePageMethods.getErrorMessage("search_with_invalid_name");
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with invalid email id
	 */
	@When("user searches with invalid email")
	public void search_without_invalid_email(){
		String[] formData = carInsurancePageMethods.getAllData("InvalidEmail");
		carInsurancePageMethods.fillApplicationDetails(formData[0],formData[1],formData[2]);
		boolean status = carInsurancePageMethods.getErrorMessage("search_without_invalid_email");
		Assert.assertTrue(status);
	}

	@Then("user should get error message for invalid email input")
	public void check_error_message_for_invalid_email(){
		boolean status = carInsurancePageMethods.getErrorMessage("search_without_invalid_email");
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with invalid number
	 */
	@When("user searches with invalid number")
	public void search_with_invalid_number(){
		String[] formData = carInsurancePageMethods.getAllData("InvalidNumber");
		carInsurancePageMethods.fillApplicationDetails(formData[0],formData[1],formData[2]);
		boolean status = carInsurancePageMethods.getErrorMessage("search_with_invalid_number");
		Assert.assertTrue(status);
	}

	@Then("user should get error message for invalid number input")
	public void check_error_message_for_invalid_number(){
		String[] formData = carInsurancePageMethods.getAllData("InvalidNumber");
		carInsurancePageMethods.fillApplicationDetails(formData[0],formData[1],formData[2]);
		boolean status = carInsurancePageMethods.getErrorMessage("search_with_invalid_number");
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with all valid details
	 */
	@When("user search with valid details")
	public void search_with_valid_details(){
		String[] formData = carInsurancePageMethods.getAllData("ValidDetails");
		carInsurancePageMethods.fillApplicationDetails(formData[0],formData[1],formData[2]);
		boolean status = carInsurancePageMethods.getErrorMessage("search_with_valid_details");
		Assert.assertFalse(status);
	}

	@Then("no error message should be displayed")
	public void verify_valid_details_submission(){
		boolean status = carInsurancePageMethods.getErrorMessage("search_with_valid_details");
		Assert.assertFalse(status);
	}

	/**
	 * Closes active tab and releases resources.
	 */
	@Then("car insurance teardown")
	public void teardown() {
		driver.quit();
	}
}
