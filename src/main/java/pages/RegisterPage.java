package pages;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import common.ExcelUtils;

public class RegisterPage extends Page{
public static HashMap<String,By> locatorMap=readLocatorFromExcle(locatorPath, "Register");

public By txtName= locatorMap.get("txtName");
public By txtPassword= locatorMap.get("txtPassword");
public By txtEmail= locatorMap.get("txtEmail");
public By txtTelephoneNumber= locatorMap.get("txtTelephoneNumber");
public By btnRegister= locatorMap.get("btnRegister");
public By chbShowPassword=locatorMap.get("chbShowPassword");
public By lblUnsuccessfulMessage= locatorMap.get("lblUnsucessfulMessage");
public By lblSuccessfulMessage= locatorMap.get("lblSuccessfulMessage");
public By btnQuayLai=locatorMap.get("btnQuayLai");
public By btnDangKy=locatorMap.get("btnDangKy");

	public RegisterPage(WebDriver driver) {
		super(driver);
		
	}
public String getCurrentURL() {
	String currentURL= driver.getCurrentUrl();
	return currentURL;
}


public void sendKeys(String username, String password, String email, String phoneNumber) {
 fillInPlaceholder(txtName, username);
 fillInPlaceholder(txtPassword, password);
 fillInPlaceholder(txtEmail, email);
 fillInPlaceholder(txtTelephoneNumber, phoneNumber);
}

public String checkTogglePassword() {
	String type=driver.findElement(chbShowPassword).getAttribute("type");
	return type;
}


}
