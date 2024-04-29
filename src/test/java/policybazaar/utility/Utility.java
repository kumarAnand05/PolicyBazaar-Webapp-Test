package policybazaar.utility;


import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import java.io.FileReader;

import java.util.Random;

/**
 * Contains different utility methods that has been used in the project to perform operation like
 * taking screenshot and saving them in the Screenshot directory. Writing into an Excel in testOutput dir.
 * Parsing the JSON data stored in the testData dir under resources. Get the current local machine date.
 * Get a random element from the passed dataset.
 */
public class Utility {

    /**
     * Captures the screenshot save in the Screenshots directory of the project.
     * @param driver WebDriver object.
     * @param methodName Takes the name of the test method as String from which it is called.
     */
    public void getScreenshot(WebDriver driver, String methodName){

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String filePath = "Screenshots"+File.separator+methodName+".png";
        File destination = new File(filePath);
        try{
            FileUtils.copyFile(source,destination);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot for"+methodName);
        }
    }

    /**
     * To write into specified Excel sheet in TestOutput directory of the project.
     * @param sheetName String value for name of the sheet into which data is to be written.
     * @param cellValue String value for the value that needs to be written.
     * @param row int value of the row into which the data is to be written.
     * @param col int value of the column into which the data is to be written.
     * @throws IOException in case the location cannot be found or some error occurs while writing
     * into the specified Excel file
     */
    public void writeToExcel(String sheetName, String cellValue, int row, int col) throws IOException {
        String filePath = "TestOutput"+ File.separator+"ProgramOutput.xlsx";
        File excelFile = new File(filePath);
        XSSFWorkbook workbook;

        if(excelFile.exists()){
            FileInputStream inputStr = new FileInputStream(excelFile);
            workbook = new XSSFWorkbook(inputStr);
            inputStr.close();
        }
        else{
            workbook = new XSSFWorkbook();
        }

        XSSFSheet sheet = workbook.getSheet(sheetName);
        if(sheet==null){
            sheet = workbook.createSheet(sheetName);
        }

        XSSFRow currentRow = sheet.getRow(row);
        if(currentRow==null){
            currentRow = sheet.createRow(row);
        }

        XSSFCell currentCell = currentRow.getCell(col);
        if(currentCell==null){
            currentCell = currentRow.createCell(col);
        }
        currentCell.setCellValue(cellValue);
        FileOutputStream outputStr = new FileOutputStream(excelFile);
        workbook.write(outputStr);
        outputStr.close();
    }

    /**
     * To get current date of the local machine
     * @return Array of String with Year, Month and Date as values.
     */
    public String[] getCurrentDate(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.toString().split("T")[0].split("-");
    }

    /**
     * To parse the JSON data file in the test/resources/testData directory.
     * @param fileName String value for the name of the file from which data is to be accessed.
     * @param fieldName String value of the name of the field value whose data is to be accessed.
     * @return Value from the specified filed as Array of String.
     */
    public String[] parseTestData(String fileName, String fieldName) {
        String dataDirectory = "src"+ File.separator+"test"+File.separator+"resources"+File.separator+"testData"+File.separator;
        JSONParser parser = new JSONParser();
        String[] fieldValue = null;
        try {
            Object obj = parser.parse(new FileReader(dataDirectory+fileName+".json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get(fieldName);
            fieldValue = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                fieldValue[i] = (String) jsonArray.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldValue;
    }

    /**
     * To get a random value from an Array dataset.
     * @param dataSet String array dataset from which random value is to be picked.
     * @return A random String value from the passed array dataset.
     */
    public String getRandomData(String[] dataSet){
        Random random = new Random();
        int randomIndex = random.nextInt(dataSet.length);
        return dataSet[randomIndex];
    }
}
