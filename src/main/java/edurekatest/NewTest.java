package edurekatest;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NewTest {
	
	static String userID;
	static String passwd;
	
	private WebDriver driver;
	
	@Test
	public void testEasy() {
		
		logInfo("getUserLogin()  ********");
		getUserLogin();
		
		//driver.get("https://www.edureka.co/");
		//String title = driver.getTitle();
		//Assert.assertTrue(title.contains("Instructor-Led"));
	}
	
	@BeforeTest
	public void beforeTest() {
		
		//System.setProperty("webdriver.chrome.driver", "C:\\2018\\edureka\\selenium\\EXEs\\chromedriver.exe");
		//driver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		
		//driver.quit();
	}
	
	private static void getUserLogin() {
		
		logInfo("Retrieve userID and password from Excel sheet 1st row : column 0 & 1");
		ReadSheet es = new ReadSheet("C:\\2018\\edureka\\selenium\\INPUT\\edureka_input.xlsx");
		userID = es.getDataCell(0, 0, 0);
		passwd = es.getDataCell(0, 0, 1);
		logInfo("UserID : " + userID + ", Passwd : " + passwd);
		
	}
	
	private static void logInfo(String msg) {
		
		System.out.println( LocalDateTime.now() + " : " + msg );
		
	}

}

class ReadSheet {
	
	XSSFWorkbook xlsx;
	XSSFSheet sheet;
	
	public ReadSheet(String file) {
		
		try {
			
			File f = new File(file);	
			FileInputStream fs = new FileInputStream(f);
				
			xlsx = new XSSFWorkbook(fs);	
			sheet = xlsx.getSheetAt(0);
			
			
		} catch (Exception e) {
			
			//e.printStackTrace();
			System.out.println(e.getMessage());
		
		}
	}
	
	public String getDataCell(int sheetIndex, int rowIndex, int colIndex) {
		try {
			
			return sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			
		
		} catch(Exception e) {
			
			System.out.println("getDataCell : " + e.getMessage());
		}
		
		return null;
	}
	
}