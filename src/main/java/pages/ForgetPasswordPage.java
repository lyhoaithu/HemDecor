package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.ExcelUtils;

public class ForgetPasswordPage extends Page {
	public HashMap<String, By> locatorMap = readLocatorFromExcle(
			"D:\\AutomationTest\\02Projects\\HemDecor\\Responsitory\\HemDecor_Responsitory.xlsx", "ForgetPassword");
	public By txtEmail = locatorMap.get("txtEmail");
	public By txtTelephone = locatorMap.get("txtTelephone");
	public By btnRetrievePassword = locatorMap.get("btnRetrievePassword");
	public By btnLogin = locatorMap.get("btnLogin");
	public By btnForgetPassword = locatorMap.get("btnForgetPassword");
	public By lblSuccessfulMessage=locatorMap.get("lblSuccessfulMessage");
	public By lblErrorMessage= locatorMap.get("lblErrorMessage");

	public ForgetPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void sendKeys(String email, String phoneNumber) {
		fillInPlaceholder(By.xpath("//input[@name='email']"), email);
		fillInPlaceholder(By.name("telephone"), phoneNumber);
	}

}
