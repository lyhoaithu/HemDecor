package pages;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.ExcelUtils;
import common.TestBase;

public class Page extends TestBase {
	
public Page(WebDriver driver) {
		this.driver= driver;
		// TODO Auto-generated constructor stub
	}

public static String locatorPath="D:\\AutomationTest\\02Projects\\HemDecor\\Responsitory\\HemDecor_Responsitory.xlsx";

public void navigateToPage(String destinationPage) {
	driver.navigate().to(destinationPage);
}

public static HashMap<String,By> readLocatorFromExcle(String filePath, String sheetName) {
	ExcelUtils excelUtils= new ExcelUtils();
	String[][] dataTable=null;
	try {
		dataTable = excelUtils.getDataFromExcel(filePath, sheetName);
	} catch (IOException e) {
		System.out.println("File Not Found");
		e.printStackTrace();
	}

	String[] locatorName= new String[dataTable.length];

	
	//Get locator Name
	for(int i=0;i <locatorName.length;i++) {
			locatorName[i]=dataTable[i][0];
		}
	
	//Get Locator
	By[]locator=new By[dataTable.length]; 
	for (int i=0; i<locatorName.length;i++) {
		locator[i]=excelUtils.readLocatorByType(dataTable[i][1], dataTable[i][2]);
	}
	
	//Create a hash map to call locator by its name
 HashMap<String,By> locatorArr= new HashMap<String,By>();
for (int i=0; i<locatorName.length; i++) {
	locatorArr.put(locatorName[i], locator[i]);
}
return locatorArr;
}


public String getCurrentURL() {
	String currentURL= driver.getCurrentUrl();
	return currentURL;

}



}
