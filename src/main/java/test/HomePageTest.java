package test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import pages.HomePage;

public class HomePageTest extends TestCase {
@Test(description = "Navigate To Homepage By Clicking On Logo", groups = "main")
public void navigateToHomepageByShopLogo() {
	HomePage homePage = new HomePage(driver);
	homePage.navigateToPage("http://localhost:8081/HemDecor/anon/homepage.php");
	clickOnElemnet(By.xpath("/html/body/header/a[1]"));
	String currentURL = homePage.getCurrentURL();
	assertTrue(currentURL.contains("homepage.php"));
}

@Test(description = "Navigate To Homepage By Clicking On Shop Name", groups = "main")
public void navigateToHomepageByShopName() {
	HomePage homePage = new HomePage(driver);
	homePage.navigateToPage("http://localhost:8081/HemDecor/anon/homepage.php");
	clickOnElemnet(By.className("web_name"));
	String currentURL = homePage.getCurrentURL();
	assertTrue(currentURL.contains("homepage.php"));
}

@Test(description = "Navigate To Homepage By Clicking On 'Trang Chủ'", groups = "main")
public void navigateToHomepageByLinkText() {
	HomePage homePage = new HomePage(driver);
	homePage.navigateToPage("http://localhost:8081/HemDecor/anon/homepage.php");
	clickOnElemnet(By.xpath("/html/body/header/nav/ul/li[1]/a"));
	String currentURL = homePage.getCurrentURL();
	assertTrue(currentURL.contains("homepage.php"));
}
}
