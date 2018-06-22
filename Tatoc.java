package com.qait.MavenDemo.BasicTatoc;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Tatoc extends TatocMain
{
	public static void main(String[] args) throws InterruptedException {
  System.setProperty("webdriver.chrome.driver", "C:\\Users\\adityadwivedi\\Downloads\\chromedriver_win32\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();   
		driver.get("http://10.0.1.86/tatoc/basic");  
		driver.findElement(By.cssSelector("a")).click(); 
		//1.GRID GATE
		WebElement red= driver.findElement(By.className("redbox"));  
		red.click();  //error page due to red box event
		driver.navigate().back();  //go back

		WebElement green= driver.findElement(By.className("greenbox"));
		green.click();  //next case

		//2.FRAME DUNGEON
		driver.switchTo().frame(driver.findElement(By.id("main")));   
		String box1= driver.findElement(By.cssSelector("#answer")).getAttribute("class"); 

		driver.switchTo().frame(driver.findElement(By.id("child"))); 
		String box2= driver.findElement(By.cssSelector("#answer")).getAttribute("class");   

		while (!(box1.equals(box2))) {  
			driver.switchTo().defaultContent(); 
			driver.switchTo().frame(driver.findElement(By.id("main"))); 
			driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click(); 
			driver.switchTo().frame(driver.findElement(By.id("child")));  
			box2 = driver.findElement(By.cssSelector("#answer")).getAttribute("class");
		}
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(driver.findElement(By.id("main"))); 
		driver.findElement(By.xpath("/html/body/center/a[2]")).click(); 

		//3.DRAG AROUND
		WebElement drop=driver.findElement(By.id("dropbox")); 
		WebElement drag=driver.findElement(By.id("dragbox")); 
		Actions dragAndDrop = new Actions(driver);   
		dragAndDrop.dragAndDrop(drag, drop).build().perform();
		driver.findElement(By.cssSelector("body > div > div.page > a")).click();

		//4.POPUP WINDOWS
		String mainwindow= driver.getWindowHandle(); 
		driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();

		for(String win : driver.getWindowHandles()) 
		{      driver.switchTo().window(win);  
		}
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("pass"); 
		driver.findElement(By.xpath("//input[@id='submit']")).click();
		for(String window : driver.getWindowHandles())
		{
			driver.switchTo().window(mainwindow);
		}
		driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click();
		//5.COOKIE HANDLING
		driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click(); 
		String t=driver.findElement(By.xpath("//*[@id=\"token\"]")).getText();  
		String[] tokenValue = t.split("\\s");
		String token = tokenValue[1];  
		Cookie cookie = new Cookie("Token", token);  
		driver.manage().addCookie(cookie);  
		driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click(); 

		Thread.sleep(1580);  
		driver.close(); 

	}
}
