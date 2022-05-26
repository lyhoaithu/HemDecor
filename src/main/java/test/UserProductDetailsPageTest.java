package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

	@BeforeMethod
	public void LogIn() {
		LogInPage logInPage = new LogInPage(driver);
		logInPage.navigateToPage("http://localhost:8080/HemDecor/user_account/login.php");
		logInPage.sendKeys("0962370612", "Hoaithu*2001");
		clickOnElemnet(logInPage.btnLogIn);

	}

@Test(description = "Validate navigate to product details page by clicking on product image")
public void validateNavigateToProductDetailsPageByClickingOnProductImage() {
	UserProductDetailsPage updp= new UserProductDetailsPage(driver);
	updp.navigateToPage("http://localhost:8080/HemDecor/user_products/product_page.php");
	clickOnElemnet(updp.btnProductName);
	String currentURL= driver.getCurrentUrl();
	assertEquals(currentURL, "http://localhost:8080/HemDecor/user_products/view_product.php?cid=K/C01");

}
	@DataProvider(name = "Product Detail Page Initial Data", indices = {0})
	public String[][] productDetailsPageInitialData() throws IOException {
		String[][] productDetailsPageInitialData = excelUtils.getDataFromExcel(
				"D:\\Automation Test\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx",
				"UserProductDetailsPage");
		return productDetailsPageInitialData;

	}

	@Test(description = "Validate Initial Displayed Information", dataProvider = "Product Detail Page Initial Data")
	public void validateInitialDisplayedInformation(String Pic1, String Pic2, String Pic3, String Pic4, String Pic5,
			String name, String price, String size, String totalQuantity, String singleQuantity, String quantityPicker,
			String description) throws ClassNotFoundException, SQLException {
		UserProductDetailsPage userProductDetailsPage = new UserProductDetailsPage(driver);
		userProductDetailsPage
				.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=K/C01");

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

		// Assert Product Picture
		String numberOfPicture = getValueFromDatabase(
				"SELECT (ThumbnailImage <>'') +(AddPic1 <>'') +(AddPic2 <>'') +(AddPic3 <>'') + (AddPic4 <>'') AS PictureCount FROM Categories where categoryID='K/C01';",
				1).replace("[", "").replace("]", "");
		int numberOfPictureInt = Integer.parseInt(numberOfPicture);
		By[] actualPictureLocator = { userProductDetailsPage.imgPic1, userProductDetailsPage.imgPic2,
				userProductDetailsPage.imgPic3, userProductDetailsPage.imgPic4, userProductDetailsPage.imgPic5 };
		for (int i = 0; i < numberOfPictureInt; i++) {
			assertEquals(checkElementVisibility(actualPictureLocator[i]), true);
		}

		// Assert Product Size
		String expectedProductSize = getValueFromDatabase(size, 1);
		String[] actualSizeName = getDataFromTableColumn(userProductDetailsPage.lblSize);
		String actualSizeNameStr = Arrays.deepToString(actualSizeName);
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
				"D:\\Automation Test\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx",
				"UserProductDetailsPage");
		return productDetailsPageWhenSizeIsChosenData;

	}
	@Test(description = "Validate Displayed Information When A Size Is Chosen", dataProvider = "Product Detail Page When Size Is Chosen Data")
	public void validateInitialDisplayedInformationWhenSizeIsChosen(String Pic1, String Pic2, String Pic3, String Pic4, String Pic5,
			String name, String price, String size, String totalQuantity, String singleQuantity, String quantityPicker,
			String description) throws ClassNotFoundException, SQLException {
		UserProductDetailsPage userProductDetailsPage = new UserProductDetailsPage(driver);
		userProductDetailsPage
				.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=K/C01");
clickOnElemnet(userProductDetailsPage.btnSize);

//Assert Product Name
String actualProductName= driver.findElement(userProductDetailsPage.lblProductName).getText();
String expectedProductName= driver.findElement(userProductDetailsPage.lblSize).getText();
assertTrue(actualProductName.contains(expectedProductName));

//Assert Product Price
String actualProductPrice= driver.findElement(userProductDetailsPage.lblProductPrice).getText().replace(",", "");
String expectedProductPrice= getValueFromDatabase(price, 1).replace("]", "").replace("[", "");
assertEquals(expectedProductPrice, actualProductPrice);

//Assert Product Quantity
String actualProductQuantity= driver.findElement(userProductDetailsPage.lblTotalProduct).getText();
String expectedProductQuantity= getValueFromDatabase(singleQuantity, 1).replace("]", "").replace("[", "")+" sản phẩm";
assertEquals(expectedProductQuantity, actualProductQuantity);
	}
	
	@Test(description = "Validate quantity pick when clicking on the - adjustment button when the current quantity value is 1")
	public void validateQuantityPickWhenClickingOnMinusButtonWhenCurrentQuantityIs1() {
		UserProductDetailsPage userProductDetailsPage = new UserProductDetailsPage(driver);
		userProductDetailsPage.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=K/C01&pid=K/C01");
	    clickOnElemnet(userProductDetailsPage.btnMinus);
	    System.out.println(driver.findElement(userProductDetailsPage.lblProductPickerQuantity).getText());
	    assertEquals(driver.findElement(userProductDetailsPage.lblProductPickerQuantity).getText(), "1");
	}
}

