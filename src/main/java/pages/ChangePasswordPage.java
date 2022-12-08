package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.ExcelUtils;

public class ChangePasswordPage extends Page {

	public ChangePasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public HashMap<String, By> locatorMap= readLocatorFromExcle(locatorPath, "ChangePassword");
    public By txtOldPass= locatorMap.get("txtOldPass"); 
    public By txtNewPass= locatorMap.get("txtNewPass"); 
    public By txtConfirmPass= locatorMap.get("txtConfirmPass"); 
    public By chbShowPassword= locatorMap.get("chbShowPassword");
    public By btnChangePassword= locatorMap.get("btnChangePassword");
    public By lblErrorMessage= locatorMap.get("lblErrorMessage");
	public By lblSuccessfulMessage= locatorMap.get("lblSuccessfulMessage");
	
			public String getCurrentURL() {
	String currentURL= driver.getCurrentUrl();
return currentURL;
}

public void sendKeys(String oldPass, String newPasss, String confirmPass){
	fillInPlaceholder(txtOldPass, oldPass);
	fillInPlaceholder(txtNewPass, newPasss);
	fillInPlaceholder(txtConfirmPass, confirmPass);
	
}

//public void preCondition(String username, String password) {
//	LogInPage logIn= new LogInPage(driver);
//	fillInPlaceholder(logIn.txtEmail, username);
//	fillInPlaceholder(logIn.txtPassword, password);
//	clickOnElemnet(logIn.btnLogIn);
//	hoverMouse(By.xpath("/html/body/header/div/div/a"));
//	clickOnElemnet(By.linkText("Đổi Mật Khẩu"));
//}
//public static void main(String[] args) {
//	ExcelUtils excelUtils= new ExcelUtils();
//	String [][] data= null;
//	try {
//		data= excelUtils.getDataFromExcel("D:\\Automation Test\\02Projects\\HemDecor\\TestData\\AutomationTestData.xlsx", "ChangePassword");
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		System.out.println("File Not Found");
//	}
//	
//	    List<String> dataList = new ArrayList<String>();
//	    for(int i=0;i<data.length;i++) {
//	        for(int j=0;j<data.length;j++) {
//	           dataList.add(data[i][j]);
//	        }
//	    }
//System.out.println(dataList.toString());
//}
}
