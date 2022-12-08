package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.DriverAction;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.AdminHomePage;
import pages.AdminOrderPage;
import pages.HomePage;
import pages.LogInPage;

public class AdminOrderPageTest extends TestCase{
	
	@BeforeMethod (alwaysRun = true)

	public void preCondition() {
		LogInPage logIn= new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("HemDecor", "HemDecor*1234");
		clickOnElemnet(logIn.btnLogIn);
		AdminHomePage adminHomePage= new AdminHomePage(driver);
		clickOnElemnet(adminHomePage.btnOrder);
	}
	public ExcelUtils excelUtils= new ExcelUtils();
	public static File data=new File("TestData/AutomationTestData.xlsx");
	
@DataProvider(name="Verify Order ID")
public String[][] allOrderIDData() throws IOException {
	String[][] allOrderIDData= excelUtils.getDataFromExcel(data.getAbsolutePath(), "AdminOrderPage");
return allOrderIDData;
}

@Test(description = "Verify Order ID at All Status", dataProvider ="Verify Order ID", groups="main")
public void verifyOrderIDAtAllStatus(String query1, String query2, String query3, String query4, String query5, String query6, String query7) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
	AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 0);
	String expectedResult= getValueFromDatabase(query1, 1);
	String actualResult=null;
	if(expectedResult.equals("[]")) {
		 actualResult= driver.findElement(adminOrderPage.lblMessage).getText();
	}
	else {
		actualResult= Arrays.deepToString(getDataFromTableColumn(adminOrderPage.lblOrderID));
	assertEquals(actualResult, expectedResult);
	boolean btnChangeOrderStatusIsDisabled=checkElementDisable(adminOrderPage.btnChangeOrderStatusAtAll);
	assertEquals(btnChangeOrderStatusIsDisabled, true);
	boolean iconChangeOrderStatusIsDisabled= checkElementDisable(adminOrderPage.iconChangeOrderStatus);
	assertEquals(iconChangeOrderStatusIsDisabled, false);
	List<WebElement> iconCancel = driver.findElements(adminOrderPage.iconCancelOrder);
	boolean check= iconCancel.isEmpty();
	assertEquals(check, true);
	boolean iconViewDetailsIsEnabled= checkElementDisable(adminOrderPage.iconViewOrderDetails);
	assertEquals(iconViewDetailsIsEnabled, true);}
}

@Test(description = "Verify Order ID at Chờ Xác Nhận - COD Status", dataProvider ="Verify Order ID", groups="main")
public void verifyOrderIDAtChoXacNhanCODStatus(String query1, String query2, String query3, String query4, String query5, String query6, String query7) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
	AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 1);
	String expectedResult= getValueFromDatabase(query2, 1);
	String actualResult=null;
	if(expectedResult.equals("[]")) {
		 actualResult= driver.findElement(adminOrderPage.lblMessage).getText();
		 assertEquals(actualResult, "Không Có Kết Quả Trùng Khớp");
	}
	else {
		actualResult= Arrays.deepToString(getDataFromTableColumn(adminOrderPage.lblOrderID));
	assertEquals(actualResult, expectedResult);
		boolean btnChangeOrderStatusIsDisabled=checkElementDisable(adminOrderPage.btnChangeOrderStatus);
	assertEquals(btnChangeOrderStatusIsDisabled, true);
	boolean iconChangeOrderStatusIsDisabled= checkElementDisable(adminOrderPage.iconChangeOrderStatus);
	assertEquals(iconChangeOrderStatusIsDisabled, true);
	List<WebElement> iconCancel = driver.findElements(adminOrderPage.iconCancelOrder);
	boolean check= iconCancel.isEmpty();
	assertEquals(check, false);
	boolean iconViewDetailsIsEnabled= checkElementDisable(adminOrderPage.iconViewOrderDetails);
	assertEquals(iconViewDetailsIsEnabled, true);}
}

@Test(description = "Verify Order ID at Chờ Xác Nhận - Banking Status", dataProvider ="Verify Order ID", groups="main")
public void verifyOrderIDAtChoXacNhanBankingStatus(String query1, String query2, String query3, String query4, String query5, String query6, String query7) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
	AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 2);
	String expectedResult= getValueFromDatabase(query3, 1);
	System.out.println(expectedResult);
	String actualResult=null;
	if(expectedResult.equals("[]")) {
		 actualResult= driver.findElement(adminOrderPage.lblMessage).getText();
		 assertEquals(actualResult, "Không Có Kết Quả Trùng Khớp");
	}
	else {
		actualResult= Arrays.deepToString(getDataFromTableColumn(adminOrderPage.lblOrderID));
	assertEquals(actualResult, expectedResult);
	boolean btnChangeOrderStatusIsDisabled=checkElementDisable(adminOrderPage.btnChangeOrderStatus);
	assertEquals(btnChangeOrderStatusIsDisabled, true);
	boolean iconChangeOrderStatusIsDisabled= checkElementDisable(adminOrderPage.iconChangeOrderStatus);
	assertEquals(iconChangeOrderStatusIsDisabled, true);
	List<WebElement> iconCancel = driver.findElements(adminOrderPage.iconCancelOrder);
	boolean check= iconCancel.isEmpty();
	assertEquals(check, false);
	boolean iconViewDetailsIsEnabled= checkElementDisable(adminOrderPage.iconViewOrderDetails);
	assertEquals(iconViewDetailsIsEnabled, true);}
}

@Test(description = "Verify Order ID at Đang Chuẩn Bị Hàng Status", dataProvider ="Verify Order ID", groups="main")
public void verifyOrderIDAtDangChuanBiHangStatus(String query1, String query2, String query3, String query4, String query5, String query6, String query7) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
	AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 3);
	String expectedResult= getValueFromDatabase(query4, 1);
	String actualResult=null;
	if(expectedResult.equals("[]")) {
		 actualResult= driver.findElement(adminOrderPage.lblMessage).getText();
		 assertEquals(actualResult, "Không Có Kết Quả Trùng Khớp");
	}
	else {
		actualResult= Arrays.deepToString(getDataFromTableColumn(adminOrderPage.lblOrderID));
	assertEquals(actualResult, expectedResult);
	boolean btnChangeOrderStatusIsDisabled=checkElementDisable(adminOrderPage.btnChangeOrderStatus);
	assertEquals(btnChangeOrderStatusIsDisabled, true);
	boolean iconChangeOrderStatusIsDisabled= checkElementDisable(adminOrderPage.iconChangeOrderStatus);
	assertEquals(iconChangeOrderStatusIsDisabled, true);
	List<WebElement> iconCancel = driver.findElements(adminOrderPage.iconCancelOrder);
	boolean check= iconCancel.isEmpty();
	assertEquals(check, true);
	boolean iconViewDetailsIsEnabled= checkElementDisable(adminOrderPage.iconViewOrderDetails);
	assertEquals(iconViewDetailsIsEnabled, true);}
}
@Test(description = "Verify Order ID at Đang Giao Hàng Status", dataProvider ="Verify Order ID", groups="main")
public void verifyOrderIDAtDangGiaoHangStatus(String query1, String query2, String query3, String query4, String query5, String query6, String query7) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
	AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 4);
	String expectedResult= getValueFromDatabase(query5, 1).replace("[", "").replace("]", "");
	String actualResult=null;
	if(expectedResult.equals("[]")) {
		 actualResult= driver.findElement(adminOrderPage.lblMessage).getText();
		 assertEquals(actualResult, "Không Có Kết Quả Trùng Khớp");
	}
	else {
		actualResult= Arrays.deepToString(getDataFromTableColumn(adminOrderPage.lblOrderID)).replace("[", "").replace("]", "");
	assertEquals(actualResult, expectedResult);
	boolean btnChangeOrderStatusIsDisabled=checkElementDisable(adminOrderPage.btnChangeOrderStatus);
	assertEquals(btnChangeOrderStatusIsDisabled, true);
	boolean iconChangeOrderStatusIsDisabled= checkElementDisable(adminOrderPage.iconChangeOrderStatus);
	assertEquals(iconChangeOrderStatusIsDisabled, true);
	List<WebElement> iconCancel = driver.findElements(adminOrderPage.iconCancelOrder);
	boolean check= iconCancel.isEmpty();
	assertEquals(check, true);
	boolean iconViewDetailsIsEnabled= checkElementDisable(adminOrderPage.iconViewOrderDetails);
	assertEquals(iconViewDetailsIsEnabled, true);}
}
@Test(description = "Verify Order ID at Đã Hoàn Thành Status", dataProvider ="Verify Order ID", groups="main")
public void verifyOrderIDAtDaHoanThanhStatus(String query1, String query2, String query3, String query4, String query5, String query6, String query7) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
	AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 5);
	String expectedResult= getValueFromDatabase(query6, 1);
	String actualResult=null;
	if(expectedResult.equals("[]")) {
		 actualResult= driver.findElement(adminOrderPage.lblMessage).getText();
		 assertEquals(actualResult, "Không Có Kết Quả Trùng Khớp");
	}
	else {
		actualResult= Arrays.deepToString(getDataFromTableColumn(adminOrderPage.lblOrderID));
	assertEquals(actualResult, expectedResult);
	boolean btnChangeOrderStatusIsDisabled=checkElementDisable(adminOrderPage.btnChangeOrderStatusAtAll);
	assertEquals(btnChangeOrderStatusIsDisabled, true);
	boolean iconChangeOrderStatusIsDisabled= checkElementDisable(adminOrderPage.iconChangeOrderStatus);
	assertEquals(iconChangeOrderStatusIsDisabled, false);
	List<WebElement> iconCancel = driver.findElements(adminOrderPage.iconCancelOrder);
	boolean check= iconCancel.isEmpty();
	assertEquals(check, true);
	boolean iconViewDetailsIsEnabled= checkElementDisable(adminOrderPage.iconViewOrderDetails);
	assertEquals(iconViewDetailsIsEnabled, true);}
}
@Test(description = "Verify Order ID at Đã Hủy Status", dataProvider ="Verify Order ID", groups="main")
public void verifyOrderIDAtDaHuyStatus(String query1, String query2, String query3, String query4, String query5, String query6, String query7) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
	AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 6);
	String expectedResult= getValueFromDatabase(query7, 1);
	String actualResult=null;
	if(expectedResult.equals("[]")) {
		 actualResult= driver.findElement(adminOrderPage.lblMessage).getText();
		 assertEquals(actualResult, "Không Có Kết Quả Trùng Khớp");
	}
	else {
		actualResult= Arrays.deepToString(getDataFromTableColumn(adminOrderPage.lblOrderID));
	assertEquals(actualResult, expectedResult);
	boolean btnChangeOrderStatusIsDisabled=checkElementDisable(adminOrderPage.btnChangeOrderStatusAtAll);
	assertEquals(btnChangeOrderStatusIsDisabled, true);
	boolean iconChangeOrderStatusIsDisabled= checkElementDisable(adminOrderPage.iconChangeOrderStatus);
	assertEquals(iconChangeOrderStatusIsDisabled, false);
	List<WebElement> iconCancel = driver.findElements(adminOrderPage.iconCancelOrder);
	boolean check= iconCancel.isEmpty();
	assertEquals(check, true);
	boolean iconViewDetailsIsEnabled= checkElementDisable(adminOrderPage.iconViewOrderDetails);
	assertEquals(iconViewDetailsIsEnabled, true);}
}

@Test(description = "Validate navigating to order details page", groups="main")
public void navigateToOrderDetailsPage() {
	AdminOrderPage adminOrderPage= new AdminOrderPage(driver);
	String orderID= driver.findElement(adminOrderPage.lblOrderID).getText();
	clickOnElemnet(adminOrderPage.iconViewOrderDetails);
	String actualURL= driver.getCurrentUrl();
	assertTrue(actualURL.contains(orderID));
}

@DataProvider(name="Search Order Successfully Data")
public String[][] searchOrderData() throws IOException {
	String[][] searchOrderData= excelUtils.getDataFromExcel(data.getAbsolutePath(), "AdminSearchOrderSuccessfully");
return searchOrderData;
}

@Test(description = "Validate Search Order Successfully", dataProvider = "Search Order Successfully Data", groups="main")
public void searchOrderSuccessfully(String keyword, String query) throws ClassNotFoundException, SQLException {
	AdminOrderPage adminOrderPage= new AdminOrderPage(driver);
	fillInPlaceholder(adminOrderPage.txtSearch, keyword);
	clickOnElemnet(adminOrderPage.iconSearch);
	String actualResult= Arrays.deepToString(getDataFromTableColumn(adminOrderPage.lblOrderID)).replace("[", "").replace("]", "");
	String expectedResult= getValueFromDatabase(query, 1).replace("[", "").replace("]", "");
	assertEquals(actualResult, expectedResult);
}

@DataProvider(name="Search Order Fail Data")
public String[][] searchOrderFailData() throws IOException {
	String[][] searchOrderData= excelUtils.getDataFromExcel(data.getAbsolutePath(), "AdminSearchOrderFail");
return searchOrderData;
}

@Test(description = "Validate Search Order Successfully", dataProvider = "Search Order Fail Data", groups="validation")
public void searchOrderFail(String keyword, String query) throws ClassNotFoundException, SQLException {
	AdminOrderPage adminOrderPage= new AdminOrderPage(driver);
	fillInPlaceholder(adminOrderPage.txtSearch, keyword);
	clickOnElemnet(adminOrderPage.iconSearch);
	String actualMessage= null;
	if(!keyword.equals("")) {
	 actualMessage= driver.findElement(adminOrderPage.lblMessage).getText();
	assertTrue(actualMessage.equals("Không Có Kết Quả Trùng Khớp"));
}
	else if (keyword.equals("")) {
	 actualMessage= getHtml5ValidationMessage(adminOrderPage.txtSearch);
	 assertEquals(actualMessage, query);
	}
}

@Test(description = "Validate cancel order successfully", groups="main")
public void cancelOrderSuccessfully() throws UnsupportedEncodingException {
	AdminOrderPage adminOrderPage= new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 1);
	String orderIDBeforeDelete= driver.findElement(adminOrderPage.lblOrderID).getText();
	clickOnElemnet(adminOrderPage.iconCancelOrder);
	acceptAlertMessage();
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 6);
	String orderIDAfterDelete = driver.findElement(adminOrderPage.lblOrderID).getText();
	assertEquals(orderIDBeforeDelete, orderIDAfterDelete);
}

@Test(description = "Validate cancel order fail", groups="main")
public void cancelOrderFail() throws UnsupportedEncodingException {
	AdminOrderPage adminOrderPage= new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 1);
	String orderIDBeforeDelete= driver.findElement(adminOrderPage.lblOrderID).getText();
	clickOnElemnet(adminOrderPage.iconCancelOrder);
    rejectAlertMessage();
	String orderIDAfterDelete = driver.findElement(adminOrderPage.lblOrderID).getText();
	assertEquals(orderIDBeforeDelete, orderIDAfterDelete);
}

@Test(description = "Validate change single order status successfully", groups="main")
public void changeSingleOrderStatusSuccessfully() throws UnsupportedEncodingException {
	AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 1);
	String orderIDBeforeChange= driver.findElement(adminOrderPage.lblOrderID).getText();
	clickOnElemnet(adminOrderPage.iconChangeOrderStatus);
	
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 3);
	String orderIDAfterChange1= driver.findElement(adminOrderPage.lblOrderID).getText();
	assertEquals(orderIDBeforeChange, orderIDAfterChange1);
	clickOnElemnet(adminOrderPage.iconChangeOrderStatus);
	
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 4);
	String orderIDAfterChange2= driver.findElement(adminOrderPage.lblOrderID).getText();
	assertEquals(orderIDAfterChange1, orderIDAfterChange2);
	clickOnElemnet(adminOrderPage.iconChangeOrderStatus);
	
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 5);
	String orderIDAfterChange3= driver.findElement(adminOrderPage.lblOrderID).getText();
	assertEquals(orderIDAfterChange2, orderIDAfterChange3);
	

	LogInPage loginPage= new LogInPage(driver);
	loginPage.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
	loginPage.sendKeys("0962370612","Hoaithu*2812");
	clickOnElemnet(loginPage.btnLogIn);
	hoverMouse(By.xpath("/html/body/header/div/div/a"));
	clickOnElemnet(By.linkText("Đơn Của Tôi"));
	String userPageResult=driver.findElement(By.xpath("//tbody//tr//th[3]//a[contains(@href,'"+orderIDBeforeChange+"')]")).getText();
	String paymentStatus= driver.findElement(By.xpath("//tbody//tr//th[2]//a[contains(@href,'"+orderIDBeforeChange+"')]")).getText();
	assertEquals(userPageResult,"Đã Hoàn Thành");
	assertEquals(paymentStatus, "Đã Hoàn Thành");
}

@Test(description = "Validate changing order status by clicking on Chuyển Trạng Thái button", groups="main")
public void changeOrderStatusByClickingOnButton() throws UnsupportedEncodingException {
	AdminOrderPage adminOrderPage= new AdminOrderPage(driver);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 2);
	String orderIDBefore= driver.findElement(adminOrderPage.lblOrderID).getText();
	clickOnElemnet(adminOrderPage.chbOrder1);
	clickOnElemnet(adminOrderPage.btnChangeOrderStatus);
	selectDropdownBox(adminOrderPage.ddbOrderStatus, 3);
	String orderIDAfter= driver.findElement(adminOrderPage.lblOrderID).getText();
	LogInPage loginPage= new LogInPage(driver);
	loginPage.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
	loginPage.sendKeys("0963566858","Ngocyen*2102");
	clickOnElemnet(loginPage.btnLogIn);
	hoverMouse(By.xpath("/html/body/header/div/div/a"));
	clickOnElemnet(By.linkText("Đơn Của Tôi"));
	String userPageResult=driver.findElement(By.xpath("//tbody//tr//th[3]//a[contains(@href,'"+orderIDBefore+"')]")).getText();
	assertEquals(userPageResult,"Đang Chuẩn Bị Hàng");
}

@Test(description = "Validate changing order status when order status is 'Đã Hoàn Thành'", groups="validation")
public void changeOrderStatusWhenStatusIsCompleted() throws UnsupportedEncodingException {
	AdminOrderPage adminOrderPage= new AdminOrderPage(driver);
    selectDropdownBox(adminOrderPage.ddbOrderStatus, 5);
    boolean elementIsDisabled=checkElementDisable(adminOrderPage.chbOrder1);
    assertEquals(elementIsDisabled, false);
    selectDropdownBox(adminOrderPage.ddbOrderStatus, 6);
    boolean elementIsDisabled02=checkElementDisable(adminOrderPage.chbOrder1);
    assertEquals(elementIsDisabled02, false);

}
}
