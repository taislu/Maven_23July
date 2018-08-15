package HelloMaven;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UploadProfilePhoto {
	
	static WebDriver driver;
	static WebDriverWait wait;
	static int maxWaitSeconds = 30;
	private static String url = "https://www.edureka.co/";
	static String userID;
	static String passwd;

	@BeforeTest
	public void test_setup() {
		
		logInfo("browserSetup()  ********");
		browserSetup();

	}
	
	@Parameters({"userID", "passWORD"})
	@Test(priority=1)
	public void test_userLogin(String userid, String password) throws Exception {
		
		userID = userid;
		passwd = password;
		logInfo("UserID : " + userID + ", Passwd : " + passwd);
		
		logInfo("test_Login()  ********");
		test_Login();
	}
	
	@Test(priority=2)
	public void test_uploadProfilePhoto() throws Exception {
	
		logInfo("test_UpdatePersonalDetails()  ********");
	    test_UpdatePersonalDetails();
	    
	}
	
	@AfterTest
	public void closeBrowser() {
		
		//Thread.sleep(3000);
	    logInfo("browserClose()  ********");
		browserClose();
		
		logInfo("DONE!");
	}
	
	private static void browserClose() {
		
		logInfo("Closing chrome");
		driver.quit();
	}
	
	
	private static void test_UpdatePersonalDetails() throws Exception {
		
		logInfo("Navigate to My Profile Page");
		driver.navigate().to("https://learning.edureka.co/my-profile");
		
		logInfo("WebDriver Wait on page loading complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
	
		logInfo("Click Editing personal_details");
		FindElement(driver, By.id("personal_details"), 4).click();
		
		logInfo("Click Camera on profile picture");
		FindElement(driver, By.xpath("//i[@class='icon-camera']"), 4).click();
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);
		
		logInfo("Click Choose File");
		FindElement(driver, By.id("custom-input"), 4).click();
		
		logInfo("Pause for few seconds");
		Thread.sleep(6000);
		
		logInfo("Run AutoIT script to select the profile photo");
		String Photo = "C:\\2018\\edureka\\selenium\\AutoIT\\profile_photo_1.jpg";
		String upload = "C:\\2018\\edureka\\selenium\\AutoIT\\Upload_Edureka_ProfilePhoto.exe";
		String uploadPhoto = upload + " " + Photo;
		
		Runtime.getRuntime().exec(uploadPhoto);
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);
		
		logInfo("Click Upload");
		FindElement(driver, By.xpath("//button[@class='submitbtn']"), 4).click();
		
		logInfo("Navigate to home page");
		driver.navigate().to("https://www.edureka.co/");
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);
		
		logInfo("Click Profile Picture");
		driver.findElement(By.xpath("//*[@id='header-II']/section/nav/div/ul[2]/li[1]/a/img")).click();
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);
		
		logInfo("Click Log Out");
		List<WebElement> profileDropdown = driver.findElements(By.xpath("//ul[@class='dropdown-menu user-menu profile-xs hidden-sm hidden-xs']//li/a"));
		for(WebElement e: profileDropdown) {
			String str = e.getAttribute("innerHTML");
			if(str.contentEquals("Log Out")) {
				e.click();
				break;
			}
		}
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);

		
	}
	
	private static void test_Login() throws Exception {
		
		logInfo("Click Log In");
		FindElement(driver, By.linkText("Log In"), 4).click();
		
		logInfo("WebDriver Wait on LOGIN button to be clickable");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='submit'])[4]")));
		
		Thread.sleep(3000);
		
		logInfo("Enter Email ID");
		FindElement(driver, By.id("inputName"), 4).sendKeys(userID);
		
		logInfo("Enter Password");
		FindElement(driver, By.id("pwd1"), 4).sendKeys(passwd);
		
		logInfo("Click LOGIN");
		FindElement(driver, By.xpath("(//button[@type='submit'])[4]"), 4).click();
		
		logInfo("WebDriver Wait on login complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
	
	}
	
	private static void browserSetup() {
		
		logInfo("Start chrome browser");
		System.setProperty("webdriver.chrome.driver", "C:\\2018\\edureka\\selenium\\EXEs\\chromedriver.exe");
		driver = new ChromeDriver();
		
		logInfo("Set the page load timeout for any page load");
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		logInfo("Navigate to url : " + url);
		driver.navigate().to(url);
		
		logInfo("Maximize window");
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		logInfo("Set implicit wait for all the activities on the browser");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		logInfo("Set selenium script timeout");
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
	
		logInfo("Execute any asynchronous script");
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");
		
		logInfo("WebDriver Wait maxWaitSeconds : " + maxWaitSeconds);
		wait = new WebDriverWait(driver, maxWaitSeconds);
		
	}
	

	private static WebElement FindElement(WebDriver driver, By by, int timeoutInSeconds) {
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			logInfo( "FindElement *** " + by + " *** Found");
			return driver.findElement(by);
			
		} catch (Exception e) {
			
			logInfo("Exception : " + e.getMessage() + " x-x-x-x-x-x");
		}
		
		logInfo( "FindElement --> " + by + " --> Not found");
		return null;
	}
	
	private static void logInfo(String msg) {
		
		System.out.println( LocalDateTime.now() + " : " + msg );
		
	}

}




