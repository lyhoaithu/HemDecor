package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.AdminEditProductPage;
import pages.AdminHomePage;
import pages.LogInPage;

public class AdminEditProductPageTest extends TestCase {
	@BeforeMethod (alwaysRun = true)
	@Parameters({"admin", "adminPassword"})
	public void preCondition(String phoneNumber, String password) {
		LogInPage logIn= new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys(phoneNumber, password);
		clickOnElemnet(logIn.btnLogIn);
		AdminHomePage adminHomePage= new AdminHomePage(driver);
		clickOnElemnet(adminHomePage.btnProduct);
		
	}
	public ExcelUtils excelUtils= new ExcelUtils();
	public static File dataFile=new File("TestData/AutomationTestData.xlsx");
	
	@DataProvider(name="Edit product general information successfully data")
	public String [][] generalInformationSuccessfully() throws IOException {
		String[][] data= excelUtils.getDataFromExcel(dataFile.getAbsolutePath(), "EditGeneralInforSuccessfully");
		return data;

	}
	@Test(description = "Validate  edit product general information successfully", dataProvider = "Edit product general information successfully data", groups="main")
	public void editProductGeneralInforSuccessfully(String productName, String categoryID, String material, String note) {
		AdminEditProductPage adminEdit= new AdminEditProductPage(driver);
	    clickOnElemnet(adminEdit.iconEye);
	    clickOnElemnet(adminEdit.btnEdit);
//		String[] beforeEdit= {driver.findElement(adminEdit.lblProductName).getText(), driver.findElement(adminEdit.lblCategoryID).getText(), driver.findElement(adminEdit.lblMaterial).getText(), driver.findElement(adminEdit.lblNote).getText()};
		adminEdit.sendKeys(productName, categoryID, material, note);
		clickOnElemnet(adminEdit.btnSave);
acceptAlertMessage();
String[] afterEdit= {driver.findElement(adminEdit.lblProductName).getText(), driver.findElement(adminEdit.lblCategoryID).getText(), driver.findElement(adminEdit.lblMaterial).getText(), driver.findElement(adminEdit.lblNote).getText()};
String[] data= {"Tên: "+productName, "Mã phân loại: "+categoryID, "Chất liệu: "+material,"Mô Tả Sản Phẩm: "+ note};
assertEquals(adminEdit, data);
	}
	
	@DataProvider(name="Edit product general information fail when provide invalid data", indices = {2,3,4})
	public String [][] generalInformationFail() throws IOException {
		String[][] data= excelUtils.getDataFromExcel(dataFile.getAbsolutePath(), "EditGeneralInforFail");
		return data;

	}
	@Test(description = "Validate  edit product general information fail when provide invalid data", dataProvider = "Edit product general information fail when provide invalid data", groups="validation")
	public void editProductGeneralInforFail(String productName, String categoryID, String material, String note, String message) {
		AdminEditProductPage adminEdit= new AdminEditProductPage(driver);
	    adminEdit.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D01");
	    clickOnElemnet(adminEdit.btnEdit);
	    WebDriverWait w = new WebDriverWait(driver, 5);
	    w.until(ExpectedConditions.presenceOfElementLocated(adminEdit.popUp));
//		String[] beforeEdit= {driver.findElement(adminEdit.lblProductName).getText(), driver.findElement(adminEdit.lblCategoryID).getText(), driver.findElement(adminEdit.lblMaterial).getText(), driver.findElement(adminEdit.lblNote).getText()};
		adminEdit.sendKeys(productName, categoryID, material, note);
		clickOnElemnet(adminEdit.btnSave);
String actualErrorMessage= driver.findElement(adminEdit.lblErrorMessage).getText();
assertEquals(actualErrorMessage, message);
	}

	@DataProvider(name="Edit product general information fail when leaving fields blank", indices = {0,1})
	public String [][] generalInformationBlankFail() throws IOException {
		String[][] data= excelUtils.getDataFromExcel(dataFile.getAbsolutePath(), "EditGeneralInforFail");
		return data;

	}
	@Test(description = "Validate  edit product general information fail when leaving fields blank", dataProvider = "Edit product general information fail when leaving fields blank", groups="validation")
	public void editProductGeneralInforBlankFieldFail(String productName, String categoryID, String material, String note, String message) {
		AdminEditProductPage adminEdit= new AdminEditProductPage(driver);
	    adminEdit.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D01");
	    clickOnElemnet(adminEdit.btnEdit);
//		String[] beforeEdit= {driver.findElement(adminEdit.lblProductName).getText(), driver.findElement(adminEdit.lblCategoryID).getText(), driver.findElement(adminEdit.lblMaterial).getText(), driver.findElement(adminEdit.lblNote).getText()};
	    WebDriverWait w = new WebDriverWait(driver, 5);
	    w.until(ExpectedConditions.presenceOfElementLocated(adminEdit.popUp));
	    adminEdit.sendKeys(productName, categoryID, material, note);
		clickOnElemnet(adminEdit.btnSave);
if(productName.equals("")) {
	assertEquals(getHtml5ValidationMessage(adminEdit.txtName), message);
}
else if (categoryID.equals("")) {
	assertEquals(getHtml5ValidationMessage(adminEdit.txtCategoryID), message);
}

//Edit product Size
	}
@DataProvider(name="Edit product size information successfully data")
public String [][] sizeInformationSuccessfully() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFile.getAbsolutePath(), "EditSizeSuccessfully");
	return data;

}
@Test(description = "Validate  edit product size information successfully", dataProvider = "Edit product size information successfully data")
public void editProductSizeInforSuccessfully(String sizeName, String sizeSize, String sizePrice, String sizeQuantity) {
	AdminEditProductPage adminEdit= new AdminEditProductPage(driver);
    adminEdit.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D01");
String[] beforeEdit= {driver.findElement(adminEdit.lblSizeID).getText(), driver.findElement(adminEdit.lblSizeSize).getText(), driver.findElement(adminEdit.lblSizePrice).getText(), driver.findElement(adminEdit.lblSizeSize).getText()};
clickOnElemnet(adminEdit.btnEdit);	
//clickOnElemnet(editPage.btnAddSize);
//WebDriverWait w = new WebDriverWait(driver, 5);
//w.until(ExpectedConditions.presenceOfElementLocated(editPage.popUp));
clickOnElemnet(adminEdit.iconSizeEdit);
WebDriverWait w = new WebDriverWait(driver, 5);
w.until(ExpectedConditions.presenceOfElementLocated(adminEdit.popUp));
adminEdit.sendKeysSize(sizeName, sizeSize, sizePrice, sizeQuantity);
	clickOnElemnet(adminEdit.btnSaveSize);
	acceptAlertMessage();
	String[] afterEdit= {driver.findElement(adminEdit.lblSizeID).getText(), driver.findElement(adminEdit.lblSizeSize).getText(), driver.findElement(adminEdit.lblSizePrice).getText(), driver.findElement(adminEdit.lblSizeSize).getText()};
assertEquals(beforeEdit, afterEdit);
}


@DataProvider(name="Edit product size information fail when provide invalid data", indices = {3,4,5,6,7,8})
public String [][] sizeInformationFail() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFile.getAbsolutePath(), "EditSizeFail");
	return data;

}
@Test(description = "Validate  edit product size information fail when provide invalid data", dataProvider = "Edit product size information fail when provide invalid data", groups="validation")
public void editProductSizeInforFail(String sizeName, String sizeSize, String sizePrice, String sizeQuantity, String message) {
	AdminEditProductPage adminEdit= new AdminEditProductPage(driver);
    adminEdit.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D01");
    clickOnElemnet(adminEdit.btnEdit);
    String[] beforeEdit= {driver.findElement(adminEdit.lblSizeID).getText(), driver.findElement(adminEdit.lblSizeSize).getText(), driver.findElement(adminEdit.lblSizePrice).getText(), driver.findElement(adminEdit.lblSizeSize).getText()};
    clickOnElemnet(adminEdit.iconSizeEdit);
  WebDriverWait w = new WebDriverWait(driver, 5);
  w.until(ExpectedConditions.presenceOfElementLocated(adminEdit.popUp));
	adminEdit.sendKeysSize(sizeName, sizeSize, sizePrice, sizeQuantity);
	clickOnElemnet(adminEdit.btnSaveSize);
acceptAlertMessage();
String[] afterEdit= {driver.findElement(adminEdit.lblSizeID).getText(), driver.findElement(adminEdit.lblSizeSize).getText(), driver.findElement(adminEdit.lblSizePrice).getText(), driver.findElement(adminEdit.lblSizeSize).getText()};
assertEquals(beforeEdit, afterEdit);
}


@DataProvider(name="Edit product size information fail when leaving fields blank data", indices = {0,1,2})
public String [][] sizeInformationBlankFieldsFail() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFile.getAbsolutePath(), "EditSizeFail");
	return data;

}
@Test(description = "Validate  edit product size information fail when leaving fields blank", dataProvider = "Edit product size information fail when leaving fields blank data", groups="validation")
public void editProductSizeInforBlankFieldsFail(String sizeName, String sizeSize, String sizePrice, String sizeQuantity, String message) {
	AdminEditProductPage adminEdit= new AdminEditProductPage(driver);
    adminEdit.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D01");
    clickOnElemnet(adminEdit.btnEdit);
    clickOnElemnet(adminEdit.iconSizeEdit);
    WebDriverWait w = new WebDriverWait(driver, 5);
    w.until(ExpectedConditions.presenceOfElementLocated(adminEdit.popUp));
	adminEdit.sendKeysSize(sizeName, sizeSize, sizePrice, sizeQuantity);
	clickOnElemnet(adminEdit.btnSaveSize);
if(sizeName.equals("")) {
	assertEquals(getHtml5ValidationMessage(adminEdit.txtSizeName), message);
}
else if(sizePrice.equals("")) {
	assertEquals(getHtml5ValidationMessage(adminEdit.txtSizePrice), message);
}
else if(sizeQuantity.equals("")) {
	assertEquals(getHtml5ValidationMessage(adminEdit.txtSizeQuantity), message);
}
}

//Add Product
@DataProvider(name="Add product size information successfully data")
public String [][] addSizeSuccessfully() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFile.getAbsolutePath(), "AddSizeSuccessfully");
	return data;

}
@Test(description = "Validate  add size successfully", dataProvider = "Add product size information successfully data", groups="main")
public void addSizeSuccessfully(String sizeName, String sizeSize, String sizePrice, String sizeQuantity) {
	AdminEditProductPage adminEdit= new AdminEditProductPage(driver);
    adminEdit.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D01");
    clickOnElemnet(adminEdit.btnEdit);
String[] data= {sizeName, sizeSize, sizePrice, sizeQuantity};
	clickOnElemnet(adminEdit.btnAddSize);
	WebDriverWait w = new WebDriverWait(driver, 5);
	w.until(ExpectedConditions.presenceOfElementLocated(adminEdit.popUp));
	adminEdit.sendKeysSize(sizeName, sizeSize, sizePrice, sizeQuantity);
	clickOnElemnet(adminEdit.btnSaveSize);
	acceptAlertMessage();
	String[] afterEdit= {driver.findElement(adminEdit.lblSizeID).getText(), driver.findElement(adminEdit.lblSizeSize).getText(), driver.findElement(adminEdit.lblSizePrice).getText(), driver.findElement(adminEdit.lblSizeSize).getText()};
assertEquals(data, afterEdit);
}

//Cần sửa lại sau khi fix bug
@DataProvider(name="Add product size information fail when provide invalid data", indices = {3,4,5,6,7,8})
public String [][] addSizeFail() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFile.getAbsolutePath(), "AddSizeFail");
	return data;

}
@Test(description = "Validate  add size fail when provdie invalid information", dataProvider = "Add product size information fail when provide invalid data", groups="validation")
public void addSizeFail(String sizeName, String sizeSize, String sizePrice, String sizeQuantity, String errorMessage) {
	AdminEditProductPage adminEdit= new AdminEditProductPage(driver);
    adminEdit.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D01");
    clickOnElemnet(adminEdit.btnEdit);
    String[] beforeEdit= {driver.findElement(adminEdit.lblSizeID).getText(), driver.findElement(adminEdit.lblSizeSize).getText(), driver.findElement(adminEdit.lblSizePrice).getText(), driver.findElement(adminEdit.lblSizeSize).getText()};
	clickOnElemnet(adminEdit.btnAddSize);
	WebDriverWait w = new WebDriverWait(driver, 5);
	w.until(ExpectedConditions.presenceOfElementLocated(adminEdit.popUp));
	adminEdit.sendKeysSize(sizeName, sizeSize, sizePrice, sizeQuantity);
	clickOnElemnet(adminEdit.btnSaveSize);
	acceptAlertMessage();
	String[] afterEdit= {driver.findElement(adminEdit.lblSizeID).getText(), driver.findElement(adminEdit.lblSizeSize).getText(), driver.findElement(adminEdit.lblSizePrice).getText(), driver.findElement(adminEdit.lblSizeSize).getText()};
	assertEquals(beforeEdit, afterEdit);
}

@DataProvider(name="Add product size information fail when leave fields blank data", indices = {0,1,2})
public String [][] addSizeFailBlankFields() throws IOException {
	String[][] data= excelUtils.getDataFromExcel(dataFile.getAbsolutePath(), "AddSizeFail");
	return data;

}
@Test(description = "Validate  add size fail when leave fields blank", dataProvider = "Add product size information fail when leave fields blank data", groups="validation")
public void addSizeFailWhenFieldsBlank(String sizeName, String sizeSize, String sizePrice, String sizeQuantity, String errorMessage) {
	AdminEditProductPage adminEdit= new AdminEditProductPage(driver);
    adminEdit.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D01");
    clickOnElemnet(adminEdit.btnEdit);
	clickOnElemnet(adminEdit.btnAddSize);
	WebDriverWait w = new WebDriverWait(driver, 5);
	w.until(ExpectedConditions.presenceOfElementLocated(adminEdit.popUp));
	adminEdit.sendKeysSize(sizeName, sizeSize, sizePrice, sizeQuantity);
	clickOnElemnet(adminEdit.btnSaveSize);
	if(sizeName.equals("")) {
		assertEquals(getHtml5ValidationMessage(adminEdit.txtSizeName), errorMessage);
	}
	else if(sizePrice.equals("")) {
		assertEquals(getHtml5ValidationMessage(adminEdit.txtSizePrice), errorMessage);
	}
	else if(sizeQuantity.equals("")) {
		assertEquals(getHtml5ValidationMessage(adminEdit.txtSizeQuantity), errorMessage);
	}
}

@Test(description = "Validate editing product size fail when using duplicate product ID")
public void editProductSizeWithDuplicateID() {
	AdminEditProductPage editPage= new AdminEditProductPage(driver);
	editPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D02");
	clickOnElemnet(editPage.btnEdit);
	String beforeEdit=driver.findElement(editPage.lblSizeID).getText();
	clickOnElemnet(editPage.iconSizeEdit);
	WebDriverWait w = new WebDriverWait(driver, 5);
	w.until(ExpectedConditions.presenceOfElementLocated(editPage.popUp));
	boolean check = checkElementVisibility(editPage.popUp);
	assertEquals(check, true);
	boolean check91 = checkElementVisibility(editPage.txtSizeName);
	assertEquals(check91, true);
}	

@Test(description = "Validate adding product size fail when using duplicate product ID")
public void addProductSizeWithDuplicateID() {
	AdminEditProductPage editPage= new AdminEditProductPage(driver);
	editPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D02");
	clickOnElemnet(editPage.btnEdit);
	String beforeEdit=driver.findElement(editPage.lblSizeID).getText();
	clickOnElemnet(editPage.btnAddSize);
	WebDriverWait w = new WebDriverWait(driver, 5);
	w.until(ExpectedConditions.presenceOfElementLocated(editPage.popUp));
	editPage.sendKeysSize("TB/D02", "20cm", "123456", "02");
	clickOnElemnet(editPage.btnSaveSize);
	String alertMessage= getAlertMessage();
	acceptAlertMessage();
String afterEdit= driver.findElement(editPage.lblSizeID).getText();
assertEquals(alertMessage, "Mã sản phẩm đã tồn tại!!");
assertEquals(beforeEdit, afterEdit);
}

@Test(description = "Validate closing size popup")
public void closingSizePopUp() {
	AdminEditProductPage editPage= new AdminEditProductPage(driver);
	editPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D02");
	clickOnElemnet(editPage.btnEdit);
	clickOnElemnet(editPage.btnAddSize);
	WebDriverWait w = new WebDriverWait(driver, 5);
	w.until(ExpectedConditions.presenceOfElementLocated(editPage.popUp));
//	editPage.sendKeysSize("TB/T01", "20cm", "123456", "02");
//	clickOnElemnet(editPage.iconExit);
//	boolean check= checkElementVisibility(editPage.popUp);
//	assertEquals(check, false);
}

@Test(description = "Validate delete size successfully")
public void deleteSizeSuccessfully() {
	AdminEditProductPage editPage= new AdminEditProductPage(driver);
	editPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D02");
	clickOnElemnet(editPage.btnEdit);
	String beforeEdit=driver.findElement(editPage.lblSizeID).getText();
	clickOnElemnet(editPage.iconSizeDelete);
	acceptAlertMessage();
	String afterEdit= driver.findElement(editPage.lblSizeID).getText();
	assertNotEquals(beforeEdit, afterEdit);

}

@Test(description = "Validate delete size fail")
public void deleteSizeFail() {
	AdminEditProductPage editPage= new AdminEditProductPage(driver);
	editPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D02");
	clickOnElemnet(editPage.btnEdit);
	String beforeEdit=driver.findElement(editPage.lblSizeID).getText();
	clickOnElemnet(editPage.iconSizeDelete);
	rejectAlertMessage();
	String afterEdit= driver.findElement(editPage.lblSizeID).getText();
	assertEquals(beforeEdit, afterEdit);
}

@Test(description = "Validate edit product fail when clicking on button 'Hủy' at edit product page")
public void editProductWhenClickingOnHuy() {
	AdminEditProductPage editPage= new AdminEditProductPage(driver);
	editPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D02");
	String beforeEdit=driver.findElement(editPage.lblProductName).getText();
	clickOnElemnet(editPage.btnEdit);
	fillInPlaceholder(editPage.txtName, "Hello");
	clickOnElemnet(editPage.btnCancel);
	String afterEdit= driver.findElement(editPage.lblProductName).getText();
	assertEquals(beforeEdit, afterEdit);

}
}
