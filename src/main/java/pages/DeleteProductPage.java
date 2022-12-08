package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteProductPage extends Page {

	public DeleteProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public HashMap<String, By> locatorMap = readLocatorFromExcle(locatorPath, "DeleteProduct");
	public By btnDelete = locatorMap.get("btnDelete");
	public By chbSingleDelete1 = locatorMap.get("chbSingleDelete1");
	public By chbDeleteAll = locatorMap.get("chbDeleteAll");
	public By iconDelete = locatorMap.get("iconDelete");
	public By chbSingleDelete2 = locatorMap.get("chbSingleDelete2");
	public By lblCategory1 = locatorMap.get("lblCategoryID1");
	public By lblCategory2 = locatorMap.get("lblCategoryID2");
	public By lblCategoryIDColumn = locatorMap.get("lblCategoryIDColumn");
}
