package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddProductPage extends Page {
	public HashMap<String, By> locatorMap = readLocatorFromExcle(locatorPath, "AddProductPage");
	public By btnAddThumbnailImage = locatorMap.get("btnAddThumbnailImage");
	public By btnAddAditionalImage1 = locatorMap.get("btnAddAditionalImage1");
	public By btnAddAditionalImage2 = locatorMap.get("btnAddAditionalImage2");
	public By btnAddAditionalImage3 = locatorMap.get("btnAddAditionalImage3");
	public By btnAddAditionalImage4 = locatorMap.get("btnAddAditionalImage4");
	public By txtProductName = locatorMap.get("txtProductName");
	public By txtCategoryID = locatorMap.get("txtCategoryID");
	public By txtMaterial = locatorMap.get("txtMaterial");
	public By txtDescription = locatorMap.get("txtDescription");
	public By btnContinue = locatorMap.get("btnContinue");
	public By btnCancel = locatorMap.get("btnCancel");
	public By txtProductID = locatorMap.get("txtProductID");
	public By txtSize = locatorMap.get("txtSize");
	public By txtQuantity = locatorMap.get("txtQuantity");
	public By txtPrice = locatorMap.get("txtPrice");
	public By btnAddSize = locatorMap.get("btnAddSize");
	public By btnSave = locatorMap.get("btnSave");
	public By btnCancel02 = locatorMap.get("btnCancel02");
	public By lblErrorMessage=locatorMap.get("lblErrorMessage");
	public By btnAddProduct=locatorMap.get("btnAddProduct");

	public AddProductPage(WebDriver driver) {
		super(driver);
	}
	
	public void sendKeysAtGeneralPage(String picture, String additionalPicture1, String additionalPicture2, String additionalPicture3, String additionalPicture4, String name, String categoryID, String material, String description) {
		driver.findElement(btnAddThumbnailImage).sendKeys(picture);
		if(!additionalPicture1.equals("")) {
			driver.findElement(btnAddAditionalImage1).sendKeys(additionalPicture1);
			driver.findElement(btnAddAditionalImage2).sendKeys(additionalPicture2);
			driver.findElement(btnAddAditionalImage3).sendKeys(additionalPicture3);
			driver.findElement(btnAddAditionalImage4).sendKeys(additionalPicture4);
		}
		fillInPlaceholder(txtProductName, name);
		fillInPlaceholder(txtCategoryID, categoryID);
		fillInPlaceholder(txtMaterial, material);
		fillInPlaceholder(txtDescription, description);
	}
	
	public void sendKeysAtDetailsPage(String productID, String size, String quantity, String price) {
		fillInPlaceholder(txtProductID, productID);
		fillInPlaceholder(txtSize, size);
		fillInPlaceholder(txtQuantity, quantity);
		fillInPlaceholder(txtPrice, price);

	}
	
	public void sendKeysAtGeneralPage02(String picture,  String name, String categoryID, String material, String description) {
		if(!picture.equals("")) {
		driver.findElement(btnAddThumbnailImage).sendKeys(picture);
		}
		fillInPlaceholder(txtProductName, name);
		fillInPlaceholder(txtCategoryID, categoryID);
		fillInPlaceholder(txtMaterial, material);
		fillInPlaceholder(txtDescription, description);
	
}
}


