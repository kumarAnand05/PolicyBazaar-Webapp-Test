package policybazaar.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import policybazaar.baseclass.BaseClass;
import policybazaar.pageobjects.TravelInsurance;

import java.util.ArrayList;

public class TravelInsuranceTests extends BaseClass {

    /**
     * Declaration Travel Insurance page object class
     */
    private TravelInsurance travelInsurance;

    /**
     * Initialize the Travel Insurance page object
     */
    @BeforeClass(groups = {"smoke", "regression"})
    public void initTravelInsurancePage(){
        travelInsurance = new TravelInsurance();
    }


    /**
     * Navigates to policy bazaar then navigates to Travel Insurance section
     */
    @Test(priority = 1 ,groups = {"smoke", "regression"})
    public void navigateToTravelInsurance(){
        travelInsurance.visitTravelInsurance();
    }

    /**
     * Tests continuation of application without selecting any country.
     */
    @Test(priority = 2, dependsOnMethods = "navigateToTravelInsurance")
    public void searchWithoutSelectingCountry(){
        travelInsurance.continueApplication();
        boolean status = travelInsurance.checkErrorMessage();
        Assert.assertTrue(status);
    }

    /**
     * Tests whether all the search suggestion are related to the provided search term.
     * @param searchTerm String value that is to be entered in the search box.
     * @param suggestedDestination String value of the destination that is loaded on entering the search term.
     */
    @Test(priority = 3, dependsOnMethods = "navigateToTravelInsurance", dataProvider = "searchResults")
    public void validateSearchSuggestions(String searchTerm, String suggestedDestination){
        Assert.assertTrue(suggestedDestination.contains(searchTerm));
    }

    /**
     * To get the suggested destination that is displayed on entering specified search term
     * @return Two-dimensional String array with first element as search term and second the suggested destination.
     */
    @DataProvider(name = "searchResults", indices = {0})
    public String[][] getSearchSuggestions(){
        String searchTerm = travelInsurance.getData("SearchTerm");
        return travelInsurance.getSearchResults(searchTerm);
    }

    /**
     * Tests whether the countries can be selected or not.
     */
    @Test(priority = 4, dependsOnMethods = "navigateToTravelInsurance",groups = {"regression"})
    public void validateCountrySelection(){
        ArrayList<String> countryNames = travelInsurance.getData("NonEuropeanCountry",2);
        travelInsurance.selectDestination(countryNames);
        Assert.assertEquals(countryNames, travelInsurance.selectedDestinations());
    }

    /**
     * Tests whether the selected countries can be removed or not.
     */
    @Test(priority = 5, dependsOnMethods = "validateCountrySelection")
    public void validateRemovingCountrySelection(){
        travelInsurance.removeSelectedDestination();
        Assert.assertEquals( travelInsurance.selectedDestinations().size(),0);
    }

    /**
     * Tests whether search is being continued with selection of country.
     */
    @Test(priority = 6, dependsOnMethods = "navigateToTravelInsurance", groups = {"regression"})
    public void validateCountrySearch(){
        String countryName = travelInsurance.getData("EuropeanCountry");
        travelInsurance.selectDestination(countryName);
        travelInsurance.continueApplication();
        boolean status = travelInsurance.checkErrorMessage();
        Assert.assertFalse(status);
    }

    /**
     * Tests searching without selection of the dates.
     */
    @Test(priority = 7, dependsOnMethods = "validateCountrySearch")
    public void searchWithoutDateSelection(){
        travelInsurance.continueApplication();
        boolean status = travelInsurance.checkErrorMessage();
        Assert.assertTrue(status);
    }

    /**
     * Tests searching with invalid dates selection of the dates.
     */
    @Test(priority = 8, dependsOnMethods = "validateCountrySearch")
    public void selectInvalidStartDate(){
        // Date Format Should be "2024-Mar-08"
        String sampleStartDate = travelInsurance.getData("InvalidStartDate");
        String sampleEndDate = travelInsurance.getData("InvalidEndDate");
        travelInsurance.selectDate("startDate",sampleStartDate);
        travelInsurance.selectDate("endDate",sampleEndDate);
        Assert.assertTrue(travelInsurance.checkErrorMessage());
    }

    /**
     * Tests searching with valid dates selection.
     */
    @Test(priority = 9, dependsOnMethods = "validateCountrySearch", groups = {"regression"})
    public void searchWithValidDates(){
        String sampleStartDate = travelInsurance.getData("ValidStartDate");
        String sampleEndDate = travelInsurance.getData("ValidEndDate");
        travelInsurance.selectDate("startDate",sampleStartDate);
        travelInsurance.selectDate("endDate",sampleEndDate);
        String nextHeader = travelInsurance.getDetailPageHeader("thirdStep");
        Assert.assertEquals(nextHeader,"How many people are travelling?");
    }

    /**
     * Tests selection of the number of traveller and the respective box number synchronization.
     */
    @Test(priority = 10, dependsOnMethods = "searchWithValidDates", groups = {"regression"})
    public void validateTravellerNumberSelection(){
        int numberOfTravellers = Integer.parseInt(travelInsurance.getData("NumberOfTravellers"));
        Assert.assertTrue(travelInsurance.selectNumberOfTraveller(numberOfTravellers));
    }

    /**
     * Tests searching without providing the age of the travellers.
     */
    @Test(priority = 11, dependsOnMethods = "validateTravellerNumberSelection")
    public void searchWithoutTravellerAge(){
        travelInsurance.continueApplication();
        boolean status = travelInsurance.checkErrorMessage();
        Assert.assertTrue(status);
    }

    /**
     * Tests searching with the age of the travellers.
     */
    @Test(priority = 12, dependsOnMethods = "validateTravellerNumberSelection", groups = {"regression"})
    public void searchWithTravellersAge(){
        ArrayList<String> ages = travelInsurance.getData("Ages",2);
        travelInsurance.selectTravellerAge(ages);
        String nextHeader = travelInsurance.getDetailPageHeader("fourthStep");
        Assert.assertTrue(nextHeader.contains("have a pre-existing medical condition?"));
    }

    /**
     * Tests searching without choosing the medical condition.
     */
    @Test(priority = 13, dependsOnMethods = "searchWithTravellersAge")
    public void searchWithoutMedicalConditionDetail(){
        travelInsurance.continueApplication();
        boolean status = travelInsurance.checkErrorMessage();
        Assert.assertTrue(status);
    }

    /**
     * Tests searching with the medical conditions.
     */
    @Test(priority = 14, dependsOnMethods = "searchWithTravellersAge", groups = {"regression"})
    public void searchWithMedicalConditionDetail(){
        String medicalCondition = travelInsurance.getData("MedicalCondition");
        String headerValue = travelInsurance.selectMedicalCondition(medicalCondition);
        Assert.assertEquals(headerValue,"Great! One last step to get your travel insurance plans");
    }

    /**
     * Tests searching without providing phone number
     */
    @Test(priority = 15, dependsOnMethods = "searchWithMedicalConditionDetail")
    public void searchWithoutPhoneNumber(){
        travelInsurance.continueApplication();
        boolean status = travelInsurance.checkErrorMessage();
        Assert.assertTrue(status);
    }

    /**
     * Tests searching with invalid phone number
     */
    @Test(priority = 16, dependsOnMethods = "searchWithMedicalConditionDetail")
    public void searchWithInvalidPhoneNumber(){
        String phoneNumber = travelInsurance.getData("InvalidPhoneNumber");
        travelInsurance.enterPhoneNumber(phoneNumber);
        boolean status = travelInsurance.checkErrorMessage();
        Assert.assertTrue(status);
    }

    /**
     * Tests searching with a valid phone number
     */
    @Test(priority = 17, dependsOnMethods = "searchWithMedicalConditionDetail")
    public void searchWithValidPhoneNumber(){
        String phoneNumber = travelInsurance.getData("ValidPhoneNumber");
        travelInsurance.enterPhoneNumber(phoneNumber);
        boolean status = travelInsurance.checkErrorMessage();
        Assert.assertFalse(status);
    }

    /**
     * Tests whether the filter button is active without providing any details
     */
    @Test(priority = 18, dependsOnMethods = "searchWithValidPhoneNumber")
    public void validateStudentFilterWithoutPlanDetail(){
        String planType = travelInsurance.getData("PlanType");
        boolean status = travelInsurance.selectPlanType(planType);
        Assert.assertFalse(status);
    }

    /**
     * Tests applying filter after providing the details
     */
    @Test(priority = 19, dependsOnMethods = "searchWithValidPhoneNumber")
    public void validatePlanTypeDetailsSubmission(){
        String tripDuration = travelInsurance.getData("TripDuration");
        boolean status = travelInsurance.fillStudentPlanDetails(tripDuration);
        Assert.assertTrue(status);
    }

    /**
     * Tests whether results can be sorted, and then collects the top 3 results.
     */
    @Test(priority = 20, dependsOnMethods = "searchWithValidPhoneNumber")
    public void validateSortingResults(){
        String sortType = travelInsurance.getData("SortType");
        travelInsurance.sortResults(sortType);
        int numberOfDataToCollect = Integer.parseInt(travelInsurance.getData("DataToCollect"));
        travelInsurance.collectInsuranceDetails(numberOfDataToCollect);
    }

    /**
     * Closes active tab and releases resources.
     */
    @AfterClass(groups = {"smoke", "regression"})
    public void teardown(){
        driver.quit();
    }
}
