package pages;

import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOutPage extends Page {
	public LogOutPage(WebDriver driver) {
		super(driver);
	}
	
	public By txtLogOut= By.xpath("//a[contains(@href,'logout')]");
}
