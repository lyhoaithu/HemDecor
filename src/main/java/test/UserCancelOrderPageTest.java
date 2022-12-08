package test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.LogInPage;
import pages.UserCancelOrderPage;

public class UserCancelOrderPageTest extends TestCase {
	@BeforeMethod(alwaysRun = true)

	public void preCondition() {
		LogInPage logIn = new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("0962370612", "Hoaithu*2812");
		clickOnElemnet(logIn.btnLogIn);
		hoverMouse(By.xpath("/html/body/header/div/div/a"));
		clickOnElemnet(By.linkText("Đơn Của Tôi"));
	}
	public ExcelUtils excelUtils = new ExcelUtils();
	
	@Test(description = "validate cancel order successfully", groups="main", priority = 2)
	public void cancelOrderSuccessfully() {
		UserCancelOrderPage userCancelOrder =new UserCancelOrderPage(driver);
		clickOnElemnet(userCancelOrder.lblOrderStatus);
		clickOnElemnet(userCancelOrder.btnCancel);
	boolean check= checkElementVisibility(By.xpath("//div[2][@class='modal-dialog']//div"));
	assertEquals(check, true);
		clickOnElemnet(userCancelOrder.btnCancelPopUp);
		WebDriverWait w = new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.presenceOfElementLocated(userCancelOrder.lblOrderStatusAtOrderDetails));
		String orderStatusAfterCancel= driver.findElement(userCancelOrder.lblOrderStatusAtOrderDetails).getText();
		assertEquals(orderStatusAfterCancel, "Đã Hủy");
	}
	
	@Test(description = "validate cancel order fail when clicks on Quay Lại", groups="main", priority = 1)
	public void cancelOrderFailWhenClickOnQuayLai() {
		UserCancelOrderPage userCancelOrder =new UserCancelOrderPage(driver);
		clickOnElemnet(userCancelOrder.lblOrderStatus);
		String urlBefore= driver.getCurrentUrl();
		clickOnElemnet(userCancelOrder.btnCancel);
		boolean check= checkElementVisibility(By.xpath("//div[2][@class='modal-dialog']//div"));
		assertEquals(check, true);
		clickOnElemnet(userCancelOrder.btnReturn);
		String urlAfter= driver.getCurrentUrl();
		assertEquals(urlBefore, urlAfter);
	}
	
	@Test(description = "validate cancel order fail when order is not 'Chờ Xác Nhận'", groups="main", priority = 3)
	public void cancelOrderFailWhenInvalidOrderStatus() {
		UserCancelOrderPage userCancelOrder =new UserCancelOrderPage(driver);
		clickOnElemnet(userCancelOrder.lblOrderStatus2);
		boolean checkDisabled=checkElementVisibility(userCancelOrder.btnCancelDisabled);
		assertEquals(checkDisabled, true);
	}
	
}
