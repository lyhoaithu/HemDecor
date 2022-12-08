package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.HomePage;
import pages.LogInPage;
import pages.RegisterPage;

public class LogInPageTest extends TestCase{
	public ExcelUtils excelUtils = new ExcelUtils();
	public String pagePath= "http://localhost:8081/HemDecor/user_account/login.php";
	public static File data=new File("TestData/AutomationTestData.xlsx");
	
	@DataProvider(name = "Login Successfully")
	public static String[][] logInSuccessfullyData() {
		ExcelUtils excelUtils= new ExcelUtils();
	    String[][] logInSuccessfullyData=null;
		try {
			logInSuccessfullyData = excelUtils.getDataFromExcel(data.getAbsolutePath(),"LogInSuccessfully");
		} catch (IOException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
			return logInSuccessfullyData;
		
	}
	
	@DataProvider(name = "Login Fail Using Invalid Data", indices = {0,1,2})
	public static String[][] logInFailUsingInvalidData() {
		ExcelUtils excelUtils= new ExcelUtils();
	    String[][] logInFailData=null;
		try {
			logInFailData = excelUtils.getDataFromExcel(data.getAbsolutePath(),"LogInFail");
		} catch (IOException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
			return logInFailData;
		
	}
	
	@DataProvider(name = "Login Fail When Leave Field Blank", indices = {3,4,5})
	public static String[][] logInFailWhenLeaveFieldBlank() {
		ExcelUtils excelUtils= new ExcelUtils();
	    String[][] logInFailWhenLeaveFieldBlankData=null;
		try {
			logInFailWhenLeaveFieldBlankData = excelUtils.getDataFromExcel(data.getAbsolutePath(),"LogInFail");
		} catch (IOException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
			return logInFailWhenLeaveFieldBlankData;
		
	}
	
	@Test(description = "Login Successfully", dataProvider ="Login Successfully", priority = 1, groups={"main"})
	public void logInSuccessfully(String username, String password) throws IOException {
		LogInPage logInPage= new LogInPage(driver);
		logInPage.navigateToPage(pagePath);
		logInPage.sendKeys(username, password);
		clickOnElemnet(logInPage.btnLogIn);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("user"));
	}
		
	@Test(description = "Login Using Invalid Data", dataProvider ="Login Fail Using Invalid Data", priority = 2, groups="validation")
	public void logInFailUsingInvalidData(String username, String password, String expectedMessage, String locator) throws IOException {
		LogInPage logInPage= new LogInPage(driver);	
		logInPage.navigateToPage(pagePath);
		logInPage.sendKeys(username, password);
		clickOnElemnet(logInPage.btnLogIn);
		String actualMessage= driver.findElement(logInPage.lblErrorMessage).getText();
		assertEquals(actualMessage, expectedMessage);
	}
	
	@Test(description = "Log In Fail When Leave Field Blank", dataProvider ="Login Fail When Leave Field Blank", priority = 3, groups="validation")
	public void logInFailWhenLeaveFieldBlankData(String username, String password, String expectedMessage, String locator) throws IOException {
		LogInPage logInPage= new LogInPage(driver);
		logInPage.navigateToPage(pagePath);
				logInPage.sendKeys(username, password);
				clickOnElemnet(logInPage.btnLogIn);
				String actualMessage=null;
					if(username.equals("")) {
						actualMessage= getHtml5ValidationMessage(logInPage.txtEmail);
					}
					else if (password.equals("")) {
						 actualMessage= getHtml5ValidationMessage(logInPage.txtPassword);
					}
					assertEquals(actualMessage, expectedMessage);
			}		
	
	@Test(description = "Validate password is hidden", priority = 4)
		public void validatePasswordIsHidden() {
			LogInPage logIn = new LogInPage(driver);
			logIn.navigateToPage(pagePath);
			fillInPlaceholder(logIn.txtPassword, "Hoaithu*2811");
			String expectedType="password";
			String actualType= driver.findElement(logIn.txtPassword).getAttribute("type");
assertEquals(expectedType, actualType);
		}
	
	@Test(description = "Validate password is show in text form when clicking on show password checkbox", priority = 5, groups="validation")
	public void validatePasswordIsShowed() {
		LogInPage logIn = new LogInPage(driver);
		logIn.navigateToPage(pagePath);
		fillInPlaceholder(logIn.txtPassword, "Hoaithu*2811");
		clickOnElemnet(logIn.chbShowPassword);
		String expectedType="text";
		String actualType= driver.findElement(logIn.txtPassword).getAttribute("type");
assertEquals(expectedType, actualType);
	}
}
		

