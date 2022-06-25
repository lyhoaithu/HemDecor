package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.LogInPage;
import pages.UserCheckOutPage;

public class UserCheckOutPageTest extends TestCase{
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "phoneNumber", "password" })
	public void preCondition(String phoneNumber, String password) {
		LogInPage logIn = new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys(phoneNumber, password);
		clickOnElemnet(logIn.btnLogIn);
		clickOnElemnet(By.xpath("//a[contains(@href,'view_cart')]"));
	}
	public ExcelUtils excelUtils = new ExcelUtils();
	
	@DataProvider(name= "Create Order Successfully Data")
	public String [][]successfullyData() throws IOException {
		String [][] successfullyData= excelUtils.getDataFromExcel("D:\\AutomationTest\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx", "UserCreateOrderSuccessfully");
return successfullyData;
	}
	
	@Test(description = "Validate Create Order Successfully", dataProvider = "Create Order Successfully Data", priority = 3)
public void createOrderSuccessfully(String name, String phone, String address, String note) {
	UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
	clickOnElemnet(userCheckOutPage.btnThanhToanAtCart);
	userCheckOutPage.sendKeys(name, phone, address, note);
	clickOnElemnet(userCheckOutPage.rbCOD);
	clickOnElemnet(userCheckOutPage.btnThanhToanAtCheckOut);
	String currentURL=driver.getCurrentUrl();
	assertTrue(currentURL.contains("Đặt%20Hàng%20Thành%20Công"));
	String successfullMessage= driver.findElement(userCheckOutPage.lblSuccessfullMessage).getText();
	assertEquals(successfullMessage, "Đặt Hàng Thành Công");	
}
	
	@DataProvider(name= "Create Order Fail When Provide Invalid Data", indices = {0,1,2,3,4})
	public String [][]inavlidData() throws IOException {
		String [][] invalidData= excelUtils.getDataFromExcel("D:\\AutomationTest\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx", "UserCreateOrderFail");
return invalidData;
	}
	
	@Test(description = "Validate Create Order Fail When Provide Invalid Data", dataProvider = "Create Order Fail When Provide Invalid Data", priority = 1)
public void createOrderWhenProvideInvalidData(String name, String phone, String address, String note, String expectedMessage) {
		UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCart);
		userCheckOutPage.sendKeys(name, phone, address, note);
		clickOnElemnet(userCheckOutPage.rbCOD);
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCheckOut);
		String actualMessage=driver.findElement(userCheckOutPage.lblErrorMessage).getText();
		assertEquals(actualMessage, expectedMessage);
}
	
	@DataProvider(name= "Create Order Fail When Leave Field Blank", indices = {5,6,7})
	public String [][]fieldBlankData() throws IOException {
		String [][] fieldBlankData= excelUtils.getDataFromExcel("D:\\AutomationTest\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx", "UserCreateOrderFail");
return fieldBlankData;
	}
	
	@Test(description = "Validate Create Order Fail When Provide Invalid Data", dataProvider = "Create Order Fail When Leave Field Blank", priority = 2)
	public void createOrderWhenLeaveFieldBlank(String name, String phone, String address, String note, String expectedMessage) {
			UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
			clickOnElemnet(userCheckOutPage.btnThanhToanAtCart);
			userCheckOutPage.sendKeys(name, phone, address, note);
			clickOnElemnet(userCheckOutPage.rbCOD);
			clickOnElemnet(userCheckOutPage.btnThanhToanAtCheckOut);
			String actualMessage=null;
			if(name.equals("")) {
				actualMessage=getHtml5ValidationMessage(userCheckOutPage.txtName);
			}
			else if(phone.equals("")) {
				actualMessage=getHtml5ValidationMessage(userCheckOutPage.txtPhonenumber);
			}
			else if(address.equals("")) {
				actualMessage=getHtml5ValidationMessage(userCheckOutPage.txtAddress);
			}
			assertEquals(actualMessage, expectedMessage);
	}
	
	@Test(description = "Validate create order fail when clicking on Hủy")
	public void clickOnHuy() {
		UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCart);
		userCheckOutPage.sendKeys("Lý Hoài Thu", "0962370612", "Hà Nội", "abcd123");
		clickOnElemnet(userCheckOutPage.rbCOD);
		clickOnElemnet(userCheckOutPage.btnCancel);
		String currentURL= driver.getCurrentUrl();
		assertEquals(currentURL, "http://localhost:8081/HemDecor/user_cart/view_cart.php");
	}
	
	@Test(description = "Validate create order fail when don't choose payment method")
	public void notChoosePaymentMethod() {
		UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCart);
		userCheckOutPage.sendKeys("Lý Hoài Thu", "0962370612", "Hà Nội", "abcd123");
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCheckOut);
		String actualMessage= getHtml5ValidationMessage(userCheckOutPage.rbCOD);
		assertEquals(actualMessage, "Please select one of these options.");
	}
}
