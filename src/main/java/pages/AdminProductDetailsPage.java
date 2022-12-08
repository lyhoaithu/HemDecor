package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminProductDetailsPage extends Page {
	
	public HashMap<String, By> locatorMap= readLocatorFromExcle(locatorPath,"AdminProductDetails");
	public By btnDelete= locatorMap.get("btnDelete");
	public By btnEdit= locatorMap.get("btnEdit");
	public By iconEye= locatorMap.get("iconEye");
	public By iconEdit= locatorMap.get("iconEdit");
	public By lblCategoryID= locatorMap.get("lblCategoryID");
	public By lblCategoryName= locatorMap.get("lblCategoryName");
	public By lblMaterial= locatorMap.get("lblMaterial");
	public By lblDescription= locatorMap.get("lblDescription");
	public By rowDetails= locatorMap.get("rowDetails");
	public By column1= locatorMap.get("column1");
	public By column2= locatorMap.get("column2");
	public By column3= locatorMap.get("column3");
	public By column4= locatorMap.get("column4");
	
	
	public AdminProductDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

}
