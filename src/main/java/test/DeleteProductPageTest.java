package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.DataConnection;
import common.ExcelUtils;
import pages.AdminHomePage;
import pages.DeleteProductPage;
import pages.LogInPage;

public class DeleteProductPageTest extends TestCase {
	public ExcelUtils excelUtils = new ExcelUtils();

	@BeforeMethod(alwaysRun = true)

	public void preCondition() {
		LogInPage logIn = new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("HemDecor", "HemDecor*1234");
		clickOnElemnet(logIn.btnLogIn);
		AdminHomePage adminHomePage = new AdminHomePage(driver);
		clickOnElemnet(adminHomePage.btnProduct);
	}

	@Test(description = "Validate Deleteing Product By CLicking At The Delete Icon Of The Product", priority = 1, groups = "main")
	public void validateDeletingProductByClickingAtTheDeleteIconOfTheProduct()
			throws ClassNotFoundException, SQLException {
		DeleteProductPage deleteProductPage = new DeleteProductPage(driver);
		String beforeDelete = driver.findElement(deleteProductPage.lblCategory1).getText();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(deleteProductPage.iconDelete));
		acceptAlertMessage();
		acceptAlertMessage();
		String afterDelete = driver.findElement(deleteProductPage.lblCategory1).getText();
		assertNotEquals(beforeDelete, afterDelete);

	}

	@Test(description = "Validate Deleteing Multiple Products", priority = 2, groups = "main")
	public void validateDeletingMultipleProducts() throws ClassNotFoundException, SQLException {
		DeleteProductPage deleteProductPage = new DeleteProductPage(driver);
		String beforeDelete1 = driver.findElement(deleteProductPage.lblCategory1).getText();
		String beforeDelete2 = driver.findElement(deleteProductPage.lblCategory2).getText();
		clickOnElemnet(deleteProductPage.chbSingleDelete1);
		clickOnElemnet(deleteProductPage.chbSingleDelete2);
		clickOnElemnet(deleteProductPage.btnDelete);
		acceptAlertMessage();
		acceptAlertMessage();
		String afterDelete1 = driver.findElement(deleteProductPage.lblCategory1).getText();
		String afterDelete2 = driver.findElement(deleteProductPage.lblCategory2).getText();
		assertNotEquals(beforeDelete1, afterDelete1);
		assertNotEquals(beforeDelete2, afterDelete2);
	}

	@Test(description = "Validate Deleteing One Product By Clicking On The Delete Button", priority = 3, groups = "main")
	public void validateDeletingOneProductByClickingOnTheDeleteButton() throws ClassNotFoundException, SQLException {
		DeleteProductPage deleteProductPage = new DeleteProductPage(driver);
		String beforeDelete1 = driver.findElement(deleteProductPage.lblCategory1).getText();
		clickOnElemnet(deleteProductPage.chbSingleDelete1);
		clickOnElemnet(deleteProductPage.btnDelete);
		acceptAlertMessage();
		acceptAlertMessage();
		String afterDelete1 = driver.findElement(deleteProductPage.lblCategory1).getText();
		assertNotEquals(beforeDelete1, afterDelete1);
	}

	@Test(description = "Validate Deleteing All Product In One Page", priority = 5, groups = "main")
	public void validateDeletingAllProductInOnePage() throws ClassNotFoundException, SQLException {
		DeleteProductPage deleteProductPage = new DeleteProductPage(driver);
		String[] beforeDelete = getDataFromTableColumn(deleteProductPage.lblCategoryIDColumn);
		clickOnElemnet(deleteProductPage.chbDeleteAll);
		clickOnElemnet(deleteProductPage.btnDelete);
		acceptAlertMessage();
		acceptAlertMessage();
		String[] afterDelete = getDataFromTableColumn(deleteProductPage.lblCategoryIDColumn);
		assertNotEquals(beforeDelete, afterDelete);
	}

	@Test(description = "Validate Deleteing Product Fail When Clicking On 'Hủy' At The Alert", priority = 4, groups = "validation")
	public void validateDeletingProductWhenClickingOnButtonHuy() throws ClassNotFoundException, SQLException {
		DeleteProductPage deleteProductPage = new DeleteProductPage(driver);
		String beforeResult = driver.findElement(deleteProductPage.lblCategory1).getText();
		clickOnElemnet(deleteProductPage.chbSingleDelete1);
		clickOnElemnet(deleteProductPage.btnDelete);
		rejectAlertMessage();
		String afterResult = driver.findElement(deleteProductPage.lblCategory1).getText();
		assertEquals(beforeResult, afterResult);
	}
}
