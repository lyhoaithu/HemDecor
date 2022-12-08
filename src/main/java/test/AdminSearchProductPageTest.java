package test;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Arrays;

import org.checkerframework.checker.units.qual.Length;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.AdminHomePage;
import pages.AdminSearchProductPage;
import pages.LogInPage;

public class AdminSearchProductPageTest extends TestCase {
public ExcelUtils excelUtils= new ExcelUtils();
public static File data=new File("TestData/AutomationTestData.xlsx");
@BeforeMethod (alwaysRun = true)

public void preCondition() {
	LogInPage logIn= new LogInPage(driver);
	logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
	logIn.sendKeys("HemDecor", "HemDecor*1234");
	clickOnElemnet(logIn.btnLogIn);
	AdminHomePage adminHomePage= new AdminHomePage(driver);
	clickOnElemnet(adminHomePage.btnProduct);
}

@DataProvider(name="Validate Search Product At Product List Page Successfully")
public String[][] searchProductSuccessfully() throws IOException {
	String [][] searchProductSuccessfullyData= excelUtils.getDataFromExcel(data.getAbsolutePath(), "PL_SearchProductSuccessfully");
	return searchProductSuccessfullyData;

}

@DataProvider(name="Validate Search Product At Product List Page When No Product Is Found", indices = {0,1,2,3,4})
public String[][] searchProductWhenNoProductIsFoundAtProductListPage() throws IOException {
	String [][] searchProductSuccessfullyData= excelUtils.getDataFromExcel(data.getAbsolutePath(), "PL_SearchProductFail");
	return searchProductSuccessfullyData;

}


@DataProvider(name="Validate Search Product At Storage Page Successfully")
public String[][] searchProductSuccessfullyAtStoragePage() throws IOException {
	String [][] searchProductSuccessfullyData= excelUtils.getDataFromExcel(data.getAbsolutePath(), "SP_SearchProductSuccessfully");
	return searchProductSuccessfullyData;

}

@DataProvider(name="Validate Search Product At Storage Page When No Product Is Found", indices = {0,1,2,3})
public String[][] searchProductWhenNoProductIsFoundAtStoragePage() throws IOException {
	String [][] searchProductSuccessfullyData= excelUtils.getDataFromExcel(data.getAbsolutePath(), "SP_SearchProductFail");
	return searchProductSuccessfullyData;

}

@Test(description="Validate Searching Product At Product List Page Successfully", dataProvider="Validate Search Product At Product List Page Successfully", groups="main" )
public void validateSearchingProductSuccessfullyAtProductListPage(String keyword, String result) throws ClassNotFoundException, SQLException {
	AdminSearchProductPage searchProduct= new AdminSearchProductPage(driver);
	searchProduct.sendKey(keyword);
	clickOnElemnet(searchProduct.btnSearch);
	String resultFromDataBase=null;
	resultFromDataBase= getValueFromDatabase(result, 1);
	String [] actualResult= getDataFromTableColumn(By.xpath("//tbody//tr/td[2]"));
	String actualResultString= Arrays.deepToString(actualResult);
	assertEquals(actualResultString, resultFromDataBase);
	
}

@Test(description="Validate Searching Product At Product List Page When No Product Is Found", dataProvider="Validate Search Product At Product List Page When No Product Is Found", groups="validation")
public void validateSearchingProductWhenNoProductIsFoundAtProductListPage(String keyword, String result) throws ClassNotFoundException, SQLException {
	AdminSearchProductPage searchProduct= new AdminSearchProductPage(driver);
	searchProduct.sendKey(keyword);
	clickOnElemnet(searchProduct.btnSearch);
	String actualErrorMessage= driver.findElement(searchProduct.lblErrorMessage).getText();
	assertEquals(actualErrorMessage, result);
}

@Test(description="Validate Searching Product At Product List Page When Leave Field Blank",groups="validation")
public void validateSearchingProductWhenLeaveFieldBlankAtProductListPage() throws ClassNotFoundException, SQLException {
	AdminSearchProductPage searchProduct= new AdminSearchProductPage(driver);
	clickOnElemnet(searchProduct.btnSearch);
	String actualErrorMessage=getHtml5ValidationMessage(searchProduct.txtSearchBox);
	assertEquals(actualErrorMessage, "Please fill out this field.");
}

@Test(description="Validate Searching Product At Storage Page Successfully", dataProvider="Validate Search Product At Storage Page Successfully", groups="main")
public void validateSearchingProductSuccessfullyAtStoragePage(String keyword, String result) throws ClassNotFoundException, SQLException {
	AdminSearchProductPage searchProduct= new AdminSearchProductPage(driver);
	searchProduct.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/storage.php");
	searchProduct.sendKey(keyword);
	clickOnElemnet(searchProduct.btnSearch);
	String resultFromDataBase=null;
	resultFromDataBase= getValueFromDatabase(result, 1);
	String [] actualResult= getDataFromTableColumn(By.xpath("//tbody//tr/td[2]"));
	String actualResultString= Arrays.deepToString(actualResult);
	assertEquals(actualResultString, resultFromDataBase);
	
}

@Test(description="Validate Searching Product At Storage Page When No Product Is Found", dataProvider="Validate Search Product At Storage Page When No Product Is Found", groups="validation")
public void validateSearchingProductWhenNoProductIsFoundAtStoragePage(String keyword, String result) throws ClassNotFoundException, SQLException {
	AdminSearchProductPage searchProduct= new AdminSearchProductPage(driver);
	searchProduct.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/storage.php");
	searchProduct.sendKey(keyword);
	clickOnElemnet(searchProduct.btnSearch);
	String actualErrorMessage= driver.findElement(searchProduct.lblErrorMessage).getText();
	assertEquals(actualErrorMessage, result);
}

@Test(description="Validate Searching Product At Storage Page When Leave Field Blank", groups="validation")
public void validateSearchingProductWhenLeaveFieldBlankAtStoragePage() throws ClassNotFoundException, SQLException {
	AdminSearchProductPage searchProduct= new AdminSearchProductPage(driver);
	clickOnElemnet(searchProduct.btnSearch);
	String actualErrorMessage= getHtml5ValidationMessage(searchProduct.txtSearchBox);
	assertEquals(actualErrorMessage, "Please fill out this field.");
}
}