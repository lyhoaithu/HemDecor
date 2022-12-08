package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
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
	public ExcelUtils excelUtils = new ExcelUtils();
	public static File dataPath=new File("TestData/AutomationTestData.xlsx");
	public static String pagePath="http://localhost:8081/HemDecor/user_account/forget-password.php";
	
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
	String [][]forgetPasswordFailData = excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "ForgetPasswordSuccessfully"); 
return forgetPasswordFailData;
}

@Test(description = "Retrieve Password Successfully", dataProvider = "Forget Password Successfully", priority = 2, groups={"main"})
public void retrievePasswordSuccessfully(String email, String phoneNumber) {
	ForgetPasswordPage forgetPass= new ForgetPasswordPage(driver);
	forgetPass.navigateToPage(pagePath);
	forgetPass.sendKeys(email, phoneNumber);
	clickOnElemnet(forgetPass.btnRetrievePassword);
	String afterForgetPassword=driver.findElement(forgetPass.lblSuccessfulMessage).getText().substring(25,31);
	boolean check=checkElementVisibility(forgetPass.lblSuccessfulMessage);
	assertEquals(check, true);
	System.out.println(afterForgetPassword);
	LogInPage logIn= new LogInPage(driver);
	logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
	logIn.sendKeys("0963566858", afterForgetPassword);
	clickOnElemnet(logIn.btnLogIn);
	hoverMouse(By.xpath("/html/body/header/div/div/a"));
	clickOnElemnet(By.linkText("Đổi Mật Khẩu"));
	ChangePasswordPage changePass= new ChangePasswordPage(driver);
	changePass.sendKeys(afterForgetPassword, "Ngocyen*2102", "Ngocyen*2102");
	clickOnElemnet(changePass.btnChangePassword);
	
}

@DataProvider (name="Forget Password When Provide Wrong Email Or Phone Number", indices = {0,1})
public String[][] forgetPasswordFailWhenProvideEmailOrPhoneNumberData() throws IOException { 
	String [][]forgetPasswordFailData = excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "ForgetPasswordFail"); 
return forgetPasswordFailData;
}

@Test(description = "Forget Password When Provide Wrong Email Or Phone Number", dataProvider = "Forget Password When Provide Wrong Email Or Phone Number", priority = 3, groups="validation")
public void retrievePasswordFailWhenProvideWrongEmailOrPhoneNumber(String email, String phoneNumber, String expectedMessage) {
	ForgetPasswordPage forgetPass= new ForgetPasswordPage(driver);
	forgetPass.navigateToPage(pagePath);
	forgetPass.sendKeys(email, phoneNumber);
	clickOnElemnet(forgetPass.btnRetrievePassword);
	String actualMessage=driver.findElement(forgetPass.lblErrorMessage).getText();
	assertEquals(actualMessage, expectedMessage);
}
}
