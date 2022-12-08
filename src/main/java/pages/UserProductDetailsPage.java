package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserProductDetailsPage extends Page {

	public UserProductDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public HashMap<String, By> locatorMap= readLocatorFromExcle(locatorPath, "UserProductDetails");
public By btnProductName= locatorMap.get("btnProductName");
public By imgProduct= locatorMap.get("imgProduct");
public By btnSize= locatorMap.get("btnSize");
public By btnMinus= locatorMap.get("btnMinus");
public By btnPlus= locatorMap.get("btnPlus");
public By lblProductPickerQuantity= locatorMap.get("lblProductPickerQuantity");
public By lblTotalProduct= locatorMap.get("lblTotalProduct");
public By lblProductPrice= locatorMap.get("lblProductPrice");
public By lblDescription= locatorMap.get("lblDescription");
public By lblProductName= locatorMap.get("lblProductName");
public By imgPic1= locatorMap.get("imgThumbnail");
public By imgPic2= locatorMap.get("imgAddPic1");
public By imgPic3= locatorMap.get("imgAddPic2");
public By imgPic4= locatorMap.get("imgAddPic3");
public By imgPic5= locatorMap.get("imgAddPic4");
public By lblSize= locatorMap.get("lblSize");

}
