package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.ChangePasswordPage;
import pages.ForgetPasswordPage;
import pages.LogInPage;

public class FogetPasswordTest extends TestCase {
	ExcelUtils excelUtils = new ExcelUtils();
	
	
	@Test(description = "Navigate To Forget Password Page", priority = 1)
	public void navigateToLogInPage() {
		ForgetPasswordPage forgetPass= new ForgetPasswordPage(driver);
		forgetPass.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		clickOnElemnet(forgetPass.btnForgetPassword);
		String currentURL=driver.getCurrentUrl();
		assertTrue(currentURL.contains("forget-password.php"));
	}
	
@DataProvider (name="Forget Password Successfully")
public String[][] forgetPasswordSuccessfullyData() throws IOException { 
	String [][]forgetPasswordFailData = excelUtils.getDataFromExcel("D:\\AutomationTest\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx", "ForgetPasswordSuccessfully"); 
return forgetPasswordFailData;
}

@Test(description = "Retrieve Password Successfully", dataProvider = "Forget Password Successfully", priority = 2, groups={"main"})
public void retrievePasswordSuccessfully(String email, String phoneNumber) {
	ForgetPasswordPage forgetPass= new ForgetPasswordPage(driver);
	forgetPass.navigateToPage("http://localhost:8081/HemDecor/user_account/forget-password.php");
	forgetPass.sendKeys(email, phoneNumber);
	clickOnElemnet(forgetPass.btnRetrievePassword);
	boolean check=checkElementVisibility(forgetPass.lblSuccessfulMessage);
	assertEquals(check, true);
}

@DataProvider (name="Forget Password When Provide Wrong Email Or Phone Number", indices = {0,1})
public String[][] forgetPasswordFailWhenProvideEmailOrPhoneNumberData() throws IOException { 
	String [][]forgetPasswordFailData = excelUtils.getDataFromExcel("D:\\AutomationTest\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx", "ForgetPasswordFail"); 
return forgetPasswordFailData;
}

@Test(description = "Forget Password When Provide Wrong Email Or Phone Number", dataProvider = "Forget Password When Provide Wrong Email Or Phone Number", priority = 3, groups="validation")
public void retrievePasswordFailWhenProvideWrongEmailOrPhoneNumber(String email, String phoneNumber, String expectedMessage) {
	ForgetPasswordPage forgetPass= new ForgetPasswordPage(driver);
	forgetPass.navigateToPage("http://localhost:8081/HemDecor/user_account/forget-password.php");
	forgetPass.sendKeys(email, phoneNumber);
	clickOnElemnet(forgetPass.btnRetrievePassword);
	String actualMessage=driver.findElement(forgetPass.lblErrorMessage).getText();
	assertEquals(actualMessage, expectedMessage);
}
}
