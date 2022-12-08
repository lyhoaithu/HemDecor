package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ExcelUtils;
import pages.AdminHomePage;
import pages.AdminProductDetailsPage;
import pages.LogInPage;

public class AdminProductDetailsPageTest extends TestCase {
public ExcelUtils excelUtils= new ExcelUtils();

@BeforeMethod (alwaysRun = true)
public void preCondition() {
	LogInPage logIn= new LogInPage(driver);
	logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
	logIn.sendKeys("HemDecor", "HemDecor*1234");
	clickOnElemnet(logIn.btnLogIn);
	AdminHomePage adminHomePage= new AdminHomePage(driver);
	clickOnElemnet(adminHomePage.btnProduct);
}

@Test(description = "Validate Navigate To Product Details Page Successfully", groups = "main")
public void validateNavigateToProductDetailsPageSuccessfully() {
	AdminProductDetailsPage productDetailsPage= new AdminProductDetailsPage(driver);
	productDetailsPage.navigateToPage("http://localhost:8081/HemDecor/admin_manage_product/manage_category.php");
	String productID=driver.findElement(By.xpath("//tbody//td[2]")).getText();
	clickOnElemnet(productDetailsPage.iconEye);
	String productIDDetails=driver.findElement(By.xpath("//div[@class='product-info']//p[2]")).getText().substring(14);
	assertEquals(productIDDetails, productID);


}

}
