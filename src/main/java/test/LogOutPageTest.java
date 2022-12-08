package test;

import static org.testng.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.AdminHomePage;
import pages.HomePage;
import pages.LogInPage;
import pages.LogOutPage;
import pages.Page;

public class LogOutPageTest extends TestCase {
	@BeforeMethod (alwaysRun = true)

	public void preCondition() {
		LogInPage logIn= new LogInPage(driver);
		logIn.navigateToPage("http://localhost:8081/HemDecor/user_account/login.php");
		logIn.sendKeys("HemDecor", "HemDecor*1234");
		clickOnElemnet(logIn.btnLogIn);
	}
@Test (description = "Validate User Log Out Successfully", groups = "main")
public void validateLogOut() throws UnsupportedEncodingException {
	LogOutPage logOutPage = new LogOutPage(driver);
	clickOnElemnet(logOutPage.txtLogOut);
	String currentURL=driver.getCurrentUrl();
	assertTrue(currentURL.contains("anon/homepage.php"));

}
	

}
