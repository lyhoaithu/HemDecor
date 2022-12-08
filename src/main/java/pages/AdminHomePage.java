package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminHomePage extends Page {
public HashMap<String, By> locatorMap = readLocatorFromExcle(locatorPath, "AdminHomePage");
public By btnProduct = locatorMap.get("btnProduct");
public By btnOrder = locatorMap.get("btnOrder");
public By btnChangePassword = locatorMap.get("btnChangePassword");
public By btnTotalProduct = locatorMap.get("btnTotalProduct");
public By btnTotalOrder = locatorMap.get("btnTotalOrder");
public By lblTotalProduct = locatorMap.get("lblTotalProduct");
public By lblTotalOrder = locatorMap.get("lblTotalOrder");
public By lblTotalUnconfirmOrder = locatorMap.get("lblTotalUnconfirmOrder");
public By lblTotalPreparingOrder = locatorMap.get("lblTotalPreparingOrder");
public By lblTotalDeliveringOrder = locatorMap.get("lblTotalDeliveringOrder");
public By lblTotalCompletedOrder = locatorMap.get("lblTotalCompletedOrder");
public By btnLogOut=locatorMap.get("btnLogOut");
public By btnHomePage= locatorMap.get("btnHomePage");


	public AdminHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public void preCondition() {
		LogInPage logIn= new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8080/HemDecor/user_account/login.php");
	    logIn.sendKeys("HemDecor", "HemDecor*1234");
		clickOnElemnet(logIn.btnLogIn);
	}
}
