package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.AdminHomePage;
import pages.LogInPage;
import pages.UserCartPage;

public class UserCartPageTest extends TestCase {
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "phoneNumber", "password" })
	public void preCondition(String phoneNumber, String password) {
		LogInPage logIn = new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8080/HemDecor/user_account/login.php");
		logIn.sendKeys(phoneNumber, password);
		clickOnElemnet(logIn.btnLogIn);
	}

	public ExcelUtils excelUtils = new ExcelUtils();

@Test(description = "Validate Delete product by clicking on the trash icon",priority = 1)
public void deleteProductByClickingOnTheTrashIcon() {
	UserCartPage userCartPage= new UserCartPage(driver);
	clickOnElemnet(userCartPage.iconCart);
	String productNameBeforeDelete= driver.findElement(userCartPage.lblProductNameAtCart).getText();
	clickOnElemnet(userCartPage.iconDelete);
	String productNameAfterDelete= driver.findElement(userCartPage.lblProductNameAtCart).getText();
	assertNotEquals(productNameAfterDelete, productNameBeforeDelete);
}

@Test(description = "Validate delete all product from cart", groups="validation", priority = 2)
public void deleteAllProductFromCart() {
	UserCartPage userCartPage= new UserCartPage(driver);
	clickOnElemnet(userCartPage.iconCart);
	List<WebElement> productName= driver.findElements(userCartPage.lblProductNameAtCart);
	while(!productName.isEmpty()) {
		clickOnElemnet(userCartPage.iconDelete);
		productName=driver.findElements(userCartPage.lblProductNameAtCart);
	}
	String message= driver.findElement(userCartPage.lblMessage).getText();
	assertEquals(message, "Giỏ hàng rỗng");
}
	
	@Test(description = "Validate add to cart successfully when adding the quantity smaller than the in stock quantity")
	public void addCartWithSmallerQuantity() {
		UserCartPage userCartPage= new UserCartPage(driver);
		userCartPage.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=TB/D02");
		String instockProduct=null;
		int randomInstockProduct;
		List<WebElement> size= driver.findElements(userCartPage.btnSize);
		Random rand = new Random();
		
		if(!size.isEmpty()) {
			clickOnElemnet(userCartPage.btnSize);
			String[] instockProductAr= driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
            instockProduct= instockProductAr[0];
			 int ranProductQuantity = rand.nextInt(Integer.parseInt(instockProduct)-1)+1;
		        String ranProductQuantityStr= String.valueOf(ranProductQuantity);
			driver.findElement(userCartPage.txtQuantity).clear();
			fillInPlaceholder(userCartPage.txtQuantity,instockProduct);
		}
		
		else if(size.isEmpty()){
			String[] instockProductAr= driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
            instockProduct= instockProductAr[0];
			 int ranProductQuantity = rand.nextInt(Integer.parseInt(instockProduct)-1)+1;
		        String ranProductQuantityStr= String.valueOf(ranProductQuantity);
			driver.findElement(userCartPage.txtQuantity).clear();
			fillInPlaceholder(userCartPage.txtQuantity,instockProduct);
		}
		clickOnElemnet(userCartPage.btnAddToCart);
		String successfullMessage= driver.findElement(userCartPage.lblSuccessfulMessage).getText();
		assertEquals(successfullMessage, "Đã Thêm Vào Giỏ Hàng");
	}
	
	
	@Test(description = "Validate add to cart successfully when adding the quantity equal to the in stock quantity")
	public void addCartWithEqualQuantity() {
		UserCartPage userCartPage= new UserCartPage(driver);
		userCartPage.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=TH01");
		String instockProduct=null;
		List<WebElement> size= driver.findElements(userCartPage.btnSize);
		Random rand = new Random();
		if(!size.isEmpty()) {
			clickOnElemnet(userCartPage.btnSize);
			String[] instockProductAr= driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
            instockProduct= instockProductAr[0];
			driver.findElement(userCartPage.txtQuantity).clear();
			fillInPlaceholder(userCartPage.txtQuantity,instockProduct);
		}
		else if(size.isEmpty()){
			String[] instockProductAr= driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
            instockProduct= instockProductAr[0];
			driver.findElement(userCartPage.txtQuantity).clear();
			fillInPlaceholder(userCartPage.txtQuantity,instockProduct);
		}
		clickOnElemnet(userCartPage.btnAddToCart);
		String successfullMessage= driver.findElement(userCartPage.lblSuccessfulMessage).getText();
		assertEquals(successfullMessage, "Đã Thêm Vào Giỏ Hàng");
	}
	
	@Test(description = "Validate add product fail when no size is chosen", groups = "validation")
	public void addProductWhenNoSizeIsChosen() {
		UserCartPage userCartPage = new UserCartPage(driver);
		userCartPage.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=TB/D02");
		clickOnElemnet(userCartPage.btnAddToCart);
		String errorMessage = driver.findElement(userCartPage.lblErrorMessage).getText();
		assertEquals(errorMessage, "Vui lòng chọn Size");
	}

	@Test(description = "Validate add product fail when add product quantity bigger than instock quantity")
	public void addProductWithBiggerQuantity() {
		UserCartPage userCartPage = new UserCartPage(driver);
		userCartPage.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=TH01");
		String instockProduct = null;
		String biggerInstockProductStr = null;
		List<WebElement> size = driver.findElements(userCartPage.btnSize);
		if (!size.isEmpty()) {
			clickOnElemnet(userCartPage.btnSize);
			String[] instockProductAr = driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
			instockProduct = instockProductAr[0];
			biggerInstockProductStr = String.valueOf(Integer.parseInt(instockProduct) + 1);
			driver.findElement(userCartPage.txtQuantity).clear();
			fillInPlaceholder(userCartPage.txtQuantity, biggerInstockProductStr);
		} else if (size.isEmpty()) {
			String[] instockProductAr = driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
			instockProduct = instockProductAr[0];
			biggerInstockProductStr = String.valueOf(Integer.parseInt(instockProduct) + 1);
			driver.findElement(userCartPage.txtQuantity).clear();
			fillInPlaceholder(userCartPage.txtQuantity, biggerInstockProductStr);
			System.out.println(biggerInstockProductStr);
		}
		clickOnElemnet(userCartPage.btnAddToCart);
		String errorMessage = driver.findElement(userCartPage.lblErrorMessage).getText();
		assertEquals(errorMessage, "Số lượng sản phẩm vượt quá số lượng sản phẩm trong kho");
	}

	@Test(description = "Validate add product fail when add product with quantity equal 0")
	public void addProductWithQuantity0() {
		UserCartPage userCartPage = new UserCartPage(driver);
		userCartPage.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=TH01");
		String instockProduct = null;
		String biggerInstockProductStr = null;
		List<WebElement> size = driver.findElements(userCartPage.btnSize);
		if (!size.isEmpty()) {
			clickOnElemnet(userCartPage.btnSize);
			driver.findElement(userCartPage.txtQuantity).clear();
			fillInPlaceholder(userCartPage.txtQuantity, "0");
		} else if (size.isEmpty()) {
			driver.findElement(userCartPage.txtQuantity).clear();
			fillInPlaceholder(userCartPage.txtQuantity, "0");
		}
		clickOnElemnet(userCartPage.btnAddToCart);
		String errorMessage = driver.findElement(userCartPage.lblErrorMessage).getText();
		assertEquals(errorMessage, "Số lượng sản phẩm không hợp lệ");
	}

	@Test(description = "Validate add product fail when leave quantity picker blank")
	public void addProductWhenQuantityPickerBlank() {
		UserCartPage userCartPage = new UserCartPage(driver);
		userCartPage.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=TH01");
		String instockProduct = null;
		String biggerInstockProductStr = null;
		List<WebElement> size = driver.findElements(userCartPage.btnSize);
		if (!size.isEmpty()) {
			clickOnElemnet(userCartPage.btnSize);
			driver.findElement(userCartPage.txtQuantity).clear();
		} else if (size.isEmpty()) {
			driver.findElement(userCartPage.txtQuantity).clear();
		}
		clickOnElemnet(userCartPage.btnAddToCart);
		String errorMessage = getHtml5ValidationMessage(userCartPage.txtQuantity);
		assertEquals(errorMessage, "Please fill out this field.");
	}

	@Test(description = "Validate adjust quantity picker to 0")
	public void adjustQuantityPickerTo0() {
		UserCartPage userCartPage = new UserCartPage(driver);
		userCartPage.navigateToPage("http://localhost:8080/HemDecor/user_products/view_product.php?cid=TH01");
		String quantityBefore= driver.findElement(userCartPage.txtQuantity).getText();
		clickOnElemnet(userCartPage.btnSubtract);
		String quantityAfter= driver.findElement(userCartPage.txtQuantity).getText();
		assertEquals(quantityBefore, quantityAfter);

	}
}
