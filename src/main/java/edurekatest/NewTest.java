package edurekatest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NewTest {
	
	private WebDriver driver;
	
	@Test
	public void testEasy() {
		
		driver.get("https://www.edureka.co/");
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Instructor-Led"));
	}
	
	@BeforeTest
	public void beforeTest() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\2018\\edureka\\selenium\\EXEs\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		
		//driver.quit();
	}

}
