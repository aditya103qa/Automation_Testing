package com.qait.Automation.HrisLogin;

import java.util.Set;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HrisPom {
	WebDriver driver;

	@BeforeTest
	public void browser()
	{
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://hris.qainfotech.com/login.php");
		driver.manage().window().maximize();
	}


	@Test(priority=1)
	public void Test1()
	{
		driver.findElement(By.cssSelector("#demo-2 > div > div.span12.text-center.margin-10 > a > img")).click();	  
	}

	@Test(priority=2)
	public void Test2()
	{
		driver.findElement(By.cssSelector("#demo-2 > div > div.tabbable.custom-tabs.tabs-left.tabs-animated.flat."
				+ "flat-all.hide-label-980.shadow.track-url.auto-scroll > ul > li.active.pausePlayer > a")).click();
	}
	@Test(priority=3)
	public void Test3()
	{
		driver.findElement(By.cssSelector(" #demo-2 > div > div.tabbable.custom-tabs.tabs-left.tabs-animated."
				+ "flat.flat-all.hide-label-980.shadow.track-url.auto-scroll > ul > li:nth-child(2) > a")).click();
	} 
	@Test(priority=4)
	public void Test4()
	{
		driver.findElement(By.cssSelector("#demo-2 > div > div.tabbable.custom-tabs.tabs-left.tabs-animated."
				+ "flat.flat-all.hide-label-980.shadow.track-url.auto-scroll > ul > li:nth-child(3) > a")).click();
	}  
	@Test(priority=5)
	public void Test5()
	{
		driver.findElement(By.cssSelector("#demo-2 > div > div.tabbable.custom-tabs.tabs-left.tabs-animated."
				+ "flat.flat-all.hide-label-980.shadow.track-url.auto-scroll > ul > li:nth-child(4) > a")).click();
	} 
	@Test(priority=6)
	public void Test6()
	{
		driver.findElement(By.cssSelector("#demo-2 > div > div.tabbable.custom-tabs.tabs-left.tabs-animated."
				+ "flat.flat-all.hide-label-980.shadow.track-url.auto-scroll > ul > li:nth-child(5) > a")).click();
	} 
	@Test(priority=7)
	public void Test7()
	{
		driver.findElement(By.cssSelector("#demo-2 > div > div.tabbable.custom-tabs.tabs-left.tabs-animated"
				+ ".flat.flat-all.hide-label-980.shadow.track-url.auto-scroll > ul > li:nth-child(6) > a")).click();
	} 
	@Test(priority=8)
	public void Test8()
	{
		//	  String handle= driver.getWindowHandle();

		driver.findElement(By.cssSelector("body > footer > div > div:nth-child(1) > ul > li:nth-child(1) > a")).click();
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		driver.switchTo().window(main);
	}
	@Test(priority=9)
	public void Test9()
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("body > footer > div > div:nth-child(1) > ul > li:nth-child(2) > a")).click();
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		driver.switchTo().window(main);
		driver.findElement(By.xpath("//*[@id=\"demo-2\"]/div/div[2]/ul/li[1]/a")).click();
	}
	@Test(priority=10)
	public void Test10()
	{		
		driver.findElement(By.id("txtUserName")).sendKeys("adityadwivedi");
		driver.findElement(By.id("txtPassword")).sendKeys("Incorrect_Password");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();
	}
	@Test(priority=11)
	public void Test11()
	{		
		driver.findElement(By.id("txtUserName")).sendKeys("adityadwivedi");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();
	}
	@Test(priority=12)
	public void Test12()
	{		
		driver.findElement(By.id("txtUserName")).sendKeys("adityadwivedi");
		driver.findElement(By.id("txtPassword")).sendKeys("Aditya@321#");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();
	}
	@AfterClass
	public void close()
	{
		driver.quit();
	}

}
