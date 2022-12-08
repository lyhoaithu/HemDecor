package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import common.ExcelUtils;

public class AdminOrderPage extends Page {

	public AdminOrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	public ExcelUtils excelUtils= new ExcelUtils();
	public HashMap<String, By> locatorMap= readLocatorFromExcle(locatorPath, "AdminOrderPage");
	public By lblOrderID= locatorMap.get("lblOrderID");
	public By txtSearch= locatorMap.get("txtSearch");
	public By iconSearch = locatorMap.get("iconSearch");
	public By ddbOrderStatus= locatorMap.get("ddbOrderStatus");
	public By chbCheckAll= locatorMap.get("chbCheckAll");
	public By chbOrder1= locatorMap.get("chbOrder1");
	public By chbOrder2=locatorMap.get("chbOrder2");
	public By btnChangeOrderStatus= locatorMap.get("btnChangeOrderStatus");
	public By iconViewOrderDetails= locatorMap.get("iconViewOrderDetails");
	public By iconChangeOrderStatus= locatorMap.get("iconChangeOrderStatus");
	public By iconCancelOrder= locatorMap.get("iconCancelOrder");
	public By btnChangeOrderStatusAtAll= locatorMap.get("btnChangeOrderStatusAtAll");
	public By lblMessage= locatorMap.get("lblMessage");
}
