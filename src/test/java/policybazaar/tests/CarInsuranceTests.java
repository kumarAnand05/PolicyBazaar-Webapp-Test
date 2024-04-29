package policybazaar.tests;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import policybazaar.baseclass.BaseClass;
import policybazaar.pageobjects.CarInsurance;

public class CarInsuranceTests extends BaseClass {

	/**
	 * Declaration Car Insurance page object class
	 */
	private CarInsurance carInsurance;

	/**
	 * Initialize the Car Insurance page object.
	 */
	@BeforeClass(groups = {"smoke", "regression"})
	public void initCarInsurancePage() {
		carInsurance = new CarInsurance();
	}

	/**
	 * Navigates to policy bazaar then navigates to Car Insurance section.
	 */
	@Test(priority = 1, groups = {"smoke", "regression"})
	public void navigateToCarInsurance() {
		carInsurance.visitCarInsurance();
	}

	/**
	 * Tests submission of application without providing car number
	 */
	@Test(priority = 2, dependsOnMethods = "navigateToCarInsurance")
	public void searchWithoutCarNumber(){
		carInsurance.submitCarNumber("");
		boolean status = carInsurance.getErrorMessage();
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with invalid car number
	 */
	@Test(priority = 3, dependsOnMethods = "navigateToCarInsurance")
	public void searchWithInvalidCarNumber(){
		String carNumber = carInsurance.getData("InvalidCarNumber");
		carInsurance.submitCarNumber(carNumber);
		boolean status = carInsurance.getErrorMessage();
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with valid car number
	 */
	@Test(priority = 4, dependsOnMethods = "navigateToCarInsurance", groups = {"regression"})
	public void searchWithValidCarNumber(){
		String carNumber = carInsurance.getData("ValidCarNumber");
		carInsurance.submitCarNumber(carNumber);
		boolean status = carInsurance.getErrorMessage();
		Assert.assertFalse(status);
	}

	/**
	 * Tests submission of the application without providing any user details
	 */
	@Test(priority = 5, dependsOnMethods = "searchWithValidCarNumber")
	public void searchWithoutUserDetails(){
		carInsurance.fillApplicationDetails("","","");
		boolean status = carInsurance.getErrorMessage();
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with invalid name
	 */
	@Test(priority = 6, dependsOnMethods = "searchWithValidCarNumber")
	public void searchWithInvalidName(){
		String[] formData = carInsurance.getAllData("InvalidName");
		carInsurance.fillApplicationDetails(formData[0],formData[1],formData[2]);
		boolean status = carInsurance.getErrorMessage();
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with invalid email id
	 */
	@Test(priority = 7, dependsOnMethods = "searchWithValidCarNumber")
	public void searchWithInvalidEmail(){
		String[] formData = carInsurance.getAllData("InvalidEmail");
		carInsurance.fillApplicationDetails(formData[0],formData[1],formData[2]);
		boolean status = carInsurance.getErrorMessage();
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with invalid number
	 */
	@Test(priority = 8, dependsOnMethods = "searchWithValidCarNumber")
	public void searchWithInvalidNumber(){
		String[] formData = carInsurance.getAllData("InvalidNumber");
		carInsurance.fillApplicationDetails(formData[0],formData[1],formData[2]);
		boolean status = carInsurance.getErrorMessage();
		Assert.assertTrue(status);
	}

	/**
	 * Tests submission of the application with all valid details
	 */
	@Test(priority = 9, dependsOnMethods = "searchWithValidCarNumber", groups = {"regression"})
	public void searchWithValidDetails(){
		String[] formData = carInsurance.getAllData("ValidDetails");
		carInsurance.fillApplicationDetails(formData[0],formData[1],formData[2]);
		boolean status = carInsurance.getErrorMessage();
		Assert.assertFalse(status);
	}

	/**
	 * Closes active tab and releases resources.
	 */
	@AfterClass(groups = {"smoke", "regression"})
	public void teardown() {
		driver.quit();
	}
}
