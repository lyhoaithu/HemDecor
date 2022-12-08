package test;

import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


import common.TestBase;

public class TestCase extends TestBase {
	@BeforeMethod (alwaysRun = true)
//	@Parameters("browser")
public void openBrowser() {
	String browser= "edge";
	openSingleBrowser(browser);
}
	
	@AfterMethod (alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	
	public boolean verifyVietnameseErrorMessage(String actualMessage, String expectedMessString) throws UnsupportedEncodingException {
		boolean verifyMessage=true;
	      String actualMessageVietnamese= new String(actualMessage.getBytes( "ISO-8859-1"),"UTF-8");
	      String expectedMessageVietnamese= new String(actualMessage.getBytes( "ISO-8859-1"),"UTF-8");
if(actualMessageVietnamese.equalsIgnoreCase(expectedMessageVietnamese)) {
	verifyMessage=true;
}
else if(!actualMessageVietnamese.equalsIgnoreCase(expectedMessageVietnamese)) {
	verifyMessage= false;
}
return verifyMessage;
	}
	
}
