package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import common.ExcelUtils;
import pages.AddProductPage;
import pages.AdminHomePage;
import pages.LogInPage;

public class AddProductPageTest extends TestCase {
	public static File data=new File("TestData/AutomationTestData.xlsx");
	@BeforeMethod (alwaysRun = true)
	public void preCondition() {
		LogInPage logIn= new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("HemDecor", "HemDecor*1234");
		clickOnElemnet(logIn.btnLogIn);
		AdminHomePage adminHomePage= new AdminHomePage(driver);
		clickOnElemnet(adminHomePage.btnProduct);
	}
	public ExcelUtils excelUtils = new ExcelUtils();

	@DataProvider(name = "Add Product Successfully", indices = {0,1})
	public String[][] addProductSuccessfullyData() throws IOException {
		String[][] addProductSuccessfully = excelUtils.getDataFromExcel(
				data.getAbsolutePath(),
				"AddProductSuccessfully");
		return addProductSuccessfully;
	}
	
	@DataProvider(name = "Add Product With Multiple Size Successfully", indices = {2})
	public String[][] addProductWithMultipleSizeSuccessfullyData() throws IOException {
		String[][] addProductWithMultipleSizeSuccessfullyData = excelUtils.getDataFromExcel(
				data.getAbsolutePath(),
				"AddProductSuccessfully");
		return addProductWithMultipleSizeSuccessfullyData;
	}

	@DataProvider(name = "Add Product Fail When Leaving Field Blank At General Information Page", indices = { 0, 1 })
	public String[][] addProductFailWhenLeavingFieldAtGeneralInformationPageBlank() throws IOException {
		String[][] addProductWhenLeavingFieldBlankData = excelUtils.getDataFromExcel(
				data.getAbsolutePath(), "AddProductFail");
		return addProductWhenLeavingFieldBlankData;
	}

	@DataProvider(name = "Add Product Fail When Leaving Field Blank At Details Information Page", indices = { 2, 3, 4 })
	public String[][] addProductFailWhenLeavingFieldAtDetailsInformationPageBlank() throws IOException {
		String[][] addProductWhenLeavingFieldBlankData = excelUtils.getDataFromExcel(
				data.getAbsolutePath(), "AddProductFail");
		return addProductWhenLeavingFieldBlankData;
	}
	
	@DataProvider(name = "Add Product Fail When Providing Invalid Information At General Information Page", indices = {5,6,7, 14, 15, 16})
	public String[][] addProductFailWhenProvidingInvalidInformationAtGeneralInformationPage() throws IOException {
		String[][] addProductFailWhenProvidingInvalidInformationAtGeneralInformationPageData = excelUtils.getDataFromExcel(
				data.getAbsolutePath(), "AddProductFail");
		return addProductFailWhenProvidingInvalidInformationAtGeneralInformationPageData;
	}
	
	@DataProvider(name = "Add Product Fail When Providing Invalid Information At Detail Information Page", indices = {8,9,10,11,12,13, 17})
	public String[][] addProductFailWhenProvidingInvalidInformationAtDetailInformationPage() throws IOException {
		String[][] addProductFailWhenProvidingInvalidInformationAtDetailInformationPageData = excelUtils.getDataFromExcel(
				data.getAbsolutePath(), "AddProductFail");
		return addProductFailWhenProvidingInvalidInformationAtDetailInformationPageData;
	}
	@Test(description = "Add Product With One Size Successfully", dataProvider = "Add Product Successfully", priority = 3, groups="main")
	public void addProductWithOneSizeSuccessfully(String picture, String additionalPicture1, String additionalPicture2,
			String additionalPicture3, String additionalPicture4, String name, String categoryID, String material,
			String description, String productID, String size, String quantity, String price) {
		AddProductPage addProductPage = new AddProductPage(driver);
		addProductPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/add_basic.php");
		addProductPage.sendKeysAtGeneralPage(picture, additionalPicture1, additionalPicture2, additionalPicture3, additionalPicture4, name, categoryID, material, description);
clickOnElemnet(addProductPage.btnContinue);
addProductPage.sendKeysAtDetailsPage(productID, size, quantity, price);
clickOnElemnet(addProductPage.btnSave);
acceptAlertMessage();
boolean checkVisibility=checkElementVisibility(By.xpath("//thead//following-sibling::tbody//following-sibling::td[text()='"+categoryID+"']"));
assertEquals(checkVisibility, true);
	}
	
	@Test(description = "Add Product With Multiple Size Successfully", dataProvider = "Add Product With Multiple Size Successfully", priority = 4, groups="main")
	public void addProductWithMultipleSizeSuccessfully(String picture, String additionalPicture1, String additionalPicture2,
			String additionalPicture3, String additionalPicture4, String name, String categoryID, String material,
			String description, String productID, String size, String quantity, String price) {
		AddProductPage addProductPage = new AddProductPage(driver);
		addProductPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/add_basic.php");
		addProductPage.sendKeysAtGeneralPage(picture, additionalPicture1, additionalPicture2, additionalPicture3, additionalPicture4, name, categoryID, material, description);
clickOnElemnet(addProductPage.btnContinue);
addProductPage.sendKeysAtDetailsPage(productID, size, quantity, price);
clickOnElemnet(addProductPage.btnAddSize);
addProductPage.sendKeysAtDetailsPage("B01-02", "16x10", "10", "20000");
clickOnElemnet(addProductPage.btnSave);
acceptAlertMessage();
boolean checkVisibility=checkElementVisibility(By.xpath("//thead//following-sibling::tbody//following-sibling::td[text()='"+categoryID+"']"));
assertEquals(checkVisibility, true);
	}
	
	

	@Test(description = "Validate Adding Product Fail When Leaving Field At General Information Page Blank", dataProvider = "Add Product Fail When Leaving Field Blank At General Information Page", priority = 5, groups="validation")
	public void validateAddingProductFailWhenLeavingFieldBlank(String picture, String name, String categoryID,
			String material, String description, String productID, String size, String quantity, String price,
			String expectedResult) {		
		AddProductPage addProductPage = new AddProductPage(driver);
		clickOnElemnet(addProductPage.btnAddProduct);
		addProductPage.sendKeysAtGeneralPage02(picture, name, categoryID, material, description);
		String actualMessage = null;
		if (name.equals("")) {
			actualMessage = getHtml5ValidationMessage(addProductPage.txtProductName);
		} else if (categoryID.equals("")) {
			actualMessage = getHtml5ValidationMessage(addProductPage.txtCategoryID);
		}
		assertEquals(actualMessage, expectedResult);

	}

	@Test(description = "Validate Adding Product Fail When Leaving Field At Details Information Page Blank", dataProvider = "Add Product Fail When Leaving Field Blank At Details Information Page", priority = 6, groups="validation")
	public void validateAddingProductFailWhenLeavingFieldBlankAtDetailsInformationPage(String picture, String name,
			String categoryID, String material, String description, String productID, String size, String quantity,
			String price, String expectedResult) {
		AddProductPage addProductPage = new AddProductPage(driver);
		clickOnElemnet(addProductPage.btnAddProduct);
		addProductPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/add_details.php");
		addProductPage.sendKeysAtDetailsPage(productID, size, quantity, price);
		String actualMessage = null;
		if (productID.equals("")) {
			actualMessage = getHtml5ValidationMessage(addProductPage.txtProductID);
		} else if (quantity.equals("")) {
			actualMessage = getHtml5ValidationMessage(addProductPage.txtQuantity);
		}
		else if (price.equals("")) {
			actualMessage = getHtml5ValidationMessage(addProductPage.txtPrice);
		}
		assertEquals(actualMessage, expectedResult);
	}
	
	@Test(description = "Validate Adding Product Fail When Providing Invalid Information At General Information Page", dataProvider = "Add Product Fail When Providing Invalid Information At General Information Page", priority = 7, groups="validation")
	public void validateAddingProductFailWhenProvidingInvalidInformationAtGeneralInformationPage(String picture, String name,
			String categoryID, String material, String description, String productID, String size, String quantity,
			String price, String expectedResult) {
		AddProductPage addProductPage = new AddProductPage(driver);
		clickOnElemnet(addProductPage.btnAddProduct);
		addProductPage.sendKeysAtGeneralPage02(picture, name, categoryID, material, description);
		clickOnElemnet(addProductPage.btnContinue);
		String actualMessage = driver.findElement(addProductPage.lblErrorMessage).getText();
		assertEquals(actualMessage, expectedResult);
	}
	
	@Test(description = "Validate Adding Product Fail When Providing Invalid Information At Detail Information Page", dataProvider = "Add Product Fail When Providing Invalid Information At Detail Information Page", priority = 8,groups="validation")
	public void validateAddingProductFailWhenProvidingInvalidInformationAtDetailInformationPage(String picture, String name,
			String categoryID, String material, String description, String productID, String size, String quantity,
			String price, String expectedResult) {
		AddProductPage addProductPage = new AddProductPage(driver);
		clickOnElemnet(addProductPage.btnAddProduct);
		addProductPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/add_details.php");
		addProductPage.sendKeysAtDetailsPage(productID, size, quantity, price);
		clickOnElemnet(addProductPage.btnSave);
		String actualMessage = driver.findElement(addProductPage.lblErrorMessage).getText();
		assertEquals(actualMessage, expectedResult);
	}
	
	@Test(description = "Validate Clicking on button 'Hủy' At General Information Page", dataProvider = "Add Product With Multiple Size Successfully", priority = 1, groups="validation")
	public void validateClickingOnButtonHuyAtGeneralInformationPage(String picture, String additionalPicture1, String additionalPicture2,
			String additionalPicture3, String additionalPicture4, String name, String categoryID, String material,
			String description, String productID, String size, String quantity, String price) {
		AddProductPage addProductPage= new AddProductPage(driver);
		clickOnElemnet(addProductPage.btnAddProduct);
//		addProductPage.navigateToPage("http://localhost:8080/HemDecor/admin_manage_product/add_basic.php?error=Ch%C6%B0a%20Th%C3%AAm%20%E1%BA%A2nh%20B%C3%ACa");
		addProductPage.sendKeysAtGeneralPage02(picture, name, categoryID, material, description);
		clickOnElemnet(addProductPage.btnCancel);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("manage_category.php"));
	}
	
	@Test(description = "Validate Clicking on button 'Hủy' At Details Information Page", dataProvider = "Add Product With Multiple Size Successfully", priority = 2, groups="validation")
	public void validateClickingOnButtonHuyAtDetailsInformationPage(String picture, String additionalPicture1, String additionalPicture2,
			String additionalPicture3, String additionalPicture4, String name, String categoryID, String material,
			String description, String productID, String size, String quantity, String price) {
		AddProductPage addProductPage= new AddProductPage(driver);
        addProductPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/add_details.php");
		addProductPage.sendKeysAtDetailsPage(productID, size, quantity, price);
		clickOnElemnet(addProductPage.btnCancel02);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("manage_category.php"));
	}
}
