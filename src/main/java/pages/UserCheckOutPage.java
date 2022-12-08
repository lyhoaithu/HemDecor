package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.ExcelUtils;

public class UserCheckOutPage extends Page {

	public UserCheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public ExcelUtils excelUtils = new ExcelUtils();
	public HashMap<String, By> locatorMap = readLocatorFromExcle(
			locatorPath,
			"UserCreateOrderPage");
	public By btnThanhToanAtCart = locatorMap.get("btnThanhToanAtCart");
	public By txtName = locatorMap.get("txtName");
	public By txtPhonenumber = locatorMap.get("txtPhonenumber");
	public By txtAddress = locatorMap.get("txtAddress");
	public By rbCOD = locatorMap.get("rbCOD");
	public By rbBanking = locatorMap.get("rbBanking");
	public By txtNote = locatorMap.get("txtNote");
	public By btnThanhToanAtCheckOut = locatorMap.get("btnThanhToanAtCheckOut");
	public By btnCancel = locatorMap.get("btnCancel");
	public By lblSuccessfullMessage = locatorMap.get("lblSuccessfullMessage");
	public By lblErrorMessage = locatorMap.get("lblErrorMessage");
	public By iconCart= By.xpath("//a[contains(@href,'view_cart')]");
	public By chbCheckAll= By.id("checkAll");

	public void sendKeys(String name, String phone, String address, String note) {
		fillInPlaceholder(txtName, name);
		fillInPlaceholder(txtPhonenumber, phone);
		fillInPlaceholder(txtAddress, address);
		fillInPlaceholder(txtNote, note);
	}
	
	public void addProductPreCon() {
		UserCartPage cartPage= new UserCartPage(driver);
		cartPage.navigateToPage("http://localhost:8081/HemDecor/user_products/view_product.php?cid=TB/D01");
		clickOnElemnet(cartPage.btnSize);
		clickOnElemnet(cartPage.btnAddToCart);
	}
}
