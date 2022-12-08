package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.LogInPage;
import pages.UserProductDetailsPage;

public class UserProductDetailsPageTest extends TestCase {
	public ExcelUtils excelUtils = new ExcelUtils();
public String pagePath="http://localhost:8081/HemDecor/anon/product_page.php";
public static File dataPath=new File("TestData/AutomationTestData.xlsx");


@Test(description = "Validate navigate to product details page by clicking on product image", groups = "main")
public void validateNavigateToProductDetailsPageByClickingOnProductImage() {
	UserProductDetailsPage updp= new UserProductDetailsPage(driver);
	updp.navigateToPage(pagePath);
	String productName=driver.findElement(By.xpath("//div[@class='productName']//h3")).getText();
	clickOnElemnet(updp.btnProductName);
	String productNameDetails=driver.findElement(updp.lblProductName).getText();
	assertEquals(productNameDetails, productName);

}
	@DataProvider(name = "Product Detail Page Initial Data", indices = {0})
	public String[][] productDetailsPageInitialData() throws IOException {
		String[][] productDetailsPageInitialData = excelUtils.getDataFromExcel(dataPath.getAbsolutePath(),"UserProductDetailsPage");
		return productDetailsPageInitialData;

	}

	@Test(description = "Validate Initial Displayed Information", dataProvider = "Product Detail Page Initial Data", groups = "main")
	public void validateInitialDisplayedInformation(String Pic1, String Pic2, String Pic3, String Pic4, String Pic5,
			String name, String price, String size, String totalQuantity, String singleQuantity, String quantityPicker,
			String description) throws ClassNotFoundException, SQLException {
		UserProductDetailsPage userProductDetailsPage = new UserProductDetailsPage(driver);
		userProductDetailsPage
				.navigateToPage("http://localhost:8081/HemDecor/anon/view_product.php?cid=K/C01");

		//Assert Product Name
		String expectedProductName= getValueFromDatabase(name, 1).replace("[", "").replace("]", "");
		String actualProductName= driver.findElement(userProductDetailsPage.lblProductName).getText();
		assertEquals(expectedProductName, actualProductName);
		
		//Assert Product Price
		String expectedMinProductPrice= getValueFromDatabase(price, 1).replace("[", "").replace("]", "");
		String expectedMaxProductPrice= getValueFromDatabase(price, 2).replace("[", "").replace("]", "");
		String actualProductPrice= driver.findElement(userProductDetailsPage.lblProductPrice).getText().replace(",", "");
		assertTrue(actualProductPrice.contains(expectedMinProductPrice));
		assertTrue(actualProductPrice.contains(expectedMaxProductPrice));


		// Assert Product Size
		String expectedProductSize = getValueFromDatabase(size, 1).replace("[", "").replace("]", "");
		String[] actualSizeName = getDataFromTableColumn(userProductDetailsPage.lblSize);
		String actualSizeNameStr = Arrays.deepToString(actualSizeName).replace("[", "").replace("]", "");
		assertEquals(actualSizeNameStr, expectedProductSize);
	
	//Assert Total Product Quantity
	String expectedTotalProductQuantity= getValueFromDatabase(totalQuantity, 1).replace("[", "").replace("]", "");
	String actualTotalQuantity= driver.findElement(userProductDetailsPage.lblTotalProduct).getText();
	assertTrue(actualTotalQuantity.contains(expectedTotalProductQuantity));

		//Assert Description
		String expectedDescription= getValueFromDatabase(description, 1).replace("[", "").replace("]", "");
		String actualDescription= driver.findElement(userProductDetailsPage.lblDescription).getText();
			assertEquals(actualDescription, expectedDescription);
	}
	
	
	@DataProvider(name = "Product Detail Page When Size Is Chosen Data", indices = {1})
	public String[][] productDetailsPageWhenSizeIsChosenData() throws IOException {
		String[][] productDetailsPageWhenSizeIsChosenData = excelUtils.getDataFromExcel(
				dataPath.getAbsolutePath(),
				"UserProductDetailsPage");
		return productDetailsPageWhenSizeIsChosenData;

	}
	@Test(description = "Validate Displayed Information When A Size Is Chosen", dataProvider = "Product Detail Page When Size Is Chosen Data", groups = "main")
	public void validateInitialDisplayedInformationWhenSizeIsChosen(String Pic1, String Pic2, String Pic3, String Pic4, String Pic5,
			String name, String price, String size, String totalQuantity, String singleQuantity, String quantityPicker,
			String description) throws ClassNotFoundException, SQLException {
		UserProductDetailsPage userProductDetailsPage = new UserProductDetailsPage(driver);
		userProductDetailsPage
				.navigateToPage("http://localhost:8081/HemDecor/anon/view_product.php?cid=K/C01");
clickOnElemnet(userProductDetailsPage.btnSize);


//Assert Product Price
String actualProductPrice= driver.findElement(userProductDetailsPage.lblProductPrice).getText().replace(",", "");
String expectedProductPrice= getValueFromDatabase(price, 1).replace("]", "").replace("[", "");
assertEquals(expectedProductPrice, actualProductPrice);

//Assert Product Quantity
String actualProductQuantity= driver.findElement(userProductDetailsPage.lblTotalProduct).getText();
String expectedProductQuantity= getValueFromDatabase(singleQuantity, 1).replace("]", "").replace("[", "")+" sản phẩm";
assertEquals(expectedProductQuantity, actualProductQuantity);
	}
}

