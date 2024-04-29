
# Find travel insurance plan for students

### Problem Statement

The challenge is to find suitable travel insurance plans for student travelers,
specifically targeting a group of two individuals aged 22 and 21,
intending to visit any European country. The goal is to identify the three lowest international travel insurance plans, including their respective amounts and insurance provider companies.

Along with that open car insurance menu and proceed without car number, keep filling details, give invalid email or phone number & capture the error message.

Finally collect all the menu items under Health Insurance.


### Detailed Problem Statement description

1. Find travel insurance plan for students, for 2 people (Age 22 & 21) & any European country, fill further dummy details & display three lowest international travel insurance plan with amount and insurance provider company

2. Get a Car Insurance quote, proceed without car number, keep filling details, give invalid email or phone number & capture the error message

3. Retrieve all Health Insurance menu items and store in a List; Display the same.

**Site Tested:** www.policybazaar.com

## Features
1. **Automation with Selenium and Java:** This project leverages Selenium in test scripts to automate and test PolicyBazaar.
2. **Page Object Model (POM) Design Pattern:** Implemented the POM design pattern using PageFactory to enhance test maintainability by separating page elements from test logic. Each web page has its corresponding Page Object class, making it easier to manage and update test cases.
3. **Data-Driven Testing:** We’ve incorporated data-driven testing by reading test data from JSON files allowing us to parameterize test and execute them with different input data.
4. **TestNG for Test Suite Creation**: Implemented TestNG to organize test cases into different test suites enabling us to define dependencies, perform parallel execution, and other configurations using TestNG annotations.
5. **Apache POI for Excel Integration:** We used Apache POI to write test results and data to Excel spreadsheets.
6. **Multi-Browser Testing Support:** Our test suite supports multiple browsers, including Google Chrome and Microsoft Edge to ensure cross-browser compatibility.
7. **Extent Report Generation:**
   After execution of the test, extent report is generated with embedded screenshots in report for visual validation.
8. **Maven Integration:** We’ve integrated Maven to handles dependencies easily.

## Technologies Used

1. Java
2. Selenium 
3. TestNG 
4. Apache POI 
5. ExtentReports

