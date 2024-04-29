package policybazaar.pageactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


/**
 * Contains different method that are used to perform various operation on the web pages like
 * Scrolling to any specified WebElement.
 * Hovering on any element
 * Clicking on the WebElement using JavaScript Executor whenever .click() method doesn't work.
 * Selecting any option from a drop-down list.
 */
public class PageActions {

    public WebDriver driver;

    /**
     * Constructor to initialize the driver class variable.
     * @param driver WebDriver object.
     */
    public PageActions(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Scrolls to the specified WebElement into center block view.
     * @param element WebElement to which scroll operation is to be performed.
     */
    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block : 'center'});", element);
    }

    /**
     * Hover on the specified WebElement.
     * @param element WebElement on which the hover action is to be performed
     */
    public void hoverOnElement(WebElement element){
        (new Actions(driver)).moveToElement(element).perform();
    }

    /**
     * To click on a WebElement wherever .click() method is inoperable.
     * @param pageElement WebElement on which the click action is to be performed.
     */
    public void clickUsingJS(WebElement pageElement){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageElement);
    }

    /**
     * To select an option from the specified drop-down element.
     * @param dropDownElement The drop-down element from which we need to choose an option.
     * @param textOption String option that needs to be selected.
     */
    public void selectOption(WebElement dropDownElement, String textOption){
        Select select = new Select(dropDownElement);
        select.selectByVisibleText(textOption);
    }

    /**
     * To select an option from the specified drop-down element.
     * @param dropDownElement The drop-down element from which we need to choose an option.
     * @param indexOfOption option specified at the specified index that needs to be selected.
     */
    public void selectOption(WebElement dropDownElement, int indexOfOption){
        Select select = new Select(dropDownElement);
        select.selectByIndex(indexOfOption);
    }
}
