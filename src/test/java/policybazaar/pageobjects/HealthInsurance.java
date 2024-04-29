package policybazaar.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import policybazaar.baseclass.BaseClass;
import policybazaar.pageactions.PageActions;
import policybazaar.utility.Utility;

public class HealthInsurance extends BaseClass {
	PageActions pageActions = new PageActions(driver);
	Utility utility = new Utility();


	/**
	 * Constructor to initialize the Health Insurance page object elements
	 */
	public HealthInsurance() {
		PageFactory.initElements(driver,this);
	}

	/**
	 * Insurance Product navigation tab element
	 */
	@FindBy(xpath="//a[contains(text(),'Insurance Products')]")
	public WebElement insuranceProduct;

	/**
	 * All Health Insurance products element under Insurance product navigation tab
	 */
	@FindBy(xpath="//a[@class='headlink' and contains(text(),'Health Insurance')]/following::ul[1]//a")
	public List<WebElement> healthInsuranceProducts;

	/**
	 * Health Insurance Menu container element under Health insurance header
	 */
	@FindBy(xpath = "//div[contains(@class,'ruby-col-3')][2]/ul")
	public WebElement healthInsuranceMenu;


	/**
	 * To navigate to Policy Bazaar Homepage and try to locate whether Health Insurance menu is available
	 * @return boolean value to mention whether Health Insurance menu is available or not.
	 */
	public boolean getHealthInsuranceMenu() {
		driver.get("https://www.policybazaar.com/");
		pageActions.hoverOnElement(insuranceProduct);
		try {
			if(healthInsuranceMenu.isDisplayed()){
				collectHealthInsuranceProducts();
				return true;
			}
		}
		catch(Exception e){
		}
		return false;
	}

	/**
	 * Collects all the health insurance products under the Health Insurance header menu.
	 */
	public void collectHealthInsuranceProducts(){
		String sheetName = "HealthInsuranceProducts";
		int row = 0;
		for(WebElement healthInsuranceProduct : healthInsuranceProducts) {
			try {
				utility.writeToExcel(sheetName, healthInsuranceProduct.getText(), row,0);
			}
			catch (Exception e){
				break;
			}
			row++;
		}
	}
}