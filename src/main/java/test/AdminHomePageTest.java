package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.AdminHomePage;

public class AdminHomePageTest extends TestCase {
	public ExcelUtils excelUtils = new ExcelUtils();
	public static File data=new File("TestData/AutomationTestData.xlsx");
	@Test(description = "Validate Navigating To Product Page By Clicking At Menubar")
public void validateNavigatingToProductPageByClickingAtMenuBar() {
	AdminHomePage adminHomePage= new AdminHomePage(driver);
	adminHomePage.preCondition();
	clickOnElemnet(adminHomePage.btnProduct);
	String currentURL=driver.getCurrentUrl();
	assertEquals(currentURL, "http://localhost:8081/HemDecor/admin_manage_product/manage_category.php");

}

	@Test(description = "Validate Navigating To Product Page By Clicking At Total Product Card")
public void validateNavigatingToProductPageByClickingOnTotalProductCard() {
	AdminHomePage adminHomePage= new AdminHomePage(driver);
	adminHomePage.preCondition();
	clickOnElemnet(adminHomePage.btnTotalProduct);
	String currentURL=driver.getCurrentUrl();
	assertEquals(currentURL, "http://localhost:8081/HemDecor/admin_manage_product/manage_category.php");

}

	@Test(description = "Validate Navigating To Changing Password Page")
public void validateNavigatingToChangePasswordPage() {
	AdminHomePage adminHomePage= new AdminHomePage(driver);
	adminHomePage.preCondition();
	clickOnElemnet(adminHomePage.btnChangePassword);
	String currentURL=driver.getCurrentUrl();
	assertEquals(currentURL, "http://localhost:8081/HemDecor/admin_account/change-password.php");

}

	
	@Test(description = "Validate Navigating To Home Page")
public void validateNavigatingToHomePagePage() {
	AdminHomePage adminHomePage= new AdminHomePage(driver);
	adminHomePage.preCondition();
	clickOnElemnet(adminHomePage.btnHomePage);
	String currentURL=driver.getCurrentUrl();
	assertEquals(currentURL, "http://localhost:8081/HemDecor/admin_account/admin_homepage.php");
}

	@Test(description = "Validate Logging Out")
public void validateLoggingOut() {
	AdminHomePage adminHomePage= new AdminHomePage(driver);
	adminHomePage.preCondition();
	clickOnElemnet(adminHomePage.btnLogOut);
	String currentURL=driver.getCurrentUrl();
	assertEquals(currentURL, "http://localhost:8081/HemDecor/anon/homepage.php");
}

	@DataProvider(name = "SQL Query")
	public String[][] SQLQuery() throws IOException {
		String[][] SQLQuery = excelUtils.getDataFromExcel(
				data.getAbsolutePath(), "AdminHomePage");
		return SQLQuery;
	}

	@Test(description = "Validate Displayed Information", dataProvider = "SQL Query")
	public void validateDisplayedInformation(String query1, String query2, String query3, String query4, String query5,
			String query6) throws ClassNotFoundException, SQLException {
		AdminHomePage adminHomePage = new AdminHomePage(driver);
		adminHomePage.preCondition();
		String[] query = { query1, query2, query3, query4, query5, query6 };
		By[] lbl = { adminHomePage.lblTotalProduct, adminHomePage.lblTotalOrder, adminHomePage.lblTotalUnconfirmOrder,
				adminHomePage.lblTotalPreparingOrder, adminHomePage.lblTotalDeliveringOrder,
				adminHomePage.lblTotalCompletedOrder };
		ArrayList<String> actualResult = new ArrayList<String>();
		String[] expectedResult = new String[lbl.length];
		for (int i = 0; i < query.length; i++) {
			/*
			 * ResultSet rs= con.verifyData(query[i]); if(rs.next()){
			 * result[i]=rs.getString(1); }
			 */
			 
			expectedResult[i] = getValueFromDatabase(query[i], 1).replace("[", "").replace("]", "");
			actualResult.add(driver.findElement(lbl[i]).getText());
		}

		Object[] actualResultArr = actualResult.toArray();
		String actualResultStr=Arrays.toString(actualResultArr);
		String expectedResultStr=Arrays.toString(expectedResult);
		assertEquals(actualResultStr, expectedResultStr);
	}
}
