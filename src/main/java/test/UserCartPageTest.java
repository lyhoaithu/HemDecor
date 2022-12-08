package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.AdminHomePage;
import pages.LogInPage;
import pages.UserCartPage;

public class UserCartPageTest extends TestCase {
	@BeforeMethod(alwaysRun = true)
	public void preCondition() {
		LogInPage logIn = new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("0962370612", "Hoaithu*2812");
		clickOnElemnet(logIn.btnLogIn);
	}
	public String pagePath="http://localhost:8081/HemDecor/user_products/product_page.php";

	public ExcelUtils excelUtils = new ExcelUtils();

@Test(description = "Validate Delete product by clicking on the trash icon", groups="main")
public void deleteProductByClickingOnTheTrashIcon() {
	UserCartPage userCartPage= new UserCartPage(driver);
	clickOnElemnet(userCartPage.iconCart);
	String productNameBeforeDelete= driver.findElement(userCartPage.lblProductNameAtCart).getText();
	clickOnElemnet(userCartPage.iconDelete);
	String productNameAfterDelete= driver.findElement(userCartPage.lblProductNameAtCart).getText();
	assertNotEquals(productNameAfterDelete, productNameBeforeDelete);
}

@Test(description = "Validate delete all product from cart", groups="main", priority = 2)
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
	
	@Test(description = "Validate add to cart successfully when adding the quantity smaller than the in stock quantity", groups="main", priority = 3)
	public void addCartWithSmallerQuantity() {
		UserCartPage userCartPage= new UserCartPage(driver);
		userCartPage.navigateToPage(pagePath);
		String instockProduct=null;
		int randomInstockProduct;
		List<WebElement> size= driver.findElements(userCartPage.btnSize);
		Random rand = new Random();
		
		if(!size.isEmpty()) {
			clickOnElemnet(userCartPage.btnSize);
			String[] instockProductAr= driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
            instockProduct= instockProductAr[0];
			 int ranProductQuantity = rand.nextInt(Integer.parseInt(instockProduct)-1)+1;   
		        for(int i=0; i<ranProductQuantity;i++) {
					clickOnElemnet(userCartPage.btnAdd);
				}
		}
		
		else if(size.isEmpty()){
			clickOnElemnet(userCartPage.btnSize);
			String[] instockProductAr= driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
            instockProduct= instockProductAr[0];
			 int ranProductQuantity = rand.nextInt(Integer.parseInt(instockProduct)-1)+1;
		        String ranProductQuantityStr= String.valueOf(ranProductQuantity);
		        for(int i=0; i<ranProductQuantity;i++) {
					clickOnElemnet(userCartPage.btnAdd);
				}
		}
		clickOnElemnet(userCartPage.btnAddToCart);
		String successfullMessage= driver.findElement(userCartPage.lblSuccessfulMessage).getText();
		assertEquals(successfullMessage, "Đã Thêm Vào Giỏ Hàng");
	}
	
	
	@Test(description = "Validate add to cart successfully when adding the quantity equal to the in stock quantity", groups="main")
	public void addCartWithEqualQuantity() {
		UserCartPage userCartPage= new UserCartPage(driver);
		userCartPage.navigateToPage(pagePath);
		String instockProduct=null;
		List<WebElement> size= driver.findElements(userCartPage.btnSize);
		Random rand = new Random();
		if(!size.isEmpty()) {
			clickOnElemnet(userCartPage.btnSize);
			String[] instockProductAr= driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
            instockProduct= instockProductAr[0];
            int instockProductInt= Integer.parseInt(instockProduct);
			for(int i=0; i<instockProductInt-1;i++) {
				clickOnElemnet(userCartPage.btnAdd);
			}
		}
		else if(size.isEmpty()){
			String[] instockProductAr= driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
            instockProduct= instockProductAr[0];
            int instockProductInt= Integer.parseInt(instockProduct);
			for(int i=0; i<instockProductInt-1;i++) {
				clickOnElemnet(userCartPage.btnAdd);
			}
		}
		clickOnElemnet(userCartPage.btnAddToCart);
		String successfullMessage= driver.findElement(userCartPage.lblSuccessfulMessage).getText();
		assertEquals(successfullMessage, "Đã Thêm Vào Giỏ Hàng");
	}
	
	@Test(description = "Validate add product fail when no size is chosen", groups = "validation")
	public void addProductWhenNoSizeIsChosen() {
		UserCartPage userCartPage = new UserCartPage(driver);
		userCartPage.navigateToPage(pagePath);
		clickOnElemnet(By.xpath("//div[@class='content']//a"));
		clickOnElemnet(userCartPage.btnAddToCart);
		String errorMessage = driver.findElement(userCartPage.lblErrorMessage).getText();
		assertEquals(errorMessage, "Vui lòng chọn Size");
	}

	@Test(description = "Validate add product fail when add product quantity bigger than instock quantity", groups="validation")
	public void addProductWithBiggerQuantity() {
		UserCartPage userCartPage = new UserCartPage(driver);
		userCartPage.navigateToPage("http://localhost:8081/HemDecor/user_products/product_page.php");
		clickOnElemnet(By.xpath("//div[@class='content']//a"));
		String instockProduct = null;
		String biggerInstockProductStr = null;
		List<WebElement> size = driver.findElements(userCartPage.btnSize);
		if (!size.isEmpty()) {
			clickOnElemnet(userCartPage.btnSize);
			String[] instockProductAr = driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
			instockProduct = instockProductAr[0];
			 int instockProductInt= Integer.parseInt(instockProduct);
				for(int i=0; i<instockProductInt;i++) {
					clickOnElemnet(userCartPage.btnAdd);
				}
		} else if (size.isEmpty()) {
			String[] instockProductAr = driver.findElement(userCartPage.lblInstockProduct).getText().split(" ");
			instockProduct = instockProductAr[0];
			 int instockProductInt= Integer.parseInt(instockProduct);
				for(int i=0; i<instockProductInt+1;i++) {
					clickOnElemnet(userCartPage.btnAdd);
				}
		}
		clickOnElemnet(userCartPage.btnAddToCart);
		
String actualMessage=driver.findElement(userCartPage.lblErrorMessage02).getText();
assertEquals(actualMessage, "Vượt quá số lượng có sẵn của sản phẩm");
	}
}
