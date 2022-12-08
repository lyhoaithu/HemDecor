package pages;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.ExcelUtils;

public class LogInPage extends Page {
	public HashMap<String, By> locatorMap=readLocatorFromExcle("D:\\AutomationTest\\02Projects\\HemDecor\\Responsitory\\HemDecor_Responsitory.xlsx", "LogIn");
	public By txtEmail=locatorMap.get("txtEmail");
	public  By txtPassword= locatorMap.get("txtPassword");
	public  By chbShowPassword= locatorMap.get("chbShowPassword");
	public  By btnLogIn= locatorMap.get("btnLogIn");
	public By lblErrorMessage= locatorMap.get("lblErrorMessage");
	public By iconAccount= locatorMap.get("iconAccount");
	public By txtLogIn= locatorMap.get("txtLogIn");
	
	public LogInPage(WebDriver driver) {
		super(driver);
	}
public void sendKeys(String username, String password) {
	fillInPlaceholder(txtEmail, username);
	fillInPlaceholder(txtPassword, password);

}
	
//public static void main(String[] args) {
//	ExcelUtils excelUtils= new ExcelUtils();
//    String[][] userNameAndPassword=null;
//	try {
//		userNameAndPassword = excelUtils.getDataFromExcel("D:\\Automation Test\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx","LogInData");
//		System.out.println(Arrays.deepToString(userNameAndPassword));
//	} catch (IOException e) {
//		System.out.println("File Not Found");
//		e.printStackTrace();
//	}
		
			
		}
	


