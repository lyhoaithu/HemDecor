package pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.checkerframework.checker.units.qual.Length;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import common.TestBase;

public class AdminSearchProductPage extends Page {

	public AdminSearchProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public HashMap<String, By> locatorMap = readLocatorFromExcle(locatorPath, "AdminSearchProduct");
public By txtSearchBox= locatorMap.get("txtSearchBox");
public By btnSearch= locatorMap.get("btnSearch");
public By lblErrorMessage= locatorMap.get("lblErrorMessage");

public void sendKey(String keyword) {
	fillInPlaceholder(txtSearchBox, keyword);

}

//Handling Dynamic Web Table
//public static void main(String[] args) throws ClassNotFoundException, SQLException {
// TestBase testBase= new TestBase();
//// testBase.openSingleBrowser("edge");
//// testBase.driver.get("http://localhost:8080/HemDecor/admin_manage_product/manage_category.php");
// ArrayList<String> resultFromDataBase=new ArrayList<String>();
//	resultFromDataBase= getValueFromDatabase("select CategoryID from product where productName LIKE'%Óc chó%' GROUP BY CategoryID;", 1);
//for (int i=0; i<resultFromDataBase.size();i++) {
//	System.out.println(resultFromDataBase.get(i).toString());
//}
//	//List<WebElement> row= testBase.driver.findElements(By.xpath("//tbody//tr/th[2]"));
////for (int i=0; i<row.size();i++) {
////	System.out.println("Number of row: "+ row.get(i).getText());
////
////}

 
}

