package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.ExcelUtils;

public class UserCancelOrderPage extends Page {

	public UserCancelOrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public ExcelUtils excelUtils = new ExcelUtils();
	public HashMap<String, By> locatorMap = readLocatorFromExcle(
			locatorPath,
			"UserCancelOrderPage");
	
	public By lblOrderStatus = locatorMap.get("lblOrderStatus");
	public By btnCancel = locatorMap.get("btnCancel");
	public By btnCancelPopUp = locatorMap.get("btnCancelPopUp");
	public By btnReturn = locatorMap.get("btnReturn");
	public By lblOrderStatus2= locatorMap.get("lblOrderStatus2");
	public By btnCancelDisabled= locatorMap.get("btnCancelDisabled");
	public By lblOrderStatusAtOrderDetails= locatorMap.get("lblOrderStatusAtOrderDetails");

}
