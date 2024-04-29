package policybazaar.pageobjects;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import policybazaar.baseclass.BaseClass;
import policybazaar.pageactions.PageActions;
import policybazaar.utility.Utility;
import policybazaar.utility.ExtentReportManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravelInsurance extends BaseClass{

    PageActions pageActions = new PageActions(driver);
    Utility utility = new Utility();

    /**
     * To Initialize the Travel Insurance page object
     */
    public TravelInsurance(){
        PageFactory.initElements(driver,this);
    }


    /**
     * Insurance Product navigation tab element
     */
    @FindBy(xpath = "//a[contains(text(),'Insurance Products')]")
    public WebElement insuranceProducts;

    /**
     * Travel Insurance page element under Insurance product navigation tab
     */
    @FindBy(xpath = "//a[@class='headlink' and contains(text(),'Other Insurance')]/following::ul[1]/li[1]//a")
    public WebElement travelInsurancePage;

    /**
     * List of elements of continue button of Travel Insurance page.
     * Generally return list of two buttons, 1st for detail form, 2nd for main city page.
     */
    @FindBy(xpath = "//div[@class='nextbtn']//button")
    public List<WebElement> continueButton;

    /**
     * To locate any error message element on the Travel Insurance page
     */
    @FindBy(xpath = "//*[@class='err' and @style='display: block;']")
    public WebElement errorMessage;

    /**
     * City search box element
     */
    @FindBy(xpath = "//div[@id='selected-destinations']//input")
    public WebElement searchBox;

    /**
     * Search suggestion element returned after entering any search term
     */
    @FindBy(xpath = "//ul[contains(@class,'autocomplete')]/li")
    public List<WebElement> searchSuggestions;

    /**
     * Selected destination element within the search box
     */
    @FindBy(xpath = "//*[@id='selected-destinations']/p")
    public List<WebElement> selectedDestinations;

    /**
     * Cross button element of the selected destination element within the search box.
     */
    @FindBy(xpath = "//*[contains(@id,'countryRemove')]")
    public List<WebElement> removeSelectedDestination;

    /**
     * Currently active tab on the details submission page
     */
    @FindBy(xpath = "//li[contains(@class,'active') and not(contains(@class,'checked'))]")
    public WebElement activeApplicationTab;

    /**
     * Start date input box
     */
    @FindBy(xpath = "//*[@id='startdate']")
    public WebElement startDate;

    /**
     * End date input box
     */
    @FindBy(xpath = "//*[@id='enddate']")
    public WebElement endDate;

    /**
     * Back arrow element of the calendar page
     */
    @FindBy(xpath = "//*[contains(@class,'previous-action')]")
    public WebElement previousCalendarPage;

    /**
     * Nexr arrow element of the calendar page
     */
    @FindBy(xpath = "//*[contains(@class,'next-action')]")
    public WebElement nextCalendarPage;

    /**
     * Currently active month name Element
     */
    @FindBy(xpath = "//*[contains(@class,'select-months')]/option[@selected='selected']")
    public WebElement currentCalendarMonth;

    /**
     * Currently active year value Element
     */
    @FindBy(xpath = "//*[contains(@class,'select-years')]/option[@selected='selected']")
    public WebElement currentCalendarYear;

    /**
     * List of the date elements of the calendar page
     */
    @FindBy(xpath = "//*[@class='lightpick__days']/div")
    public List<WebElement> calendarDates;

    /**
     * List of the age boxes element of the detail page
     */
    @FindBy(xpath = "//div[@class='travellerDetails']//select")
    public List<WebElement> ageSelectionBoxes;

    /**
     * List of the number of traveller button elements of the detail page
     */
    @FindBy(xpath = "//div[contains(@class,'tcl-item')]")
    public List<WebElement> numberOfTraveller;

    /**
     * List of the yes and no button element of the detail page
     */
    @FindBy(xpath = "//label[@class='radioButton']//input")
    public List<WebElement> medicalConditions;

    /**
     * Phone number input text box element
     */
    @FindBy(xpath = "//*[@id='travelmobile']")
    public WebElement phoneNumber;

    /**
     * Frequent flyer radio button element to select the plan type
     */
    @FindBy(xpath = "//input[@id='multiTrip' and @type='radio']")
    public WebElement frequentFlyer;

    /**
     * Student plan radio button element to select the plan type
     */
    @FindBy(xpath = "//input[@id='studentTrip' and @type='radio']")
    public WebElement studentType;

    /**
     * Radio button elements of the right opened pane on selection of Frequent flyer option
     */
    @FindBy(xpath = "//*[@class='multiTripOptions']//input")
    public List<WebElement> multiTripDurations;

    /**
     * Radio button elements of the right opened pane on selection of Student plan option
     */
    @FindBy(xpath = "//input[contains(@id, 'Traveller') and @type='checkbox']")
    public List<WebElement> studentTravellers;

    /**
     * Drop-down element to select the duration of the travel
     */
    @FindBy(xpath = "//*[contains(@class,' tripDurationInput')]//select")
    public WebElement studentTripDuration;

    /**
     * Apply filter button element of the right opened pane
     */
    @FindBy(xpath = "//button[contains(text(),'Apply')]")
    public WebElement applyFilter;

    /**
     * Sort By button option element
     */
    @FindBy(xpath = "//*[@class='filter_name_heading']")
    public WebElement sortByButton;

    /**
     * List of the elements under sort by menu
     */
    @FindBy(xpath = "//*[contains(@name,'sort') and @type='radio']")
    public List<WebElement> sortValues;

    /**
     * List of the name of the insurer of the policy results
     */
    @FindBy(xpath = "//*[contains(@class,'insurerName')]")
    public List<WebElement> insurerNames;

    /**
     * List of the price of the insurer of the policy results
     */
    @FindBy(xpath = "//*[@class='premiumPlanPrice']")
    public List<WebElement> insurancePrice;


    /**
     * Navigates to Policy bazaar page then navigates to Travel Insurance page
     */
    public void visitTravelInsurance(){
        driver.get("https://www.policybazaar.com");
        pageActions.hoverOnElement(insuranceProducts);
        pageActions.scrollToElement(travelInsurancePage);
        pageActions.clickUsingJS(travelInsurancePage);
    }

    /**
     * Clicks on the currently displayed continue button of Travel Insurance Page
     */
    public void continueApplication(){
        if(continueButton.get(0).isDisplayed()){
            pageActions.clickUsingJS(continueButton.get(0));
        }
        else if(continueButton.get(1).isDisplayed()){
            pageActions.clickUsingJS(continueButton.get(1));
        }
    }

    /**
     * Checks for presence of any error message element on the Travel Insurance page
     * @return boolean value whether there is any error message on the element or not.
     */
    public boolean checkErrorMessage(){
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
        catch(NoSuchElementException e){
        }
        ExtentReportManager.errorMessage=false;
        return false;
    }

    /**
     * Parses the JSON travelInsurance data for the specified filed value
     * @param filedValue String value of the field name that needs to be accessed
     * @return String value at the specified field value
     */
    public String getData(String filedValue){
        String dataFile = "travelInsuranceData";
        String[] testData = utility.parseTestData(dataFile,filedValue);
        return utility.getRandomData(testData);
    }

    /**
     * Parses the JSON travelInsurance data for the specified filed value.
     * @param filedValue String value of the field name that needs to be accessed
     * @param requiredDataSize int value for the size of data that needs to be accessed
     * @return ArrayList of String containing the values from the dataset of specified size
     */
    public ArrayList<String> getData(String filedValue, int requiredDataSize){
        String dataFile = "travelInsuranceData";
        String[] testData = utility.parseTestData(dataFile,filedValue);
        ArrayList<String> dataList = new ArrayList<>();
        if(testData.length<=requiredDataSize){
            return new ArrayList<>(Arrays.asList(testData));
        }
        else{
            for(int i=0; i<requiredDataSize;i++){
                dataList.add(testData[i]);
            }
            return dataList;
        }
    }

    /**
     * Enters the destination into the text box for city selection
     * @param destinationName String value for the city name that needs to be selected
     */
    public void enterDestination(String destinationName){
        searchBox.clear();
        searchBox.click();
        searchBox.sendKeys(destinationName);
    }

    /**
     * To get the search results for the entered search term
     * @param searchTerm String value for the search term
     * @return Two-dimensional String array containing the first element as the search term and
     * second value as the suggested destination name on entering the search term
     */
    public String[][] getSearchResults(String searchTerm){
        enterDestination(searchTerm);
        String[][] suggestions = new String[searchSuggestions.size()][2];
        for(int i=0; i<searchSuggestions.size();i++){
            suggestions[i][0] = searchTerm;
            suggestions[i][1] = searchSuggestions.get(i).getText();
        }
        return suggestions;
    }

    /**
     * Enters the location name and select the destination
     * @param location String value of the location that needs to be selected
     */
    public void selectDestination(String location){
        if(!selectedDestinations().contains(location)){
            enterDestination(location);
            new Actions(driver).keyDown(Keys.ENTER).perform();
            searchBox.clear();
        }
    }

    /**
     * Enters the location name and select all the passed location names in the ArrayList
     * @param location ArrayList of String values of the locations that needs to be selected
     */
    public void selectDestination(ArrayList<String> location){
        for(String locationName : location){
            selectDestination(locationName);
        }
    }

    /**
     * Gets all the currently selected location names
     * @return ArrayList of String containing the name of the destination currently selected
     */
    public ArrayList<String> selectedDestinations(){
        ArrayList<String> selections = new ArrayList<>();
        for(WebElement destinations: selectedDestinations){
            selections.add(destinations.getText().replace("×","").trim());
        }
        return selections;
    }

    /**
     * Clears the Country search box by removing all the locations selected
     */
    public void removeSelectedDestination(){
        List<WebElement> selectedLocations = removeSelectedDestination;
        for(WebElement deselectDestination : selectedLocations){
            deselectDestination.click();
        }
    }

    /**
     * To get tab title (header) of currently opened tab of application details
     * @param stepName tab number of the page currently being accessed.
     * @return String value containing the header text of currently opened tab of application detail
     */
    public String getDetailPageHeader(String stepName){
        continueApplication();
        if(!checkErrorMessage()){
            try {
                return driver.findElement(By.xpath("//div[@class='" + stepName + "']/div")).getText().trim();
            }
            catch(Exception e){
            }
        }
        return null;
    }

    /**
     * To move to the next calendar month of the opened calendar element
     */
    public void moveCalendarForward(){
        nextCalendarPage.click();
    }

    /**
     * Selects the specified userDate from the opened calendar
     * @param userDate String value of the date specified by user that needs to be selected
     * @return boolean value based on the result whether the selection of date was successful
     */
    public boolean getDateElement(String userDate){
        List<WebElement> dates = calendarDates;
        for(WebElement date: dates){
            try {
                if (date.getText().equals(userDate)) {
                    date.click();
                    activeApplicationTab.click();
                    return true;
                }
            }
            catch(ElementClickInterceptedException exception) {
                return false;
            }
        }
        return false;
    }

    /**
     * To select the date specified by the user from specified year and month
     * @param dateType String value of date type whether the date is a startDate or endDate
     * @param date String value of the date specified by the user in format "2024-Mar-12"
     * @return boolean value if the date selection is valid selection as per the application guidelines
     */
    public boolean selectDate(String dateType ,String date){
        if(dateType.equals("startDate")){
            startDate.click();
        }
        else if (dateType.equals("endDate")){
            pageActions.clickUsingJS(endDate);
        }

        ArrayList<String> months = new ArrayList<>(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));

        String[] currentDate = utility.getCurrentDate();
        int currYear = Integer.parseInt(currentDate[0]);
        String currMonth = months.get(Integer.parseInt(currentDate[1])-1);
        int currDay = Integer.parseInt(currentDate[2]);

        String[] userDate = date.split("-");
        int usrYear = Integer.parseInt(userDate[0]);
        String usrMonth = userDate[1];
        int usrDay = Integer.parseInt(userDate[2]);


        if(currYear<=usrYear){
            while(usrYear!=Integer.parseInt(currentCalendarYear.getText())) {
                if (nextCalendarPage.isDisplayed()) {
                    moveCalendarForward();
                }
                else {
                    return false;
                }
            }
            if(months.indexOf(currMonth)<= months.indexOf(usrMonth)){
                while(!(currentCalendarMonth.getText().contains(usrMonth))){
                    if (nextCalendarPage.isDisplayed()) {
                        nextCalendarPage.click();
                    }
                    else {
                        return false;
                    }
                }
                if(currDay<=usrDay){
                    if(!getDateElement(Integer.toString(usrDay))){
                        return false;
                    }
                }
                else{
                    if(getDateElement(Integer.toString(usrDay))){
                        return false;
                    }
                }
            }
            else{
                if(previousCalendarPage.isDisplayed()){
                    return false;
                }
            }
        }
        else{
            if(previousCalendarPage.isDisplayed()){
                return false;
            }
        }
        return true;
    }

    /**
     * To select the number of travellers applying for the policy
     * @param travellerNumber number of travellers applying for the policy
     * @return boolean value if the number of age boxes is same as the number of travellers
     */
    public boolean selectNumberOfTraveller(int travellerNumber){
        WebElement userSelection = numberOfTraveller.get(travellerNumber-1);
        userSelection.click();
        return (ageSelectionBoxes.size()==travellerNumber);
    }

    /**
     * Selects the age of the travellers applying for the policy
     * @param ages ArrayList of String containing the ages of the travellers
     */
    public void selectTravellerAge(ArrayList<String> ages){
        for(int i=0; i<ageSelectionBoxes.size();i++){
            pageActions.selectOption(ageSelectionBoxes.get(i),Integer.parseInt(ages.get(i)));
        }
    }

    /**
     * To select the medical condition of the traveller
     * @param medicalCondition String value "Yes" or "No" specifying whether the travellers have any medical conditions
     * @return String value of the header tab of the next tab of the application.
     */
    public String selectMedicalCondition(String medicalCondition) {
        if(medicalCondition.equalsIgnoreCase("no")) {
            medicalConditions.get(1).click();
        } else if (medicalCondition.equalsIgnoreCase("yes")) {
            medicalConditions.get(0).click();
        }
        return driver.findElement(By.xpath("//div[@class='fifthStep']/div")).getText().trim();
    }

    /**
     * Enter the phone number detail
     * @param number String value of the phone number
     */
    public void enterPhoneNumber(String number){
        phoneNumber.clear();
        phoneNumber.sendKeys(number);
        continueApplication();
    }

    /**
     * Selects the frequentFlyer plan from the type of plan
     * @param tripDuration Duration of the journey
     */
    public void selectFrequentPlanType(int tripDuration){
        frequentFlyer.click();
        ArrayList<Integer> availableDurations = new ArrayList<>(Arrays.asList(30,45,60,90));
        int selectedDuration = availableDurations.indexOf(tripDuration)+1;
        multiTripDurations.get(selectedDuration).click();
        applyFilter.click();
    }

    /**
     * Selects the Plantype from the type of plan
     * @param planType String value of the type of plan that needs to be selected
     * @return boolean value if the selection was successful
     */
    public boolean selectPlanType(String planType){
        if(planType.equalsIgnoreCase("Frequent flyer plans")) {
            frequentFlyer.click();
        }
        else if (planType.equalsIgnoreCase("Student plans")) {
            studentType.click();
        }
        return applyDetails();
    }

    /**
     * Clicks on the apply button of the right opened pane if it is enabled
     * @return boolean value of it is enabled and clicked
     */
    public boolean applyDetails(){
        if(applyFilter.isEnabled()) {
            applyFilter.click();
            return true;
        }
        return false;
    }

    /**
     * Fills the student plan details
     * @param tripDuration String value for the trip duration
     * @return boolean value if the selection was successful
     */
    public boolean fillStudentPlanDetails(String tripDuration){
        for(WebElement students: studentTravellers){
            try {
                students.click();
            }
            catch(ElementClickInterceptedException e){
                pageActions.clickUsingJS(students);
            }
        }
        pageActions.selectOption(studentTripDuration,tripDuration);
        return applyDetails();
    }

    /**
     * Sorts the search results
     * @param sortType String value of the sort type "high or low"
     */
    public void sortResults(String sortType){
        try {
            sortByButton.click();
        }
        catch (Exception e){
            pageActions.clickUsingJS(sortByButton);
        }

        if(sortType.equalsIgnoreCase("low")){
            try {
                sortValues.get(1).click();
            }
            catch (Exception e){
                pageActions.clickUsingJS(sortValues.get(1));
            }
        } else if (sortType.equalsIgnoreCase("high")) {
            try {
                sortValues.get(2).click();
            }
            catch (Exception e){
                pageActions.clickUsingJS(sortValues.get(2));
            }
        }
        else{
            try {
                sortValues.get(0).click();
            }
            catch (Exception e){
                pageActions.clickUsingJS(sortValues.get(0));
            }
        }
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
        }
    }

    /**
     * To collect specified number of insurance details from the search result in Excel sheet
     * @param listSize number of insurance details that needs to be collected
     */
    public void collectInsuranceDetails(int listSize){

        ArrayList<String> insurers = new ArrayList<>();
        ArrayList<String> premiumPrices = new ArrayList<>();
        int i=0;
        for(WebElement policies: insurerNames){
            pageActions.scrollToElement(policies);
            insurers.add(policies.getText());
            premiumPrices.add(insurancePrice.get(i).getText());
            if(insurers.size()==listSize){
                break;
            }
            i++;
        }
        int currIndex=0;
        for(int row = 0; row<insurers.size();row++){
            try {
                utility.writeToExcel("InsuranceDetails", insurers.get(currIndex), row,0);
                utility.writeToExcel("InsuranceDetails", premiumPrices.get(currIndex), row,1);
            }
            catch (IOException e){
                System.out.println("Some error occurred while storing data in excel");
                break;
            }
            currIndex++;
        }
    }
}
