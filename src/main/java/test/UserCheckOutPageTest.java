package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.LogInPage;
import pages.UserCartPage;
import pages.UserCheckOutPage;

public class UserCheckOutPageTest extends TestCase{
	@BeforeMethod(alwaysRun = true)
	
	public void preCondition() {
		LogInPage logIn = new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("0962370612", "Hoaithu*2812");
		clickOnElemnet(logIn.btnLogIn);
	}
	
	public void addCartWithSmallerQuantity() {
		UserCartPage userCartPage= new UserCartPage(driver);
		userCartPage.navigateToPage("http://localhost:8081/HemDecor/user_products/product_page.php");
		clickOnElemnet(By.xpath("//div[@class='content']//a"));
		String instockProduct=null;
		int randomInstockProduct;
		List<WebElement> size= driver.findElements(userCartPage.btnSize);
		Random rand = new Random();
		
		if(!size.isEmpty()) {
			clickOnElemnet(userCartPage.btnSize);
					clickOnElemnet(userCartPage.btnAdd);
				}
		
		else if(size.isEmpty()){
			String[] instockProductAr= driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
            instockProduct= instockProductAr[0];
			 int ranProductQuantity = rand.nextInt(Integer.parseInt(instockProduct)-1)+1;
		        String ranProductQuantityStr= String.valueOf(ranProductQuantity);
		        for(int i=0; i<ranProductQuantity;i++) {
					clickOnElemnet(userCartPage.btnAdd);
				}
		}
		clickOnElemnet(userCartPage.btnAddToCart);
	}
	
	public ExcelUtils excelUtils = new ExcelUtils();
	public static File dataPath=new File("TestData/AutomationTestData.xlsx");
	
	@DataProvider(name= "Create Order Successfully Data")
	public String [][]successfullyData() throws IOException {
		String [][] successfullyData= excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "UserCreateOrderSuccessfully");
return successfullyData;
	}
	
	@Test(description = "Validate Create Order Successfully", dataProvider = "Create Order Successfully Data", priority = 3, groups="main")
public void createOrderSuccessfully(String name, String phone, String address, String note) {
	UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
	userCheckOutPage.addProductPreCon();
	clickOnElemnet(userCheckOutPage.iconCart);
	clickOnElemnet(userCheckOutPage.chbCheckAll);
	clickOnElemnet(userCheckOutPage.btnThanhToanAtCart);
	userCheckOutPage.sendKeys(name, phone, address, note);
	clickOnElemnet(userCheckOutPage.rbCOD);
	clickOnElemnet(userCheckOutPage.btnThanhToanAtCheckOut);
	String currentURL=driver.getCurrentUrl();
	assertTrue(currentURL.contains("my-order.php?success"));
	String successfullMessage= driver.findElement(userCheckOutPage.lblSuccessfullMessage).getText();
	assertEquals(successfullMessage, "Đặt Hàng Thành Công");	
}
	
	@DataProvider(name= "Create Order Fail When Provide Invalid Data", indices = {0,1,2,3,4})
	public String [][]inavlidData() throws IOException {
		String [][] invalidData= excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "UserCreateOrderFail");
return invalidData;
	}
	
	@Test(description = "Validate Create Order Fail When Provide Invalid Data", dataProvider = "Create Order Fail When Provide Invalid Data", priority = 1, groups="validation")
public void createOrderWhenProvideInvalidData(String name, String phone, String address, String note, String expectedMessage) {
		UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
		addCartWithSmallerQuantity();
		clickOnElemnet(By.xpath("//a[contains(@href,'view_cart')]"));
		clickOnElemnet(userCheckOutPage.chbCheckAll);
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCart);
		userCheckOutPage.sendKeys(name, phone, address, note);
		clickOnElemnet(userCheckOutPage.rbCOD);
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCheckOut);
		String actualMessage=driver.findElement(userCheckOutPage.lblErrorMessage).getText();
		assertEquals(actualMessage, expectedMessage);
}
	
	@DataProvider(name= "Create Order Fail When Leave Field Blank", indices = {5,6,7})
	public String [][]fieldBlankData() throws IOException {
		String [][] fieldBlankData= excelUtils.getDataFromExcel(dataPath.getAbsolutePath(), "UserCreateOrderFail");
return fieldBlankData;
	}
	
	@Test(description = "Validate Create Order Fail When Leave Field Blank", dataProvider = "Create Order Fail When Leave Field Blank", priority = 2, groups="validation")
	public void createOrderWhenLeaveFieldBlank(String name, String phone, String address, String note, String expectedMessage) {
			UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
			addCartWithSmallerQuantity();
			clickOnElemnet(userCheckOutPage.iconCart);
			clickOnElemnet(userCheckOutPage.chbCheckAll);
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
	
	@Test(description = "Validate create order fail when don't choose payment method", groups="validation")
	public void notChoosePaymentMethod() {
		UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
		addCartWithSmallerQuantity();
		clickOnElemnet(userCheckOutPage.iconCart);
		clickOnElemnet(userCheckOutPage.chbCheckAll);
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCart);
		userCheckOutPage.sendKeys("Lý Hoài Thu", "0962370612", "Hà Nội", "abcd123");
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCheckOut);
		String actualMessage= getHtml5ValidationMessage(userCheckOutPage.rbCOD);
		assertEquals(actualMessage, "Please select one of these options.");
	}
	
	@Test(description = "Validate create order fail when clicking on Hủy", groups="validation")
	public void clickOnHuy() {
		UserCheckOutPage userCheckOutPage = new UserCheckOutPage(driver);
		addCartWithSmallerQuantity();
		clickOnElemnet(userCheckOutPage.iconCart);
		clickOnElemnet(userCheckOutPage.chbCheckAll);
		clickOnElemnet(userCheckOutPage.btnThanhToanAtCart);
		userCheckOutPage.sendKeys("Lý Hoài Thu", "0962370612", "Hà Nội", "abcd123");
		clickOnElemnet(userCheckOutPage.rbCOD);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(userCheckOutPage.btnCancel));
		String currentURL= driver.getCurrentUrl();
		assertEquals(currentURL, "http://localhost:8081/HemDecor/user_cart/view_cart.php");
	}
}
