package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.ExcelUtils;

public class UserCartPage  extends Page{

	public UserCartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
 public ExcelUtils excelUtils = new ExcelUtils();
 public HashMap<String, By> locatorMap = readLocatorFromExcle(locatorPath, "UserCartPage");
 public By iconDelete= locatorMap.get("iconDelete");
 public By btnThanhToan= locatorMap.get("btnThanhToan");
 public By lblProductNameAtCart= locatorMap.get("lblProductNameAtCart");
 public By lblUnitPrice= locatorMap.get("lblUnitPrice");
 public By lblQuantity= locatorMap.get("lblQuantity");
 public By lblTotalProductPrice= locatorMap.get("lblTotalProductPrice");
 public By btnAddToCart= locatorMap.get("btnAddToCart");
 public By btnAdd= locatorMap.get("btnAdd");
 public By btnSubtract= locatorMap.get("btnSubtract");
 public By txtQuantity= locatorMap.get("txtQuantity");
 public By btnSize= locatorMap.get("btnSize");
 public By iconCart= locatorMap.get("iconCart");
 public By lblMessage= locatorMap.get("lblMessage");
 public By lblProductNameAtProductList= By.xpath("//a[contains(@href,'view_product')]");
 public By lblInstockProduct= By.xpath("//p[@id='pnumber']");
 public By lblSuccessfulMessage= By.xpath("//p[@class='success']");
 public By lblErrorMessage= By.xpath("//p[@class='error']");
 public By lblErrorMessage02= locatorMap.get("lblErrorMessage02");
 public By lblCuaHang= By.xpath("//a[contains(@href,'product_page')]");
 
 
}
