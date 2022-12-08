package common;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

public class TestBase {
	
	public WebDriver driver;
public void openSingleBrowser(String browser) {
	File file01= new File("driver/msedgedriver.exe");
	File file02= new File("driver/chromedriver.exe");
	if(browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", file02.getAbsolutePath());
		driver = new ChromeDriver();
}
	else if(browser.equalsIgnoreCase("edge")) {
		System.setProperty("webdriver.edge.driver", file01.getAbsolutePath());
		driver = new EdgeDriver();
}
	driver.manage().window().maximize();
}

public void clickOnElemnet(By locator) {
	WebDriverWait w = new WebDriverWait(driver, 10);
	w.until(ExpectedConditions.presenceOfElementLocated(locator));
	driver.findElement(locator).click();
}

public void fillInPlaceholder(By locator, String value) {
	WebElement e = driver.findElement(locator);
	if (!e.getText().isEmpty()) {
		e.clear();
	}
	e.sendKeys(value);
}

public void hoverMouse(By locator) {
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(locator)).perform();
}

public void selectDropdownBox(By locator, int index) throws UnsupportedEncodingException {
	Select dropdownBox = new Select(driver.findElement(locator));
	//String valueVietnamese = new String (value.getBytes( "ISO-8859-1"),"UTF-8");
	dropdownBox.selectByIndex(index);;
}

public boolean checkElementVisibility(By locator) {
	boolean checkVisibility=false;
	int count=0;
	while (!driver.findElement(locator).isDisplayed()|| count <10) {
		WebDriverWait w = new WebDriverWait(driver, 3);
	      // presenceOfElementLocated condition
	      w.until(ExpectedConditions.presenceOfElementLocated(locator));	
	      count+=1;
	      checkVisibility= false;
	}
	checkVisibility= true;
	return checkVisibility;
}

public String getAlertMessage() {
	String alertMessage=driver.switchTo().alert().getText();
	return alertMessage;
}

public void acceptAlertMessage() {
	WebDriverWait wait = new WebDriverWait(driver, 10);
	driver.switchTo().alert().accept();
}

public void rejectAlertMessage() {
	driver.switchTo().alert().dismiss();

}

public String getHtml5ValidationMessage(By locator) {
JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(locator));
}

public  String getValueFromDatabase(String query, int columnIndex) throws ClassNotFoundException, SQLException {
	DataConnection con= new DataConnection();
	ResultSet rs= con.verifyData(query);
	
	//Tạo Arrays List để chứa kq lấy từ DB
	ArrayList<String> result= new ArrayList<String>();
	
	//ĐỌc kq từ db r ghi vào array
	while(rs.next()){
		 result.add(rs.getString(columnIndex));
		}
	
	 Object[] resultArr= new String[result.size()];
	 resultArr=result.toArray();
	 String resultString=Arrays.deepToString(resultArr);
return resultString;
}
public int countNumberOfRows() {
	List<WebElement> row= driver.findElements(By.xpath("//tbody//tr"));
	int numberOfRows= row.size();
return numberOfRows;
}

public int countNumberOfColumns() {
	List<WebElement> column= driver.findElements(By.xpath("//tbody[1]//tr//th"));
int numberOfColumns= column.size();
return numberOfColumns;
}

public String[] getDataFromTableColumn(By locator) {
	List<WebElement> column= driver.findElements(locator);
	
	//Tống hết element vào 1 list
	String [] columnData= new String [column.size()];
	for (int i=0; i<column.size();i++) {
		columnData[i]= column.get(i).getText();
	}
	return columnData;
}

public String[] getDataFromTableRow(String locator) {
	List<WebElement> column= driver.findElements(By.xpath(locator));
	
	//Tống hết element vào 1 list
	String [] rowData= new String [column.size()];
	for (int i=0; i<column.size();i++) {
		rowData[i]= column.get(i).getText();
	}
	return rowData;
}
public void dragAndDrop(By sourceLocator, By destinationLocator) {
	Actions action = new Actions(driver);
	action.dragAndDrop(driver.findElement(sourceLocator), driver.findElement(destinationLocator)).perform();
}

public boolean checkElementDisable(By locator) {
	boolean status=driver.findElement(locator).isEnabled();
	return status;

}


}