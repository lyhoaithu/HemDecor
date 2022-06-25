package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.AdminProductDetailsPage;

public class AminProductDetailsPageTest extends TestCase {
public ExcelUtils excelUtils= new ExcelUtils();

@Test(description = "Validate Navigate To Product Details Page Successfully", groups = "main")
public void validateNavigateToProductDetailsPageSuccessfully() {
	AdminProductDetailsPage productDetailsPage= new AdminProductDetailsPage(driver);
	productDetailsPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/manage_category.php");
	clickOnElemnet(productDetailsPage.iconEye);
	String currentURL= driver.getCurrentUrl();
	assertEquals(currentURL, "http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=TB/D02");

}

@DataProvider(name="Expected Displayed Information Data")
public String [][] expectedDisplayedInformationData() throws IOException {
	String [][] expectedDisplayedInformationData = excelUtils.getDataFromExcel("D:\\AutomationTest\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx", "AdminProductDetailsPage");
return expectedDisplayedInformationData;
}

@Test(description = "Validate Displayed Information", dataProvider = "Expected Displayed Information Data" )
public void validateDisplayedInformation(String categoryID, String categoryName, String material, String description, String query) throws ClassNotFoundException, SQLException {
	AdminProductDetailsPage productDetailsPage= new AdminProductDetailsPage(driver);
	productDetailsPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/product-detail.php?cid=OC/D01");
	
	//Locator để lấy data mà không trong table từ Screen
	By [] actualResultData= {productDetailsPage.lblCategoryID, productDetailsPage.lblCategoryName, productDetailsPage.lblMaterial, productDetailsPage.lblDescription}; 
	
	//Locator để lấy data trong bảng từ screen
	By [] actualTableResultData= {productDetailsPage.column1, productDetailsPage.column2, productDetailsPage.column3, productDetailsPage.column4};
	
	//Giá trị expected  của những elements ko trong bảng
	String [] expectedResultData= {categoryID,  categoryName, material, description};
	
	//Tạo Arr để chứa các giá trị actual của những elements ko trong bảng 
	String[] actualResult= new String[actualResultData.length];
	
	//Tạo arr để chưa các phần tử trong table lấy từ database (có 4 cột trong db tất cả)
	String [] expectedTableColumnResult=new String [expectedResultData.length];
	for (int i=0; i< expectedResultData.length;i++) {
		expectedTableColumnResult[i]=getValueFromDatabase(query, i+1);
	}
	
	//Tạo list để chứa các phần tử trong table lấy từ html mỗi cột là 1 array (có 4 cột tất cả)
	ArrayList<String> actualTableColumnResult= new ArrayList<String>();
	for (int i=0; i< expectedResultData.length;i++) {
		actualTableColumnResult.add(Arrays.deepToString(getDataFromTableColumn(actualTableResultData[i])));
	}
	
	//Chuyển từ list về arr để so sánh với các phần tywr trong table lấy từ database
	Object[]actualTableColumnResultArr= actualTableColumnResult.toArray();
	
	//So sánh các phần tử trong table với nhau
	for (int i=0; i< expectedResultData.length; i++) {
		assertEquals(expectedTableColumnResult[i], actualTableColumnResultArr[i]);
	}

	//So sánh các phần tử không ở trong table
	for (int i=0; i<actualResultData.length;i++) {
		actualResult[i]=driver.findElement(actualResultData[i]).getText();
	}
assertEquals(Arrays.deepToString(expectedResultData), Arrays.deepToString(actualResult));

}
}
