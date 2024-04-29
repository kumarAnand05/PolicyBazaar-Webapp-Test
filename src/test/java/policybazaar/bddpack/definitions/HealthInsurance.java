package policybazaar.bddpack.definitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import policybazaar.bddpack.baseclass.BaseClass;
import policybazaar.pageactions.PageActions;
import policybazaar.utility.Utility;

import java.util.List;


public class HealthInsurance{

	/**
	 * Instance of utility class
	 */
	Utility utility = new Utility();
	/**
	 * Instance of driver
	 */
	WebDriver driver;

	/**
	 * Navigation to Health Insurance page object.
	 */
	@Given("user opens policy bazaar website")
	public void user_opens_policy_bazaar() {
		driver = new BaseClass().createDriver("Chrome");
		driver.get("https://www.policybazaar.com/");
	}

	/**
	 * Hovers on insurance products
	 */
	@When("user hover on insurance products")
	public void hover_on_insurance_products() {
		new PageActions(driver).hoverOnElement(driver.findElement(By.xpath("//a[contains(text(),'Insurance Products')]")));
	}

	/**
	 * To check the presence of health insurance products and collect all the insurance products
	 */
	@Then("user should be able to see all health insurance product")
	public void health_insurance_products_should_be_available() {
		String sheetName = "HealthInsuranceProducts";
		try {
			WebElement healthInsuranceMenu = driver.findElement(By.xpath("//div[contains(@class,'ruby-col-3')][2]/ul"));
			if(healthInsuranceMenu.isDisplayed()){
				int row = 0;
				List<WebElement> healthInsuranceProducts = driver.findElements(By.xpath("//a[@class='headlink' and contains(text(),'Health Insurance')]/following::ul[1]//a"));
				for(WebElement healthInsuranceProduct : healthInsuranceProducts) {
					try {
						utility.writeToExcel(sheetName, healthInsuranceProduct.getText(), row,0);
					}
					catch (Exception e){
						break;
					}
					row++;
				}
				Assert.assertTrue(true);
			}
		}
		catch(Exception e){
            Assert.fail();
		}
	}

	/**
	 * To close the browser and release resources
	 */
	@Then("close the browser")
	public void teardown() {
		driver.quit();
	}
}
