package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.ExcelUtils;

public class AdminEditProductPage extends Page {

	public AdminEditProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public ExcelUtils excelUtils = new ExcelUtils();
public HashMap<String, By> locatorMap =readLocatorFromExcle(locatorPath, "AdminEditProductPage");
public By iconEdit= locatorMap.get("iconEdit");
public By iconEye= locatorMap.get("iconEye");
public By btnEdit= locatorMap.get("btnEdit");
public By txtName= locatorMap.get("txtName");
public By txtCategoryID= locatorMap.get("txtCategoryID");
public By txtMaterial= locatorMap.get("txtMaterial");
public By txtNote= locatorMap.get("txtNote");
public By btnAddSize= locatorMap.get("btnAddSize");
public By lblSizeID= locatorMap.get("lblSizeID");
public By lblSizeSize= locatorMap.get("lblSizeSize");
public By lblSizePrice= locatorMap.get("lblSizePrice");
public By lblSizeQuantity= locatorMap.get("lblSizeQuantity");
public By iconSizeEdit= locatorMap.get("iconSizeEdit");
public By iconSizeDelete= locatorMap.get("iconSizeDelete");
public By btnAddPic= locatorMap.get("btnAddPic");
public By btnSave= locatorMap.get("btnSave");
public By btnCancel= locatorMap.get("btnCancel");
public By txtSizeName= By.xpath("//input[@name='proid']");
public By txtSizeSize= By.xpath("//input[@name='size']");
public By txtSizePrice=By.xpath("//input[@name='price']");
public By txtSizeQuantity=By.xpath("//input[@name='quantity']");
public By btnSaveSize=By.xpath("//button[@class='button8']");
public By iconExit=By.xpath("//span[@class='close']");
public By lblErrorMessage= By.xpath("//p[@class='error']");
public By lblProductName=By.xpath("//div[@class='product-info']//p[1]");
public By lblCategoryID= By.xpath("//div[@class='product-info']//p[2]");
public By lblMaterial= By.xpath("//div[@class='product-info']//p[3]");
public By lblNote= By.xpath("//div[@class='product-detail']//p");
public By popUp=By.xpath("//div[@class='modal-content']");

public void sendKeys(String name, String categoryID, String material, String note ) {
	driver.findElement(txtName).clear();
	driver.findElement(txtCategoryID).clear();
	driver.findElement(txtMaterial).clear();
	driver.findElement(txtNote).clear();
	fillInPlaceholder(txtName, name);
	fillInPlaceholder(txtCategoryID, categoryID);
	fillInPlaceholder(txtMaterial, material);
	fillInPlaceholder(txtNote, note);
}

public void sendKeysSize(String sizeName, String sizeSize, String sizePrice, String sizeQuantity) {
	driver.findElement(txtSizeName).clear();
	driver.findElement(txtSizeSize).clear();
	driver.findElement(txtSizePrice).clear();
	driver.findElement(txtSizeQuantity).clear();
	fillInPlaceholder(txtSizeName, sizeName);
	fillInPlaceholder(txtSizeSize, sizeSize);
	fillInPlaceholder(txtSizePrice, sizePrice);
	fillInPlaceholder(txtSizeQuantity, sizeQuantity);

}
}
