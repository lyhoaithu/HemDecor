package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import common.TestBase;
import pages.ChangePasswordPage;
import pages.LogInPage;
import pages.Page;

public class ChangePasswordTest extends TestCase {
	public static File dataPath=new File("TestData/AutomationTestData.xlsx");
	public static String pagePath="http://localhost:8081/HemDecor/user_account/change-password.php";
	
	@BeforeMethod (onlyForGroups = "Before Changing")
	public void preCondition() {
		LogInPage logIn= new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("0962370612", "Hoaithu*2811");
		clickOnElemnet(logIn.btnLogIn);
		hoverMouse(By.xpath("/html/body/header/div/div/a"));
		clickOnElemnet(By.linkText("Đổi Mật Khẩu"));
	}

	
	public void preConditionAfter() {
		LogInPage logIn= new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("0962370612", "Hoaithu*2812");
		clickOnElemnet(logIn.btnLogIn);
		hoverMouse(By.xpath("/html/body/header/div/div/a"));
		clickOnElemnet(By.linkText("Đổi Mật Khẩu"));
	}
	
	@DataProvider(name= "Change Password Successfully")
	public String[][] readDataFromExcel() {
		ExcelUtils excelUtils= new ExcelUtils();
		String [][] data= null;
		try {
			data= excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "ChangePasswordSuccessfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File Not Found");
		}
		
return data;
	}
	
	@DataProvider(name= "Change Password Fail When Provide Invalid Information", indices = {0,1,2,3,4})
	public String[][] readDataFromExcel1() {
		ExcelUtils excelUtils= new ExcelUtils();
		String [][] data= null;
		try {
			data= excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "ChangePasswordFail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File Not Found");
		}
return data;
	}
	
	@DataProvider(name= "Change Password Unsuccessfully When Leaving Fields Blank", indices = {5,6,7,8,9,10})
	public String[][] readDataFromExcel2() {
		ExcelUtils excelUtils= new ExcelUtils();
		String [][] data= null;
		try {
			data= excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "ChangePasswordFail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File Not Found");
		}
return data;
	}
	
	@Test(description = "Change Password Successfully", dataProvider = "Change Password Successfully", priority=1, groups="main")
	public void changePasswordSuccessfully(String oldPass, String newPass, String confirmPass, String result) throws IOException {	
		LogInPage logIn= new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("0962370612", "Hoaithu*2811");
		clickOnElemnet(logIn.btnLogIn);
		hoverMouse(By.xpath("/html/body/header/div/div/a"));
		clickOnElemnet(By.linkText("Đổi Mật Khẩu"));
		ChangePasswordPage changePass= new ChangePasswordPage(driver);
		changePass.sendKeys(oldPass, newPass, confirmPass);
		clickOnElemnet(changePass.btnChangePassword);
		String actualMessage= getAlertMessage();
		String expectedMessage= result;
		assertEquals(actualMessage, expectedMessage);
	}
	
	@Test (description = "Change Password Unsuccessfully", dataProvider = "Change Password Fail When Provide Invalid Information",priority = 2, groups={"validation"})
	public void changePasswordUnsuccessfully(String oldPass, String newPass, String confirmationPass, String result) throws IOException {
		preConditionAfter();
		ChangePasswordPage changePass= new ChangePasswordPage(driver);
		changePass.sendKeys(oldPass, newPass, confirmationPass);
		clickOnElemnet(changePass.btnChangePassword);
		String actualMessage= driver.findElement(changePass.lblErrorMessage).getText();
		String expectedMessage= result;
		assertEquals(actualMessage, expectedMessage);
		
			}
	
	@Test (description = "Change Password Unsuccessfully When leaving fields blank",priority = 3, dataProvider = "Change Password Unsuccessfully When Leaving Fields Blank",  groups={"validation"})
	public void changePasswordUnsuccessfullyWhenLeavingFieldsBlank(String oldPass, String newPass, String confirmationPass, String result) throws IOException {
		preConditionAfter();
		ChangePasswordPage changePass= new ChangePasswordPage(driver);
		changePass.sendKeys(oldPass, newPass, confirmationPass);
		clickOnElemnet(changePass.btnChangePassword);
		String actualMessage=null;
		if(oldPass.equals("")) {
			actualMessage= getHtml5ValidationMessage(changePass.txtOldPass);
		}
		else if(newPass.equals("")) {
			actualMessage= getHtml5ValidationMessage(changePass.txtNewPass);
		}
		else if(confirmationPass.equals("")) {
			actualMessage= getHtml5ValidationMessage(changePass.txtConfirmPass);
		}
		String expectedMessage= result;
		assertEquals(actualMessage, expectedMessage);
		
			}

	@Test (description = "Validate Password Is Hidden", groups={"validation"},priority = 4)
	public void validatePasswordIsHidden() {
		preConditionAfter();
		ChangePasswordPage changePass = new ChangePasswordPage(driver);
		changePass.sendKeys("Hoaithu*2811", "hoaithu*2001", "hoaithu*2001");
		String currentPassType = driver.findElement(changePass.txtOldPass).getAttribute("type");
		String newPassType = driver.findElement(changePass.txtNewPass).getAttribute("type");
		String confirmPassType = driver.findElement(changePass.txtConfirmPass).getAttribute("type");
		assertEquals(currentPassType, "password");
		assertEquals(newPassType, "password");
		assertEquals(newPassType, "password");
	}		
	@Test (description = "Validate Password Is Shown", priority = 5, groups={"validation"})
	public void validatePasswordIsShown() {
		preConditionAfter();
		ChangePasswordPage changePass = new ChangePasswordPage(driver);
		changePass.sendKeys("Hoaithu*2811", "hoaithu*2001", "hoaithu*2001");
		clickOnElemnet(By.xpath("/html/body/section/div[2]/div/form/input"));
		String currentPassTypeText = driver.findElement(changePass.txtOldPass).getAttribute("type");
		String newPassTypeText = driver.findElement(changePass.txtNewPass).getAttribute("type");
		String confirmPassTypeText = driver.findElement(changePass.txtConfirmPass).getAttribute("type");
		assertEquals(currentPassTypeText, "text");
		assertEquals(newPassTypeText, "text");
		assertEquals(newPassTypeText, "text");
	}
	
}
