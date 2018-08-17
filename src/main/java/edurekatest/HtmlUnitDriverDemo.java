package edurekatest;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitDriverDemo {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Start HtmlUnitDriver()");
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://www.google.com");
		System.out.println("Before search : Page title is : " + driver.getTitle());
		
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Edureka");
		element.submit();
		
		System.out.println("After search : Page title is : " + driver.getTitle());
		
		driver.quit();
		System.out.println("DONE!");

	}

}
