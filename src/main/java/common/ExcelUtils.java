package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

public class ExcelUtils {
	private static Workbook dataWorkbook;
	private static Sheet dataWorkbookSheet;
	private static Cell dataWorkbookCell;
	private static Row dataWorkbookRow;

	// Get data from excel by sheet name
	public String[][] getDataFromExcel(String filePath, String sheetName) throws IOException {
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		inputStream = new FileInputStream(file);
			// Khai Báo workbook
			Workbook workBook = new XSSFWorkbook(inputStream);

			// Lấy tên sheet
			Sheet sheet = workBook.getSheet(sheetName);

			// Lấy row
			Row row = sheet.getRow(0);

			// Lấy số dòng của mảng và số cột của mảng
			int tableRow = sheet.getLastRowNum();
			int tableColumn = row.getLastCellNum();
			String[][] dataTable = new String[tableRow][tableColumn];
			
			// Đọc data trong excel ra Mảng 2 chiều
			for (int i = 0; i < tableRow; i++) {
				for (int j = 0; j < tableColumn; j++) {
					row = sheet.getRow(i+1);
					dataTable[i][j] = row.getCell(j).toString();
				}
			}

		return dataTable;

	}

	public static void writeDataInExcelFile(String sheetName) throws IOException {
		// Create an object of File class to open xlsx file
		File file = new File(
				"D:\\Automation Test\\02Projects\\HemDecor\\src\\main\\java\\excelExportAndFileIO\\AutomationTestData.xlsx");

		// Read file using FileInputStream
		FileInputStream inputStream = new FileInputStream(file);

		// creating workbook instance that refers to .xlsx file
		dataWorkbook = new XSSFWorkbook(inputStream);

		// Create new sheet
		dataWorkbookSheet = dataWorkbook.createSheet(sheetName);

		// Create new row
		dataWorkbookRow = dataWorkbookSheet.createRow(1);

		// Insert value in cell
		dataWorkbookRow.createCell(0).setCellValue(sheetName);

		// Write value in excel using FileOutputStream
		FileOutputStream outputStream = new FileOutputStream(
				"D:\\Automation Test\\02Projects\\HemDecor\\src\\main\\java\\excelExportAndFileIO\\AutomationTestData.xlsx");
		dataWorkbook.write(outputStream);
	}
public By readLocatorByType(String locatorValue, String locatorType) {
	By result;
	switch (locatorType) {
	case "id":
		result=By.id(locatorValue);
		break;
	case "name":
		result=By.name(locatorValue);
		break;
	case "xpath":
		result=By.xpath(locatorValue);
		break;
	case "className":
		result=By.className(locatorValue);
		break;
	case "cssSelector":
		result=By.cssSelector(locatorValue);
		break;
	case "tagName":
		result=By.tagName(locatorValue);
		break;
	case "linkText":
		result=By.linkText(locatorValue);
		break;
	case "partialLinkText":
		result=By.partialLinkText(locatorValue);
		break;
	default:
		result=null;
		break;
	}
return result;	
}
}
