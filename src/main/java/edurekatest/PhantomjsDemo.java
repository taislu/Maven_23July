package edurekatest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomjsDemo {

	public static void main(String[] args) throws Exception {
	/*
		String fp = "C:\\2018\\edureka\\selenium\\EXEs\\phantomjs.exe";
		File file = new File(fp);
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
		
	*/	
		System.out.println("Starting PhantomJSDriver ");
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setJavascriptEnabled(true);                
		caps.setCapability("takesScreenshot", true);  
		caps.setCapability(
		                        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		                        "C:\\2018\\edureka\\selenium\\EXEs\\phantomjs.exe"
		                    );
		
		WebDriver driver = new PhantomJSDriver(caps);
		
		driver.get("http://www.google.com");
		System.out.println("Before search : Page title is : " + driver.getTitle());
		
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Edureka");
		element.submit();
		
		System.out.println("After search : Page title is : " + driver.getTitle());
		
		System.out.println("Taking screenshot now");
        
		 
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("File:" + srcFile);
         
        String screenFile = "C:\\2018\\edureka\\selenium\\OUTPUT\\screen_shot1.png";
 		File DestFile=new File(screenFile);
 		FileUtils.copyFile(srcFile, DestFile);
 		System.out.println("Screen Shot : " + screenFile);
		
		driver.quit();
		System.out.println("DONE!");
		
	}

}
