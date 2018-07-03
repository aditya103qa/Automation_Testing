package com.qait.MavenDemo.Bing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;


public class BingFirefoxTranslator {
	WebDriver driver;
	BingActions obj;

	@BeforeTest
	public void open()
	{
		obj = new BingActions();
		driver= obj.bingfirefox();
	}

	@Test(priority=1)
	public void Test1ToCheckTheLeftDropDownBox()
	{
		obj.leftdropdown();
		
		WebElement select = driver.findElement(By.cssSelector("select[id='t_sl']"));
		Select rightdropdown = new Select(select);  
		String selected = rightdropdown.getFirstSelectedOption().getText();
		Assert.assertEquals(selected, "English");
	}
	@Test(priority=2)
	public void Test2ToCheckTheRightDropDownBox()
	{
		obj.rightdropdown();
		
		WebElement select = driver.findElement(By.cssSelector("select[id='t_tl']"));
		Select leftdropdown = new Select(select);  
		String selected = leftdropdown.getFirstSelectedOption().getText();
		Assert.assertEquals(selected, "Hindi");
	}
	@Test(priority=3)
	public void Test3ToLeftCheckTheTextArea()
	{
		obj.lefttextarea();
	}
	@Test(priority=4)
	public void Test4ToCheckTheSwapButton()
	{
		obj.swap();
	}
	@Test(priority=5)
	public void Test5ToCheckTheCrossButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		 
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[id='t_edc']")));
		obj.clear();
	}
	
	@Test
	public void Test6ToCheckTheRightTextArea()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String t = (String)js.executeScript("document.querySelectorAll(\"textarea[id='t_tv']\")[0].value");
		System.out.println(t);
	}
	
	@AfterClass
	public void close()
	{
		driver.quit();
	}
}
