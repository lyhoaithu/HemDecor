package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.LogInPage;
import pages.RegisterPage;

public class RegisterPageTest extends TestCase {
public ExcelUtils excelUtils= new ExcelUtils();
public static String pagePath="http://localhost:8081/HemDecor/user_account/signup.php";
public static File dataPath=new File("TestData/AutomationTestData.xlsx");

	@Test(description = "Verify Navigate To Register Page", priority = 1)
	public void navigateToRegisterPage() {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		clickOnElemnet(registerPage.btnDangKy);
		String currentURL=driver.getCurrentUrl();
		assertTrue(currentURL.contains("signup.php"));
	}

	@Test(description = "System directs To Login Page When Click On Quay Lại", priority = 2, groups="validation")
	public void clickOnQuayLai() {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.navigateToPage(pagePath);
		clickOnElemnet(registerPage.btnQuayLai);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("login.php"));
	}
	
	@DataProvider(name= "Register Successfully")
	public String[][] registerSuccessfullyData() {
		String[][] registerSuccessfullyData=null;
		try {
			registerSuccessfullyData = excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "RegisterSuccessfully");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File Not Found");
		}
     return registerSuccessfullyData;
	}

	@Test(description = "Validate Create Account Successfully", dataProvider = "Register Successfully", priority = 3, groups = "main")
	public void validateRegisterSuccessfully(String name, String password, String email, String phoneNumber, String expectedMessage) throws IOException {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.navigateToPage(pagePath);
		registerPage.sendKeys(name, password, email, phoneNumber);
		clickOnElemnet(registerPage.btnRegister);
		String actualMessage= driver.findElement(registerPage.lblSuccessfulMessage).getText();
		assertEquals(actualMessage, expectedMessage);
		
	}
	
	@DataProvider (name= "Register Fail When Provide Invalid Information", indices = {0,1,2,3,4,5,6,7,8,9})
	public String[][] registerFailData() {
		String[][] registerFailData= null;
		try {
			registerFailData= excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "RegisterFail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File Not Found");
		}
return registerFailData;
	}
	
	@Test(description = "Validate Create Account Unsuccessfully When Provide Invalid Information", dataProvider = "Register Fail When Provide Invalid Information", priority = 4, groups="validation")
	public void validateRegisterUnsuccessfully(String userName, String password, String email, String phoneNumber, String result) throws IOException {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.navigateToPage(pagePath);
		registerPage.sendKeys(userName, password, email, phoneNumber);
		clickOnElemnet(registerPage.btnRegister);
		if (email.equals("1@")) {
			String errorAlert= getHtml5ValidationMessage(registerPage.txtEmail);
			assertEquals(result, errorAlert);
		}
		else if (email.equals("1f")) {
			String errorAlert= getHtml5ValidationMessage(registerPage.txtEmail);
			assertEquals(result, errorAlert);
		}
		else{
			String actualMessage=driver.findElement(registerPage.lblUnsuccessfulMessage).getText();
			String expectedMessage= result;
			assertEquals(actualMessage, expectedMessage);
		}
	
	}
	
	
	@Test(description = "Validate Password Is Hidden When Entered", priority = 6, groups="validation")
	public void validatePasswordIsHidden() {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.navigateToPage(pagePath);
		fillInPlaceholder(registerPage.txtPassword, "Hoaithu*2811");
		String type = driver.findElement(registerPage.txtPassword).getAttribute("type");
		assertEquals(type, "password");
	}
	
	@Test(description = "Validate Password Is Showed When Click On Show Password Checkbox", priority = 7, groups="validation")
	public void validatePasswordIsShowWhenClickOnShowPasswordCheckbox() {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.navigateToPage(pagePath);
		fillInPlaceholder(registerPage.txtPassword, "Hoaithu*2811");
		clickOnElemnet(registerPage.chbShowPassword);
		String type = driver.findElement(registerPage.txtPassword).getAttribute("type");
		assertEquals(type, "text");
	}

	
	@DataProvider (name= "Validate Create Account Fail When Leaving Field Blank", indices = {12,13,14,15})
	public String[][] registerFailData2() {
		String[][] registerFailData2= null;
		try {
			registerFailData2= excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "RegisterFail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File Not Found");
		}
return registerFailData2;
	}
	
	@Test (description = "Validate Register Fail When Leaving Field Blank",  dataProvider = "Validate Create Account Fail When Leaving Field Blank", priority =5, groups="validation" )
	public void verifyOutcomeMessage(String userName, String password, String email, String phoneNumber, String result ) throws IOException {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.navigateToPage(pagePath);
		registerPage.sendKeys(userName, password, email, phoneNumber);
		clickOnElemnet(registerPage.btnRegister);
		String actualMessage= null;
		if (userName.equals("")) {
			actualMessage= getHtml5ValidationMessage(registerPage.txtName);
		}
		else if (password.equals("")) {
			actualMessage= getHtml5ValidationMessage(registerPage.txtPassword);
		}
		else if (email.equals("")) {
			actualMessage= getHtml5ValidationMessage(registerPage.txtEmail);
		}
		else if (phoneNumber.equals("")) {
			actualMessage= getHtml5ValidationMessage(registerPage.txtTelephoneNumber);
		}
		
		assertEquals(actualMessage, result);
		}
}

