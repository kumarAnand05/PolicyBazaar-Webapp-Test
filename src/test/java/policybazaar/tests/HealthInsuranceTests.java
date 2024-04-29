package policybazaar.tests;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import policybazaar.baseclass.BaseClass;
import policybazaar.pageobjects.HealthInsurance;


public class HealthInsuranceTests extends BaseClass{

	/**
	 * Declaration of Health Insurance page object class
	 */
	private HealthInsurance healthInsuranceObj;

	/**
	 * Initialize the Health Insurance page object.
	 */
	@BeforeClass(groups = {"smoke", "regression"})
	public void initHealthInsurancePage() {
		healthInsuranceObj = new HealthInsurance();
	}

	/**
	 * Navigates to policy bazaar and checks the presence of Health Insurance menu and collects all the menu in Excel.
	 */
	@Test(priority=1, groups = {"smoke", "regression"})
	public void validateHeathInsuranceMenu() {
		boolean status = healthInsuranceObj.getHealthInsuranceMenu();
		Assert.assertTrue(status);
	}

	/**
	 * Closes active tab and releases all resources.
	 */
	@AfterClass(groups = {"smoke", "regression"})
	public void teardown() {
		driver.quit();
	}
}
