package com.qait.MavenDemo.Bing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BingActions {
	WebDriver driver;
	
	public WebDriver bingchrome()
	{
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\adityadwivedi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.bing.com/translator");
		return driver;
	}
	
	public WebDriver bingfirefox()
	{
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\adityadwivedi\\Downloads\\geckodriver-v0.21.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.bing.com/translator");
		return driver;
	}
	
	public void leftdropdown()
	{
		Select leftdrop = new Select(driver.findElement(By.cssSelector("select[id='t_sl']")));
		leftdrop.selectByVisibleText("English");
	}
	public void rightdropdown()
	{
		Select rightdrop = new Select(driver.findElement(By.cssSelector("select[id='t_tl']")));
		rightdrop.selectByVisibleText("Hindi");
	}
	public void lefttextarea()
	{
		driver.findElement(By.cssSelector("textarea[id='t_sv']")).sendKeys("Good Morning !");
	}
public void swap()
{
	driver.findElement(By.cssSelector("a[id='t_revIcon']")).click();
}
public void clear()
{
	driver.findElement(By.cssSelector("a[id='t_edc']")).click();
}

}
